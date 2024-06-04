package reConstructor.security.jwt.services;

import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import reConstructor.domain.entities.Role;
import reConstructor.security.jwt.domain.JwtAuthentication;
import reConstructor.services.RoleService;

@Component
public class JwtUtils {
    private RoleService roleService;

    public JwtUtils(RoleService roleService) {
        this.roleService = roleService;
    }

    public JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtAuthentication = new JwtAuthentication();

        String identifier = claims.getSubject();

        jwtAuthentication.setIdentifier(identifier);
        jwtAuthentication.setRole(getRole(claims));
        jwtAuthentication.setRestaurantId(getRestaurantId(claims, jwtAuthentication.getRole()));

        return jwtAuthentication;
    }

    private Role getRole(Claims claims) {
        String roleName = claims.get("role", String.class);
        return roleService.findByName(roleName);
    }

    private Object getRestaurantId (Claims claims, Role roleName){
        if (roleName.getName().equals("ROLE_MODERATOR")) {
            return claims.get("restaurantsId", ArrayList.class);
        }
        return claims.get("restaurantId", Long.class);
    }
}
