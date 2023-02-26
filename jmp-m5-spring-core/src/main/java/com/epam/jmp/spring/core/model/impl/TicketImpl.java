package com.epam.jmp.spring.core.model.impl;

import com.epam.jmp.spring.core.model.Ticket;

import java.util.Objects;

/**
 * TicketImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class TicketImpl implements Ticket {

    private long id;
    private long eventId;
    private long userId;
    private Ticket.Category category;
    private int place;

    public TicketImpl() {
    }

    public TicketImpl(long id, long eventId, long userId, Category category, int place) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getEventId() {
        return eventId;
    }

    @Override
    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getPlace() {
        return place;
    }

    @Override
    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketImpl ticket = (TicketImpl) o;
        return id == ticket.id && eventId == ticket.eventId && userId == ticket.userId && place == ticket.place && category == ticket.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, userId, category, place);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TicketImpl{");
        sb.append("id=").append(id);
        sb.append(", eventId=").append(eventId);
        sb.append(", userId=").append(userId);
        sb.append(", category=").append(category);
        sb.append(", place=").append(place);
        sb.append('}');
        return sb.toString();
    }
}
