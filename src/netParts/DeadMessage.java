package netParts;

import logic.ClientGame;
import logic.IMessage;
import logic.IRunOver;

public class DeadMessage implements IMessage {
    @Override
    public void run(IRunOver runOver)
    {
        if (runOver instanceof ClientGame)
        {
            ClientGame game = (ClientGame) runOver;
            game.setGameEnded();
        }
    }
}
