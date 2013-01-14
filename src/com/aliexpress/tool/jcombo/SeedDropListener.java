package com.aliexpress.tool.jcombo;


import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.iharder.dnd.FileDrop;

public class SeedDropListener implements FileDrop.Listener {

    private TargetPanel target = null;

    public SeedDropListener(TargetPanel target) {
        this.target = target;
    }

    public void filesDropped(File[] files) {
        for(File file : files) {
            Pattern pattern = Pattern.compile("\\.(css|js)\\.seed$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(file.getName());
            if(matcher.find()) {
                this.target.addItem(file.getAbsolutePath());
            }
        }
        this.target.updateRoot();
    }
}
