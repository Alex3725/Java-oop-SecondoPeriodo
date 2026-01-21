public class Lampada {
    private String marca;
    private Led led; // Lampada ha un Led

    // Costruttore
    public Lampada(String marca, Led led) {
        this.marca = marca;
        this.led = led;
    }

    // Getter e Setter
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Led getLed() {
        return led;
    }

    public void setLed(Led led) {
        this.led = led;
    }

    @Override
    public String toString() {
        return "Lampada [marca=" + marca + ", " + led + "]";
    }
}
