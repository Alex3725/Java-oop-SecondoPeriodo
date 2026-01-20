package it.cardgames.net;

import java.io.*;
import java.net.Socket;

public class GameClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5555);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println(in.readLine());
        out.println("PRONTO A GIOCARE");
        socket.close();
    }
}
