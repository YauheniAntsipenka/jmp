package com.epam.jmp.spring.core.model;

import java.util.Objects;

/**
 * Ticket
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class Ticket {

    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;

    public Ticket() {
    }

    public Ticket(long id, long eventId, long userId, Category category, int place) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && eventId == ticket.eventId && userId == ticket.userId && place == ticket.place && category == ticket.category;
    }

    public int hashCode() {
        return Objects.hash(id, eventId, userId, category, place);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Ticket{");
        sb.append("id=").append(id);
        sb.append(", eventId=").append(eventId);
        sb.append(", userId=").append(userId);
        sb.append(", category=").append(category);
        sb.append(", place=").append(place);
        sb.append('}');
        return sb.toString();
    }
}
