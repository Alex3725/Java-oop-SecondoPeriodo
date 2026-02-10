public class SmartPhone extends Dispositivo implements Connettivita{
    private double polliciDisplay;
    private String colore;
    private int memoria;

    public SmartPhone(String marca, float prezzo, double polliciDisplay, String colore, int memoria) {
        super(marca, prezzo);
        this.polliciDisplay = polliciDisplay;
        this.colore = colore;
        this.memoria = memoria;
    }

    @Override
    public void connettitiAInternet() {
        System.out.println("collegato tramite erte WI-FI");
    }


    @Override
    public double calcolaConsumo() {
        return 300;
    }
}
