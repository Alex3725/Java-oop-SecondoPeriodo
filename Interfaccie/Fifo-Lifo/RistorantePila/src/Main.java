import java.util.Stack;

public class Main {
    public static void main(String[] args) {


        Stack<Piatto> piatti = new Stack<>();
        int numPiatti = 20;
        //Pino aggiunge i piatti
        for (int i = 0; i < numPiatti; i++) {
            piatti.push(new Piatto(14,TipoPiatto.PIANO));
        }



        if(!piatti.isEmpty()){
            for (int i = 0; i < numPiatti; i++) {
                System.out.println(piatti.peek());
                System.out.println(piatti.removeLast());

            }
        }else {
            System.out.println("non servi gino ");
        }
    }
}
