public class Auto extends MezzoTrasporto implements Rifornibile {

    private int numeroPorte;

    public Auto(String codice, int velocitaMax, int numeroPorte) {
        super(codice, velocitaMax);
        this.numeroPorte = numeroPorte;
    }

    @Override
    public double calcolaCosto() {
        // Esempio di calcolo costo
        return 20000 + (numeroPorte * 500);
    }

    @Override
    public void rifornisci() {
        System.out.println("L'auto con codice " + codice + " è stata rifornita.");
    }

    @Override
    public void descrizione() {
        super.descrizione();
        System.out.println("Numero porte: " + numeroPorte);
    }
}
