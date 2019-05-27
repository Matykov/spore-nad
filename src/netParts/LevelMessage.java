package netParts;

import engine.Player;
import logic.*;

public class LevelMessage implements IMessage {
    private NetSectorNet sectors;
    public LevelMessage(NetSectorNet sectors) {
        this.sectors = sectors;
    }

    @Override
    public void run(IRunOver runOver)
    {
        if(runOver instanceof ClientGame)
        {
            ClientGame game = (ClientGame) runOver;
            game.setSectorNet(sectors);
        }
    }
}
