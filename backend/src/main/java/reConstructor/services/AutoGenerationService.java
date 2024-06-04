package reConstructor.services;

import org.passay.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reConstructor.exception_handling.exceptions.role.NonExistentRoleException;
import reConstructor.security.PasswordEncoder;

@Service
public class AutoGenerationService {

    private PasswordGenerator generator;

    public AutoGenerationService() {
        this.generator = new PasswordGenerator();
    }

    public static String generateStaffCode(String role, int restaurantCode) {
        int roleCode = switch (role) {
            case "ROLE_ADMINISTRATOR" -> 2;
            case "ROLE_WAITER" -> 3;
            case "ROLE_CHEF" -> 4;
            case "ROLE_BARTENDER" -> 5;
            default -> throw new NonExistentRoleException("Invalid role: " + role, HttpStatus.BAD_REQUEST);
        };

        int random = (int) (Math.random() * 1000);
        return String.format("%04d", restaurantCode) + roleCode + String.format("%03d", random);
    }

    public String generateRestaurantCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }

        return code.toString();
    }

    public String generatePassword() {

        List<CharacterRule> characterRules = Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 5),
                new CharacterRule(EnglishCharacterData.Digit, 2)
        );
        return generator.generatePassword(8, characterRules);
    }

    public String generateValidationCode() {
        List<CharacterRule> characterRules = Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase, 13),
                new CharacterRule(EnglishCharacterData.LowerCase, 12),
                new CharacterRule(EnglishCharacterData.Digit, 12),
                new CharacterRule(EnglishCharacterData.Special, 13)
        );
        return PasswordEncoder.encode(generator.generatePassword(50, characterRules));
    }
}
