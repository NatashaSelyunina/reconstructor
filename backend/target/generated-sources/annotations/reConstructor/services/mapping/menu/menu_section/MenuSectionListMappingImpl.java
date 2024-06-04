package reConstructor.services.mapping.menu.menu_section;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.menuDto.MenuSectionDto;
import reConstructor.domain.entities.menu.MenuSection;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:38+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MenuSectionListMappingImpl implements MenuSectionListMapping {

    @Autowired
    private MenuSectionMapping menuSectionMapping;

    @Override
    public List<MenuSectionDto> mapListToDto(List<MenuSection> menuSections) {
        if ( menuSections == null ) {
            return null;
        }

        List<MenuSectionDto> list = new ArrayList<MenuSectionDto>( menuSections.size() );
        for ( MenuSection menuSection : menuSections ) {
            list.add( menuSectionMapping.mapToDto( menuSection ) );
        }

        return list;
    }

    @Override
    public List<MenuSection> mapListToEntity(List<MenuSectionDto> menuSectionDtos) {
        if ( menuSectionDtos == null ) {
            return null;
        }

        List<MenuSection> list = new ArrayList<MenuSection>( menuSectionDtos.size() );
        for ( MenuSectionDto menuSectionDto : menuSectionDtos ) {
            list.add( menuSectionMapping.mapToEntity( menuSectionDto ) );
        }

        return list;
    }

    @Override
    public List<MenuSection> mapListToEntityWithoutSeveralFields(List<MenuSectionDto> menuSectionDtos) {
        if ( menuSectionDtos == null ) {
            return null;
        }

        List<MenuSection> list = new ArrayList<MenuSection>( menuSectionDtos.size() );
        for ( MenuSectionDto menuSectionDto : menuSectionDtos ) {
            list.add( menuSectionMapping.mapToEntity( menuSectionDto ) );
        }

        return list;
    }

    @Override
    public List<MenuSectionDto> mapListToDtoWithoutSeveralFields(List<MenuSection> menuSections) {
        if ( menuSections == null ) {
            return null;
        }

        List<MenuSectionDto> list = new ArrayList<MenuSectionDto>( menuSections.size() );
        for ( MenuSection menuSection : menuSections ) {
            list.add( menuSectionMapping.mapToDto( menuSection ) );
        }

        return list;
    }
}
