package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import main.Plugin;
import frame.WindowPanel;

public class PluginHandler {
	public HashSet<Plugin> pluginSet;
	public PluginHandler(){
		this.pluginSet = new HashSet<Plugin>();
	}
	
	/**
	 * Returns true or false based on if the plugin was added
	 * @param filepath
	 * @returns True 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void addPlugin(String filepath) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
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
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(".\\plugins.txt", true)))) {
		    out.println(filepath);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
}
