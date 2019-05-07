package netParts;

import logic.IMessage;
import logic.IRunOver;
import logic.NetPlayer;
import logic.ServerGame;

public class PlayerMessage implements IMessage {
    private NetPlayer player;
    public PlayerMessage(NetPlayer player){
        this.player = player;
    }
    @Override
    public void run(IRunOver runOver) {
        if(runOver instanceof ServerGame){
            ServerGame game = (ServerGame)runOver;
            game.getLevel().setPlayer(player);
            game.renewCreatures();
        }

    }
}
