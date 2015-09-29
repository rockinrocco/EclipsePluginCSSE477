package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import main.Plugin;

import frame.WindowPanel;

public class PluginHandler {
	public HashSet<Plugin> pluginSet;
	public PluginHandler(){
		this.pluginSet = new HashSet<Plugin>();
	}
	public void addPlugin(String filepath) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		JarClassLoader jarLoader = new JarClassLoader (filepath);
		Class c = jarLoader.loadClass("PluginImp",true);
		Object o = c.newInstance();
		if(o instanceof Plugin)
		{
			Plugin plugin = (Plugin) o;
			this.pluginSet.add(plugin);
			WindowPanel.listaddButton(plugin);
		}
	}
	
	public void writeFilePathToText(String filepath) throws IOException{
		File fout = new File(".\\plugins.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		osw.write(filepath);
	 	osw.close();
	}
}
