package it.cardgames.uno;

import it.cardgames.model.Mazzo;

public class MazzoUno extends Mazzo<UnoCarta> {
    public MazzoUno() {
        for (ColoreUno colore : ColoreUno.values()) {
            if (colore == ColoreUno.NERO) continue;
            for (int i = 0; i <= 9; i++) carte.add(new UnoCarta(colore, i));
        }
        carte.add(new UnoCarta(TipoCartaUno.JOLLY));
        carte.add(new UnoCarta(TipoCartaUno.PESCA_QUATTRO));
    }
}
