package gui;

import logic.Creature;
import logic.Food;
import logic.Game;
import logic.NetPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

    public void drawPlayer(Graphics g)
    {
        for(var creature:game.getLevel().getCreatures()) {
            if (creature instanceof NetPlayer && ((NetPlayer) creature).isActive())
            {
                g.setColor(new Color(0, 150, 200));
                g.drawImage(new ImageIcon("src/skins/player.png").getImage(),
                        creature.getPosition().x - creature.getFattiness(),
                        creature.getPosition().y - creature.getFattiness(),
                        creature.getFattiness() * 2,
                        creature.getFattiness() * 2,
                        null);
            }
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
