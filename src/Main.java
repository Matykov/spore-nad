import gui.*;

import java.util.concurrent.Semaphore;


public class Main
{
    public static void main(String[] args)
    {
        Semaphore semaphore = new Semaphore(1);
        var gui = new GUI(semaphore);
        try {
            semaphore.acquire();
            var game = gui.getGame();
            while (true) {
                game.update();
                gui.repaint();
            }
        }catch (InterruptedException ie){
            System.out.println(ie);
        }
    }


}
