package com.aliexpress.tool.jcombo;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tutiez.java.WiderDropDownCombo;



public class ActionPanel extends JPanel {
	public static final long serialVersionUID = 1L;

	private MainWindow window = null;
	private JTextField lineBreakTextField = new JTextField();
    private JComboBox<String> charsetComboBox = new JComboBox<String>(new String[]{"UTF-8", "GBK"});
    private WiderDropDownCombo rootComboBox = new WiderDropDownCombo();
    private JCheckBox nocompressCheckBox = new JCheckBox("nocompress");
    private JCheckBox verboseCheckBox = new JCheckBox("verbose");
    private JCheckBox nomungeCheckBox = new JCheckBox("nomunge");
    private JCheckBox preserveSemiCheckBox = new JCheckBox("preserve-semi");
    private JCheckBox disableOptiomizationsCheckBox = new JCheckBox("disable-optiomizations");
    private JButton comboButton = new JButton("Combo!");

	/**
	 * 构造函数
	 * @param window	程序主窗口
	 */
	public ActionPanel(MainWindow window) {
		this.window = window;
        this.initLayout();
	}

    /**
     * 初始化面板布局
     */
    private void initLayout() {
        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);

        JPanel controlTopPanel = this.getControlTopPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.8;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(controlTopPanel, c);
        this.add(controlTopPanel);

        JPanel controlBottomPanel = this.getControlBottomPanel();
        c.gridy = GridBagConstraints.RELATIVE;
        gridbag.setConstraints(controlBottomPanel, c);
        this.add(controlBottomPanel);

