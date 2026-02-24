import java.util.EnumMap;
import java.util.Map;

public class GestoreCode {
    private Coda codaAlta;
    private Coda codaMedia;
    private Coda codaBassa;

    private Map<Priority, Coda> insieme;

    public GestoreCode(Coda codaAlta, Coda codaMedia, Coda codaBassa) {
        this.codaAlta = codaAlta;
        this.codaMedia = codaMedia;
        this.codaBassa = codaBassa;

        this.insieme = new EnumMap<>(Priority.class);
        insieme.put(Priority.ALTA, codaAlta);
        insieme.put(Priority.MEDIA, codaMedia);
        insieme.put(Priority.BASSA, codaBassa);
    }

    private Coda getCodaWithPriority(Priority priority) {
        return insieme.get(priority);
    }

    public void completaTicket() {
        for (Priority p : Priority.values()) {
            Coda coda = insieme.get(p);
            if (coda != null && !coda.isEmpty()) {
                System.out.println("Ticket completato: " + coda.completaTicket());
                return;
            }
        }
        throw new IllegalStateException("Nessun ticket da completare");
    }

    // ---------------------------------------------
    // METODO PER STAMPARE LE CODE COME TABELLA
    // ---------------------------------------------
    public void stampaCode() {
        System.out.println("GESTORE CODE:");
        System.out.println("--------------------------------------------------------------");
        System.out.printf("%-30s | %-30s | %-30s%n", "ALTA", "MEDIA", "BASSA");
        System.out.println("--------------------------------------------------------------");

        int maxSize = Math.max(codaAlta.getSize(),
                Math.max(codaMedia.getSize(), codaBassa.getSize()));

        for (int i = 0; i < maxSize; i++) {
            String alta = i < codaAlta.getSize() ? codaAlta.coda.toArray()[i].toString() : "";
            String media = i < codaMedia.getSize() ? codaMedia.coda.toArray()[i].toString() : "";
            String bassa = i < codaBassa.getSize() ? codaBassa.coda.toArray()[i].toString() : "";

            System.out.printf("%-30s | %-30s | %-30s%n", alta, media, bassa);
        }

        System.out.println("--------------------------------------------------------------\n");
    }
    // ---------------------------------------------
}