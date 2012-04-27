import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class scrambles {
	static Random rand = new Random(System.nanoTime());
	
	public static String scrambler(List<String> faces, List<String> suffix, int l) {
		String[] t = (String[]) faces.toArray();
		String[] suf = (String[]) suffix.toArray();
		int a = -1; int b = -1; int j;
		String s = "";
		
		while (l-- > 0) {
			j = rand.nextInt(t.length);
			if (j % 3 != b % 3 && j != a) {
				s += t[j] + suf[rand.nextInt(suf.length)] + " ";
				 a = b;
                 b = j;
			}
		}
		return s;
	}
	
	public static String mu(int l) {
		String[] t = {"M", "U"};
		String[] suf = {"", "'", "2"};
		int j = 0;
		String s = "";
		while (l-- > 0) {
			s += t[j] + suf[rand.nextInt(3)] + " ";
			j = 1 - j;
		}
		return s;
	}
	
	public static String scrambler222() {
		return "Not yet!";
	}
	
	public static String relem(String[] a) {
		return a[rand.nextInt(a.length)];
	}
	
	public static String handler(String msg) {
		if (msg.startsWith("2")) return scrambler222();
		if (msg.startsWith("3")) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B"), Arrays.asList("", "'", "2"), 25);
		if (msg.startsWith("4")) {
				if (msg.startsWith("w", 1)) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "Uw", "Rw", "Fw"), Arrays.asList("", "'", "2"), 40);
				else return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "u", "r", "f"), Arrays.asList("", "'", "2"), 40);
		}
		if (msg.startsWith("5")) {
			if (msg.startsWith("w", 1)) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "Uw", "Rw", "Fw", "Dw", "Lw", "Bw"), Arrays.asList("", "'", "2"), 60);
			else return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "u", "r", "f", "d", "l", "b"), Arrays.asList("", "'", "2"), 60);
		}
		if (msg.startsWith("6")) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B"), Arrays.asList("", "'", "2"), 80); 
		if (msg.startsWith("7")) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B"), Arrays.asList("", "'", "2"), 100); 
		if (msg.startsWith("8")) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B", "4U", "4R", "4F", "4D", "4L", "4B"), Arrays.asList("", "'", "2"), 120); 
		if (msg.startsWith("mu")) return mu(20);
		
		return "something went pear-shaped :(";
	}
	
}
