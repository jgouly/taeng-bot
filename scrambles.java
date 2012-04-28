import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class scrambles {
	static Random rand = new Random(System.nanoTime());
	
	public static String counter(int n) {
		// Load scramble counts into arraylist
		int[] c = new int[20];
		int j = 0;
		try {
			FileInputStream f = new FileInputStream("scramble.count");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			String t;
			while ((t = b.readLine()) != null) {
				c[j++] = Integer.parseInt(t);
			}
			i.close();
		}
		catch (Exception e) {}
		c[n] = c[n] + 1;
		
		// wipe contents of count file and replace with updated values
		try {
			FileWriter f = new FileWriter("scramble.count");
			BufferedWriter o = new BufferedWriter(f);
			o.write("");
			o.close();
			
			for (int i = 0; i < c.length; i++) {
				try {
					FileWriter fw = new FileWriter("scramble.count", true);
					BufferedWriter fb = new BufferedWriter(fw);
					fb.write("" + c[i]);
					fb.newLine();
					fb.close();
				} catch (Exception e) {}
			}
			
		}
		catch (Exception e) {}
		
		String s = "";
		
		switch (n) {
			case 0: s = "2x2x2 "; break;
			case 1: s = "3x3x3 "; break;
			case 2: s = "4x4x4 "; break;
			case 3: s = "5x5x5 "; break;
			case 4: s = "6x6x6 "; break;
			case 5: s = "7x7x7 "; break;
			case 6: s = "8x8x8 "; break;
			case 7: s = "Roux "; break;
			case 8: s = "2-Gen "; break;
			case 9: s = "Megaminx "; break;
			case 10: s = "Pyraminx "; break;
			case 11: s = "Square-1 "; break;
			case 12: s = "Clock "; break;
			case 13: s = "LSLL "; break;
			case 14: s = "K4 "; break;
			case 15: s = "LL "; break;
			case 16: s = "ZBLL "; break;
			case 17: s = "Skewb "; break;
		}
		return s + "scramble #" + c[n] + ": ";
	}
	
	public static String scrambler(List<String> faces, List<String> suffix, int l) {
		String[] t = (String[]) faces.toArray();
		String[] suf = (String[]) suffix.toArray();
		int a = -1; int b = -1; int j;
		String s = "";
		
		while (l > 0) {
			j = rand.nextInt(t.length);
			if (j % 3 != b % 3 && j != a) {
				s += t[j] + suf[rand.nextInt(suf.length)] + " ";
				 a = b;
                 b = j;
                 l--;
			}
		}
		return s;
	}
	
	public static String twogen(String f1, String f2, int l) {
		String[] t = {f1, f2};
		String[] suf = {"", "'", "2"};
		int j = 0;
		String s = "";
		while (l-- > 0) {
			s += t[j] + suf[rand.nextInt(3)] + " ";
			j = 1 - j;
		}
		return s;
	}
	
	public static String twotwo() {
		return "Not yet!";
	}
	
	public static String pyraminx() {
		return pyraminx.scramble();
	}
	
	public static String squareone() {
		return squareone.scramble();
	}
	
	public static String megaminx() {
		String[] t = {"R", "D"};
		String[] suf1 = {"+", "-",};
		String[] suf2 = {"", "'",};
		int j = 0;
		String s = "";
		for (int i = 0; i < 7; i++) {
			int l = 10;
			while (l-- > 0) {
				s += t[j] + suf1[rand.nextInt(2)] + " ";
				j = 1 - j;
			}
			s += "U" + suf2[rand.nextInt(2)] + " ";
		}
		return s;
	}

	public static String clock() {
		String[] pins = {"U", "d"};
		String s = "";
		for (int i = 0; i < 4; i++) s += "(" + (rand.nextInt(12) - 5) + ", " + (rand.nextInt(12) - 5) + ") / ";
		for (int i = 0; i < 6; i++) s += "(" + (rand.nextInt(12) - 5) + ") / ";
		for (int i = 0; i < 4; i++) {
			int j = rand.nextInt(2);
			s += pins[j];
		}
		return s;
	}
	
	public static String k4ell() {
		try {
			FileInputStream f = new FileInputStream("scramblers/ell");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			String t;
			int n = rand.nextInt(40319); int m = 0;
			System.out.println(n);
			while ((t = b.readLine()) != null) {
				if (m == n) {
					i.close();
					return t;
				}
				m++;
			}
			i.close();
		}
		catch (Exception e) {}
		return "erm...";
	}
	
	public static String lastlayer() {
		try {
			FileInputStream f = new FileInputStream("scramblers/ll");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			String t;
			int n = rand.nextInt(62207); int m = 0;
			System.out.println(n);
			while ((t = b.readLine()) != null) {
				if (m == n) {
					i.close();
					return t;
				}
				m++;
			}
			i.close();
		}
		catch (Exception e) {}
		return "erm...";
	}
	
	public static String zbll() {
		try {
			FileInputStream f = new FileInputStream("scramblers/zbll");
			DataInputStream i = new DataInputStream(f);
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			String t;
			int n = rand.nextInt(7775); int m = 0;
			System.out.println(n);
			while ((t = b.readLine()) != null) {
				if (m == n) {
					i.close();
					return t;
				}
				m++;
			}
			i.close();
		}
		catch (Exception e) {}
		return "erm...";
	}
	
	public static String handler(String msg) {
		if (msg.startsWith("2")) return counter(0) + twotwo();
		if (msg.startsWith("3")) return counter(1) + scrambler(Arrays.asList("U", "R", "F", "D", "L", "B"), Arrays.asList("", "'", "2"), 25);
		if (msg.startsWith("4w")) return counter(2) + scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "Uw", "Rw", "Fw"), Arrays.asList("", "'", "2"), 40);
		if (msg.startsWith("4")) return counter(2) + scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "u", "r", "f"), Arrays.asList("", "'", "2"), 40);
		if (msg.startsWith("5w")) return counter(3) + scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "Uw", "Rw", "Fw", "Dw", "Lw", "Bw"), Arrays.asList("", "'", "2"), 60);
		if (msg.startsWith("5")) return counter(3) + scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "u", "r", "f", "d", "l", "b"), Arrays.asList("", "'", "2"), 60);
		if (msg.startsWith("6")) return counter(4) + scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B"), Arrays.asList("", "'", "2"), 80); 
		if (msg.startsWith("7")) return counter(5) + scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B"), Arrays.asList("", "'", "2"), 100); 
		if (msg.startsWith("8")) return counter(6) + scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B", "4U", "4R", "4F", "4D", "4L", "4B"), Arrays.asList("", "'", "2"), 120); 
		
		if (msg.startsWith("mu")) return counter(7) + twogen("M", "U", 20);
		if (msg.startsWith("ru")) return counter(8) + twogen("R", "U", 20);
		if (msg.startsWith("mega")) return counter(9) + megaminx();
		if (msg.startsWith("py")) return counter(10) + pyraminx();
		if (msg.startsWith("sq1")) return counter(11) + squareone();
		if (msg.startsWith("cl")) return counter(12) + clock();
		if (msg.startsWith("ls")) return counter(13) + scrambler(Arrays.asList("R U R'", "F' U F", "U", "R U' R'", "F' U' F", "U'", "R U2 R'", "F' U2 F", "U2"), Arrays.asList(""), 15);
		if (msg.startsWith("k4")) return counter(14) + k4ell();
		if (msg.startsWith("ll")) return counter(15) + lastlayer();
		if (msg.startsWith("zb")) return counter(16) + zbll();
		
		return "something went pear-shaped :(";
	}
	
}
