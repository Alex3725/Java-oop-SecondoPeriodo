import classe.Docente;
import classe.Persona;
import classe.Studente;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Persona> listaPersone = new ArrayList<>();

            Docente d1 = new Docente("MArio","Rossi",40,2);
            Docente d2 = new Docente("Antonio","Magni",50,3);
            Docente d3 = new Docente("David","Mistri",30,1);

            Studente st1 = new Studente("Fabio","Esposito",17,"4BI",200);
            Studente st2 = new Studente("Cristian","Cocci",17,"4BI",2);
            Studente st3 = new Studente("Giulio","Verde",17,"4BI",10);

            listaPersone.addAll(List.of(d1, d2, d3, st1, st2, st3));
            System.out.println("---------Prima ondata voti---------");
            for (Persona p : listaPersone) {
                System.out.println(p);
            }
            for (Persona p : listaPersone) {
                if (p instanceof Studente s){
                    s.studia();
                }
                else if (p instanceof Docente d){
                    for (Persona pe : listaPersone){
                        if (!(pe instanceof Studente s))continue;
                        d.interroga(s);
                    }
                }
            }
            System.out.println("--------Seconda ondata voti---------");
            for (Persona p : listaPersone) {
                System.out.println(p);
            }
            for (Persona p : listaPersone) {
                if (p instanceof Studente s){
                    s.studia();
                }
                else if (p instanceof Docente d){
                    for (Persona pe : listaPersone){
                        if (!(pe instanceof Studente s))continue;
                        d.interroga(s);
                    }
                }
            }
            System.out.println("--------Seconda ondata voti---------");
            for (Persona p : listaPersone) {
                System.out.println(p);
            }


        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
