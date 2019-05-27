package netParts;

import logic.*;

public class RegistrationMessage implements IMessage {
    private NetPlayer player;
    private NetSectorNet sectors;
    private int playerId;
    public RegistrationMessage(NetSectorNet sectors, NetPlayer player){
        this.sectors = sectors;
        this.player = player;
        this.playerId = player.getId();
    }
    @Override
    public void run(IRunOver runOver) {
        if (runOver instanceof ClientGame){
            ClientGame game = (ClientGame)runOver;
            game.registerSelf(sectors, player, playerId);
        }
    }
}
