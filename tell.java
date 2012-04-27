import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
		try {
			FileInputStream f = new FileInputStream(name + ".tell");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			if (b.readLine().length() > 2) {
				i.close(); return true;
			}
			i.close();
		}
		catch (Exception e) {}

		return false;
	}
	
	public static ArrayList<String> get(String name) {
		ArrayList<String> tells = new ArrayList<String>();
		
		try {
			FileInputStream f = new FileInputStream(name + ".tell");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			String s;
			while ((s = b.readLine()) != null) {
				tells.add(s);
			}
			i.close();
		}
		catch (Exception e) {}
		
		return tells;
	}
	
	public static String set(String sender, String rec, boolean priv) {
		String[] m = rec.split(" ", 2);
		File f = new File(m[0] + ".tell"); 
		
		if (!f.exists()) {
			try {
				f.createNewFile();
			}
			catch (Exception e) {}
		}
		PrintWriter fw;
		try {
			fw = new PrintWriter(m[0] + ".tell");
			fw.println(sender + " " + System.currentTimeMillis() + " " + (priv ? "1" : "0") + " " + m[1]);
			fw.close();
		} catch (Exception e) {}

		
		
		return sender + ": message noted";
	}

	public static void clear(String sender) {
		try {
			FileWriter f = new FileWriter(sender + ".tell");
			BufferedWriter o = new BufferedWriter(f);
			o.write("");
			o.close();
		}
		catch (Exception e) {}
	}
}
