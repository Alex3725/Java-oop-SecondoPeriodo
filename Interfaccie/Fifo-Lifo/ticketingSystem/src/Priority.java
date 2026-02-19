public enum Priority {
    ALTA(10),
    MEDIA(5),
    BASSA(1);

    public int valore;

    Priority(int valore) {
        this.valore = valore;
    }

    public int getValore() {
        return valore;
    }
}
