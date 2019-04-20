import gui.GUI;
import logic.Levels;
import logic.ServerGame;

public class ServerMain {
    public static void main(String[] args)
    {
        var game = new ServerGame(8081, Levels.getTestNetLevel());

        GUI.run(game);
    }

}
