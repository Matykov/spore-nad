package gui;

import logic.Creature;
import logic.Food;
import logic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends JPanel implements ActionListener
{
    Timer timer = new Timer(10, this);

    private JFrame frame;
    private Game game;
    private KeyAdapter keyAdapter;

    public MainWindow(JFrame frame, Game game)
    {
        timer.start();
        game.update();
        this.frame = frame;
        this.game = game;
        this.keyAdapter = new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    game.getPlayer().move(-5);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    game.getPlayer().move(5);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    game.getPlayer().turn(Math.PI / 8);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    game.getPlayer().turn(-Math.PI / 8);
                }
                repaint();
            }
        };
        frame.addKeyListener(keyAdapter);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawPlayer(g);
        drawFood(g);

    }

    public void drawCreature(Graphics g)
    {
        Creature creature = game.getPlayer();
        g.setColor(new Color(0, 150, 200));
        g.fillOval(creature.getPosition().x - creature.getFattiness(),
                creature.getPosition().y - creature.getFattiness(),
                creature.getFattiness() * 2,
                creature.getFattiness() * 2);
    }

    private void drawPlayer(Graphics g)
    {

        Creature creature = game.getPlayer();
        BufferedImage bi;
        try
        {
            bi = ImageIO.read(new File("src/skins/player.png"));
            Graphics2D g2d = (Graphics2D)g;
            g2d.rotate(creature.getDirection());
            g2d.drawImage(bi, creature.MapLocation.x, creature.MapLocation.y , null);
            g2d.rotate(-creature.getDirection());
            g2d.setColor(new Color(201, 42, 21));
            g2d.drawOval(creature.getPosition().x - creature.getFattiness(), creature.getPosition().y -creature.getFattiness(), creature.getFattiness() * 2, creature.getFattiness() * 2);
        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening player skin");
        }
    }

    public void drawFood(Graphics g)
    {
        ArrayList<Food> food = game.getLevel().getMeals();

        g.setColor(new Color(39, 200, 32));

        for (var group = 0; group < food.size(); group++)
        {
            var pieces = food.get(group).getPieces();
            for (Point p: pieces.keySet())
            {
                g.fillOval(p.x - pieces.get(p), p.y - pieces.get(p), pieces.get(p) + 5, pieces.get(p) + 5);
                //g.drawLine(p.x - pieces.get(p), p.y - pieces.get(p), game.getPlayer().getPosition().x, game.getPlayer().getPosition().y);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}
