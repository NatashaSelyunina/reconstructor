package reConstructor.services.mapping.tables;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.TablesDto;
import reConstructor.domain.entities.Tables;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class TableListMappingImpl implements TableListMapping {

    @Autowired
    private TableMapping tableMapping;

    @Override
    public Set<TablesDto> mapListToDto(Set<Tables> tables) {
        if ( tables == null ) {
            return null;
        }

        Set<TablesDto> set = new LinkedHashSet<TablesDto>( Math.max( (int) ( tables.size() / .75f ) + 1, 16 ) );
        for ( Tables tables1 : tables ) {
            set.add( tableMapping.mapToDto( tables1 ) );
        }

        return set;
    }

    @Override
    public Set<Tables> mapListToEntity(Set<TablesDto> tableDtos) {
        if ( tableDtos == null ) {
            return null;
        }

        Set<Tables> set = new LinkedHashSet<Tables>( Math.max( (int) ( tableDtos.size() / .75f ) + 1, 16 ) );
        for ( TablesDto tablesDto : tableDtos ) {
            set.add( tableMapping.mapToEntity( tablesDto ) );
        }

        return set;
    }
}
