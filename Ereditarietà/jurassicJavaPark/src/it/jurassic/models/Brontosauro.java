package it.jurassic.models;

public class Brontosauro extends Dinosauro{
    public Brontosauro(String nome, String specie, int energia) {
        super(nome, specie, energia);
    }

    @Override
    public void emettiVerso() {
        System.out.println("Muuuuuu (suono basso e profondo)");
    }

    @Override
    public void mangia(String cibo) {
        System.out.println("Tentativo alimentazione : "+super.getNome()+" mangia "+cibo);
        if (cibo.toLowerCase() != "erba" || cibo.toLowerCase()!= "foglie")throw new IllegalArgumentException("Non sono buone queste cose");
        System.out.println("Pasto completato . Energia aumentata !");
        emettiVerso();
        setEnergia(15);
        System.out.println("------------------------------------");
    }

}
