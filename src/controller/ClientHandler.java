package controller;/*
    @author Dasun
*/

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    public static ArrayList<ClientHandler> clientHandlerArrayList;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private PrintWriter printWriter;

    public ClientHandler(Socket socket,ArrayList<ClientHandler>clientHandlers){
        try{
            this.socket = socket;
            this.clientHandlerArrayList =clientHandlers;
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter=new PrintWriter(socket.getOutputStream(),true);


        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public void run() {

        try{
            String massage;
            while ((massage = bufferedReader.readLine()) != null) {
                if(massage.equalsIgnoreCase("exit")) {
                    break;
                }

                for(ClientHandler c : clientHandlerArrayList) {
                    c.printWriter.println(massage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
