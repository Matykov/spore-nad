package netParts;

import engine.Level;
import logic.ClientGame;
import logic.IMessage;
import logic.IRunOver;

public class LevelMessage implements IMessage {
    private Level level;
    public LevelMessage(Level level)
    {
        this.level = level;
    }

    @Override
    public void run(IRunOver runOver)
    {
        if(runOver instanceof ClientGame)
        {
            ClientGame game = (ClientGame) runOver;
            game.setLevel(level);
        }
    }
}
