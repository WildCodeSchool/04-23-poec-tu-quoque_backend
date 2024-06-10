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

    /*
      "schedules": [
    {
      "id": "1",
      "tablesId": 1
    }
  ],
  "events": [
    {
      "id": 1,
      "tableId": 1,
      "title": "dispo Gimli",
      "description": "anniversaire Gimli",
      "start": "2024-05-24 00:00:00",
      "allDay": true
    },
    {
      "id": 2,
      "tableId": 1,
      "title": "dispo Freyr",
      "start": "2024-05-27 00:00:00",
      "allDay": true
    },
    {
      "id": 3,
      "tableId": 1,
      "title": "dispoMJ",
      "start": "2024-05-28 09:00:00",
      "end": "2024-05-30 09:00:00",
      "allDay": true,
      "color": "#c29b0f"
    },
    {
      "id": 4,
      "tableId": 4,
      "title": "table MJ dispo",
      "start": "2024-06-26 09:00:00",
      "end": "2024-06-29 09:00:00",
      "description": "Disponible 24/24 pendant ces 3 jours, maison déjà louée",
      "allDay": true,
      "color": "#c29b0f"
    }
  ],
     */
}
