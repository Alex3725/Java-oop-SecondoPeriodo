package it.jurassic.main;

import it.jurassic.models.Brontosauro;
import it.jurassic.models.Dinosauro;
import it.jurassic.models.Tirannosauro;

import java.util.*;



public class Main {
    public static void main(String[] args) {
        Brontosauro b1 = new Brontosauro("Devid","pocoSigma",10);
        Brontosauro b2 = new Brontosauro("Ismail","cane",0);
        Tirannosauro t1 = new Tirannosauro("Espo","pocoSigma",20);
        Tirannosauro t2 = new Tirannosauro("More","sigma",67);

        ArrayList<Dinosauro>listaDino = new ArrayList<>(
                Arrays.asList(b1,b2,t1,t2)
        );

        ArrayList<String>listaCibo = new ArrayList<>(
                Arrays.asList("carne","erba","chiodi")
        );

        try {
            listaDino.get(1).emettiVerso();
            listaDino.get(1).stato();
            listaDino.get(1).mangia("erba");
            listaDino.get(1).mangia("carne");

            listaDino.get(2).emettiVerso();
            listaDino.get(2).stato();
            listaDino.get(2).mangia("erba");
            listaDino.get(2).mangia("carne");


        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
