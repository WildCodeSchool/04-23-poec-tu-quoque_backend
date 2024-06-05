package com.poec.projet_backend.domaine.calendar_event;
import com.poec.projet_backend.domaine.abstract_package.AbstractController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class CalendarEventController extends AbstractController<CalendarEvent> {

    @Autowired
    private CalendarEventService service;
}
