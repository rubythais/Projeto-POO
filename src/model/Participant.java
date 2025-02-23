package model;

import java.util.ArrayList;
import java.util.List;

public class Participant extends User {
    private List<Event> registeredEvents;

    public Participant(String name, String email, String cpf) {
        super(name, email, cpf);
        this.registeredEvents = new ArrayList<>();
    }

    public void registerForEvent(Event event) {
        if (!registeredEvents.contains(event)) {
            registeredEvents.add(event);
        }
    }

    public void cancelEventRegistration(Event event) {
        registeredEvents.remove(event);
    }

    public List<Event> getRegisteredEvents() {
        return new ArrayList<>(registeredEvents);
    }
}

