import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;

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

    public void completaTicket(){
        System.out.println(coda.poll());
    }

    private int CalcoloTempoEsecuzione(){
        return priorita.getValore() * coda.size();
    }
}
