package it.cardgames.uno;

public enum ColoreUno {
    ROSSO("#E53935"), GIALLO("#FDD835"), VERDE("#43A047"), BLU("#1E88E5"), NERO("#000000");

    private final String hex;
    ColoreUno(String hex) { this.hex = hex; }
    public String getHex() { return hex; }
}
