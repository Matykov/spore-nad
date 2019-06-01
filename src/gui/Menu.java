package gui;

//import java.awt.image;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Menu extends JPanel
{
    public Menu(GUI gui)
    {
        var frame = gui.frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.top = frame.getHeight() / 10;
        //BufferedImage buttonIcon = ImageIO.read(new File("src/skins/Sector.png"));
        JButton butSPMode = new JButton("Single Player Mode");
        butSPMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeSPMode();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(butSPMode, gbc);

        JButton butMPMode = new JButton("Multi Player Mode");
        butMPMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeMPMode();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(butMPMode, gbc);

        JButton butEdit = new JButton("Creature Editor");
        butEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeEditor();
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(butEdit, gbc);

        JButton butClose = new JButton("Quit");
        butClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeClosing();
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(butClose, gbc);
    }
}