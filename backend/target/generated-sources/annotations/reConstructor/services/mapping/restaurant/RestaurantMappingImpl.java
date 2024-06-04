package reConstructor.services.mapping.restaurant;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.RestaurantDto;
import reConstructor.domain.entities.Restaurant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class RestaurantMappingImpl implements RestaurantMapping {

    @Override
    public RestaurantDto mapToDto(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setId( restaurant.getId() );
        restaurantDto.setCode( restaurant.getCode() );
        restaurantDto.setPhoneNumber( restaurant.getPhoneNumber() );
        restaurantDto.setAddress( restaurant.getAddress() );
        restaurantDto.setWebsite( restaurant.getWebsite() );
        restaurantDto.setActive( restaurant.isActive() );
        restaurantDto.setLogoUrl( restaurant.getLogoUrl() );
        restaurantDto.setBackgroundUrl( restaurant.getBackgroundUrl() );
        restaurantDto.setName( restaurant.getName() );

        return restaurantDto;
    }

    @Override
    public RestaurantDto mapToDtoWithoutCode(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setId( restaurant.getId() );
        restaurantDto.setPhoneNumber( restaurant.getPhoneNumber() );
        restaurantDto.setAddress( restaurant.getAddress() );
        restaurantDto.setWebsite( restaurant.getWebsite() );
        restaurantDto.setActive( restaurant.isActive() );
        restaurantDto.setLogoUrl( restaurant.getLogoUrl() );
        restaurantDto.setBackgroundUrl( restaurant.getBackgroundUrl() );
        restaurantDto.setName( restaurant.getName() );

        return restaurantDto;
    }

    @Override
    public Restaurant mapToEntity(RestaurantDto restaurantDto) {
        if ( restaurantDto == null ) {
            return null;
        }

        Restaurant restaurant = new Restaurant();

        restaurant.setId( restaurantDto.getId() );
        restaurant.setCode( restaurantDto.getCode() );
        restaurant.setPhoneNumber( restaurantDto.getPhoneNumber() );
        restaurant.setAddress( restaurantDto.getAddress() );
        restaurant.setWebsite( restaurantDto.getWebsite() );
        restaurant.setActive( restaurantDto.isActive() );
        restaurant.setLogoUrl( restaurantDto.getLogoUrl() );
        restaurant.setBackgroundUrl( restaurantDto.getBackgroundUrl() );
        restaurant.setName( restaurantDto.getName() );

        return restaurant;
    }
}
