import java.util.ArrayDeque;
import java.util.Queue;

public class Coda {
    private int tempoEsecuzione;
    private Priority priorita;
    private static int COUNT;
    Queue<Ticket> coda;

    public Coda(Priority priorita, Queue<Ticket> coda) {
        this.priorita = priorita;
        this.coda = coda;
        COUNT++;

        //calcolo tempo esecuzione


    }
    public Coda(Priority priorita) {
        this.priorita = priorita;
        this.coda = new ArrayDeque<>();
    }

    public Ticket completaTicket(){
        return coda.poll();
    }
    public int getSize(){
        return coda.size();
    }

    public Priority getPriorita() {
        return priorita;
    }
    public boolean isEmpty(){
        return coda.isEmpty();
    }

    private int CalcoloTempoEsecuzione(){
        return priorita.getValore() * coda.size();
    }

    @Override
    public String toString() {
        return "Coda{" +
                "tempoEsecuzione=" + tempoEsecuzione +
                ", priorita=" + priorita +
                ", coda=" + coda +
                '}';
    }
}
