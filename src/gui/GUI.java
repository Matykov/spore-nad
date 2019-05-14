package gui;

import logic.Game;

import javax.swing.*;
import java.awt.*;


public class GUI
{
    public JFrame frame;

    public void run(Game game)
    {
        frame = new JFrame("spore-nad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(true);

        invokeMainMenu();

//
//        if (game instanceof ServerGame)
//            frame.add(new ServerWindow(frame, (ServerGame)game));
//        else
//            frame.add(new ClientWindow(frame, (ClientGame)game));
//
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
    }

    public void invokeMPMode()
    {
        System.out.println("Multi Player");
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new MultiPlayerMode(this));
        frame.repaint();
        frame.pack();
    }

    public void invokeClosing()
    {
        frame.dispose();
        System.exit(0);
    }

    public void invokeEditor()
    {
        System.out.println("Editor");
    }

    public void invokeNetGame(String text)
    {
        System.out.println(text);
    }

    public void invokeMainMenu()
    {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new Menu(this));
        frame.repaint();
        frame.pack();
    }
}
