package ai.neodatagroup.themis.server.mapper;

import ai.neodatagroup.themis.server.entity.JobEntity;
import ai.neodatagroup.themis.server.model.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {
    public Job toDto(JobEntity jobEntity);

    public JobEntity toEntity(Job job);
}
