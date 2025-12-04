package ai.villanova.themis.server.api;

import ai.villanova.themis.server.model.Source;
import ai.villanova.themis.server.service.NotFoundException;
import ai.villanova.themis.server.service.SourceService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
public class SourcesApiDelegateImpl implements SourcesApiDelegate {
    private final SourceService sourceService;

    public SourcesApiDelegateImpl(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @Override
    public ResponseEntity<Source> createSource(Source source) {
        try {
            Source created = sourceService.create(source);
            // Location header pointing to GET /sources/{id}
            return ResponseEntity
                    .created(URI.create("/sources/" + created.getId()))
                    .body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (DataIntegrityViolationException e) {
            // Conflict on unique name, etc.
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteSource(Long id) {
        try {
            sourceService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Source> getSource(Long id) {
        try {
            Source source = sourceService.get(id);
            return ResponseEntity.ok(source);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Source>> listSources(Integer page, Integer size) {
        int pageNum = (page == null) ? 0 : page;
        int pageSize = (size == null) ? 20 : size;
        List<Source> sources = sourceService.list(PageRequest.of(pageNum, pageSize)).getContent();
        return ResponseEntity.ok(sources);
    }

    @Override
    public ResponseEntity<Source> updateSource(Long id, Source source) {
        try {
            Source updated = sourceService.replace(id, source);
            return ResponseEntity.ok(updated);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
