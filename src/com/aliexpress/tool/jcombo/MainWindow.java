package com.aliexpress.tool.jcombo;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame {
	public static final long serialVersionUID = 1L;
	public static final String ycomboVersion = "0.1.8";

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
	private static final int MIN_WIDTH = 800;
	private static final int MIN_HEIGHT = 450;

	private TargetPanel targetPanel = new TargetPanel(this);
	private ActionPanel actionPanel = new ActionPanel(this);
    private MessagePanel messagePanel = new MessagePanel();

	public MainWindow() {
		super("JCombo");

		this.setContentPane(getMainPanel());                          // 主面板
		this.pack();
        this.setSize(new Dimension(WIDTH, HEIGHT));                   // 显示尺寸
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));    // 显示的最小尺寸
		this.setLocationRelativeTo(null);                             // 显示在屏幕中间
		this.setVisible(true);                                        // 可见
		this.toFront();                                               // 置顶
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
	}
    
    /**
     * 返回主面板
     */
    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(actionPanel, BorderLayout.NORTH); // 操作面板
        mainPanel.add(targetPanel, BorderLayout.CENTER); // 目标文件列表面板
        mainPanel.add(messagePanel, BorderLayout.SOUTH); // 信息展示面板

        return mainPanel;
    }

	/**
	 * 退出程序
	 */
	public void exit() {
		this.setVisible(false);
		this.dispose();
		System.exit(0);
	}

    public ActionPanel getActionPanel() {
        return this.actionPanel;
    }

	public TargetPanel getTargetPanel() {
	    return this.targetPanel;
	}

    public MessagePanel getMessagePanel() {
        return this.messagePanel;
    }

}
