public class Prestito {

    private Libro libro;
    private Utente utente;
    private int giorniPrestito;
    private boolean isRestituito;

    private static int numeroPrestiti = 0;

    public Prestito(Libro libro, Utente utente, int giorniPrestito) {

        if (libro == null || utente == null) {
            throw new NullPointerException("Libro o utente non assegnato al prestito");
        }

        if (giorniPrestito <= 0 || giorniPrestito > 20) {
            throw new IllegalArgumentException(
                    "Giorni di prestito non validi (max 20)"
            );
        }

        this.libro = libro;
        this.utente = utente;
        this.giorniPrestito = giorniPrestito;
        this.isRestituito = false;
        numeroPrestiti++;
    }

    public void setRestituito(boolean restituito) {
        isRestituito = restituito;
    }

    public Libro getLibro() {
        return libro;
    }

    public float calcolaCostoTotale() {
        if (isRestituito) {
            return 0;
        }
        if (libro.getCostoGiornaliero() == 0) {
            throw new ArithmeticException("Costo giornaliero del libro non impostato");
        }
        return giorniPrestito * libro.getCostoGiornaliero();
    }

    public static int getNumeroPrestiti() {
        return numeroPrestiti;
    }

    @Override
    public String toString() {
        return "Prestito: " + libro + " | Utente: " + utente;
    }
}
