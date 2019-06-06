package gui;

import engine.Player;
import logic.Game;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


//Класс эдитора
public class Editor extends JPanel

{

    static String part;
    static String bodySelected = "src/skins/body1.png";
    static String flagellaSelected = "src/skins/flagella1Abut.png";
    static String spikeSelected = "src/skins/spike1A.png";
    static JFrame frame;




    public Editor(GUI gui, Game game){

        //prev.paintComponent(frame.getGraphics());
        frame = gui.frame;
        this.setBackground(Color.BLACK);




        var frame = gui.frame;
        Preview prev = new Preview(frame.getGraphics());
        frame.add(prev).setLocation(350,300);
        frame.add(prev).setSize(600,600);
        frame.add(prev).setBackground(Color.BLACK);


        //this.game = game;
        //this.setOpaque(true);
        //this.setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.top = frame.getHeight() / 10;

        //Цвет клетки
        JLabel colorLable = new JLabel(new ImageIcon("src/skins/labels/bodyLabel.png"));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5,0,5,40);
        add(colorLable, gbc);

        ImageIcon[] colors = {
                new ImageIcon("src/skins/body1but.png"),
                new ImageIcon("src/skins/body2but.png"),
                new ImageIcon("src/skins/body3but.png"),
                new ImageIcon("src/skins/body4but.png")
        };
        JComboBox colorList = new JComboBox(colors);
        colorList.setPreferredSize(new Dimension(25,30));

        colorList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(colorList, gbc);
        MyItemListener actionListener1 = new MyItemListener();
        colorList.addItemListener(actionListener1);

        //Жгутики
        JLabel flagellaLable = new JLabel(new ImageIcon("src/skins/labels/flagellaLabel.png"));

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(flagellaLable, gbc);

        ImageIcon[] flagella = {
                new ImageIcon("src/skins/flagella1Abut.png"),
                new ImageIcon("src/skins/flagella2Abut.png"),
                new ImageIcon("src/skins/flagella3Abut.png"),
                new ImageIcon("src/skins/flagella4Abut.png")
        };
        JComboBox flagellaList = new JComboBox(flagella);
        flagellaList.setPreferredSize(new Dimension(25,30));
        flagellaList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(flagellaList, gbc);
        MyItemListener actionListener2 = new MyItemListener();
        flagellaList.addItemListener(actionListener2);

        //Шипы
        JLabel spikesLabel = new JLabel(new ImageIcon("src/skins/labels/spikesLabel.png"));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(spikesLabel, gbc);

        ImageIcon[] spikes = {
                new ImageIcon("src/skins/spike1Abut.png"),
                new ImageIcon("src/skins/spike2Abut.png"),
                new ImageIcon("src/skins/spike3Abut.png"),
                new ImageIcon("src/skins/spike4Abut.png")
        };

        JComboBox spikesList = new JComboBox(spikes);
        spikesList.setPreferredSize(new Dimension(55,30));
        spikesList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(spikesList, gbc);
        MyItemListener actionListener3 = new MyItemListener();
        spikesList.addItemListener(actionListener3);


        gbc.gridx = 1;
        gbc.gridy = 4;
        JLabel empty = new JLabel("   \n");
        empty.setPreferredSize(new Dimension(1, 100));
        add(empty, gbc);

        ImageIcon back_but_pic = new ImageIcon("src/skins/buttons/back_but.png");
        ImageIcon back_but_hover_pic = new ImageIcon("src/skins/buttons/back_hover_but.png");
        JButton backBut = new JButton(back_but_pic);
        backBut.setBorder(null);
        backBut.setOpaque(false);
        backBut.setContentAreaFilled(false);
        backBut.setBorderPainted(false);
        backBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter fileBody = new FileWriter(Player.CurrentDir() + "\\colors.txt");
                    FileWriter fileFlagella = new FileWriter(Player.CurrentDir() + "\\flagella.txt");
                    FileWriter fileSpike = new FileWriter(Player.CurrentDir() + "\\spikes.txt");
                    fileBody.write(colorList.getSelectedItem().toString());
                    fileFlagella.write(flagellaList.getSelectedItem().toString());
                    fileSpike.write(spikesList.getSelectedItem().toString());
                    System.out.println(spikesList.getSelectedItem().toString());
                    fileBody.flush();
                    fileFlagella.flush();
                    fileSpike.flush();
                }catch(IOException ioe) {
                    System.err.println(ioe.toString());
                    gui.invokeMainMenu();
                }
                gui.invokeMainMenu();
            }
        });



        backBut.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) { backBut.setIcon(back_but_hover_pic);
            }
            public void mouseExited(MouseEvent me) { backBut.setIcon(back_but_pic);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(backBut, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;


    }

    public static class Preview extends JPanel {
        Preview(Graphics gr){
            paintComponent(gr);
        }

        public void paintComponent(Graphics g) {
            // Always call super.paintComponent (g):
            super.paintComponent(g);


            // drawString() is a Graphics method.
            // Draw the string "Hello World" at location 100,100
            g.setColor(new Color(0x136B21));
            //g.drawOval(0,0,60,60);
            try {
                BufferedImage body = ImageIO.read(new File(Editor.bodySelected.replaceAll("but", "")));
                BufferedImage eye = ImageIO.read(new File("src\\skins\\eye.png"));
                BufferedImage flagella = ImageIO.read(new File(Editor.flagellaSelected.replaceAll("but", "")));
                BufferedImage spikeL = ImageIO.read(new File(Editor.spikeSelected.replaceAll("but", "")));
                BufferedImage spikeR = ImageIO.read(new File(Editor.spikeSelected.replaceAll("but", "").replaceAll("A","B")));

                g.drawImage(body, 30,0, 100,100,
                        null);
                g.drawImage(eye, 60,10, 36,36, null);
                g.drawImage(flagella, 65,80, 30,50, null);
                g.drawImage(spikeL, 15,-10, 30,50, null);
                g.drawImage(spikeR, 115,-10, 30,50, null);
                //System.out.println("j");
                //BufferedImage body = ImageIO.read(new File(Editor.bodySelected.replaceAll("but", "")));
                //BufferedImage body = ImageIO.read(new File(Editor.bodySelected.replaceAll("but", "")));
                //подожди) сейчас, я проверю, что тут не так, можешь пока почиллить)
                //так, ну вроде все




                System.out.println("!");
            } catch (IOException e) {
                e.printStackTrace();
            }


            // Let's find out when paintComponent() is called.
            //System.out.println("Inside paintComponent");
            super.repaint();
        }
    }
}

//Класс для реакции при выборе нового предемета в выпадающем списке
//Позволяет рисовать актуаьные детали игрока
class MyItemListener implements ItemListener {

    public void itemStateChanged(ItemEvent evt) {
        JComboBox myBox = (JComboBox)evt.getSource();

        Object item = evt.getItem();

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (item.toString().contains("body")) {
                Editor.bodySelected = item.toString();

            }
            else if (item.toString().contains("flagella")){
                Editor.flagellaSelected = item.toString();

            }
            else if (item.toString().contains("spike")){
                Editor.spikeSelected = item.toString();

            }

        } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
        }
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


