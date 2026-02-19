public class Disco implements Comparable<Disco>{
    private int dimensione;
    private String colore;

    public Disco(int dimensione) {
        this.dimensione = dimensione;
    }

    public int getDimensione() {
        return dimensione;
    }

    @Override
    public String toString() {
        return String.valueOf(dimensione);
    }

    @Override
    public int compareTo(Disco o) {
        return Integer.compare(this.dimensione,o.dimensione);
    }
}

