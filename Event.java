public class Event {
    int id;
    String name;
    String date;
    String venue;

    public Event(int id, String name, String date, String venue) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "Event [ID=" + id + ", Name=" + name + ", Date=" + date + ", Venue=" + venue + "]";
    }
}
