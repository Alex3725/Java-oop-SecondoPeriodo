public enum Forma {
    QUADRATO(0),
    TRIANGOLO(1),
    CERCHIO(2),
    PARALLELOGRAMMA(3);

    private final int valore; // indice della colonna

    Forma(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return valore;
    }

    public static Forma random() {
        Forma[] values = Forma.values();
        int index = (int) (Math.random() * values.length);
        return values[index];
    }
}