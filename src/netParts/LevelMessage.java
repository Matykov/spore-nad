package netParts;

import logic.*;

public class LevelMessage implements IMessage {
    private SectorNet sectors;
    public LevelMessage(NetSectorNet sectors)
    {
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
