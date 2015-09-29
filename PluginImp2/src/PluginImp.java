import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import main.Plugin;

public class PluginImp extends Plugin{

	private boolean dontLeave = false;
	@Override
	public JPanel displayPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.add(new JButton("I'm a button!"));
		if(dontLeave){
		panel.add(new JLabel("Dont Leave Me Again."));
		}
		return panel;
	}
	
	public void pausePlugin() {
		dontLeave = true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Start and come back";
	}

	@Override
	public void resumePlugin() {
		// TODO Auto-generated method stub
	}

	@Override
	public void killPlugin() {
		System.out.println("Goodbye");
		
	}

	@Override
	public void runPlugin() {
		// TODO Auto-generated method stub
		
	}

}
