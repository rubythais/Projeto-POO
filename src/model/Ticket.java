package model;

public class Ticket {
    private String id;
    private TicketType type;
    private double price;
    private Event event;
    private Participant participant;
    private boolean isValid;

    public enum TicketType {
        FULL_PRICE,
        HALF_PRICE,
        VIP
    }

    public Ticket(TicketType type, double price, Event event, Participant participant) {
        this.id = java.util.UUID.randomUUID().toString();
        this.type = type;
        this.price = price;
        this.event = event;
        this.participant = participant;
        this.isValid = true;
    }

    public boolean validateTicket() {
        return isValid && event != null && participant != null;
    }


    public String getId() { return id; }
    public TicketType getType() { return type; }
    public double getPrice() { return price; }
    public Event getEvent() { return event; }
    public Participant getParticipant() { return participant; }
    public boolean isValid() { return isValid; }
    public void setValid(boolean valid) { isValid = valid; }
}

