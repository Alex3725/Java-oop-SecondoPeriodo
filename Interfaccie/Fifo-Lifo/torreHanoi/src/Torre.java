import java.util.Stack;

public class Torre {
    private String nome;
    private Stack<Disco> pila;

    public Torre(String nome) {
        this.nome = nome;
        pila = new Stack<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }

    public Disco peek() {
        return pila.peek();
    }

    public Disco pop() {
        return pila.pop();
    }

    public void push(Disco d) {
        pila.push(d);
    }

    public int size() {
        return pila.size();
    }

    public void stampa() {
        System.out.println(nome + ": " + pila);
    }
}
