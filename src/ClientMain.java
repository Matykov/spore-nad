import gui.GUI;
import logic.ClientGame;
import logic.Game;
import logic.Levels;

public class ClientMain {
    public static void main(String[] args)
    {
        var game = new ClientGame("127.0.0.1", 8081);

        GUI.run(game);
    }

}
