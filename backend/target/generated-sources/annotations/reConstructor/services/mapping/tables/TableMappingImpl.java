package reConstructor.services.mapping.tables;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import reConstructor.domain.dto.TablesDto;
import reConstructor.domain.entities.Tables;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T00:27:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class TableMappingImpl implements TableMapping {

    @Override
    public TablesDto mapToDto(Tables table) {
        if ( table == null ) {
            return null;
        }

        TablesDto tablesDto = new TablesDto();

        tablesDto.setId( table.getId() );
        tablesDto.setNumber( table.getNumber() );
        tablesDto.setFree( table.isFree() );
        tablesDto.setActive( table.isActive() );
        tablesDto.setQrCodeImageUrl( table.getQrCodeImageUrl() );

        return tablesDto;
    }

    @Override
    public Tables mapToEntity(TablesDto tableDto) {
        if ( tableDto == null ) {
            return null;
        }

        Tables tables = new Tables();

        tables.setId( tableDto.getId() );
        tables.setNumber( tableDto.getNumber() );
        tables.setFree( tableDto.isFree() );
        tables.setActive( tableDto.isActive() );
        tables.setQrCodeImageUrl( tableDto.getQrCodeImageUrl() );

        return tables;
    }
}
