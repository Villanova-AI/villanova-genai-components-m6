package ai.villanova.themis.server.mapper;

import ai.villanova.themis.server.entity.DatasetEntity;
import ai.villanova.themis.server.model.Dataset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DatasetMapper {
    Dataset toDto(DatasetEntity datasetEntity);

    DatasetEntity toEntity(Dataset dataset);
}
