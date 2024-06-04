package reConstructor.security.jwt.domain;

public class UserInfoDto {

    private Object user;
    private Object restaurantId;

    public UserInfoDto() {
    }

    public UserInfoDto(Object user, Object restaurantId) {
        this.user = user;
        this.restaurantId = restaurantId;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Object restaurantId) {
        this.restaurantId = restaurantId;
    }
}
