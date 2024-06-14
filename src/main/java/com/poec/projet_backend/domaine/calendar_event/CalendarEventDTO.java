package com.poec.projet_backend.domaine.calendar_event;

import java.util.Date;

public record CalendarEventDTO(
        Long id,
        Long tableId,
        String title,
        Date start,
        Date end,
        String description,
        boolean allDay,
        String color

) {
    public static CalendarEventDTO mapFromEntity(CalendarEvent calendarEvent) {
        return new CalendarEventDTO(
                calendarEvent.getId(),
                calendarEvent.getGameTable().getId(),
                calendarEvent.getTitle(),
                calendarEvent.getStart(),
                calendarEvent.getEnd(),
                calendarEvent.getDescription(),
                calendarEvent.isAllDay(),
                calendarEvent.getColor()
        );
    }
}
