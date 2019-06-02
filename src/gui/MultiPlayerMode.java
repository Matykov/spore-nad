package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiPlayerMode extends JPanel
{
    public MultiPlayerMode(GUI gui)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets.top = gui.frame.getHeight() / 10;

        JLabel label = new JLabel("Server IP: ");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(label);

        JTextField text = new JTextField("127.0.0.1:8081");
        gbc.gridy = 2;
        add(text);

        JButton butOK = new JButton("Play");
        butOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gui.invokeNetGame(text.getText());
            }
        });
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(butOK, gbc);

        JButton butBack = new JButton("Back");
        butBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back");
                gui.invokeMainMenu();
            }
        });
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets.bottom = 0;
        add(butBack, gbc);
    }
}
