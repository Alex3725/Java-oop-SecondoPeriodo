import java.util.Scanner;

public class CuboIncastri {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cubo cubo = new Cubo(5); // capacità massima per colonna

        System.out.println("=== Benvenuto a Cubo Incastri Avanzato ===");

        while (!cubo.pieno()) {
            Forma forma = Forma.random();
            System.out.println("Nuova forma generata: " + forma);

            System.out.print("Vuoi inserirla nella colonna corretta? (s/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("s") || input.equals("si")) {
                cubo.inserisci(forma);
            } else {
                System.out.println("Hai deciso di non inserire la forma.");
            }

            cubo.mostra();
        }

        System.out.println("Tutte le colonne del cubo sono piene! Gioco terminato.");
    }
}