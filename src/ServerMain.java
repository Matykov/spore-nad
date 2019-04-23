import gui.GUI;
import engine.Levels;
import logic.ServerGame;

public class ServerMain {
    public static void main(String[] args)
    {
        var game = new ServerGame(8081, Levels.getTestNetLevel());
        //game.run();
        GUI.run(game);
    }

}
