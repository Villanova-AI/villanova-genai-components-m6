package ai.neodatagroup.themis.server.mapper;

import ai.neodatagroup.themis.server.entity.ModelEntity;
import ai.neodatagroup.themis.server.model.Model;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    public Model toDto(ModelEntity modelEntity);

    public ModelEntity toEntity(Model model);
}
