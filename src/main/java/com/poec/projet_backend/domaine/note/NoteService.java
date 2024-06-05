package com.poec.projet_backend.domaine.note;
import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class NoteService extends AbstractService<Note> {
    public NoteService(NoteRepository repository) {
        super(repository);
    }

    @Override
    public Note update(Long id, Note entity) {
        Note foundNote = getById(id);
        foundNote.setName(entity.getName());
        foundNote.setText(entity.getText());
        return repository.save(foundNote);
    }
}
