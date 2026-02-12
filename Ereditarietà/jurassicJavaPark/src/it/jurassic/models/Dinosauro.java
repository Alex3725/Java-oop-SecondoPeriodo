package it.jurassic.models;

public abstract class Dinosauro {
    private String nome;
    private String specie;
    private int energia;


    public Dinosauro(String nome, String specie, int energia) {
        this.nome = nome;
        this.specie = specie;
        this.energia = energia;
    }
    public abstract void emettiVerso();
    public abstract void mangia(String cibo);

    public void stato(){
        System.out.println("Nome: "+ nome+ "Specie: "+specie+" Energia: "+energia);
    }

    public String getNome() {
        return nome;
    }

    protected void setNome(String nome) {
        this.nome = nome;
    }

    public String getSpecie() {
        return specie;
    }

    protected void setSpecie(String specie) {
        this.specie = specie;
    }

    public int getEnergia() {
        return energia;
    }

    protected void setEnergia(int energia) {
        this.energia += energia;
    }
}
