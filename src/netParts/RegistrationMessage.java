package netParts;

import engine.Level;
import logic.*;

public class RegistrationMessage implements IMessage {
    private NetPlayer player;
    private SectorNet sectors;
    public RegistrationMessage(SectorNet sectors, NetPlayer player){
        this.sectors = sectors;
        this.player = player;
    }
    @Override
    public void run(IRunOver runOver) {
        if (runOver instanceof ClientGame){
            ClientGame game = (ClientGame)runOver;
            game.registerSelf(sectors, player);
        }
    }
}
