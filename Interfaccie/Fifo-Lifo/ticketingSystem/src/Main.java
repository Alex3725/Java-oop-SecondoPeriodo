public class Main {
    public static void main(String[] args) {

        Coda codaAlta = new Coda(Priority.ALTA);
        Coda codaMedia = new Coda(Priority.MEDIA);
        Coda codaBassa = new Coda(Priority.BASSA);

        codaAlta.coda.add(new Ticket(Priority.ALTA, "Server down"));
        codaAlta.coda.add(new Ticket(Priority.ALTA, "Backup fallito"));
        codaMedia.coda.add(new Ticket(Priority.MEDIA, "Bug minore"));
        codaBassa.coda.add(new Ticket(Priority.BASSA, "Richiesta info"));

        GestoreCode gestore = new GestoreCode(codaAlta, codaMedia, codaBassa);

        // STAMPA CODE INIZIALI
        gestore.stampaCode();

        // Completa un ticket e stampa
        gestore.completaTicket();
        System.out.println("Dopo aver completato un ticket:");
        gestore.stampaCode();

        // Completa tutti i ticket
        try {
            while (true) {
                gestore.completaTicket();
            }
        } catch (IllegalStateException e) {
            System.out.println("Tutti i ticket sono stati completati.");
        }

        // Stampa finale
        gestore.stampaCode();
    }
}