public class Libro {

    private String titolo;
    private String autore;
    private String codiceISBN;
    private float costoGiornaliero;

    public Libro(String titolo, String autore, String codiceISBN, float costoGiornaliero) {
        if (titolo == null || autore == null) {
            throw new NullPointerException("Titolo e autore non possono essere null");
        }
        if (costoGiornaliero < 0) {
            throw new IllegalArgumentException("Il costo giornaliero non può essere negativo");
        }
        this.titolo = titolo;
        this.autore = autore;
        this.codiceISBN = codiceISBN;
        this.costoGiornaliero = costoGiornaliero;
    }

    public Libro(Libro l) {
        this(l.titolo, l.autore, l.codiceISBN, l.costoGiornaliero);
    }

    public Libro(String titolo, String autore) {
        this(titolo, autore, "N/D", 0);
    }

    public float getCostoGiornaliero() {
        return costoGiornaliero;
    }

    public void setCostoGiornaliero(float costoGiornaliero) {
        if (costoGiornaliero < 0) {
            throw new IllegalArgumentException("Costo giornaliero non valido");
        }
        this.costoGiornaliero = costoGiornaliero;
    }

    public String getCodiceISBN() {
        return codiceISBN;
    }

    public boolean isEconomico() {
        return costoGiornaliero <= 2.0;
    }

    @Override
    public String toString() {
        return titolo + " - " + autore + " (ISBN: " + codiceISBN + ")";
    }
}
