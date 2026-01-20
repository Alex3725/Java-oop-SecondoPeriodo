package it.cardgames.persistence;

import java.io.FileWriter;
import java.io.IOException;

public class GameState {

    public static void save(String stato) {
        try (FileWriter fw = new FileWriter("savegame.json")) {
            fw.write("{\"stato\":\"" + stato + "\"}");
        } catch (IOException e) { e.printStackTrace(); }
    }
}
