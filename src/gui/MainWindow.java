package gui;

import logic.Creature;
import logic.Food;
import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class MainWindow extends JPanel implements ActionListener
{
    Timer timer = new Timer(20, this);

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
                    game.getPlayer().moveY(-5);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    game.getPlayer().moveY(5);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    game.getPlayer().moveX(5);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    game.getPlayer().moveX(-5);
                }
                repaint();
            }
        };
        frame.addKeyListener(keyAdapter);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        drawFood(g);
        drawPlayer(g);
    }

    public void drawCreature(Graphics g)
    {
        Creature creature = game.getPlayer();
        g.setColor(new Color(0, 150, 200));
        g.fillOval(creature.getPosition().x, creature.getPosition().y, creature.getFattiness(), creature.getFattiness());
    }

    public void drawPlayer(Graphics g)
    {
        Creature creature = game.getPlayer();
        g.setColor(new Color(0, 150, 200));
        g.fillOval(creature.getPosition().x, creature.getPosition().y, creature.getFattiness(), creature.getFattiness());
    }

    public void drawFood(Graphics g)
    {
        ArrayList<Food> food = game.getLevel().getMeals();

        Random r = new Random();
        g.setColor(new Color(39, 200, 32));

        for (var group = 0; group < food.size(); group++)
        {
            var pieces = food.get(group).getPieces();
            for (Point p: pieces.keySet())
            {
                g.fillOval(p.x, p.y, pieces.get(p) + 5, pieces.get(p) + 5);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}
