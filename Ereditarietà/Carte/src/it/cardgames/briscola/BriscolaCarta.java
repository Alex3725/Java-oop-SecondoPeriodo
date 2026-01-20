package it.cardgames.briscola;

import it.cardgames.model.Carta;

public class BriscolaCarta extends Carta {
    private final SemeBriscola seme;
    private final ValoreBriscola valore;

    public BriscolaCarta(SemeBriscola seme, ValoreBriscola valore) {
        super(valore.name() + " di " + seme.name());
        this.seme = seme;
        this.valore = valore;
    }

    public ValoreBriscola getValore() { return valore; }
    public SemeBriscola getSeme() { return seme; }
}
