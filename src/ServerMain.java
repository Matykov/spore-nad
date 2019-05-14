import engine.Levels;
import gui.GUI;
import logic.ServerGame;

public class ServerMain {
    public static void main(String[] args)
    {
//        AsyncServer server = new AsyncServer(8989);
//        server.run();
        var game = new ServerGame(8081, Levels.getTestNetLevel());
        game.run();
        //GUI.run(game);
    }

}
