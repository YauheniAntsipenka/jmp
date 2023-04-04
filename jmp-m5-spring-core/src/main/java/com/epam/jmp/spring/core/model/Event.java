package com.epam.jmp.spring.core.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Event
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Event {

    private long id;
    private String title;
    private LocalDate date;

    public Event() {
    }

    public Event(long id, String title, LocalDate date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id && title.equals(event.title) && date.equals(event.date);
    }

    public int hashCode() {
        return Objects.hash(id, title, date);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
