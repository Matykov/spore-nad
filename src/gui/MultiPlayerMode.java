package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MultiPlayerMode extends JPanel
{
    public MultiPlayerMode(GUI gui)

    {
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //gbc.insets.top = gui.frame.getHeight() / 10;
        gbc.insets = new Insets(5,0,5,150);
        JLabel label = new JLabel(new ImageIcon("src/skins/labels/serverLabel.png"));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(label);

        JTextField text = new JTextField("127.0.0.1:8081");
        gbc.gridy = 2;
        add(text);


        ImageIcon play_but_pic = new ImageIcon("src/skins/buttons/play_but.png");
        ImageIcon play_but_hover_pic = new ImageIcon("src/skins/buttons/play_hover_but.png");
        JButton  butOK = new JButton(play_but_pic);
        butOK .setBorder(null);
        butOK .setOpaque(false);
        butOK .setContentAreaFilled(false);
        butOK .setBorderPainted(false);
        butOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeNetGame(text.getText());
            }
        });
        butOK.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) { butOK.setIcon(play_but_hover_pic);
            }
            public void mouseExited(MouseEvent me) { butOK.setIcon(play_but_pic);
            }
        });
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(butOK, gbc);

        ImageIcon back_but_pic = new ImageIcon("src/skins/buttons/back_but.png");
        ImageIcon back_but_hover_pic = new ImageIcon("src/skins/buttons/back_hover_but.png");
        JButton butBack = new JButton(back_but_pic);
        butBack.setBorder(null);
        butBack.setOpaque(false);
        butBack.setContentAreaFilled(false);
        butBack.setBorderPainted(false);
        butBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gui.invokeMainMenu();
            }
        });

        butBack.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent me) { butBack.setIcon(back_but_hover_pic);
            }
            public void mouseExited(MouseEvent me) { butBack.setIcon(back_but_pic);
            }
        });
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets.bottom = 0;
        add(butBack, gbc);
    }
}
