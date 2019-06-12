package gui;

import engine.Player;
import logic.Game;

import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


//Класс эдитора
public class Editor extends JPanel

{
    static String bodySelected = "src/skins/body1.png";
    static String flagellaSelected = "src/skins/flagella1Abut.png";
    static String spikeSelected = "src/skins/spike1A.png";
    static JFrame frame;


    public Editor(GUI gui, Game game){

        frame = gui.frame;
        this.setBackground(Color.BLACK);
        var frame = gui.frame;

        //Рисуем игрока ._.
        Preview prev = new Preview();
        frame.add(prev).setLocation(350,300);
        frame.add(prev).setSize(600,600);
        frame.add(prev).setBackground(Color.BLACK);

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

        ImageIcon body1 = new ImageIcon("src/skins/body1but.png");
        ImageIcon body2 = new ImageIcon("src/skins/body2but.png");
        ImageIcon body3 = new ImageIcon("src/skins/body3but.png");
        ImageIcon body4 = new ImageIcon("src/skins/body4but.png");

        ImageIcon[] colors = {
                body1, body2, body3, body4
        };

        BufferedReader readerBody;
        String skinBody = "src/skins/body1but.png";
        try {
            FileReader file = new FileReader(Player.CurrentDir() + "colors.txt");
            readerBody = new BufferedReader(file);
            skinBody = readerBody.readLine();
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

        JComboBox colorList = new JComboBox(colors);

        colorList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(colorList, gbc);
        MyItemListener actionListener1 = new MyItemListener();
        colorList.addItemListener(actionListener1);

        switch (skinBody){
            case "src/skins/body1but.png":
                colorList.setSelectedItem(body1);
                break;
            case "src/skins/body2but.png":
                colorList.setSelectedItem(body2);
                break;
            case "src/skins/body3but.png":
                colorList.setSelectedItem(body3);
                break;
            case "src/skins/body4but.png":
                colorList.setSelectedItem(body4);
                break;
        }

        colorList.setPreferredSize(new Dimension(25,30));



        //Жгутики
        JLabel flagellaLable = new JLabel(new ImageIcon("src/skins/labels/flagellaLabel.png"));

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(flagellaLable, gbc);

        ImageIcon flagella1 = new ImageIcon("src/skins/flagella1Abut.png");
        ImageIcon flagella2 = new ImageIcon("src/skins/flagella2Abut.png");
        ImageIcon flagella3 = new ImageIcon("src/skins/flagella3Abut.png");
        ImageIcon flagella4 = new ImageIcon("src/skins/flagella4Abut.png");


        ImageIcon[] flagella = {
               flagella1, flagella2, flagella3, flagella4
        };

        JComboBox flagellaList = new JComboBox(flagella);


        BufferedReader readerFlagella;
        String skinFlagella = "src/skins/flagella1Abut.png";
        try {
            FileReader file = new FileReader(Player.CurrentDir() + "flagella.txt");
            readerFlagella = new BufferedReader(file);
            skinFlagella = readerFlagella.readLine();
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

        flagellaList.setPreferredSize(new Dimension(25,30));
        flagellaList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(flagellaList, gbc);

        MyItemListener actionListener2 = new MyItemListener();
        flagellaList.addItemListener(actionListener2);

        switch (skinFlagella){
            case "src/skins/flagella1Abut.png":
                flagellaList.setSelectedItem(flagella1);
                break;
            case "src/skins/flagella2Abut.png":
                flagellaList.setSelectedItem(flagella2);
                break;
            case "src/skins/flagella3Abut.png":
                flagellaList.setSelectedItem(flagella3);
                break;
            case "src/skins/flagella4Abut.png":
                flagellaList.setSelectedItem(flagella4);
                break;
        }


        //Шипы
        JLabel spikesLabel = new JLabel(new ImageIcon("src/skins/labels/spikesLabel.png"));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(spikesLabel, gbc);

        ImageIcon spike1 = new ImageIcon("src/skins/spike1Abut.png");
        ImageIcon spike2 = new ImageIcon("src/skins/spike2Abut.png");
        ImageIcon spike3 = new ImageIcon("src/skins/spike3Abut.png");
        ImageIcon spike4 = new ImageIcon("src/skins/spike4Abut.png");


        ImageIcon[] spikes = {
                spike1, spike2, spike3, spike4
        };


        JComboBox spikesList = new JComboBox(spikes);

        BufferedReader readerSpike;
        String skinSpike = "src/skins/spike1Abut.png";
        try {
            FileReader file = new FileReader(Player.CurrentDir() + "spikes.txt");
            readerSpike = new BufferedReader(file);
            skinSpike = readerSpike.readLine();
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }


        spikesList.setPreferredSize(new Dimension(55,30));
        spikesList.setEditable(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(spikesList, gbc);
        MyItemListener actionListener3 = new MyItemListener();
        spikesList.addItemListener(actionListener3);

        switch (skinSpike){
            case "src/skins/spike1Abut.png":
                spikesList.setSelectedItem(spike1);
                break;
            case "src/skins/spike2Abut.png":
                spikesList.setSelectedItem(spike2);
                break;
            case "src/skins/spike3Abut.png":
                spikesList.setSelectedItem(spike3);
                break;
            case "src/skins/spike4Abut.png":
                spikesList.setSelectedItem(spike4);
                break;
        }


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

        //Запись параметров внешности в файл при выходе из редактора
        backBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter fileBody = new FileWriter("src\\playerIni\\colors.txt");
                    FileWriter fileFlagella = new FileWriter("src\\playerIni\\flagella.txt");
                    FileWriter fileSpike = new FileWriter("src\\playerIni\\spikes.txt");
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

        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            g = (Graphics2D)g;

            try {
                BufferedImage body = ImageIO.read(new File(Editor.bodySelected.replaceAll("but", "")));
                BufferedImage eye = ImageIO.read(new File("src\\skins\\eye.png"));
                BufferedImage flagellaL = ImageIO.read(new File(Editor.flagellaSelected.replaceAll("but", "")));
                BufferedImage flagellaR = ImageIO.read(new File(Editor.flagellaSelected.replaceAll("but", "").replaceAll("A", "B")));
                BufferedImage spikeL = ImageIO.read(new File(Editor.spikeSelected.replaceAll("but", "")));
                BufferedImage spikeR = ImageIO.read(new File(Editor.spikeSelected.replaceAll("but", "")));

                g.drawImage(body, 30,0, 100,100, null);
                g.drawImage(eye, 60,10, 36,36, null);

                //Типа анимация, ок?
                if (System.currentTimeMillis() % 400 > 200)
                    g.drawImage(flagellaL, 65, 80, 30, 50, null);

                else
                    g.drawImage(flagellaR, 65, 80, 30, 50, null);

                var oldForm = ((Graphics2D) g).getTransform();
                AffineTransform partsAT = (AffineTransform) (oldForm.clone());
                Point pos = new Point(50, 50);
                partsAT.rotate(-Math.PI/4, pos.x, pos.y);
                //partsAT.translate(pos.x, pos.y);
                ((Graphics2D) g).setTransform(partsAT);
                g.drawImage(spikeL, 45,-15, null);
                partsAT.rotate(Math.PI/2, pos.x, pos.y);
                ((Graphics2D) g).setTransform(partsAT);
                g.drawImage(spikeR, 55,-55, null);
                ((Graphics2D) g).setTransform(oldForm);

            } catch (IOException e) {
                e.printStackTrace();
            }

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


