public abstract class MezzoTrasporto {

    protected String codice;
    protected int velocitaMax;

    public MezzoTrasporto(String codice, int velocitaMax) {
        this.codice = codice;
        this.velocitaMax = velocitaMax;
    }

    public void descrizione() {
        System.out.println("Codice: " + codice);
        System.out.println("Velocità Massima: " + velocitaMax + " km/h");
    }

    public abstract double calcolaCosto();
}
