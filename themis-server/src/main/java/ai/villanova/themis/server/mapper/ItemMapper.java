package ai.villanova.themis.server.mapper;

import ai.villanova.themis.server.entity.ItemEntity;
import ai.villanova.themis.server.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.net.URI;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    @Mapping(source = "url", target = "url", qualifiedByName = "stringToUri")
    public Item toDto(ItemEntity itemEntity);

    @Mapping(source = "url", target = "url", qualifiedByName = "uriToString")
    public ItemEntity toEntity(Item item);

    @Named("stringToUri")
    default URI stringToUri(String value) {
        return value != null ? URI.create(value) : null;
    }

    @Named("uriToString")
    default String uriToString(URI value) {
        return value != null ? value.toString() : null;
    }
}
