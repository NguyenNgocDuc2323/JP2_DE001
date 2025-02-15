public class Event {
    private String id;
    private String name;
    private String organizer;
    private String location;
    private float ticketPrice;
    private int totalTickets;

    public Event() {}

    public Event(String id, String name, String organizer, String location, float ticketPrice, int totalTickets) {
        this.id = id;
        setName(name);
        setOrganizer(organizer);
        setTicketPrice(ticketPrice);
        setTotalTickets(totalTickets);
        this.location = location;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name.length() >= 3) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Event name must be at least 3 characters long.");
        }
    }

    public String getOrganizer() { return organizer; }
    public void setOrganizer(String organizer) {
        if (organizer.length() >= 3) {
            this.organizer = organizer;
        } else {
            throw new IllegalArgumentException("Organizer name must be at least 3 characters long.");
        }
    }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public float getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(float ticketPrice) {
        if (ticketPrice > 5) {
            this.ticketPrice = ticketPrice;
        } else {
            throw new IllegalArgumentException("Ticket price must be greater than $5.");
        }
    }

    public int getTotalTickets() { return totalTickets; }
    public void setTotalTickets(int totalTickets) {
        if (totalTickets >= 0) {
            this.totalTickets = totalTickets;
        } else {
            throw new IllegalArgumentException("Total tickets cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return id + "," + name + "," + organizer + "," + location + "," + ticketPrice + "," + totalTickets;
    }
}
