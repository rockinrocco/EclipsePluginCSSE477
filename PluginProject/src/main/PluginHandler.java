package main;

import java.util.HashSet;
import java.util.Set;

public class PluginHandler {
	private HashSet<Plugin> pluginSet;
	public PluginHandler(){
		this.pluginSet = new HashSet<Plugin>();
	}
	public void addPlugin(String filepath){
		Plugin newPlugin = new Plugin(filepath);
		newPlugin.installPlugin();
		this.pluginSet.add(newPlugin);
	}
	
}
