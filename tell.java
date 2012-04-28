import java.io.*;
import java.util.ArrayList;

public class tell {
	public static String time(double ms) {
		String ts;
		int sec = (int) (ms / 1000);
		int min = sec / 60;
		int h = min / 60;
		int d = h / 24;
		int w = d / 7;
		
		ts = "" + 
			((w > 0) ? w + "w" : "") +
			((d > 0) ? d % 7 + "d" : "") +
			((h > 0) ? h % 24 + "h" : "") +
			((min > 0) ? min % 60 + "m" : "") +
			sec % 60 + "s";
		return ts;
	}	
	
	public static boolean check(String name) {
		name = name.toLowerCase();
		try {
			FileInputStream f = new FileInputStream("tells/" + name + ".tell");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			if (b.readLine().length() > 2) {
				i.close(); return true; // if message exists, return true
			}
			i.close();
		}
		catch (Exception e) {}

		return false;
	}
	
	public static ArrayList<String> get(String name) {
		name = name.toLowerCase();
		ArrayList<String> tells = new ArrayList<String>();
		
		try {
			FileInputStream f = new FileInputStream("tells/" + name + ".tell");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			String s;
			while ((s = b.readLine()) != null) {
				tells.add(s); // add message from file to arraylist
			}
			i.close();
		}
		catch (Exception e) {}
		
		return tells;
	}
	
	public static String set(String sender, String rec, boolean priv) {
		String[] m = rec.split(" ", 2);
		m[0] = m[0].replace(":", ""); // fix for nubs including : in nick
		m[0] = m[0].toLowerCase();
		
		// if msg starts with ",tell help"
		if (m[0].endsWith("help")) { 
			return sender + ": format for ,tell: ,tell USERNAME MESSAGE";
		}
		
		// if there is no content after the nick
		if (m.length == 1) {
			return sender + ": you didn't include a message";
		}
		
		// create .tell file if it doesn't exist
		File f = new File("tells/" + m[0] + ".tell"); 
		if (!f.exists()) {
			try {
				f.createNewFile();
			}
			catch (Exception e) {}
		}
		
		// append new message to .tell file
		try {
			FileWriter fw = new FileWriter("tells/" + m[0] + ".tell", true);
			BufferedWriter fb = new BufferedWriter(fw);
			fb.write(sender + " " + System.currentTimeMillis() + " " + (priv ? "1" : "0") + " " + m[1]);
			fb.newLine();
			fb.close();
		} catch (Exception e) {}
		
		return sender + ": noted";
	}

	public static void clear(String sender) {
		// wipe contents of .tell file
		try {
			FileWriter f = new FileWriter("tells/" + sender + ".tell");
			BufferedWriter o = new BufferedWriter(f);
			o.write("");
			o.close();
		}
		catch (Exception e) {}
	}
}
