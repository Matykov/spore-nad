import gui.*;
import logic.*;

public class Main
{

    public static void main(String[] args)
    {
        var game = new Game(Levels.getTestLevel());

        GUI.run(game);
    }


}
