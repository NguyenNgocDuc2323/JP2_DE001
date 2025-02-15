import java.util.*;


public class TicketBookingSystem {
    private static final Map<String, Integer> eventTickets = new HashMap<>();
    private static String selectedEvent = "";
    private static final Random random = new Random();
    private static boolean running = true;

    static {
        eventTickets.put("Music Festival", 5);
        eventTickets.put("Tech Conference", 3);
        eventTickets.put("Art Exhibition", 4);
        eventTickets.put("Film Premiere", 2);
    }

    public static void main(String[] args) {
        Thread eventGenerator = new Thread(() -> {
            try {
                int count = 0;
                while (running && count < 10) {
                    Thread.sleep(1000);
                    List<String> events = new ArrayList<>(eventTickets.keySet());
                    selectedEvent = events.get(random.nextInt(events.size()));
                    System.out.println("Event Selected: " + selectedEvent + " | Available Tickets: " + eventTickets.get(selectedEvent));
                    count++;
                }
                running = false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread ticketPurchase = new Thread(() -> {
            try {
                while (running) {
                    Thread.sleep(1500);
                    synchronized (eventTickets) {
                        if (!selectedEvent.isEmpty() && eventTickets.containsKey(selectedEvent)) {
                            int availableTickets = eventTickets.get(selectedEvent);
                            if (availableTickets > 0) {
                                eventTickets.put(selectedEvent, availableTickets - 1);
                                System.out.println("Ticket booked for: " + selectedEvent + " | Remaining Tickets: " + (availableTickets - 1));
                            } else {
                                System.out.println(selectedEvent + " is Sold Out.");
                            }
                        }
                    }
                    if (eventTickets.values().stream().allMatch(t -> t == 0)) {
                        running = false;
                        System.out.println("All events are sold out. Stopping the system...");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        eventGenerator.start();
        ticketPurchase.start();

        try {
            eventGenerator.join();
            ticketPurchase.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("System has stopped.");
    }
}