public class Main {
    public static void main(String[] args) {
        // Creazione di un Led
        Led led1 = new Led(10, Colore.BIANCO);

        // Creazione di una Lampada con il Led
        Lampada lampada1 = new Lampada("Philips", led1);

        // Stampa informazioni
        System.out.println(lampada1);

        // Modifica dei dati del Led
        lampada1.getLed().setColore(Colore.RGB);
        lampada1.getLed().setNumeroLed(15);

        // Stampa aggiornamenti
        System.out.println(lampada1);
    }
}