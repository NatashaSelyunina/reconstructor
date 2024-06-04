package reConstructor.security.jwt.services;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reConstructor.domain.dto.ModeratorDto;
import reConstructor.domain.dto.StaffDto;
import reConstructor.domain.entities.Moderator;
import reConstructor.domain.entities.Staff;
import reConstructor.exception_handling.exceptions.access.AuthenticationException;
import reConstructor.exception_handling.exceptions.access.jwt.InvalidTokenException;
import reConstructor.security.PasswordEncoder;
import reConstructor.security.jwt.domain.JwtAuthentication;
import reConstructor.security.jwt.domain.JwtRequest;
import reConstructor.security.jwt.domain.UserInfoDto;
import reConstructor.services.ModeratorService;

import reConstructor.services.StaffService;
import reConstructor.services.mapping.moderator.ModeratorMapping;
import reConstructor.services.mapping.staff.StaffMapping;

@Service
public class AuthService {
    private final String PASSWORD_MESSAGE = "Password is hidden";
    private JwtProvider jwtProvider;
    private ModeratorService moderatorService;
    private ModeratorMapping moderatorMapping;
    private StaffService staffService;
    private StaffMapping staffMapping;

    public AuthService(JwtProvider jwtProvider, ModeratorService moderatorService, ModeratorMapping moderatorMapping,
                       StaffService staffService, StaffMapping staffMapping) {
        this.jwtProvider = jwtProvider;
        this.moderatorService = moderatorService;
        this.moderatorMapping = moderatorMapping;
        this.staffService = staffService;
        this.staffMapping = staffMapping;
    }

    public Object login(@Nonnull JwtRequest authRequest, HttpServletResponse response) {
        String login = authRequest.getLogin();
        String password = authRequest.getPassword();

        if (login.matches("^\\d+$")) {
            return loginStaff(login, password, response);
        } else {
            return loginModerator(login, password, response);
        }
    }

