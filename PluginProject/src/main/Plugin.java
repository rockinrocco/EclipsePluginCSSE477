package main;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.WindowPanel;

public abstract class Plugin {
	private String filepath;
	private String name;
	private JLabel statusLabel;
	//What other stuff would we need?

	public Plugin(){
	}
	
	public void installPlugin(){
	}
	
	public abstract JPanel displayPanel();
	
	public abstract void pausePlugin();
	
	public abstract void resumePlugin();
	
	public abstract void killPlugin();
	
	public void runPlugin(){
		WindowPanel.executePlugin(this);
	}
	
	public void updateStatus(String status){
		WindowPanel.setStatus(status);
	}

	public abstract String getName();
}
