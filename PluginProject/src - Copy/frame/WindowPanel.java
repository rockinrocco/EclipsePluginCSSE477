package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	public Plugin curPlugin;
	
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
		exPanel.add(plugin.displayPanel());
		exPanel.repaint();
	}
	
	public static void setStatus(String status){
		statusLabel.setText(status);
	}
	
	
	
	public class HeaderPanel extends JPanel { 
		public HeaderPanel(){
			this.setBackground(Color.white);
			this.setBorder(BorderFactory.createLineBorder(Color.white));
//			JTextField field = new JTextField("<Plugin Class>");
//			this.add(field,BorderLayout.WEST);
			JButton loadBtn = new JButton("Load Plugin");
			loadBtn.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        ".jar", "jar");
				    chooser.setFileFilter(filter);
				    int returnVal = chooser.showOpenDialog(frame);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				            chooser.getSelectedFile().getName());
				       String filePath = chooser.getSelectedFile().getPath();
				       try {
				    	   pluginHandler.addPlugin(filePath);
				       } catch (InstantiationException e1) {
				    	   // TODO Auto-generated catch block
				    	   e1.printStackTrace();
				       } catch (IllegalAccessException e1) {
				    	   // TODO Auto-generated catch block
				    	   e1.printStackTrace();
				       } catch (ClassNotFoundException e1) {
				    	   // TODO Auto-generated catch block
				    	   e1.printStackTrace();
				       }
				       setStatus("Loading Plugin: "+filePath);
				       try {
						pluginHandler.writeFilePathToText(filePath);
				       } catch (IOException e) {
				    	   // TODO Auto-generated catch block
				    	   e.printStackTrace();
				       }
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

		public void addButton(Plugin plugin) {
			JButton del = new JButton("X");
			JButton loadBtn = new JButton(plugin.getName());
			loadBtn.addActionListener(new ListClickAction(plugin));
			JPanel m = new JPanel();
			del.addActionListener(new ListDelAction(plugin,this,m));
			m.setBorder(BorderFactory.createLineBorder(Color.black));
			m.add(loadBtn,BorderLayout.WEST);
			m.add(del,BorderLayout.WEST);
			this.add(m);
		}
	}
	public class ListDelAction implements ActionListener{
		Plugin plugin;
		JPanel big;
		JPanel little;
		public ListDelAction(Plugin plugin,JPanel big,JPanel little){
			this.plugin = plugin;
			this.big = big;
			this.little = little;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String path = plugin.getFilepath();
			File inputFile = new File(".\\plugins.txt");
			File tempFile = new File("tmp.txt");

			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(inputFile));
			} catch (FileNotFoundException e1) {
				return;
			}
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter(tempFile));
			} catch (IOException e1) {
				return;
			}
			
			String currentLine;

			try {
				while((currentLine = reader.readLine()) != null) {
				    // trim newline when comparing with lineToRemove
				    String trimmedLine = currentLine.trim();
				    if(trimmedLine.equals(path)){
				    	continue;
				    }
				    writer.write(currentLine + System.getProperty("line.separator"));
				}
			} catch (IOException e) {
				return;
			}
			try {
				writer.close();
			} catch (IOException e) {
				return;
			} 
			try {
				reader.close();
			} catch (IOException e) {
				return;
			}
			if(inputFile.delete()){
    		}else{
    		}
			boolean successful = tempFile.renameTo(inputFile);
			big.remove(little);
			big.repaint();
		}
	}
	public class ListClickAction implements ActionListener{
		Plugin plugin;
		public ListClickAction(Plugin plugin){
			this.plugin = plugin;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(this.plugin.getName());
			exPanel.startNewPlugin(this.plugin);
		}
	}
	
	
	public class StatusPanel extends JPanel { 
		public StatusPanel(){
			this.setBackground(Color.white);
			this.setBorder(BorderFactory.createLineBorder(Color.red));
		}
	}
	
	public class ExecutionPanel extends JPanel { 
		private Plugin currentPlugin;
		
		public ExecutionPanel(){
			this.setBorder(BorderFactory.createLineBorder(Color.blue));
		}
		
		public void startNewPlugin(Plugin newPlugin){
			if(newPlugin != currentPlugin){
			if(currentPlugin != null){
				currentPlugin.pausePlugin();
			}
			this.removeAll();
			if(newPlugin.isActive){
				newPlugin.resumePlugin();
			} else {
			newPlugin.runPlugin();
			newPlugin.isActive = true;
			}
			add(newPlugin.displayPanel());
			setVisible(true);
			validate();
			repaint();
			currentPlugin = newPlugin;
		}
		}
	}
	
	public static void updateListPanel(String name) {
		System.out.println(name);
	}
	public static void listaddButton(Plugin plugin) {
		listPanel.addButton(plugin);
	}
}
