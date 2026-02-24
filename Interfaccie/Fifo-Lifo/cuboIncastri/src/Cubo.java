import java.util.Stack;

public class Cubo {
    private Stack<Forma>[] colonne;
    private final int capacita; // capacità per colonna

    @SuppressWarnings("unchecked")
    public Cubo(int capacita) {
        this.capacita = capacita;
        colonne = new Stack[Forma.values().length];
        for (int i = 0; i < colonne.length; i++) {
            colonne[i] = new Stack<>();
        }
    }

    // Inserimento nella colonna corretta
    public boolean inserisci(Forma forma) {
        int index = forma.getValore();
        if (colonne[index].size() < capacita) {
            colonne[index].push(forma);
            return true;
        } else {
            System.out.println("La colonna " + forma + " è piena!");
            return false;
        }
    }

    // Controlla se tutte le colonne sono piene
    public boolean pieno() {
        for (Stack<Forma> colonna : colonne) {
            if (colonna.size() < capacita) return false;
        }
        return true;
    }

    // Mostra il cubo come tabella a colonne
    public void mostra() {
        System.out.println("\nSTATO DEL CUBO:");
        int maxSize = 0;
        for (Stack<Forma> col : colonne) {
            maxSize = Math.max(maxSize, col.size());
        }

        // intestazione
        for (Forma f : Forma.values()) {
            System.out.printf("%-15s | ", f);
        }
        System.out.println();
        System.out.println("--------------------------------------------------------");

        // stampa righe dall’alto verso il basso
        for (int i = maxSize - 1; i >= 0; i--) {
            for (int j = 0; j < colonne.length; j++) {
                String simbolo = (colonne[j].size() > i) ? simboloForma(colonne[j].get(i)) : "";
                System.out.printf("%-15s | ", simbolo);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Ritorna simbolo per visualizzazione
    private String simboloForma(Forma f) {
        switch (f) {
            case QUADRATO: return "■";
            case TRIANGOLO: return "▲";
            case CERCHIO: return "●";
            case PARALLELOGRAMMA: return "▰";
            default: return "?";
        }
    }
}