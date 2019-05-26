import logic.Game;
import engine.Levels;
import gui.*;

public class Main
{

    public static void main(String[] args)
    {
        var game = new Game(Levels.getTestLevel());

        new GUI().run(game);
        while (true)
            game.update();
    }


}
