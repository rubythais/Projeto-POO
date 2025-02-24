package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Event implements Serializable {
    private String id;
    private String name;
    private LocalDateTime date;
    private String location;
    private int maxCapacity;
    private List<Participant> participants;
    private List<Speaker> speakers;
    private List<Ticket> tickets;

    public Event(String name, LocalDateTime date, String location, int maxCapacity) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.date = date;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.participants = new ArrayList<>();
        this.speakers = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    public boolean addParticipant(Participant participant) {
        if (participants.size() < maxCapacity) {
            participants.add(participant);
            return true;
        }
        return false;
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    public void addSpeaker(Speaker speaker) {
        if (!speakers.contains(speaker)) {
            speakers.add(speaker);
            speaker.addSpeakingEvent(this);
        }
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getMaxCapacity() { return maxCapacity; }
    public List<Participant> getParticipants() { return new ArrayList<>(participants); }
    public List<Speaker> getSpeakers() { return new ArrayList<>(speakers); }
}

