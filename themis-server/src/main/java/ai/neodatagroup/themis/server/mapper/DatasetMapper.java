package ai.neodatagroup.themis.server.mapper;

import ai.neodatagroup.themis.server.entity.DatasetEntity;
import ai.neodatagroup.themis.server.model.Dataset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DatasetMapper {
    Dataset toDto(DatasetEntity datasetEntity);

    DatasetEntity toEntity(Dataset dataset);
}
