package NetParts;

import java.io.*;
import java.net.Socket;

public class ServerClient {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerClient(Socket socket){
        try {
            this.socket = socket;
            this.in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e){
            System.err.println(e);
        }
    }
    public void send(String msg){
        new SendingThread(out, msg);
    }
    public String recv(){
        try{
            return in.readLine();
        }catch (IOException ignored){}
        return "";
    }
}
