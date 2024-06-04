package reConstructor.services.mapping.moderator;

import org.mapstruct.Mapper;
import reConstructor.domain.dto.ModeratorDto;
import reConstructor.domain.entities.Moderator;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {ModeratorMapping.class})
public interface ModeratorSetMapping {
    Set<ModeratorDto> mapSetToDto(Set<Moderator> moderators);
    Set<Moderator> mapSetToEntity(Set<ModeratorDto> moderatorDtos);
}
