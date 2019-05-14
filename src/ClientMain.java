import gui.GUI;
import logic.ClientGame;

public class ClientMain {
    public static void main(String[] args)
    {
        var game = new ClientGame("10.96.3.4", 8081);

        GUI.run(game);
    }

}
