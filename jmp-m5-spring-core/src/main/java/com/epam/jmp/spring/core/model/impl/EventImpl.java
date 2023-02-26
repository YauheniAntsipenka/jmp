package com.epam.jmp.spring.core.model.impl;

import com.epam.jmp.spring.core.model.Event;

import java.util.Date;
import java.util.Objects;

/**
 * EventImpl
 * Date: 02/26/2023
 *
 * @author Yauheni Antsipenka
 */
public class EventImpl implements Event {

    private long id;
    private String title;
    private Date date;

    public EventImpl() {
    }

    public EventImpl(long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
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
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventImpl event = (EventImpl) o;
        return id == event.id && title.equals(event.title) && date.equals(event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventImpl{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
