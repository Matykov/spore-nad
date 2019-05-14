package netParts;

import engine.Level;
import logic.ClientGame;
import logic.IMessage;
import logic.IRunOver;
import logic.SectorNet;

public class LevelMessage implements IMessage {
    private SectorNet sectors;
    public LevelMessage(SectorNet sectors)
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
