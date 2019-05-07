package netParts;

import engine.Level;
import logic.ClientGame;
import logic.IMessage;
import logic.IRunOver;
import logic.NetPlayer;

public class RegistrationMessage implements IMessage {
    private NetPlayer player;
    private Level level;
    public RegistrationMessage(Level level, NetPlayer player){
        this.level = level;
        this.player = player;
    }
    @Override
    public void run(IRunOver runOver) {
        if (runOver instanceof ClientGame){
            ClientGame game = (ClientGame)runOver;
            game.registerSelf(level, player);
        }
    }
}
