package reConstructor.security.access_check;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reConstructor.domain.entities.Moderator;
import reConstructor.exception_handling.exceptions.common_classes.NonExistentIdException;
import reConstructor.security.jwt.domain.JwtAuthentication;
import reConstructor.security.jwt.services.AuthService;
import reConstructor.services.ModeratorService;

@Service
public class AccessService {

    private AuthService authService;
    private ModeratorService moderatorService;

    public AccessService(@Lazy AuthService authService, ModeratorService moderatorService) {
        this.authService = authService;
        this.moderatorService = moderatorService;
    }

    public void checkAuthorization(long restaurantId) {
        JwtAuthentication authInfo = authService.getAuthInfo();

        if (!hasAccessToRestaurant(authInfo, restaurantId)) {
            throw new NonExistentIdException("Incorrect information. Please check the entered data",
                    HttpStatus.BAD_REQUEST);
        }
    }

    private boolean hasAccessToRestaurant(JwtAuthentication authentication, Long restaurantId) {
        if (!authentication.getRole().getName().equals("ROLE_MODERATOR")) {
            return authentication.getRestaurantId() == restaurantId;
        } else {
            Moderator moderator = (Moderator) moderatorService.loadUserByUsername(authentication.getIdentifier());
            return moderator.getRestaurants().stream().anyMatch(x -> x.getId() == restaurantId);
        }
    }

    public void hasAccessToChangeOrRestorePassword(String code) {
        JwtAuthentication authInfo = authService.getAuthInfo();
        if (!authInfo.getRole().getName().equals("ROLE_MODERATOR") && !authInfo.getIdentifier().equals(code)) {
            throw new NonExistentIdException("Incorrect information. Please check the entered data",
                    HttpStatus.BAD_REQUEST);
        }
    }
}
