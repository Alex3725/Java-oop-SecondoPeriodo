package it.cardgames.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5555);
        System.out.println("Server avviato");
        Socket client = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println("BENVENUTO");
        System.out.println("Client: " + in.readLine());
        server.close();
    }
}
