package classe;

import java.util.ArrayList;
import java.util.Random;

public class Studente extends Persona {
    private Random r;

    private String classe;
    private int capacita;
    private ArrayList<Integer> voti = new ArrayList<>();

    public Studente(String nome, String cognome, int eta, String classe, int capacita) {
        super(nome, cognome, eta);
        this.classe = classe;
        this.capacita = capacita;

        this.r = new Random();
    }

    @Override
    public void saluita() {
        System.out.println("Buongiorno sono lo studente: "+super.getNome()+"-"+super.getCognome()+"Ho eta"+super.getEta());
    }

    @Override
    public String toString() {
        return "Studente(" +
                "Nome: "+super.getNome()+
                "-cognome: "+super.getCognome()+
                "-classe='" + classe + '\'' +
                "-capacita=" + capacita +
                "-voti=" + voti +
                ')';
    }

    protected void addVoto(Integer voto){
        voti.add(voto);
    }

    public float calcolaMedia(){
        if (voti.isEmpty())throw new NullPointerException();
        int somma = 0;
        for (Integer voto : voti){
            if (voto == null) continue;
            somma += voto;
        }
        return (float) somma /voti.size();
    }

    public void studia(){
        int random = r.nextInt(1,11);
        capacita += random;
    }

///Get setter ///
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getCapacita() {
        return capacita;
    }

    public void setCapacita(int capacita) {
        this.capacita = capacita;
    }

    public ArrayList<Integer> getVoti() {
        return voti;
    }

    public void setVoti(ArrayList<Integer> voti) {
        this.voti = voti;
    }
}
