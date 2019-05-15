package gui;

import logic.Game;
import logic.ServerGame;

import javax.swing.*;
import java.awt.*;


public class GUI
{
    public JFrame frame;
    private Game game;

    public void run(Game game)
    {
        frame = new JFrame("spore-nad");
        this.game = game;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(true);

        invokeMainMenu();

        frame.setPreferredSize(new Dimension(600, 500));
        frame.pack();
        frame.setVisible(true);
//
//        while(true)
//        {
//            //game.update();
//            frame.repaint();
//        }
    }

    public void invokeSPMode()
    {
        System.out.println("Single Player");

        if (game instanceof ServerGame)
            frame.add(new ServerWindow(frame, (ServerGame)game));
        else
            frame.add(new ClientWindow(frame, (ClientGame)game));
    }

    public void invokeMPMode()
    {
        System.out.println("Multi Player");
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new MultiPlayerMode(this));
        frame.repaint();
        frame.pack();
    }

    private void invokeClosing()
    {
        frame.dispose();
        System.exit(0);
    }

    private void invokeEditor()
    {
        System.out.println("Editor");
    }

    private void invokeNetGame(String text)
    {
        System.out.println(text);
    }

    private void invokeMainMenu()
    {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new Menu(this));
        frame.repaint();
        frame.pack();
    }
}
