package main;

import java.util.HashSet;
import java.util.Set;

import frame.WindowPanel;

public class PluginHandler {
	private HashSet<Plugin> pluginSet;
	public PluginHandler(){
		this.pluginSet = new HashSet<Plugin>();
	}
	public void addPlugin(String filepath) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		JarClassLoader jarLoader = new JarClassLoader (filepath);
		Class c;
		c = jarLoader.loadClass("Plugin",true);
		Object o = c.newInstance();
		if(o instanceof Plugin)
		{
			Plugin plugin = (Plugin) o;
			this.pluginSet.add(plugin);
			WindowPanel.updateListPanel(plugin.getName());
		}
	}
}
