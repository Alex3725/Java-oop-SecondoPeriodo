package it.cardgames.uno;

import it.cardgames.model.Carta;

public class UnoCarta extends Carta {
    private final ColoreUno colore;
    private final TipoCartaUno tipo;
    private final int numero;

    public UnoCarta(ColoreUno colore, int numero) {
        super(colore + " " + numero);
        this.colore = colore;
        this.tipo = TipoCartaUno.NUMERO;
        this.numero = numero;
    }

    public UnoCarta(TipoCartaUno tipo) {
        super(tipo.name());
        this.colore = ColoreUno.NERO;
        this.tipo = tipo;
        this.numero = -1;
    }

    public TipoCartaUno getTipo() { return tipo; }
    public ColoreUno getColore() { return colore; }
}
