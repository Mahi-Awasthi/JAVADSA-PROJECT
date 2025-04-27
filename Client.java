public class Client {
    int id;
    String name;
    String contact;

    public Client(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Client [ID=" + id + ", Name=" + name + ", Contact=" + contact + "]";
    }
}
