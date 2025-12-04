package ai.villanova.themis.server.mapper;

import ai.villanova.themis.server.entity.JobEntity;
import ai.villanova.themis.server.model.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {
    public Job toDto(JobEntity jobEntity);

    public JobEntity toEntity(Job job);
}
