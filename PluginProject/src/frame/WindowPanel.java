package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import main.Plugin;
import main.PluginHandler;

public class WindowPanel {
	private static HeaderPanel headPanel;
	private static ListPanel listPanel;
	private static ExecutionPanel exPanel;
	private static StatusPanel stPanel;
	private static JLabel statusLabel;
	private static JFrame frame;
	private PluginHandler pluginHandler;
	
	public WindowPanel(PluginHandler pluginHandler){
		this.pluginHandler = pluginHandler;
		frame = new JFrame("Assignment 6");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800,600));
		listPanel = new ListPanel();
		exPanel = new ExecutionPanel();
		stPanel = new StatusPanel();
		headPanel = new HeaderPanel();
		
		
		statusLabel = new JLabel("Current Status");
        stPanel.add(statusLabel, BorderLayout.CENTER);
        frame.getContentPane().add(headPanel, BorderLayout.NORTH);
		frame.getContentPane().add(listPanel, BorderLayout.WEST);
		frame.getContentPane().add(exPanel, BorderLayout.CENTER);
		frame.getContentPane().add(stPanel, BorderLayout.SOUTH);
		
        
        
        
		frame.pack();
		frame.setVisible(true);
	}
	public static void executePlugin(Plugin plugin) {
		//TODO: Something with exPanel
	}
	public static void setStatus(String status){
		statusLabel.setText(status);
	}
	
	
	
	public class HeaderPanel extends JPanel { 
		public HeaderPanel(){
			this.setBackground(Color.white);
			this.setBorder(BorderFactory.createLineBorder(Color.white));
			JButton loadBtn = new JButton("Load Plugin");
			loadBtn.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "Plugin", "txt", "docx");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(frame);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				            chooser.getSelectedFile().getName());
				       pluginHandler.addPlugin(chooser.getSelectedFile().getPath());
				       setStatus("Loading Plugin: "+chooser.getSelectedFile().getPath());
				    }
				}
				
			});
			this.add(loadBtn,BorderLayout.WEST);
		}
	}	
	public class ListPanel extends JPanel { 
		public ListPanel(){
			this.setBackground(Color.white);
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			
		}
	}
	public class StatusPanel extends JPanel { 
		public StatusPanel(){
			this.setBackground(Color.white);
			this.setBorder(BorderFactory.createLineBorder(Color.red));
		}
	}
	public class ExecutionPanel extends JPanel { 
		public ExecutionPanel(){
			this.setBorder(BorderFactory.createLineBorder(Color.blue));
		}
	}
}
