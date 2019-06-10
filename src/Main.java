import gui.*;

import javax.swing.*;
import java.util.concurrent.Semaphore;


public class Main
{
    public static void main(String[] args)
    {
        while(true)
        {
            Semaphore semaphore = new Semaphore(1);
            var gui = new GUI(semaphore);
            try {
                semaphore.acquire();
                var game = gui.getGame();
                boolean gameEnded = game.update();
                while (!gameEnded)
                {
                    gameEnded = game.update();
                    gui.repaint();
                }
                gui.frame.dispose();
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
        }
    }

}
