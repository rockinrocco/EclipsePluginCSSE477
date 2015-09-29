import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import main.Plugin;

public class PluginImp extends Plugin{

	String[] urls = {"http://i1105.photobucket.com/albums/h353/moviefreak2010/SwaggerCat_Hate-1.jpg",
			"http://treasure.diylol.com/uploads/post/image/617367/resized_jim-lahey-meme-generator-relax-let-the-liquor-do-the-thinking-d41d8c.jpg",
			"https://s-media-cache-ak0.pinimg.com/236x/b5/0c/cf/b50ccf2a38e09ffb25633f2bc97da562.jpg"};
	int count = 0;
	@Override
	public JPanel displayPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		URL img;
		try {
			img = new URL(urls[count]);
			ImageIcon image = new ImageIcon(img);
			JLabel label = new JLabel("", image, JLabel.CENTER);
			panel.add(label);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return panel;
	}
	
	public void pausePlugin() {
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Motivation";
	}

	@Override
	public void resumePlugin() {
		count = (count + 1) % urls.length;
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
