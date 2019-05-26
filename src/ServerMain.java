
import gui.GUI;
import logic.ServerGame;

public class ServerMain {
    public static void main(String[] args)
    {
//        AsyncServer server = new AsyncServer(8989);
//        server.run();
        var game = new ServerGame(8081);
        //game.run();
        new GUI().run(game);
    }

}
