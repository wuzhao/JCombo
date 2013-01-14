package com.aliexpress.tool.jcombo;


import java.util.ArrayList;

import javax.swing.JPanel;


public class SeedPanel extends JPanel {
    public static final long serialVersionUID = 1L;

    private ArrayList<String> paths = new ArrayList<String>();
    private TargetPanel parent = null;

    public SeedPanel(TargetPanel parent) {
        this.parent = parent;
    }

    public void removeItem(SeedItem item) {
        super.remove(item);

        for(int i=0; i<paths.size(); i++) {
            if(this.paths.get(i).equals(item.getText())) {
                this.paths.remove(i);
                break;
            }
        }
    }

    public void addItem(SeedItem item) {
        super.add(item);
        this.paths.add(item.getText());
    }

    public void updateItem(SeedItem item) {
        for(int i=0; i<this.paths.size(); i++) {
            if(this.paths.get(i).equals(item.getText())) {
                this.paths.set(i, item.getText());
                break;
            }
        }
    }

    public String[] getPaths() {
        return this.paths.toArray(new String[this.paths.size()]);
    }

    public TargetPanel getParent() {
        return this.parent;
    }
}
