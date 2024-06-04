package reConstructor.services.mapping.restaurant;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.RestaurantDto;
import reConstructor.domain.entities.Restaurant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:36+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class RestaurantSetMappingImpl implements RestaurantSetMapping {

    @Override
    public Set<RestaurantDto> mapSetToDto(Set<Restaurant> restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        Set<RestaurantDto> set = new LinkedHashSet<RestaurantDto>( Math.max( (int) ( restaurant.size() / .75f ) + 1, 16 ) );
        for ( Restaurant restaurant1 : restaurant ) {
            set.add( restaurantToRestaurantDto( restaurant1 ) );
        }

        return set;
    }

    @Override
    public Set<RestaurantDto> mapListToDtoWithoutCode(Set<Restaurant> restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        Set<RestaurantDto> set = new LinkedHashSet<RestaurantDto>( Math.max( (int) ( restaurant.size() / .75f ) + 1, 16 ) );
        for ( Restaurant restaurant1 : restaurant ) {
            set.add( restaurantToRestaurantDto( restaurant1 ) );
        }

        return set;
    }

    @Override
    public Set<Restaurant> mapSetToEntity(Set<RestaurantDto> restaurantDto) {
        if ( restaurantDto == null ) {
            return null;
        }

        Set<Restaurant> set = new LinkedHashSet<Restaurant>( Math.max( (int) ( restaurantDto.size() / .75f ) + 1, 16 ) );
        for ( RestaurantDto restaurantDto1 : restaurantDto ) {
            set.add( restaurantDtoToRestaurant( restaurantDto1 ) );
        }

        return set;
    }

    protected RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {
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

    protected Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto) {
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
