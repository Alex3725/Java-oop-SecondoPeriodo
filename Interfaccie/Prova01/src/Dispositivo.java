public abstract class Dispositivo {
    private String marca;
    private double prezzo;

    public Dispositivo(String marca, float prezzo) {
        this.marca = marca;
        this.prezzo = prezzo;
    }

    public void accendi(){
        System.out.println("acceso");
    }

    public abstract double calcolaConsumo();
}
