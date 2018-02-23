package br.com.atenas_monitorasummer.atenas_monitorasummer.activity;

import java.util.Date;

/**
 * Created by pegurin on 23/02/18.
 */

public class Event {
    private String id;
    private String nameEvent;
    private String imageEvent;
    private String dataEvent;
    private String descEvento;
    private String localEvento;
    private String timeEvento;

    public Event(String nameEvent, String imageEvent, String dataEvent, String descEvento, String localEvento, String timeEvento) {
        this.nameEvent = nameEvent;
        this.imageEvent = imageEvent;
        this.dataEvent = dataEvent;
        this.descEvento = descEvento;
        this.localEvento = localEvento;
        this.timeEvento = timeEvento;
    }

    public String getDescEvento() {
        return descEvento;
    }

    public void setDescEvento(String descEvento) {
        this.descEvento = descEvento;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    public String getTimeEvento() {
        return timeEvento;
    }

    public void setTimeEvento(String timeEvento) {
        this.timeEvento = timeEvento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return nameEvent;
    }

    public void setName(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getImage() {
        return imageEvent;
    }

    public void setImage(String imageEvent) {
        this.imageEvent = imageEvent;
    }

    public String getDate() {
        return dataEvent;
    }

    public void setDate(String dataEvent) {
        this.dataEvent = dataEvent;
    }
}
