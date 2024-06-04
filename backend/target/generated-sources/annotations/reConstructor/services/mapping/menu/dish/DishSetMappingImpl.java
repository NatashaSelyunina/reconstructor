package reConstructor.services.mapping.menu.dish;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.menuDto.DishDto;
import reConstructor.domain.entities.menu.Dish;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class DishSetMappingImpl implements DishSetMapping {

    @Autowired
    private DishMapping dishMapping;

    @Override
    public Set<DishDto> mapListToDto(Set<Dish> dishes) {
        if ( dishes == null ) {
            return null;
        }

        Set<DishDto> set = new LinkedHashSet<DishDto>( Math.max( (int) ( dishes.size() / .75f ) + 1, 16 ) );
        for ( Dish dish : dishes ) {
            set.add( dishMapping.mapToDto( dish ) );
        }

        return set;
    }

    @Override
    public Set<Dish> mapListToEntity(Set<DishDto> dishDtos) {
        if ( dishDtos == null ) {
            return null;
        }

        Set<Dish> set = new LinkedHashSet<Dish>( Math.max( (int) ( dishDtos.size() / .75f ) + 1, 16 ) );
        for ( DishDto dishDto : dishDtos ) {
            set.add( dishMapping.mapToEntity( dishDto ) );
        }

        return set;
    }

    @Override
    public Set<DishDto> mapSetToDtoWithoutSeveralFields(Set<Dish> dishes) {
        if ( dishes == null ) {
            return null;
        }

        Set<DishDto> set = new LinkedHashSet<DishDto>( Math.max( (int) ( dishes.size() / .75f ) + 1, 16 ) );
        for ( Dish dish : dishes ) {
            set.add( dishMapping.mapToDto( dish ) );
        }

        return set;
    }

    @Override
    public Set<Dish> mapSetToEntityWithoutIdAndIsActive(Set<DishDto> dishDtos) {
        if ( dishDtos == null ) {
            return null;
        }

        Set<Dish> set = new LinkedHashSet<Dish>( Math.max( (int) ( dishDtos.size() / .75f ) + 1, 16 ) );
        for ( DishDto dishDto : dishDtos ) {
            set.add( dishMapping.mapToEntity( dishDto ) );
        }

        return set;
    }
}
