package logic;

import java.io.Serializable;

public interface IMessage extends Serializable {
    void run(IRunOver runOver);
}
