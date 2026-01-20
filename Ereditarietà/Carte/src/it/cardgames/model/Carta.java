package it.cardgames.model;

public abstract class Carta {
    protected final String nome;

    protected Carta(String nome) { this.nome = nome; }

    public String getNome() { return nome; }
}
