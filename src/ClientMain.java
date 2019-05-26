import gui.GUI;
import logic.ClientGame;

public class ClientMain {
    public static void main(String[] args)
    {
        var game = new ClientGame("127.0.0.1", 8081);

        new GUI().run(game);
    }

}
