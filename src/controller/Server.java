package controller;/*
    @author Dasun
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {


    private static ArrayList<ClientHandler>clientHandlerArrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket accpet;

        while (true){
            System.out.println("Waiting for a client");
            accpet = serverSocket.accept();
            System.out.println("New Member Connected");
            ClientHandler clientHandler = new ClientHandler(accpet,clientHandlerArrayList);
            clientHandlerArrayList.add(clientHandler);
            clientHandler.start();
        }

    }
}
