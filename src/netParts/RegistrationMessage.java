package netParts;

import engine.NetPlayer;
import logic.ClientGame;
import logic.IMessage;
import logic.IRunOver;
import logic.NetSectorNet;

public class RegistrationMessage implements IMessage {
    private NetSectorNet sectors;
    private int playerId;
    public RegistrationMessage(NetSectorNet sectors, NetPlayer player){
        this.sectors = sectors;
        this.playerId = player.getId();
    }
    @Override
    public void run(IRunOver runOver) {
        if (runOver instanceof ClientGame){
            ClientGame game = (ClientGame)runOver;
            game.registerSelf(sectors, playerId);
        }
    }
}
