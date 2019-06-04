package gui;
import engine.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Menu extends JPanel
{
    public Menu(GUI gui)
    {
        var frame = gui.frame;
        this.setOpaque(true);

//        try {
//
//            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/skins/background.png")))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,0,5,40);
        this.setBackground(Color.BLACK);




        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.insets.top = frame.getHeight() / 10;
        //BufferedImage buttonIcon = ImageIO.read(new File("src/skins/Sector.png"));
        ImageIcon single_but_pic = new ImageIcon("src/skins/buttons/single_mod_but.png");
        ImageIcon single_but_hover_pic = new ImageIcon("src/skins/buttons/single_mod_but_hover.png");
        JButton butSPMode = new JButton(single_but_pic);
        butSPMode.setMargin(new Insets(0, 0, 0, 0));
        butSPMode.setBorder(null);
        butSPMode.setOpaque(false);
        butSPMode.setContentAreaFilled(false);
        butSPMode.setBorderPainted(false);
        butSPMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeSPMode();
            }
        });
        butSPMode.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) {
                 butSPMode.setIcon(single_but_hover_pic);
            }
            public void mouseExited(MouseEvent me) {
                butSPMode.setIcon(single_but_pic);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(butSPMode, gbc);


        ImageIcon multi_but_pic = new ImageIcon("src/skins/buttons/multi_mod_but.png");
        ImageIcon multi_but_hover_pic = new ImageIcon("src/skins/buttons/multi_mod_hover_but.png");
        JButton butMPMode = new JButton(multi_but_pic);
        butMPMode.setBorder(null);
        butMPMode.setOpaque(false);
        butMPMode.setContentAreaFilled(false);
        butMPMode.setBorderPainted(false);
        butMPMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeMPMode();
            }
        });
        butMPMode.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) { butMPMode.setIcon(multi_but_hover_pic);
            }
            public void mouseExited(MouseEvent me) { butMPMode.setIcon(multi_but_pic);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(butMPMode, gbc);
        ImageIcon editor_but_pic = new ImageIcon("src/skins/buttons/editor_but.png");
        ImageIcon edit_but_hover_pic = new ImageIcon("src/skins/buttons/editor_hover_but.png");
        JButton butEdit = new JButton(editor_but_pic);
        butEdit.setBorder(null);
        butEdit.setOpaque(false);
        butEdit.setContentAreaFilled(false);
        butEdit.setBorderPainted(false);
        butEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    gui.invokeEditor();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        butEdit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) { butEdit.setIcon(edit_but_hover_pic);
            }
            public void mouseExited(MouseEvent me) { butEdit.setIcon(editor_but_pic);
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(butEdit, gbc);


        ImageIcon quit_but_pic = new ImageIcon("src/skins/buttons/quit_but.png");
        ImageIcon quit_but_hover_pic = new ImageIcon("src/skins/buttons/quit_hover_but.png");
        JButton butQuit = new JButton(quit_but_pic);
        butQuit.setMargin(new Insets(0, 0, 0, 0));
        butQuit.setBorder(null);
        butQuit.setOpaque(false);
        butQuit.setContentAreaFilled(false);
        butQuit.setBorderPainted(false);
        butQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeClosing();
            }
        });
        butQuit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) { butQuit.setIcon(quit_but_hover_pic);
            }
            public void mouseExited(MouseEvent me) { butQuit.setIcon(quit_but_pic);
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(butQuit, gbc);


        //Проверка на существование файлов внешности и их заполнение
        try {
            if ((!new File(Player.CurrentDir() + "\\colors.txt").exists()) ||
                    (!new File(Player.CurrentDir() + "\\flagella.txt").exists()) ||
                    (!new File(Player.CurrentDir() + "\\spikes.txt").exists())) {
                FileWriter fileBody = new FileWriter(Player.CurrentDir() + "\\colors.txt");
                FileWriter fileFlagella = new FileWriter(Player.CurrentDir() + "\\flagella.txt");
                FileWriter fileSpike = new FileWriter(Player.CurrentDir() + "\\spikes.txt");

                fileBody.write("src/skins/body1but.png");
                fileFlagella.write("src/skins/flagella1A.png");
                fileSpike.write("src/skins/spike1A.png");
                fileBody.flush();
                fileFlagella.flush();
                fileSpike.flush();
            }
        }catch(IOException ioe) {
            System.err.println(ioe.toString());
    }
}
}