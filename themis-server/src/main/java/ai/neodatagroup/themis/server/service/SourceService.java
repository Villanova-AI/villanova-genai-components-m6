package ai.neodatagroup.themis.server.service;

import ai.neodatagroup.themis.server.entity.SourceEntity;
import ai.neodatagroup.themis.server.mapper.SourceMapper;
import ai.neodatagroup.themis.server.model.Source;
import ai.neodatagroup.themis.server.repository.SourceRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourceService {
    private final SourceRepository repository;
    private final SourceMapper mapper;

    public SourceService(SourceRepository repository, SourceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public Source create(Source source) {
        if (source.getName() == null || source.getName().isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (repository.existsByName(source.getName())) {
            throw new DataIntegrityViolationException("Source with name already exists");
        }
        SourceEntity entity = mapper.toNewEntity(source);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Source not found: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Source get(Long id) {
        SourceEntity entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Source not found: " + id));
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<Source> list() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public Source replace(Long id, Source source) {
        SourceEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Source not found: " + id));

        // If name is changing, check uniqueness
        if (source.getName() != null && !source.getName().equals(entity.getName())
                && repository.existsByName(source.getName())) {
            throw new DataIntegrityViolationException("Source with name already exists");
        }

        mapper.updateEntityFromDto(source, entity);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }
}
