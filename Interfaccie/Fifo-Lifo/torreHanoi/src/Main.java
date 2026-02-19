import java.util.Scanner;

public class Main {

    public static void stampaTorri(Torre A, Torre B, Torre C) {
        System.out.println("\nSituazione attuale:");
        A.stampa();
        B.stampa();
        C.stampa();
    }

    public static boolean mossaValida(Torre sorgente, Torre destinazione) {
        if (sorgente.isEmpty()) {
            System.out.println("Torre sorgente vuota!");
            return false;
        }

        if (!destinazione.isEmpty() &&
                sorgente.peek().compareTo(destinazione.peek()) > 0) {
            System.out.println("Non puoi mettere un disco grande sopra uno piccolo!");
            return false;
        }

        return true;
    }

    public static boolean vittoria(Torre b, Torre c, int numeroDisc){
        if (b.size() == numeroDisc) return true;
        if (c.size() == numeroDisc) return true;
        return false;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Numero dischi: ");
        int n = scanner.nextInt();

        Torre A = new Torre("A");
        Torre B = new Torre("B");
        Torre C = new Torre("C");

        for (int i = n; i >= 1; i--) {
            A.push(new Disco(i));
        }

        int mosse = 0;


        while (!vittoria(B,C,n)) {
            System.out.println("Numero minimo teorico: " + (int)(Math.pow(2, n) - 1));
            stampaTorri(A, B, C);

            System.out.print("Da quale torre vuoi spostare? (A/B/C): ");
            String da = scanner.next().toUpperCase();

            System.out.print("Verso quale torre? (A/B/C): ");
            String a = scanner.next().toUpperCase();

            Torre sorgente = null;
            Torre destinazione = null;

            switch (da) {
                case "A": sorgente = A; break;
                case "B": sorgente = B; break;
                case "C": sorgente = C; break;
                default:
                    System.out.println("Scelta non valida!");
                    continue;
            }

            switch (a) {
                case "A": destinazione = A; break;
                case "B": destinazione = B; break;
                case "C": destinazione = C; break;
                default:
                    System.out.println("Scelta non valida!");
                    continue;
            }

            if (mossaValida(sorgente, destinazione)) {
                destinazione.push(sorgente.pop());
                mosse++;
            }
        }

        System.out.println("\n🎉 Complimenti! Hai vinto!");
        System.out.println("Numero mosse effettuate: " + mosse);
        System.out.println("Numero minimo teorico: " + (int)(Math.pow(2, n) - 1));
    }
}
