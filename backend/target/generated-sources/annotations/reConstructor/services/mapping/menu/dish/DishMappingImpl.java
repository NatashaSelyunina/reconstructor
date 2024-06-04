package reConstructor.services.mapping.menu.dish;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.menuDto.DishDto;
import reConstructor.domain.entities.menu.Dish;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class DishMappingImpl implements DishMapping {

    @Override
    public DishDto mapToDto(Dish dish) {
        if ( dish == null ) {
            return null;
        }

        DishDto dishDto = new DishDto();

        dishDto.setId( dish.getId() );
        dishDto.setName( dish.getName() );
        dishDto.setDescription( dish.getDescription() );
        dishDto.setWeight( dish.getWeight() );
        dishDto.setPrice( dish.getPrice() );
        dishDto.setActive( dish.isActive() );
        dishDto.setImageUrl( dish.getImageUrl() );

        return dishDto;
    }

    @Override
    public Dish mapToEntity(DishDto dishDto) {
        if ( dishDto == null ) {
            return null;
        }

        Dish dish = new Dish();

        dish.setId( dishDto.getId() );
        dish.setName( dishDto.getName() );
        dish.setDescription( dishDto.getDescription() );
        dish.setWeight( dishDto.getWeight() );
        dish.setPrice( dishDto.getPrice() );
        dish.setActive( dishDto.isActive() );
        dish.setImageUrl( dishDto.getImageUrl() );

        return dish;
    }

    @Override
    public Dish mapToEntityWithoutIdAndIsActive(DishDto dishDto) {
        if ( dishDto == null ) {
            return null;
        }

        Dish dish = new Dish();

        dish.setId( dishDto.getId() );
        dish.setName( dishDto.getName() );
        dish.setDescription( dishDto.getDescription() );
        dish.setWeight( dishDto.getWeight() );
        dish.setPrice( dishDto.getPrice() );
        dish.setActive( dishDto.isActive() );
        dish.setImageUrl( dishDto.getImageUrl() );

        return dish;
    }

    @Override
    public DishDto mapToDtoWithoutSeveralFields(Dish dish) {
        if ( dish == null ) {
            return null;
        }

        DishDto dishDto = new DishDto();

        dishDto.setId( dish.getId() );
        dishDto.setName( dish.getName() );
        dishDto.setDescription( dish.getDescription() );
        dishDto.setWeight( dish.getWeight() );
        dishDto.setPrice( dish.getPrice() );
        dishDto.setActive( dish.isActive() );
        dishDto.setImageUrl( dish.getImageUrl() );

        return dishDto;
    }
}