        JButton comboButton = this.comboButton;
        comboButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev) {
                combo();
            }
        });

        c.gridx = GridBagConstraints.RELATIVE;
        c.gridheight = 2;
        c.weightx = 0.2;
        c.fill = GridBagConstraints.BOTH;
        gridbag.setConstraints(comboButton, c);
        this.add(comboButton);
    }

    private void combo() {
        MessagePanel messagePanel = this.window.getMessagePanel();
        TargetPanel targetPanel = this.window.getTargetPanel();
        String[] paths = targetPanel.getPaths();

        if(paths.length > 0) {
            ArrayList<String> list = new ArrayList<String>();
    
            String lineBreak = this.lineBreakTextField.getText();
            if(lineBreak.length() > 0) {
                list.add("--line-break " + lineBreak);
            }
    
            String charset = this.charsetComboBox.getSelectedItem().toString();
            if(charset.length() > 0) {
                list.add("--charset " + charset);
            }

            String root = this.rootComboBox.getSelectedItem().toString();
            if(this.rootComboBox.getItemCount() > 1 && root.length() > 0 && !root.equals("[Automatic]")) {
                list.add("--root " + root);
            }
    
            boolean nocompress = this.nocompressCheckBox.isSelected();
            if(nocompress) {
                list.add("--nocompress");
            }
    
            boolean verbose = this.verboseCheckBox.isSelected();
            if(verbose) {
                list.add("--verbose");
            }
    
            boolean nomunge = this.nomungeCheckBox.isSelected();
            if(nomunge) {
                list.add("--nomunge");
            }
    
            boolean preserveSemi = this.preserveSemiCheckBox.isSelected();
            if(preserveSemi) {
                list.add("--preserve-semi");
            }
    
            boolean disableOptiomizations = this.disableOptiomizationsCheckBox.isSelected();
            if(disableOptiomizations) {
                list.add("--disable-optiomizations");
            }
    
            String runString = "java -jar " + System.getProperty("user.dir") + File.separator + "lib" + File.separator + "ycombo-" + MainWindow.ycomboVersion + ".jar";
    
            String argString = "";
            for(int i=0; i<list.size(); i++) {
                argString += " " + list.get(i);
            }

            String pathString = "";
            boolean filesAreExist = true;
            for(int i=0; i<paths.length; i++) {
                String path = paths[i];
                if(!new File(path).exists()) {
                    messagePanel.setText("<div style='margin:10px;'>" + this.wrapMessage("[ERROR] File " + path + " is not found!", "error") + "</div>");
                    filesAreExist = false;
                    break;
                }
                pathString += " " + path;
            }

            if(filesAreExist) {
                try {
                    String message = "<div style='border-left:2px solid #00FF00;padding:0 8px;margin:5px 0;color:#008800'>[INFO] Start YCombo with arguments: " + argString + pathString + "</div>";
                    messagePanel.setText("<div style='margin:10px;'>" + this.wrapMessage(message) + "</div>");
        
                    long startTime = System.currentTimeMillis();
                    Process proc = Runtime.getRuntime().exec(runString + argString + pathString);
    
                    BufferedReader br = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
                    StringBuilder sb = new StringBuilder();
                    String c;
                    while((c = br.readLine()) != null) {
                        sb.append(c);
                    }
                    br.close();

                    String errorMessage = "";
                    String[] errors = sb.toString().split("\\[ERROR\\]");
                    for(int i=1; i<errors.length; i++) {
                        errorMessage += this.wrapMessage("[ERROR]" + errors[i], "error");
                    }
    
                    long processTime = System.currentTimeMillis() - startTime;
                    messagePanel.setText("<div style='margin:10px;'>" + message + errorMessage + this.wrapMessage("[INFO] YCombo finished in " + String.format("%.3f", Double.valueOf(processTime)/1000) + " seconds.") + "</div>");
        
                } catch(IOException ex) {
                    System.out.println(ex);
                }
            }

        } else {
            messagePanel.setText("<div style='margin:10px;'>" + this.wrapMessage("[ERROR] No input files.", "error") + "</div>");
        }
    }

    private String wrapMessage(String message) {
        return this.wrapMessage(message, "info");
    }

    private String wrapMessage(String message, String type) {
        String fontColor = "#008800";
        String borderColor = "#00FF00";
        if(type.equals("error")) {
            fontColor = "#CC3300";
            borderColor = "#FF0000";
        }
        return "<div style='border-left:2px solid " + borderColor + ";padding:0 8px;margin:5px 0;color:" + fontColor + "'>" + message + "</div>";
    }

    private JPanel getControlTopPanel() {
        JPanel panel = new JPanel();

        JTextField lineBreakTextField = this.lineBreakTextField;
        lineBreakTextField.setPreferredSize(new Dimension(60, (int)lineBreakTextField.getPreferredSize().getHeight()));
        panel.add(new JLabel("line-break"));
        panel.add(lineBreakTextField);

        panel.add(new JLabel("charset"));
        panel.add(this.charsetComboBox);

        panel.add(new JLabel("root"));
        this.rootComboBox.setPreferredSize(new Dimension(300, (int)rootComboBox.getPreferredSize().getHeight()));
        this.rootComboBox.setEnabled(false);
        this.rootComboBox.addItem("[Automatic]");
        panel.add(this.rootComboBox);

        return panel;
    }

    private JPanel getControlBottomPanel() {
        JPanel panel = new JPanel();

        panel.add(this.nocompressCheckBox);
        panel.add(this.verboseCheckBox);
        panel.add(this.nomungeCheckBox);
        panel.add(this.preserveSemiCheckBox);
        panel.add(this.disableOptiomizationsCheckBox);

        return panel;
    }

    public void updateRoot(String[] paths) {
        this.rootComboBox.removeAllItems();

        if(paths.length <= 0) {
            this.rootComboBox.addItem("[Automatic]");
            this.rootComboBox.setEnabled(false);

        } else {
            this.rootComboBox.addItem("[Automatic]");

            String[] dirNodes = paths[0].split("\\\\|\\/");
            String rootDir = "";

            for(int i=0; i<dirNodes.length; i++) {
                node: {
                    rootDir += dirNodes[i] + File.separator;
                    boolean isRealRoot = true;
    
                    for(int j=0; j<paths.length; j++) {
                        if(paths[j].indexOf(rootDir) != 0) {
                            isRealRoot = false;
                            break node;
                        }
                    }

                    if(isRealRoot) {
                        this.rootComboBox.addItem(rootDir);
                    }
                }
            }

            if(this.rootComboBox.getItemCount() > 1) {
                this.rootComboBox.setEnabled(true);
            } else {
                this.rootComboBox.setEnabled(false);
            }
        }
        this.rootComboBox.setWide(true);
        this.rootComboBox.updateUI();
    }
	
}
