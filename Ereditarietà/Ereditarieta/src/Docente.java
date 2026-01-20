public class Docente extends Persona{
    private int severita;

    public Docente(String nome, String cognome, int eta, int severita) {
        super(nome, cognome, eta);
        this.severita = severita;
    }

    @Override
    public void saluta() {
        System.out.println("Buongiorno studenti sono il professor: "+getNome()+", "+getCognome());
    }
}
