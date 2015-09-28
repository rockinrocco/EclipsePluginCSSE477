package main;

import javax.swing.JLabel;

import frame.WindowPanel;

public class Plugin {
	private String filepath;
	private String name;
	private JLabel statusLabel;
	//What other stuff would we need?
	public Plugin(String filepath){
		this.filepath = filepath;
	}
	public void installPlugin(){
		//TODO Stuff with filepath
	}
	public void runPlugin(){
		WindowPanel.executePlugin(this);
		//TODO Run it in the executePanel
	}
	public void updateStatus(String status){
		WindowPanel.setStatus(status);
	}
}
