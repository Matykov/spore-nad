package netParts.old;

import logger.Logger;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private Logger logger = new Logger("log.txt");
    public Client(String ipAddress, int port){
        try {
            this.socket = new Socket(ipAddress, port);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch(IOException ioe){
            logger.log(ioe.toString());
        }
    }
    public String recv(){
        try {
            return in.readLine();
        }catch(IOException ioe){
            logger.log(ioe.toString());
        }
        return "";
    }
    public void send(String sendingMsg){
        try{
            if(out == null)
                return;
            out.write(sendingMsg + "\n");
            out.flush();
        }catch(IOException ioe){
            logger.log(ioe.toString());
        }
    }
    public void closeConnection(){
        try {
            in.close();
            out.close();
            socket.close();
        }catch(IOException ioe){
            logger.log(ioe.toString());
        }
    }
}