    public ModeratorDto loginModerator(String login, String password, HttpServletResponse response) {
        Moderator moderator = (Moderator) moderatorService.loadUserByUsername(login);
        if (PasswordEncoder.encoder.matches(password, moderator.getPassword())) {
            final String accessToken = jwtProvider.generateAccessTokenForModerator(moderator);
            final String refreshToken = jwtProvider.generateRefreshTokenForModerator(moderator);
            saveAccessCookie(accessToken, response);
            saveRefreshCookie(refreshToken, response);
            moderator.setPassword(PASSWORD_MESSAGE);
            return moderatorMapping.mapToDto(moderator);
        } else {
            throw new AuthenticationException("Incorrect email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    public StaffDto loginStaff(String login, String password, HttpServletResponse response) {
        Staff staff = staffService.findByCode(login);
        if (PasswordEncoder.encoder.matches(password, staff.getPassword())) {
            final String accessToken = jwtProvider.generateAccessTokenForStaff(staff);
            final String refreshToken = jwtProvider.generateRefreshTokenForStaff(staff);
            saveAccessCookie(accessToken, response);
            saveRefreshCookie(refreshToken, response);
            staff.setPassword(PASSWORD_MESSAGE);
            return staffMapping.mapToDto(staff);
        } else {
            throw new AuthenticationException("Incorrect email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    public void getAccessToken(@Nonnull String refreshToken, HttpServletResponse response)  {
        Claims refreshClaims = jwtProvider.getRefreshClaims(refreshToken);
        String subject = refreshClaims.getSubject();
        String roleName = refreshClaims.get("role", String.class);
        if ("ROLE_MODERATOR".equals(roleName)) {
            getAccessTokenForModerator(subject, response);
        } else {
            getAccessTokenForStaff(subject, response);
        }
    }

    public void getAccessTokenForModerator(String subject, HttpServletResponse response) {
        Moderator moderator = (Moderator) moderatorService.loadUserByUsername(subject);
        final String accessToken = jwtProvider.generateAccessTokenForModerator(moderator);
        saveAccessCookie(accessToken, response);
    }

    public void getAccessTokenForStaff(String subject, HttpServletResponse response) {
        Staff staff = staffService.findByCode(subject);
        final String accessToken = jwtProvider.generateAccessTokenForStaff(staff);
        saveAccessCookie(accessToken, response);
    }

    public Object getNewTokens(HttpServletRequest request, HttpServletResponse response)  {
        String refreshToken = getRefreshTokenFromRequest(request);
        if (refreshToken != null && !jwtProvider.validateRefreshToken(refreshToken)) {
            Claims refreshClaims = jwtProvider.getRefreshClaims(refreshToken);
            String subject = refreshClaims.getSubject();
            String roleName = refreshClaims.get("role", String.class);
            if ("ROLE_MODERATOR".equals(roleName)) {
                return getNewTokensForModerator(subject, response);
            } else {
                return getNewTokensForStaff(subject, response);
            }
        }
        throw new InvalidTokenException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
    }

    private StaffDto getNewTokensForStaff(String subject, HttpServletResponse response) {
        Staff staff = staffService.findByCode(subject);
        final String accessToken = jwtProvider.generateAccessTokenForStaff(staff);
        final String refreshToken = jwtProvider.generateRefreshTokenForStaff(staff);
        saveAccessCookie(accessToken, response);
        saveRefreshCookie(refreshToken, response);
        staff.setPassword(PASSWORD_MESSAGE);
        return staffMapping.mapToDto(staff);
    }

    private ModeratorDto getNewTokensForModerator(String subject, HttpServletResponse response) {
        Moderator moderator = (Moderator) moderatorService.loadUserByUsername(subject);
        final String accessToken = jwtProvider.generateAccessTokenForModerator(moderator);
        final String refreshToken = jwtProvider.generateRefreshTokenForModerator(moderator);
        saveAccessCookie(accessToken, response);
        saveRefreshCookie(refreshToken, response);
        moderator.setPassword(PASSWORD_MESSAGE);
        return moderatorMapping.mapToDto(moderator);
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public Moderator getModerator() {
        return (Moderator) moderatorService.loadUserByUsername(getAuthInfo().getIdentifier());
    }

    public UserInfoDto getUserFromToken() {
        String roleName = getAuthInfo().getRole().getName();
        if (roleName.equals("ROLE_MODERATOR")) {
            Moderator moderator = (Moderator) moderatorService.loadUserByUsername(getAuthInfo().getIdentifier());
            moderator.setPassword(PASSWORD_MESSAGE);
            Object restaurantId = getAuthInfo().getRestaurantId();
            return new UserInfoDto(moderatorMapping.mapToDto(moderator), restaurantId);
        } else {
            Staff staff = staffService.findByCode(getAuthInfo().getIdentifier());
            staff.setPassword(PASSWORD_MESSAGE);
            Object restaurantId = getAuthInfo().getRestaurantId();
            return new UserInfoDto(staffMapping.mapToDto(staff), restaurantId);
        }
    }

    public void saveAccessCookie(String accessToken, HttpServletResponse response) {
        Cookie access = new Cookie("Access-Token", accessToken);
        access.setPath("/");
        access.setHttpOnly(true);
        response.addCookie(access);
    }

    public void saveRefreshCookie(String refreshToken, HttpServletResponse response) {
        Cookie refresh = new Cookie("Refresh-Token", refreshToken);
        refresh.setPath("/");
        refresh.setHttpOnly(true);
        response.addCookie(refresh);
    }

    public void deleteCookies(HttpServletResponse response) {
        Cookie access = new Cookie("Access-Token", null);
        Cookie refresh = new Cookie("Refresh-Token", null);
        access.setPath("/");
        refresh.setPath("/");
        access.setHttpOnly(true);
        refresh.setHttpOnly(true);
        access.setMaxAge(0);
        refresh.setMaxAge(0);
        response.addCookie(access);
        response.addCookie(refresh);
    }

    private String getRefreshTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("Refresh-Token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
