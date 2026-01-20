package it.cardgames.briscola;

public enum ValoreBriscola {
    ASSO(11, 10), TRE(10, 9), RE(4, 8), CAVALLO(3, 7), FANTE(2, 6),
    SETTE(0, 5), SEI(0, 4), CINQUE(0, 3), QUATTRO(0, 2), DUE(0, 1);

    private final int punti, forza;
    ValoreBriscola(int punti, int forza) { this.punti = punti; this.forza = forza; }
    public int getPunti() { return punti; }
    public int getForza() { return forza; }
}
