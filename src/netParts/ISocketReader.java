package netParts;

import java.io.IOException;
import java.io.InputStream;

public interface ISocketReader {
    void read(InputStream stream) throws IOException;
    void onConnectRead(InputStream stream) throws IOException;
}
