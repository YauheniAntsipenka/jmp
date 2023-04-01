package com.epam.jmp.messaging.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Event
 * Date: 04/01/2023
 *
 * @author Yauheni Antsipenka
 */
public class Event {

    private Long eventId;
    private String title;
    private String place;
    private String speaker;
    private LocalDateTime dateTime;

    public Event() {
    }

    public Event(Long eventId, String title, String place, String speaker, LocalDateTime dateTime) {
        this.eventId = eventId;
        this.title = title;
        this.place = place;
        this.speaker = speaker;
        this.dateTime = dateTime;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId.equals(event.eventId) && Objects.equals(title, event.title) && Objects.equals(place, event.place) && Objects.equals(speaker, event.speaker) && Objects.equals(dateTime, event.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, title, place, speaker, dateTime);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("eventId=").append(eventId);
        sb.append(", title='").append(title).append('\'');
        sb.append(", place='").append(place).append('\'');
        sb.append(", speaker='").append(speaker).append('\'');
        sb.append(", dateTime=").append(dateTime);
        sb.append('}');
        return sb.toString();
    }
}
