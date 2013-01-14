package com.aliexpress.tool.jcombo;


import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;


public class MessagePanel extends JScrollPane {
    public static final long serialVersionUID = 1L;

    private JEditorPane htmlPanel = new JEditorPane();

    public MessagePanel() {
        this.htmlPanel.setContentType("text/html");
        this.setViewportView(this.htmlPanel);
        this.setPreferredSize(new Dimension((int)this.getSize().getWidth(), 200));
    }

    public void setText(String text) {
        this.htmlPanel.setText(text);
    }

    public String getText() {
        return this.htmlPanel.getText();
    }

    public void removeAll() {
        this.htmlPanel.removeAll();
    }
}
