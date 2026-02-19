public class Ticket {
    private Priority priority;

    public Ticket(Priority priority) {
        this.priority = priority;
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
                "priority=" + priority +
                '}';
    }
}
