public class Booking {
    Event event;
    Client client;

    public Booking(Event event, Client client) {
        this.event = event;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Booking [Event: " + event.name + " on " + event.date + " at " + event.venue + ", Client: " + client.name + "]";
    }
}
