public class Main {

    public static void main(String[] args) {

        try {
            Libro l1 = new Libro("Il Signore degli Anelli", "Tolkien", "ISBN1234", 1.5f);
            Utente u1 = new Utente("Mario", "Rossi", "ABC12345");

            Prestito p1 = new Prestito(l1, u1, 10);
            Prestito p2 = new Prestito(l1, u1, 5);
            Prestito p3 = new Prestito(
                    new Libro("1984", "Orwell", "ISBN9999", 2.5f),
                    u1,
                    7
            );

            l1.setCostoGiornaliero(2.0f);
            p1.setRestituito(true);

            Prestito[] prestiti = {p1, p2, p3};

            System.out.println("Prestiti per ISBN ISBN1234:");
            for (Prestito p : prestiti) {
                if (p.getLibro().getCodiceISBN().equals("ISBN1234")) {
                    System.out.println(p);
                }
            }

            System.out.println("\nCosto prestito p2: " + p2.calcolaCostoTotale());
            System.out.println("Numero totale prestiti: " + Prestito.getNumeroPrestiti());

        } catch (IllegalArgumentException e) {
            System.out.println("Errore di dati: " + e.getMessage());

        } catch (NullPointerException e) {
            System.out.println("Errore di riferimento: " + e.getMessage());

        } catch (ArithmeticException e) {
            System.out.println("Errore di calcolo: " + e.getMessage());
        }
    }
}
