package netParts;

import java.io.IOException;
import java.io.OutputStream;

public interface ISocketWriter {
    void write(OutputStream stream) throws IOException;
    void onConnectWrite(OutputStream stream) throws IOException;
}
