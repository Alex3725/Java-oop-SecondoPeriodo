package it.cardgames.uno;

public enum TipoCartaUno {
    NUMERO(0), SALTA(0), INVERTI(0), PESCA_DUE(2), JOLLY(0), PESCA_QUATTRO(4);

    private final int carteDaPescare;
    TipoCartaUno(int carteDaPescare) { this.carteDaPescare = carteDaPescare; }
    public int getCarteDaPescare() { return carteDaPescare; }
}
