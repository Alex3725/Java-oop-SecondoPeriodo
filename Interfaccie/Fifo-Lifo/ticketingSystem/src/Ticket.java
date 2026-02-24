public class Ticket {
    private String contenuto;
    private Priority priority;

    public Ticket(Priority priority,String contenuto) {
        this.priority = priority;
        this.contenuto = contenuto;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "contenuto='" + contenuto + '\'' +
                ", priority=" + priority +
                '}';
    }
}
