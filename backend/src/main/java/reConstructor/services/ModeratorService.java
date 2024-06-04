package reConstructor.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reConstructor.domain.dto.ModeratorDto;
import reConstructor.domain.entities.Moderator;
import reConstructor.domain.entities.Role;
import reConstructor.exception_handling.exceptions.common_classes.DatabaseErrorException;
import reConstructor.exception_handling.exceptions.common_classes.NonExistentIdException;
import reConstructor.exception_handling.exceptions.common_classes.PasswordMismatchException;
import reConstructor.exception_handling.exceptions.moderator.DuplicateEmailException;
import reConstructor.exception_handling.exceptions.moderator.NonExistenModeratorWithThisEmail;
import reConstructor.exception_handling.exceptions.moderator.NonExistenValidationCodeException;
import reConstructor.repositories.ModeratorRepository;
import reConstructor.security.PasswordEncoder;
import reConstructor.security.jwt.services.AuthService;
import reConstructor.services.mapping.moderator.ModeratorMapping;

import java.util.*;

@Service
public class ModeratorService implements UserDetailsService {

    private final String PASSWORD_MESSAGE = "Password is hidden";
    private ModeratorRepository repository;
    private ModeratorMapping mapping;
    private AuthService authService;
    private MailSenderService mailSenderService;
    private Map<String, Moderator> moderatorsToValidate;
    private AutoGenerationService autoGenerationService;

    public ModeratorService(ModeratorRepository repository, ModeratorMapping mappingService,
                            @Lazy AuthService authService, MailSenderService mailSenderService,
                            AutoGenerationService autoGenerationService) {
        this.repository = repository;
        this.mapping = mappingService;
        this.authService = authService;
        this.mailSenderService = mailSenderService;
        this.moderatorsToValidate = new HashMap<>();
        this.autoGenerationService = autoGenerationService;
    }

    // сохранение модера ✅
    public void save(ModeratorDto moderatorDto) {
        String email = moderatorDto.getEmail().toLowerCase();

        if (repository.existsByEmail(email)) {
            throw new DuplicateEmailException("Moderator with this email already exists", HttpStatus.CONFLICT);
        }
        if (moderatorsToValidate.values().stream().map(Moderator::getEmail).anyMatch(email::equals)) {
            throw new DuplicateEmailException(
                    "Moderator with this email has already received activation email. " +
                            "Please, check spam folder or try to send activation email again", HttpStatus.CONFLICT);
        }

        try {
            Moderator moderator = mapping.mapToEntity(moderatorDto);

            moderator.setId(0);
            moderator.setEmail(moderator.getEmail().toLowerCase());
            moderator.setPassword(PasswordEncoder.encode(moderator.getPassword()));
            moderator.setActive(false);
            Role role = new Role(1, "ROLE_MODERATOR");
            moderator.setRole(role);

            String validationCode = autoGenerationService.generateValidationCode();

            mailSenderService.sendActivationEmail(validationCode, moderator);

            moderatorsToValidate.put(validationCode, moderator);
        } catch (Exception e) {
            throw new DatabaseErrorException(
                    "The database is currently unavailable. Please try to make a request later. " +
                            "If the problem reoccurs, contact customer support.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ModeratorDto findActive() {
        String email = authService.getAuthInfo().getIdentifier();
        Moderator moderator = (Moderator) loadUserByUsername(email);
        moderator.setPassword(PASSWORD_MESSAGE);
        return mapping.mapToDto(moderator);
    }

    public ModeratorDto update(ModeratorDto moderatorDto) {
        String email = authService.getAuthInfo().getIdentifier();
        Moderator moderator = (Moderator) loadUserByUsername(email);
        if (moderatorDto.getName() != null && !moderatorDto.getName().isBlank()
                && !moderatorDto.getName().equals(moderator.getName())) {
            moderator.setName(moderatorDto.getName());
        }
        if (moderatorDto.getSurname() != null && !moderatorDto.getSurname().isBlank()
                && !moderatorDto.getSurname().equals(moderator.getSurname())) {
            moderator.setSurname(moderatorDto.getSurname());
        }
        if (moderatorDto.getDateOfBirth() != null && moderatorDto.getDateOfBirth().isBlank()
                && !moderatorDto.getDateOfBirth().equals(moderator.getDateOfBirth())) {
            moderator.setDateOfBirth(moderatorDto.getDateOfBirth());
        }
        repository.save(moderator);
        moderator.setPassword(PASSWORD_MESSAGE);
        return mapping.mapToDto(moderator);
    }

    public void changeEmail(String email) {
        String oldEmail = authService.getAuthInfo().getIdentifier();
        Moderator moderator = (Moderator) loadUserByUsername(oldEmail);
        if (moderator.getEmail().equals(email)) {
            throw new DuplicateEmailException("The new email matches the old email. Nothing to change!",
                    HttpStatus.CONFLICT);
        }
        moderator.setEmail(email);

        String validationCode = autoGenerationService.generateValidationCode();

        mailSenderService.sendLinkToChangeEmail(validationCode, moderator);

        moderatorsToValidate.put(validationCode, moderator);
    }

    public void changePassword(String newPassword, String oldPassword) {
        if (oldPassword.equals(newPassword)) {
            throw new PasswordMismatchException(
                    "New password can't be same as old password. Please, input new password", HttpStatus.BAD_REQUEST);
        }

        String email = authService.getAuthInfo().getIdentifier();
        Moderator moderator = (Moderator) loadUserByUsername(email);
        if (PasswordEncoder.encoder.matches(oldPassword, moderator.getPassword())) {
            moderator.setPassword(PasswordEncoder.encode(newPassword));
            repository.save(moderator);
        } else {
            throw new PasswordMismatchException(
                    "Old password doesn't match to saved password, please, try again", HttpStatus.BAD_REQUEST);
        }
    }

    public void resetPassword(String email) {
        Moderator moderator = repository.findByEmail(email).orElseThrow(
                () -> new NonExistenModeratorWithThisEmail(
                        "Can't find moderator with this email, if you forgot your email,"
                                + " please, contact customers support by email we.ratfamily@gmail.com",
                        HttpStatus.BAD_REQUEST));

        try {
            String validationCode = autoGenerationService.generateValidationCode();
            mailSenderService.sendResetPasswordEmail(validationCode, moderator);
            moderatorsToValidate.put(validationCode, moderator);
        } catch (RuntimeException e) {
            throw new NonExistentIdException("Incorrect information. Please check the entered data",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public void validateResetPassword(String validationCode, String newPassword) {
        if (!moderatorsToValidate.containsKey(validationCode)) {
            throw new NonExistenValidationCodeException(
                    "Validation code is expired. Please, try to resend or contact customer service",
                    HttpStatus.BAD_REQUEST);
        }
        Moderator moderator = moderatorsToValidate.get(validationCode);
        moderatorsToValidate.remove(validationCode);
        moderator.setPassword(PasswordEncoder.encode(newPassword));
        repository.save(moderator);
    }

    public void activate(String validationCode) {
        if (!moderatorsToValidate.containsKey(validationCode)) {
            throw new NonExistenValidationCodeException(
                    "Validation code is expired. Please, try to resend or contact customer service",
                    HttpStatus.BAD_REQUEST);
        }
        Moderator moderator = moderatorsToValidate.get(validationCode);
        moderator.setActive(true);
        repository.save(moderator);
        moderatorsToValidate.remove(validationCode);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> new NonExistenModeratorWithThisEmail(
                "Moderator not found", HttpStatus.BAD_REQUEST));
    }
}
