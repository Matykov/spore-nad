
package netParts.old;

import logger.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server {

    private Socket clientSocket;
    private ServerSocket server;
    //private BufferedReader in;
    //private BufferedWriter out;
    private Queue<ServerClient> clientList;
    private AcceptingThread acceptingThread;
    private Logger logger = new Logger("log.txt");

    public Server(int nSocketPort) {
        try{
            server = new ServerSocket(nSocketPort);
            System.out.println("Lock'n  load, waiting for client");
            logger.log(nSocketPort + " Lock'n  load, waiting for client");
            clientList = new ConcurrentLinkedQueue<>();
            acceptingThread = new AcceptingThread(this);
        } catch (IOException e){
            System.err.println(e);
        }

    }

    public void accept(){
        try {
            ServerClient client = new ServerClient(server.accept());
            clientList.add(client);
            logger.log("incoming connection " + client.getClientInfo());
            //logger.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void sendAll(String msg){
        for(ServerClient client : clientList){
            client.send(msg);
        }
    }

    public List<String> readAll(){
        LinkedList<String> receivedMessages = new LinkedList<String>();
        try {
            for (ServerClient client : clientList) {
                String message = client.recv();
                receivedMessages.add(message);
                logger.log(client.getClientInfo() + ": " + message);
            }
        }catch (IOException ignored){
            //logger.log(ioe.toString());
        }
        return receivedMessages;
    }


}
