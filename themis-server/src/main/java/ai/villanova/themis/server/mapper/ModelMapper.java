package ai.villanova.themis.server.mapper;

import ai.villanova.themis.server.entity.ModelEntity;
import ai.villanova.themis.server.model.Model;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {
    public Model toDto(ModelEntity modelEntity);

    public ModelEntity toEntity(Model model);
}
