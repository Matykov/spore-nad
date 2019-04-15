import netParts.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientCheck {

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello World!");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Client client1 = new Client("localhost", 8081);
        Client client2 = new Client("0.0.0.0", 8081);
        while(true) {
            String inLine = br.readLine();
            if (inLine.equals("stop"))
                break;
            client1.send(inLine);
            //client2.send(inLine);
        }
        client1.closeConnection();
        client2.closeConnection();
    }
}
