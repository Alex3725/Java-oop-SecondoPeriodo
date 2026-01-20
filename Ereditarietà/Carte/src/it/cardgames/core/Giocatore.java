package it.cardgames.core;

import it.cardgames.model.Carta;
import java.util.ArrayList;
import java.util.List;

public class Giocatore {

    private final String nome;
    private final List<Carta> mano = new ArrayList<>();

    public Giocatore(String nome) { this.nome = nome; }

    public void pesca(Carta carta) { mano.add(carta); }

    public Carta giocaCarta(int index) { return mano.remove(index); }

    public List<Carta> getMano() { return List.copyOf(mano); }

    public int carteInMano() { return mano.size(); }

    public String getNome() { return nome; }
}
