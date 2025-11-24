package ai.neodatagroup.themis.server.mapper;

import ai.neodatagroup.themis.server.entity.ItemEntity;
import ai.neodatagroup.themis.server.model.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    public Item toDto(ItemEntity itemEntity);

    public ItemEntity toEntity(Item item);
}
