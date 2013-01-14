package com.aliexpress.tool.jcombo;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeedItem extends JPanel {
    public static final long serialVersionUID = 1L;

    private JLabel textLabel = new JLabel();
    private String text = "";

    public SeedItem(String text) {
        this.text = text;

        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);

        JLabel textLabel = this.textLabel;
        textLabel.setText(this.text);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(textLabel, c);
        this.add(textLabel);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev) {
                dispose();
            }
        });
        c.gridx = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(removeButton, c);
        this.add(removeButton);

        this.textLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY), BorderFactory.createEmptyBorder(10, 15, 10, 15)));
    }

    public void setText(String text) {
        this.textLabel.setText(text);
    }

    public String getText() {
        return this.textLabel.getText();
    }

    public void dispose() {
        SeedPanel parent = (SeedPanel)this.getParent();
        parent.removeItem(this);
        parent.revalidate();
        parent.repaint();

        parent.getParent().updateRoot();
    }
}
