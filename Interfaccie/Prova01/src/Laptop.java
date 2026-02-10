public class Laptop extends Dispositivo implements Connettivita{
    private double pollici;
    private String modello;
    private final int consumo= 20;

    public Laptop(String marca, float prezzo, double pollici, String modello) {
        super(marca, prezzo);
        this.pollici = pollici;
        this.modello = modello;
    }

    @Override
    public double calcolaConsumo() {
        return this.pollici*consumo;
    }
}
