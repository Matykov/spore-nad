package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel
{
    public Menu(GUI gui)
    {
        var frame = gui.frame;
        setLayout(new GridBagLayout());

        frame.getContentPane().setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.top = frame.getHeight() / 10;
        //BufferedImage buttonIcon = ImageIO.read(new File("src/skins/Sector.png"));
        ImageIcon single_but_pic = new ImageIcon("src/skins/single_mod_but.png");
        ImageIcon single_but_hover_pic = new ImageIcon("src/skins/single_mod_but_hover.png");
        JButton butSPMode = new JButton(single_but_pic);
        butSPMode.setMargin(new Insets(0, 0, 0, 0));
        butSPMode.setBorder(null);
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


        ImageIcon multi_but_pic = new ImageIcon("src/skins/multi_mod_but.png");
        ImageIcon multi_but_hover_pic = new ImageIcon("src/skins/multi_mod_but_hover.png");
        JButton butMPMode = new JButton(multi_but_pic);
        butMPMode.setMargin(new Insets(0, 0, 0, 0));
        butMPMode.setBorder(null);
//        butMPMode.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                gui.invokeMPMode();
//            }
//        });
//        butSPMode.addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent me) {
//                butSPMode.setIcon(single_but_hover_pic);
//            }
//            public void mouseExited(MouseEvent me) {
//                butSPMode.setIcon(single_but_pic);
//            }
//        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(butMPMode, gbc);
        ImageIcon editor_but_pic = new ImageIcon("src/skins/editor_but.png");
        JButton butEdit = new JButton(editor_but_pic);
        butEdit.setMargin(new Insets(0, 0, 0, 0));
        butEdit.setBorder(null);
        butEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeEditor();
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(butEdit, gbc);


        ImageIcon quit_but_pic = new ImageIcon("src/skins/quit_but.png");
        JButton butQuit = new JButton(quit_but_pic);
        butQuit.setMargin(new Insets(0, 0, 0, 0));
        butQuit.setBorder(null);
        butQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeClosing();
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(butQuit, gbc);
    }
}