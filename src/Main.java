import logic.Game;
import engine.Levels;
import gui.*;

public class Main
{

    public static void main(String[] args)
    {
        var game = new Game(Levels.getTestLevel());
        var gui = new GUI();
        gui.run(game);


        while (true) {
            game.update();
            gui.repaint();
        }
    }


}
