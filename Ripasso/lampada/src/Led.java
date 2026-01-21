public class Led {
    private int numeroLed;
    private Colore colore;

    // Costruttore
    public Led(int numeroLed, Colore colore) {
        this.numeroLed = numeroLed;
        this.colore = colore;
    }

    // Getter e Setter
    public int getNumeroLed() {
        return numeroLed;
    }

    public void setNumeroLed(int numeroLed) {
        this.numeroLed = numeroLed;
    }

    public Colore getColore() {
        return colore;
    }

    public void setColore(Colore colore) {
        this.colore = colore;
    }

    @Override
    public String toString() {
        return "Led [numeroLed=" + numeroLed + ", colore=" + colore + "]";
    }
}
