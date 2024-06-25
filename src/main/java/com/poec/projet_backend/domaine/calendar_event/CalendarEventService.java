package com.poec.projet_backend.domaine.calendar_event;
import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class CalendarEventService extends AbstractService<CalendarEvent> {
    public CalendarEventService(CalendarEventRepository repository) {
        super(repository);
    }

    @Override
    public CalendarEvent update(Long id, CalendarEvent entity) {
        CalendarEvent foundEvent = getById(id);
        foundEvent.setTitle(entity.getTitle());
        foundEvent.setDescription(entity.getDescription());
        foundEvent.setStart(entity.getStart());
        foundEvent.setEnd(entity.getEnd());
        return repository.save(foundEvent);
    }
}
