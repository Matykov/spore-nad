
package NetParts;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private Socket clientSocket;
    private ServerSocket server;
    private BufferedReader in;
    private BufferedWriter out;
    private LinkedList<ServerClient> clientList;
    private AcceptingThread acceptingThread;

    public Server(int nSocketPort) {
        try{
            server = new ServerSocket(nSocketPort);
            System.out.println("Lock'n  load, waiting for client");
            clientList = new LinkedList<ServerClient>();
            acceptingThread = new AcceptingThread(this);
        } catch (IOException e){
            System.err.println(e);
        }

    }

    public void accept(){
        try {
            clientList.add(new ServerClient(server.accept()));
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
        for(ServerClient client: clientList){
            receivedMessages.add(client.recv());
        }
        return receivedMessages;
    }


}
