public class Studente extends Persona{
    private int bravura;

    public Studente(String nome, String cognome, int eta, int bravura) {
        super(nome, cognome, eta);
        this.bravura = bravura;
    }

    @Override
    public void saluta() {
        System.out.println("Buongiorno prof io sono lo studente:"+getNome()+", "+getCognome());
    }
}
