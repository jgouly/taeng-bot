import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


// translated from Michael Gottlieb's qqTimer square-1 scrambler

public class squareone {
	static Random rand = new Random(System.nanoTime());
	
	static int[] p_ = {1,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0};
	static int[] p = {1,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0};
	static ArrayList<int[]> seq = new ArrayList<int[]>();
	
	public static String scramble() {
		seq.clear();
		p = p_;
		
		getseq();
		String s = "";
		for (int i = 0; i < seq.size(); i++) {
			if (seq.get(i)[0] == 7) s += "/";
			else s += " (" + seq.get(i)[0] + "," + seq.get(i)[1] + ") ";
		}
		return s;
	}

	public static void getseq() {
		int cnt = 0;
		int len = 20;
		while (cnt < len) {
			int x = rand.nextInt(12) - 5;
			int y = rand.nextInt(12) - 5;
			int size = ((x == 0) ? 0 : 1) + ((y == 0) ? 0 : 1);
			if (size > 0 || cnt == 0) {
				if (domove(x, y)) {
					int[] m = {x, y};
					if (size > 0) seq.add(m);
					cnt++;
					int[] n = {7, 0};
					seq.add(n);
					domove(7, 0);
				}
			}
		}
	}
	
	private static boolean domove(int x, int y) {
		if (x == 7) {
			for (int i = 0; i < 6; i++) {
				int temp = p[i + 6];
				p[i + 6] = p[i + 12];
				p[i + 12] = temp;
			}
			return true;
		}
		else {
			if (p[(17 - x) % 12] >= 1 || p[(11 - x) % 12] >= 1 || p[12 + ((17 - y) % 12)] >= 1 || p[12 + ((11 - y) % 12)] >= 1) {
				return false;
			}
			else {
				int[] px = Arrays.copyOfRange(p, 0, 12);
				int[] py = Arrays.copyOfRange(p, 12, 24);
				for (int i = 0; i < 12; i++) {
					p[i] = px[(12 + i - x) % 12];
					p[i + 12] = py[(12 + i - y) % 12];
				}
				return true;
			}
		}
	}
}
