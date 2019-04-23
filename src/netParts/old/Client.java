package netParts.old;

import logger.Logger;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Logger logger = new Logger("log.txt");
    public Client(String ipAddress, int port){
        try {
            this.socket = new Socket(ipAddress, port);
            System.out.println(socket.toString());
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        }catch(IOException ioe){
            logger.log(ioe.toString());
        }
    }
    public InputStream getInputStream(){
        return inputStream;
    }
    public OutputStream getOutputStream(){
        return outputStream;
    }
    public void closeConnection(){
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        }catch(IOException ioe){
            logger.log(ioe.toString());
        }
    }
}