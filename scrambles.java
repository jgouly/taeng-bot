import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class scrambles {
	static Random rand = new Random(System.nanoTime());
	
	public static String scrambler(List<String> faces, int l) {
		return null;
	}
	
	public static String scrambler222() {
		return null;
	}
	
	public static String relem(String[] a) {
		return a[rand.nextInt(a.length)];
	}
	
	public static String handler(String msg) {
		if (msg.startsWith("2")) return scrambler222();
		if (msg.startsWith("3")) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B"), 25);
		if (msg.startsWith("4")) {
				if (msg.startsWith("w", 1)) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "Uw", "Rw", "Fw"), 40);
				else return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "u", "r", "f"), 40);
		}
		if (msg.startsWith("5")) {
			if (msg.startsWith("w", 1)) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "Uw", "Rw", "Fw", "Dw", "Lw", "Bw"), 60);
			else return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "u", "r", "f", "d", "l", "b"), 60);
		}
		if (msg.startsWith("6")) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B"), 80); 
		if (msg.startsWith("7")) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B"), 100); 
		if (msg.startsWith("8")) return scrambler(Arrays.asList("U", "R", "F", "D", "L", "B", "2U", "2R", "2F", "2D", "2L", "2B", "3U", "3R", "3F", "3D", "3L", "3B", "4U", "4R", "4F", "4D", "4L", "4B"), 120); 
		
		return null;
	}
	
}
