package netParts;

import netParts.ISocketReader;
import netParts.ISocketWriter;

public interface IServerWorker extends ISocketWriter, ISocketReader {
    void onConnectionReset();
}
