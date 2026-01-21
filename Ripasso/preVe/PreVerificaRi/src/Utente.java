public class Utente {

    private String nome;
    private String cognome;
    private String numeroTessera;

    public Utente(String nome, String cognome, String numeroTessera) {
        if (numeroTessera == null) {
            throw new NullPointerException("Numero tessera mancante");
        }
        if (numeroTessera.length() != 8) {
            throw new IllegalArgumentException(
                    "Numero tessera non valido: deve contenere esattamente 8 caratteri"
            );
        }
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTessera = numeroTessera;
    }

    public String getNumeroTessera() {
        return numeroTessera;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + " - Tessera: " + numeroTessera;
    }
}
