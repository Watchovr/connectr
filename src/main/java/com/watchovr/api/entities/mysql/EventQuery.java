package com.watchovr.api.entities.mysql;

import com.watchovr.api.entities.enums.EventQueryType;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EventQuery {

    @Id
    @GeneratedValue
    private String id;

    @Column
    private DateTime createdAt;

    @Column
    private EventQueryType eventQueryType;

    public EventQuery() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EventQueryType getEventQueryType() {
        return eventQueryType;
    }

    public void setEventQueryType(EventQueryType eventQueryType) {
        this.eventQueryType = eventQueryType;
    }
}
