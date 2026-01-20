package it.cardgames.briscola;

import it.cardgames.model.Mazzo;

public class MazzoBriscola extends Mazzo<BriscolaCarta> {
    public MazzoBriscola() {
        for (SemeBriscola seme : SemeBriscola.values()) {
            for (ValoreBriscola val : ValoreBriscola.values()) {
                carte.add(new BriscolaCarta(seme, val));
            }
        }
    }
}
