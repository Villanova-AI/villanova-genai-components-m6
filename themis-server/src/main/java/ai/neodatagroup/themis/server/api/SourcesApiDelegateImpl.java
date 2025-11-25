package ai.neodatagroup.themis.server.api;

import ai.neodatagroup.themis.server.model.Source;
import ai.neodatagroup.themis.server.service.NotFoundException;
import ai.neodatagroup.themis.server.service.SourceService;
import org.springframework.dao.DataIntegrityViolationException;
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
            Source src = sourceService.get(id);
            return ResponseEntity.ok(src);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Source>> listSources() {
        List<Source> all = sourceService.list();
        return ResponseEntity.ok(all);
    }

    @Override
    public ResponseEntity<Source> replaceSource(Long id, Source source) {
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
