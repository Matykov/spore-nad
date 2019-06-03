package gui;

import engine.Player;
import logic.Game;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor{

    public Editor(GUI gui, Game game){
        new DrawEditor(gui, game).start();
        new DrawPlayer(gui.frame.getGraphics()).start();
    }
}

class MyEditor extends JPanel
{
    static  String colorSelected;
    static JFrame frame;


    public MyEditor(GUI gui, Game game){
        frame.getContentPane().add(new MyEditor(gui, game));
        frame = gui.frame;
        var frame = gui.frame;
        //this.game = game;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.top = frame.getHeight() / 10;

        //Цвет клетки
        Label colorLable = new Label("Color");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(colorLable, gbc);

        String[] colors = { "1", "2", "3", "4", "5"};
        JComboBox colorList = new JComboBox(colors);
        colorList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(colorList, gbc);
        MyItemListener actionListener = new MyItemListener();
        colorList.addItemListener(actionListener);

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
                    FileWriter file = new FileWriter(Player.CurrentDir() + "\\colors.txt");
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

        Preview prev = new Preview(MyEditor.frame.getGraphics());
    }

}

//Класс для реакции при выборе нового предемета в выпадающем списке
class MyItemListener implements ItemListener {
    public void itemStateChanged(ItemEvent evt) {
        JComboBox colorList = (JComboBox) evt.getSource();

        Object item = evt.getItem();

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            MyEditor.colorSelected = item.toString();
            System.out.println(MyEditor.colorSelected);

        } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
        }
    }
}


//Класс для отрисовки игрока в редакторе
class Preview extends JPanel {
    protected Preview(Graphics g) {
        while (true) {

            String skin;
            if (MyEditor.colorSelected == "1") {
                skin = "body.png";
            } else {
                skin = "eye.png";
            }

            try {
                BufferedImage im = ImageIO.read(new File("src/skins/" + skin));
                g.drawImage(im, 200, 200, 200, 200, null);
            } catch (IOException ioe) {
                System.err.println(ioe.toString());
            }
        }


    }
}

class DrawEditor extends Thread {
    GUI gui;
    Game game;
    DrawEditor(GUI g, Game ga){
        gui = g;
        game = ga;

    }

    public void run(){

        MyEditor editor = new MyEditor(gui, game);
    }
}

class DrawPlayer extends Thread {
    Graphics graph;
    DrawPlayer(Graphics g){
        graph = g;
    }

    public void run(){

        Preview prev = new Preview(graph);
    }
}

//Старые черновики, пусть будут

//
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.setColor(Color.green);
//                g.fillOval(25, 25, 50, 50);
//
//                g.setColor(Color.black);
//                g.drawOval(25, 25, 50, 50);
//            }
//        }

//        var p = new Preview();
//        p.setVisible(true);
//        gbc.gridx = 1;
//        gbc.gridy = 5;
//        add(p, gbc);

//    }

//    protected void drawPlayer(Graphics g){
//        String skin;
//        if (colorSelected == "1"){
//            skin = "body.png";
//        }
//        else{
//            skin = "eye.png";
//        }
//        BufferedReader reader;
//
//        try {
//            BufferedImage im = ImageIO.read(new File("src/skins/eye.png"));
//            g.drawImage(im, 200, 200, 200, 200, null);
//        }
//        catch(IOException ioe) {
//            System.err.println(ioe.toString());
//        }
//
//
//    }
//}


