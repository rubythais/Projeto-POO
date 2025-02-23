package model;

import java.util.ArrayList;
import java.util.List;

public class Speaker extends User {
    private String specialty;
    private List<Event> speakingEvents;

    public Speaker(String name, String email, String cpf, String specialty) {
        super(name, email, cpf);
        this.specialty = specialty;
        this.speakingEvents = new ArrayList<>();
    }

    public void addSpeakingEvent(Event event) {
        if (!speakingEvents.contains(event)) {
            speakingEvents.add(event);
        }
    }

    public void removeSpeakingEvent(Event event) {
        speakingEvents.remove(event);
    }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public List<Event> getSpeakingEvents() { return new ArrayList<>(speakingEvents); }
}

