package classe;

public class Docente extends Persona {
    private int severita;

    public Docente(String nome, String cognome, int eta, int severita) {
        super(nome, cognome, eta);

        isCorrectSeverita(severita);
    }
    private void isCorrectSeverita(int sev){
        if (sev < 1 || sev > 5)throw new IllegalArgumentException("severità errata");
        this.severita = sev;
    }

    @Override
    public void saluita() {
        System.out.println("Buongiorno a tutti io sono il "+this);
    }

    @Override
    public String toString() {
        return "classe.Docente(" +
                "Nome: "+super.getNome()+
                "-cognome: "+super.getCognome()+
                "-severità:'" + severita + ')';
    }

    public void interroga(Studente st){
        Integer voto = Math.round((float) (st.getCapacita() - severita) /10);
        if (voto < 0)voto = null;
        System.out.println("Hai preso: "+voto);
        st.addVoto(voto);
    }
}
