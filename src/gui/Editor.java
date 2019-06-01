package gui;

import logic.Game;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JPanel
{
    //public String colorSelected;

    Game game;

    public Editor(GUI gui, Game game)
    {
        var frame = gui.frame;
        this.game = game;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.top = frame.getHeight() / 10;

        //Цвет клетки
        Label colorLable = new Label("Color");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(colorLable, gbc);

        String[] colors = { "red", "blue", "orange", "green", "yellow"};
        JComboBox colorList = new JComboBox(colors);
        colorList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(colorList, gbc);

        //Жгутики
        Label flagellaLable = new Label("Flagella");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(flagellaLable, gbc);

        String[] flagella = { "1", "2", "3", "4"};
        JComboBox flagellaList = new JComboBox(flagella);
        flagellaList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(flagellaList, gbc);

        //Шипы
        Label spikesLabel = new Label("Spikes");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(spikesLabel, gbc);

        String[] spikes = { "1", "2", "3", "4"};
        JComboBox spikesList = new JComboBox(flagella);
        spikesList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(spikesList, gbc);

        JButton butClose = new JButton("Back to Menu");
        butClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter file = new FileWriter("C:\\Users\\BigBird\\Desktop\\colors.txt");
                    file.write(colorList.getSelectedItem().toString());
                    System.out.println(colorList.getSelectedItem().toString());
                    file.flush();
                }catch(IOException ioe) {
                    System.err.println(ioe.toString());
                    gui.invokeMainMenu();
                }
                gui.invokeMainMenu();
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(butClose, gbc);



        class Preview extends JPanel{
            protected Preview()
            {

            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(game.getPlayer().bodyColor);
                g.fillOval(25, 25, 50, 50);

                g.setColor(Color.black);
                g.drawOval(25, 25, 50, 50);
            }
        }

        var p = new Preview();
        p.setVisible(true);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(p, gbc);

    }

    protected void drawPlayer(Graphics g)
    {
        //g.setColor(new Color(0x842D4E));
        //if (color != null)
            switch ("red") {
                case "red":
                    g.setColor(Color.red);
                    break;
                case "blue":
                    g.setColor(Color.blue);
                    break;
                case "green":
                    g.setColor(Color.green);
                    break;
                case "yellow":
                    g.setColor(Color.yellow);
                    break;
                case "orange":
                    g.setColor(Color.orange);
                    break;
                default:
                    g.setColor(Color.red);

            }
        //else
           // g.setColor(new Color(0x842D4E));


        g.fillOval(300 - 25,
                250 - 25,
                50,
                50);


    }
}
