import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<MezzoTrasporto> mezzi = new ArrayList<>();

        Auto auto = new Auto("A123", 220, 5);
        Moto moto = new Moto("M456", 180, 600);

        mezzi.add(auto);
        mezzi.add(moto);

        for (MezzoTrasporto mezzo : mezzi) {

            mezzo.descrizione();
            System.out.println("Costo: " + mezzo.calcolaCosto() + " €");

            // Cast necessario per chiamare rifornisci()
            if (mezzo instanceof Rifornibile) {
                ((Rifornibile) mezzo).rifornisci();
            }

            System.out.println("------------------------");
        }
    }
}
