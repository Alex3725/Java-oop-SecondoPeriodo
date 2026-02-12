public class Piatto {
    private int diametro;
    private TipoPiatto tipo;
    private int id;

    private static int COUNT;

    public Piatto(int diametro, TipoPiatto tipo) {
        this.diametro = diametro;
        this.tipo = tipo;
        this.id = COUNT++;
    }

    public int getDiametro() {
        return diametro;
    }

    public TipoPiatto getTipo() {
        return tipo;
    }

    public static int getCOUNT() {
        return COUNT;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Piatto{" +
                "diametro=" + diametro +
                ", tipo=" + tipo +
                ", id=" + id +
                '}';
    }
}
