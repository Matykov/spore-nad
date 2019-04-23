package gui;

import engine.Game;

import javax.swing.*;

public class GUI
{
    public static void run(Game game)
    {
        JFrame frame = new JFrame("spore-nad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(true); //false чтобы нельзя было бы поменять размеры рамки, true -можно
        frame.add(new MainWindow(frame, game));
        frame.setVisible(true);

        while(true)
        {
            game.update();
            frame.repaint();
        }
    }

}
