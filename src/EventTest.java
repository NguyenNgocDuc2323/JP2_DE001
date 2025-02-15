import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventTest {
    private static final String FILE_NAME = "events_data.bat";
    private static List<Event> events = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("\n=== Event Management System ===");
            System.out.println("1. Add Event");
            System.out.println("2. Save Events to File");
            System.out.println("3. Read Events from File");
            System.out.println("4. Search Events by Location");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = reader.readLine();
            switch (choice) {
                case "1":
                    addEvent();
                    break;
                case "2":
                    saveToFile();
                    break;
                case "3":
                    readFromFile();
                    break;
                case "4":
                    searchByLocation();
                    break;
                case "5":
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1-5.");
            }
        }
    }

    public static void addEvent() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nEnter Event Details:");
        System.out.print("ID: ");
        String id = reader.readLine();
        System.out.print("Name: ");
        String name = reader.readLine();
        System.out.print("Organizer: ");
        String organizer = reader.readLine();
        System.out.print("Location: ");
        String location = reader.readLine();
        System.out.print("Ticket Price: ");
        float price = Float.parseFloat(reader.readLine());
        System.out.print("Total Tickets: ");
        int tickets = Integer.parseInt(reader.readLine());

        try {
            Event event = new Event(id, name, organizer, location, price, tickets);
            events.add(event);
            System.out.println("Event added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Event event : events) {
            writer.write(event.toString());
            writer.newLine();
        }
        writer.close();
        System.out.println("Events saved to file successfully!");
    }

    public static void readFromFile() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No event data found.");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        System.out.println("\n=== Events List ===");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public static void searchByLocation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter location to search: ");
        String location = reader.readLine();
        boolean found = false;

        System.out.println("\n=== Search Results ===");
        for (Event event : events) {
            if (event.getLocation().equalsIgnoreCase(location)) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) System.out.println("NOT_FOUND");
    }
}
