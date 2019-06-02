import gui.GUI;
import logic.ClientGame;

public class ClientMain {
    public static void main(String[] args)
    {
        var game = new ClientGame("10.96.0.9", 8081);

//        var gui = new GUI();
//        gui.run(game);
        while(true){
            game.update();
//            gui.repaint();
            try {
                Thread.sleep(30);
            }catch(InterruptedException ie){
                System.out.println("Interrupted");
            }
        }
    }

}
