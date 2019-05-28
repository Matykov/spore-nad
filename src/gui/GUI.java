package gui;

import logic.ClientGame;
import logic.Game;
import logic.ServerGame;

import javax.swing.*;
import java.awt.*;


public class GUI
{

    public JFrame frame;
    private Game game;
    public Menu menu;

    public void run(Game game)
    {
        frame = new JFrame("spore-nad");
        this.game = game;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(600, 500));
        frame.pack();
        frame.setVisible(true);

        invokeSPMode();
        //invokeMainMenu();
    }

    public void invokeSPMode()
    {
        //frame.getContentPane().remove(this.menu);
        frame.getContentPane().removeAll();
        if(!(game instanceof ServerGame))
            frame.add(new ClientClientWindow(frame, (ClientGame) game));
        else
            frame.add(new ServerWindow(frame, (ServerGame)game));
        //game.getPlayer().getNewColor();
        //frame.requestFocus();
        //frame.requestFocus();
        frame.pack();
    }

    public void invokeMPMode()
    {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new MultiPlayerMode(this));
        frame.repaint();
        frame.requestFocus();
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
        //frame.getContentPane().remove(this.menu);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new Editor(this, game));
        //System.out.println(frame.getKeyListeners()[0].toString());
        frame.requestFocus();
        frame.repaint();
        frame.pack();
    }


    public void invokeNetGame(String text)
    {
        System.out.println(text);
    }

    public void invokeMainMenu()
    {
        frame.getContentPane().removeAll();
        this.menu = new Menu(this);
        frame.getContentPane().add(this.menu);
        frame.repaint();
        frame.pack();

    }

    public void repaint()
    {
        frame.repaint();
    }
}
