package netParts;

import java.io.*;

public class SockRW implements ISocketWriter, ISocketReader {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void read(InputStream stream) throws IOException {

    }

    @Override
    public void onConnectRead(InputStream stream) throws IOException {

    }

    @Override
    public void write(OutputStream stream) throws IOException {

    }

    @Override
    public void onConnectWrite(OutputStream stream) throws IOException {

    }
}
