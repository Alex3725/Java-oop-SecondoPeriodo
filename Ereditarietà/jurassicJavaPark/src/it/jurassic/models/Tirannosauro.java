package it.jurassic.models;

public class Tirannosauro extends Dinosauro{


    public Tirannosauro(String nome, String specie, int energia) {
        super(nome, specie, energia);
    }

    @Override
    public void emettiVerso() {
        System.out.println("ROAAAR! Il T-Rex ruggisce");
    }

    @Override
    public void mangia(String cibo) {
        System.out.println("Tentativo alimentazione : "+super.getNome()+" mangia "+cibo);
        if (cibo.toLowerCase() != "carne") throw new IllegalArgumentException("Il trex non mangia questa robaccia ;)");
        System.out.println("Pasto completato . Energia aumentata !");
        emettiVerso();
        setEnergia(20);
        System.out.println("------------------------------------");
    }
}
