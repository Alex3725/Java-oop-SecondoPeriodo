package it.cardgames.model.banco;

import it.cardgames.model.Carta;
import it.cardgames.model.Mazzo;

public class Banco {

    private final Mazzo<? extends Carta> mazzo;

    public Banco(Mazzo<? extends Carta> mazzo) {
        this.mazzo = mazzo;
        mazzo.mescola();
    }

    public Carta pesca() { return mazzo.pesca(); }
}
