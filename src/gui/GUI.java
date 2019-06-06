package gui;

import engine.Levels;
import logic.ClientGame;
import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;


public class GUI
{

    public JFrame frame;
    private Game game;
    private  Semaphore semaphore;
    public Menu menu;

    public GUI(Semaphore semaphore)
    {
        this.semaphore = semaphore;
        frame = new JFrame("spore-nad");
        this.game = game;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(600, 500));
        frame.pack();
        frame.setVisible(true);
        try {
            semaphore.acquire();
        }catch(InterruptedException ie){
            System.out.println(ie);
        }
        invokeMainMenu();
    }

    public void invokeSPMode()
    {
        game = new Game(Levels.getTestLevel());
        frame.getContentPane().removeAll();
        frame.add(new ClientWindow(frame, game));
        frame.requestFocus();
        frame.pack();
        semaphore.release();
    }

    public void invokeMPMode()
    {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(new MultiPlayerMode(this));
        frame.repaint();
        frame.requestFocus();
        frame.pack();
        //semaphore.release();
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
        //semaphore.release();
        //System.out.println(frame.getKeyListeners()[0].toString());
        frame.requestFocus();
        frame.repaint();
        frame.pack();
    }


    public void invokeNetGame(String text)
    {
        var split = text.split(":");
        try {
            game = new ClientGame(split[0], Integer.parseInt(split[1]));
        }catch(IndexOutOfBoundsException ioobe){
            System.out.println("Do not forget to specify the port");
            return;
        }catch(NumberFormatException nfe){
            System.out.println("Port must be int value");
            return;
        }
        if(game != null) {
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException ie){
                System.out.println(ie);
            }
            frame.getContentPane().removeAll();
            frame.add(new ClientClientWindow(frame, (ClientGame) game));
            frame.requestFocus();
            frame.pack();
            semaphore.release();
        }
    }

    public void invokeMainMenu() {
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

    public Game getGame(){
        return game;
    }
}
