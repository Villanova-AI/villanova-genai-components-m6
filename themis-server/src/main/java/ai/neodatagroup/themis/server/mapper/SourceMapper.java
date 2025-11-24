package ai.neodatagroup.themis.server.mapper;

import ai.neodatagroup.themis.server.entity.SourceEntity;
import ai.neodatagroup.themis.server.model.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    public Source toDto(SourceEntity sourceEntity);

    public SourceEntity toEntity(Source source);
}
