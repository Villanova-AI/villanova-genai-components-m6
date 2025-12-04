package ai.villanova.themis.server.mapper;

import ai.villanova.themis.server.entity.SourceEntity;
import ai.villanova.themis.server.model.Source;
import org.mapstruct.*;

import java.net.URI;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    // Convert JPA entity to DTO
    @Mapping(source = "url", target = "url", qualifiedByName = "stringToUri")
    Source toDto(SourceEntity sourceEntity);

    // Convert DTO to a new entity (ignore id and timestamps)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "url", target = "url", qualifiedByName = "uriToString")
    SourceEntity toNewEntity(Source source);

    // Update an existing entity from DTO (partial update)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true) // never overwrite id
    @Mapping(target = "createdAt", ignore = true) // keep original
    @Mapping(target = "updatedAt", ignore = true) // will be set in @PreUpdate
    @Mapping(source = "url", target = "url", qualifiedByName = "uriToString")
    void updateEntityFromDto(Source source, @MappingTarget SourceEntity entity);

    @Named("stringToUri")
    default URI stringToUri(String value) {
        return value != null ? URI.create(value) : null;
    }

    @Named("uriToString")
    default String uriToString(URI value) {
        return value != null ? value.toString() : null;
    }
}
