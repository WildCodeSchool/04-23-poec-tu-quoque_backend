package com.poec.projet_backend.util;

import com.poec.projet_backend.domaine.calendar_event.CalendarEvent;
import com.poec.projet_backend.domaine.calendar_event.CalendarEventService;
import com.poec.projet_backend.domaine.game_table.GameTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class EventFixtures {

    @Autowired
    private CalendarEventService eventService;
    @Autowired
    private GameTableService tableService;

    public void load() throws ParseException {
        generate();
    }

    private void generate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date dateStart = formatter.parse("2024-06-26 09:00");
        Date dateEnd = formatter.parse("2024-06-29 09:00");

        CalendarEvent event = CalendarEvent.builder()
                .game_table(tableService.getById(4L))
                .title("table MJ dispo")
                .start(dateStart)
                .end(dateEnd)
                .description("Disponible 24/24 pendant ces 3 jours, maison déjà louée")
                .allDay(true)
                .color("#c29b0f")
                .build();

        eventService.add(event);

        Date dateStart2 = formatter.parse("2024-06-28 00:00");

        CalendarEvent event2 = CalendarEvent.builder()
                .game_table(tableService.getById(1L))
                .title("dispo Gimli")
                .start(dateStart2)
                .description("anniversaire Gimli")
                .allDay(true)
                .build();

        eventService.add(event2);

        Date dateStart3 = formatter.parse("2024-06-28 00:00");

        CalendarEvent event3 = CalendarEvent.builder()
                .game_table(tableService.getById(1L))
                .title("dispo Freyr")
                .start(dateStart3)
                .allDay(true)
                .build();

        eventService.add(event3);

        Date dateStart4 = formatter.parse("2024-06-28 00:00");

        CalendarEvent event4 = CalendarEvent.builder()
                .game_table(tableService.getById(1L))
                .title("dispo MJ")
                .start(dateStart4)
                .allDay(true)
                .build();

        eventService.add(event4);

    }
}
