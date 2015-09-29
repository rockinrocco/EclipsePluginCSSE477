import javax.swing.JButton;
import javax.swing.JPanel;

import main.Plugin;

public class PluginImp extends Plugin{

	@Override
	public JPanel displayPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.add(new JButton());
		return panel;
	}
	
	public void pausePlugin();

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Test Plugin";
	}

	@Override
	public void resumePlugin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void killPlugin() {
		// TODO Auto-generated method stub
		
	}

}
