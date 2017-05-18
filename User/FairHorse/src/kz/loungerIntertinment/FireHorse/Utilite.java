package kz.loungerIntertinment.FireHorse;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utilite {
	
	
	public static void notificate(String title, String message) {
		showMessgae(title, message);
		playSound("/awp1_93.wav");
	}
	
	private static void showMessgae(String title, String message) {
		
		final JFrame frame = new JFrame(title);
		frame.setBackground(Color.WHITE);
		frame.setType(Type.UTILITY);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((int)screenSize.getWidth() - 250, (int) screenSize.getHeight() - 150, 250, 100);
		frame.setVisible(true);
		JLabel label = new JLabel(message, SwingConstants.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(label);
		
		Timer autoHideTimer = new Timer(3000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		
		autoHideTimer.start();
		
	}


	
	private static void playSound( String filename ){
	    Clip in = null;

	    try{
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream( Utilite.class.getClass().getResource( filename ) );
	        in = AudioSystem.getClip();
	        in.open( audioIn );
	        in.start();
	    }
	    catch( Exception e ){
	        e.printStackTrace();
	    }

	}
	
	
	public  static List<String>  loadFile( String filename ){
		   
		InputStream in = Utilite.class.getClass().getResourceAsStream(filename); 		
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		List<String> properties= new ArrayList<String>();
		String line = null;
		try {
			while((line = reader.readLine()) != null){
				properties.add(line);
			}
		} catch (IOException e) {
			return null;
		}
		
		try {
			in.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		//System.out.println(properties);
	    return properties;
	}
	
	
	public static String trnslate( String russianLetters){
  		Map<String, String> table = new HashMap<String, String>();
		table.put("а","a");   table.put("А","A");
		table.put("б","b");   table.put("Б","B");
		table.put("в","v");   table.put("В","V");
		table.put("г","g");   table.put("Г","G");
		table.put("д","d");   table.put("Д","D");
		table.put("е","e");   table.put("Е","E");
		table.put("ж","zh");  table.put("Ж","Zh");
		table.put("з","z");   table.put("З","Z");
		table.put("и","i");   table.put("И","I");
		table.put("й","i");   table.put("Й","I");
		table.put("к","k");   table.put("К","K");
		table.put("л","l");   table.put("Л","L");
		table.put("м","m");   table.put("М","M");
		table.put("н","n");   table.put("Н","N");
		table.put("о","o");   table.put("О","O");
		table.put("п","p");   table.put("П","P");
		table.put("р","r");   table.put("Р","R");
		table.put("с","s");   table.put("С","C");
		table.put("т","t");   table.put("Т","T");
		table.put("у","u");   table.put("У","U");
		table.put("ф","f");   table.put("Ф","F");
		table.put("х","h");   table.put("Х","H");
		table.put("ц","c");   table.put("Ц","C");
		table.put("ч","ch");  table.put("Ч","Ch");
		table.put("ш","sh");  table.put("Ш","Sh");
		table.put("щ","sh");  table.put("Щ","Sh");
		table.put("ъ","'");   table.put("Ъ","'");
		table.put("ы","i");   table.put("Ы","I");
		table.put("ь","'");   table.put("Ь","'");
		table.put("э","e");   table.put("Э","E");
		table.put("ю","y");   table.put("Ю","Y");
		table.put("я","ya");  table.put("Я","YA");
		
		for (int i = 0; i < russianLetters.length(); i++) {
			
			char character = russianLetters.charAt(i);

			if ((1103 < character) || (character < 1040)) continue;
			String first = "" + character;
			String second = table.get("" + russianLetters.charAt(i));
			
			russianLetters = russianLetters.replaceAll(first, second);
		} 
	
	    return russianLetters;
	}
	
	
}




