package com.aliexpress.tool.jcombo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.iharder.dnd.FileDrop;

public class TargetPanel extends JScrollPane {
    public static final long serialVersionUID = 1L;

    private MainWindow window = null;
    private SeedPanel seedPanel = new SeedPanel(this);

    /**
     * 构造函数
     * 
     * @param window 程序主窗口
     */
    public TargetPanel(MainWindow window) {
        this.window = window;
        this.initLayout();
        new FileDrop(this, new SeedDropListener(this));
    }

    /**
     * 初始化面板布局
     */
    private void initLayout() {
        JPanel panel = new JPanel(new BorderLayout());
        this.seedPanel.setLayout(new GridLayout(0, 1));

        panel.add(seedPanel, BorderLayout.NORTH);
        panel.add(this.getNotice(), BorderLayout.SOUTH);
        this.setViewportView(panel);
    }

    public void addItem(String path) {
        String[] paths = this.seedPanel.getPaths();
        boolean hasConflict = false;
        for(int i=0; i<paths.length; i++) {
            if(paths[i].equals(path)) {
                this.seedPanel.updateItem(new SeedItem(path));
                hasConflict = true;
                break;
            }
        }
        if(!hasConflict) {
            this.seedPanel.addItem(new SeedItem(path));
            this.seedPanel.updateUI();
        }
    }

    public String[] getPaths() {
        return this.seedPanel.getPaths();
    }

    private JPanel getNotice() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton importButton = new JButton("Click to add more input files or drag files here");
        importButton.setBackground(Color.LIGHT_GRAY);
        importButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        importButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev) {
                // 使用文件选择器
                JFileChooser fc = new JFileChooser("Import File");

                // 在本程序作为默认选择目录
                fc.setCurrentDirectory(new File(
                    System.getProperty("user.dir")
                ));

                // 选择器附属于主窗口
                int option = fc.showOpenDialog(window);

                // 如果选中稳定并不存在错误, 进行文件处理
                if(option == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    Pattern pattern = Pattern.compile("\\.(css|js)\\.seed$", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(file.getName());
                    if(matcher.find()) {
                        String path = file.getAbsolutePath();
                        addItem(path);
                        updateRoot();
                    }
                }
            }
        });
        panel.add(importButton, BorderLayout.CENTER);

        return panel;
    }

    public void updateRoot() {
        this.window.getActionPanel().updateRoot(seedPanel.getPaths());
    }
}
