package com.poec.projet_backend.domaine.drawing;
import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class DrawingService extends AbstractService<Drawing> {
    public DrawingService(DrawingRepository repository) {
        super(repository);
    }

    @Override
    public Drawing update(Long id, Drawing entity) {
        Drawing foundDrawing = getById(id);
        foundDrawing.setName(entity.getName());
        foundDrawing.setContent(entity.getContent());
        return repository.save(foundDrawing);
    }
}
