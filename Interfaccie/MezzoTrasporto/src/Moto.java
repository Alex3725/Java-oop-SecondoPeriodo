public class Moto extends MezzoTrasporto implements Rifornibile {

    private int cilindrata;

    public Moto(String codice, int velocitaMax, int cilindrata) {
        super(codice, velocitaMax);
        this.cilindrata = cilindrata;
    }

    @Override
    public double calcolaCosto() {
        // Esempio di calcolo costo
        return 8000 + (cilindrata * 2);
    }

    @Override
    public void rifornisci() {
        System.out.println("La moto con codice " + codice + " è stata rifornita.");
    }

    @Override
    public void descrizione() {
        super.descrizione();
        System.out.println("Cilindrata: " + cilindrata + " cc");
    }
}
