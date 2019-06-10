package netParts;

import engine.NetPlayer;
import logic.IMessage;
import logic.IRunOver;
import logic.ServerGame;

public class RegisterPlayerSkinsMessage implements IMessage {
    private NetPlayer player;
    public RegisterPlayerSkinsMessage(NetPlayer player){
        this.player = player;
    }
    @Override
    public void run(IRunOver runOver)
    {
        if(runOver instanceof ServerGame)
        {
            ServerGame game = (ServerGame)runOver;
            game.registerPlayer(player);
        }
    }
}
