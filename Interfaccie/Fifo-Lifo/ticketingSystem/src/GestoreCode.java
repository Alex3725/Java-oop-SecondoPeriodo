public class GestoreCode {
    private Coda codaAlta;
    private Coda codaMedia;
    private Coda codaBassa;


    private Coda completati;

    public GestoreCode(Coda codaAlta, Coda codaMedia, Coda codaBassa) {
        this.codaAlta = codaAlta;
        this.codaMedia = codaMedia;
        this.codaBassa = codaBassa;
        this.completati = new Coda(Priority.BASSA);
    }

    @Override
    public String toString() {
        return "GestoreCode{" +
                "codaAlta=" + codaAlta +
                ", codaMedia=" + codaMedia +
                ", codaBassa=" + codaBassa +
                ", completati=" + completati +
                '}';
    }
}
