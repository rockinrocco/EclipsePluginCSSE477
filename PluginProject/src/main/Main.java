package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import frame.WindowPanel;

public class Main {
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		PluginHandler pluginHandler = new PluginHandler();

		new WindowPanel(pluginHandler);
		
		String path = ".\\plugins.txt";
		File file = new File(path); 
		if (!file.exists())
		{
			System.out.println("No Plugin File");
			file.createNewFile();
		}else{
			System.out.println("Plugin File Found");
			FileReader fr = new FileReader(path);	
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       System.out.println(line);
			       pluginHandler.addPlugin(line);
			    }
			}
			fr.close();
		}
	}
}
