import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventTest {
    private static final String FILE_NAME = "";
    private static List<Event> events = new ArrayList<>();

    public static void addEvent() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Event ID:");
        String id = reader.readLine();
        System.out.println("Enter Event Name:");
        String name = reader.readLine();
        System.out.println("Enter Organizer:");
        String organizer = reader.readLine();
        System.out.println("Enter Location:");
        String location = reader.readLine();
        System.out.println("Enter Ticket Price:");
        float price = Float.parseFloat(reader.readLine());
        System.out.println("Enter Total Tickets:");
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
        System.out.println("Events saved to file.");
    }

    public static void readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    public static void searchByLocation() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter location to search:");
        String location = reader.readLine();
        boolean found = false;

        for (Event event : events) {
            if (event.getLocation().equalsIgnoreCase(location)) {
                System.out.println(event);
                found = true;
            }
        }
        if (!found) System.out.println("NOT_FOUND");
    }

    public static void main(String[] args) throws IOException {
        addEvent();
        saveToFile();
        readFromFile();
        searchByLocation();
    }
}
