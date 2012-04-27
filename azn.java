import java.io.*;
import java.net.*;
import java.util.Random;


public class azn {	
	static Random rand = new Random(System.nanoTime());
	
	public static String random() {
		String r = ""; 
		try {
			URL url = new URL("http://10.1.1.4/kdb/dump.php");
			BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()));
			String s;
			int l = 1;
			
			while ((s = input.readLine()) != null) {
				if (rand.nextInt(l++) == 1) {
					r = s;
				}
			}
		}
		catch (MalformedURLException e) {}
		catch (IOException e) {}
		
		return r.split("'")[1];
	}
		
}
