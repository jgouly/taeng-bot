import java.io.*;
import java.util.ArrayList;

public class remind {
	public static String chop(String s) {
		return s.substring(0, s.length() - 1);
	}
	
	public static boolean check(String name) {
		name = name.toLowerCase();
		
		try {
			FileInputStream f = new FileInputStream("reminds/" + name + ".remind");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			if (b.readLine().length() > 2) {
				i.close(); return true; // if reminder exists, return true
			}
			i.close();
		}
		catch (Exception e) {}

		return false;
	}
	
	public static ArrayList<String> get(String name) {
		ArrayList<String> send = new ArrayList<String>();
		ArrayList<String> keep = new ArrayList<String>();
		
		name = name.toLowerCase();
		
		try {
			FileInputStream f = new FileInputStream("reminds/" + name + ".remind");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			String s; String[] m;
			while ((s = b.readLine()) != null) {
				m = s.split(" ", 2);
				if (System.currentTimeMillis() >= Double.parseDouble(m[0])) send.add(s);
				else keep.add(s);
			}
			i.close();
		}
		catch (Exception e) {}
		
		// wipe contents of .remind file and replace with remaining reminders
		try {
			FileWriter f = new FileWriter("reminds/" + name + ".remind");
			BufferedWriter o = new BufferedWriter(f);
			o.write("");
			o.close();
			
			for (int i = 0; i < keep.size(); i++) {
				try {
					FileWriter fw = new FileWriter("reminds/" + name + ".remind", true);
					BufferedWriter fb = new BufferedWriter(fw);
					fb.write(keep.get(i));
					fb.newLine();
					fb.close();
				} catch (Exception e) {}
			}
			
		}
		catch (Exception e) {}
		
		
		return send;
	}
	
	public static String set(String sender, String rec) {
		sender = sender.toLowerCase();
		String[] m = rec.split(" ", 2);
		
		// if msg starts with ",remind help"
		if (m[0].equals("help")) {
			return sender + ": format for ,remind (w=week, d=day, h=hour, m=minute, s=second): ,remind 1w2d3h4m5s MESSAGE";
		}
		
		if (m.length == 1) { // if there is no content after the nick
			return sender + ": you didn't include a reminder";
		}
		File f = new File("reminds/" + sender + ".remind"); 
		
		// create .remind file if it doesn't exist
		if (!f.exists()) {
			try {
				f.createNewFile();
			}
			catch (Exception e) {}
		}
		
		// convert user timestamp to system time
		String ts = m[0];
		ts = ts.replaceAll("w", "w ");
		ts = ts.replaceAll("d", "d ");
		ts = ts.replaceAll("h", "h ");
		ts = ts.replaceAll("m", "m ");
		ts = ts.replaceAll("s", "s ");
		String[] split = ts.split(" ");
		long time = System.currentTimeMillis();
		for (int i = 0; i < split.length; i++) {
			System.out.println(Integer.parseInt(chop(split[i])));
			if (split[i].charAt(split[i].length() - 1) == 'w') time += (604800000 * Integer.parseInt(chop(split[i])));
			if (split[i].charAt(split[i].length() - 1) == 'd') time += ( 86400000 * Integer.parseInt(chop(split[i])));
			if (split[i].charAt(split[i].length() - 1) == 'h') time += (  3600000 * Integer.parseInt(chop(split[i])));
			if (split[i].charAt(split[i].length() - 1) == 'm') time += (    60000 * Integer.parseInt(chop(split[i])));
			if (split[i].charAt(split[i].length() - 1) == 's') time += (     1000 * Integer.parseInt(chop(split[i])));
		}
		
		// append new message to .remind file
		try {
			FileWriter fw = new FileWriter("reminds/" + sender + ".remind", true);
			BufferedWriter fb = new BufferedWriter(fw);
			fb.write(time + " " + m[1]);
			fb.newLine();
			fb.close();
		} catch (Exception e) {}
		
		return sender + ": noted";
	}
}
