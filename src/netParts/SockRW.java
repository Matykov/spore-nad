package netParts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SockRW implements ISocketWriter, ISocketReader {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public void read(String incomingMessage) {
        System.out.println(incomingMessage);
    }

    @Override
    public String write() {
        try {
            if (br.ready()) {

                return br.readLine();


            }
        } catch (IOException ignored) {
        }
        return null;
    }
}
