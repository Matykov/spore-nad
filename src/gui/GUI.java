package gui;

import engine.Game;
import logic.ClientGame;
import logic.ServerGame;
//import netParts.old.Client;
//import netParts.old.Server;

import javax.swing.*;

public class GUI
{
    public static void run(Game game)
    {
        JFrame frame = new JFrame("spore-nad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(true);

        if (game instanceof ServerGame)
            frame.add(new ServerWindow(frame, (ServerGame)game));
        else if(game instanceof  ClientGame)
            frame.add(new MainWindow(frame, (ClientGame) game));
        else
            frame.add(new MainWindow(frame, game));

        frame.setVisible(true);

        while(true)
        {
            if(!(game instanceof ClientGame))
                game.update();
            //System.out.println("update gui");
            frame.repaint();
        }
    }

}
