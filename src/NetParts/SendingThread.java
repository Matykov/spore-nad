package NetParts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SendingThread extends Thread{
    private BufferedWriter out;
    //private Socket socket;
    private String message;

    public SendingThread(BufferedWriter out, String message) {
            this.out = out;
            this.message = message;
            start();
    }
    @Override
    public void run(){
        try {
            out.write(message);
            out.flush();
        } catch(IOException ignored){}
    }

}
