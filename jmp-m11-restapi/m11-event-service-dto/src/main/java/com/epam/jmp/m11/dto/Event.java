package com.epam.jmp.m11.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Event
 * Date: 04/08/2023
 *
 * @author Yauheni Antsipenka
 */

@Entity
@Table(name = "events")
public class Event implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;
    @Column
    private String title;
    @Column
    private String place;
    @Column
    private String speaker;
    @Column
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateTime;

    public Event() {
    }

    public Event(Long eventId, String title, String place, String speaker, LocalDate dateTime) {
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

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
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
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"eventId\":").append(eventId);
        sb.append(", \"title\":\"").append(title).append('\"');
        sb.append(", \"place\":\"").append(place).append('\"');
        sb.append(", \"speaker\":\"").append(speaker).append('\"');
        sb.append(", \"dateTime\":\"").append(dateTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
