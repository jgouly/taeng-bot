import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// translated from World Cube Association's official pyraminx scrambler

public class pyraminx {
	static Random rand = new Random(System.nanoTime());
	
	static ArrayList<Integer> sol = new ArrayList<Integer>(); 
	static int[] posit = new int[36];
	static int[] perm = new int[720];
	static int[] twst = new int[2592];
	static int[][] permmv = new int[720][4];
	static int[][] twstmv = new int[2592][4];
	static int[] pcperm = {0, 1, 2, 3, 4, 5}; 
	static int[] pcori = new int[10];
	
	static String[] faces = {"U", "L", "R", "B"};
	static String[] suf = {"", "'"};
	static String[] tips = {"l", "r", "b", "u"};
	
	public static String scramble() {
		initbrd();
		calcperm();
		dosolve();
		
		String scramble = "";
		for (int i = 0; i < sol.size(); i++) {
			scramble += faces[sol.get(i) & 7] + suf[(sol.get(i) & 8) / 8] + " ";
		}
		for (int i = 0; i < 4; i++) {
			int j = rand.nextInt(3);
			if (j < 2) scramble += tips[i] + suf[j] + " ";
		}
		
		return scramble;
	}
	
	private static void dosolve() {
		int t = 0; int q = 0; boolean parity = false;
		for (int i = 0; i < 6; i++) pcperm[i] = i;
		for (int i = 0; i < 4; i++) {
			int other = i + rand.nextInt(6 - i);
			int temp = pcperm[i];
			pcperm[i] = pcperm[other];
			pcperm[other] = temp;
			if (i != other) parity = !parity;
		}
		if (parity) {
			int temp = pcperm[4];
			pcperm[4] = pcperm[5];
			pcperm[5] = temp;
		}
		parity = false;
		Arrays.fill(pcori, 0);
		for (int i = 0; i < 5; i++) {
			pcori[i] = rand.nextInt(2);
			if (pcori[i] == 1) parity = !parity;
		}
		pcori[5] = (parity ? 1 : 0);
		for (int i = 6; i < 10; i++) {
			pcori[i] = rand.nextInt(3);
		}
		for (int a = 0; a < 6; a++) {
			int b = 0;
			for (int c = 0; c < 6; c++) {
				if (pcperm[c] == a) break;
				if (pcperm[c] > a) b++;
			}
			q = q * (6 - a) + b;
		}
		for (int a = 9; a >= 6; a--) {
			t = t * 3 + pcori[a];
		}
		for (int a = 4; a >= 0; a--) {
			t = t * 2 + pcori[a];
		}
		if (q != 0 || t != 0) {
			for (int l = 7; l < 12; l++) {
				if (search(q, t, l, -1)) break;
			}
		}
	}

	private static boolean search(int q, int t, int l, int lm) {
		if (l == 0) {
			if (q == 0 && t == 0) return true;
		}
		else {
			if (perm[q] > l || twst[t] > l) return false;
			for (int m = 0; m < 4; m++) {
				if (m != lm) {
					int p = q;
					int s = t;
					for (int a = 0; a < 2; a++) {
						p = permmv[p][m];
						s = twstmv[s][m];
						sol.add(m + 8 * a);
						if (search(p, s, l - 1, m)) return true;
						sol.remove(sol.size() - 1);
					}
				}
			}
		}	    
		return false;
	}

	private static void calcperm() {
		for (int p = 0; p < 720; p++) {
			perm[p] = -1;
			Arrays.fill(permmv[p], 0);
			for (int m = 0; m < 4; m++) {
				permmv[p][m] = getprmmv(p, m);
			}
		}
		perm[0] = 0;
		for (int l = 0; l <= 6; l++) {
			int n = 0;
			for (int p = 0; p < 720; p++) {
				if (perm[p] == l) {
					for (int m = 0; m < 4; m++) {
						int q = p;
						for (int c = 0; c < 2; c++) {
							q = permmv[q][m];
							if (perm[q] == -1) {
								perm[q] = l + 1;
								n++;
							}
						}
					}
				}
			}
		}
		for (int p = 0; p < 2592; p++) {
			twst[p] = -1;
			Arrays.fill(twstmv[p], 0);
			for (int m = 0; m < 4; m++) {
				twstmv[p][m] = gettwsmv(p, m);
			}
		}
		twst[0] = 0;
		for (int l = 0; l <= 5; l++) {
			int n = 0;
			for (int p = 0; p < 2592; p++) {
				if (twst[p] == l) {
					for (int m = 0; m < 4; m++) {
						int q = p;
						for (int c = 0; c < 2; c++) {
							q = twstmv[q][m];
							if (twst[q] == -1) {
								twst[q] = l + 1;
								n++;
							}
						}
					}
				}
			}
		}
	}

	private static int gettwsmv(int p, int m) {
		int[] ps = new int[20];
		int d = 0;
		int q = p;
		for (int a = 0; a <= 4; a++) {
			ps[a] = q & 1;
			q >>= 1;
			d ^= ps[a];
		}
		ps[5] = d;
		for (int a = 6; a <= 9; a++) {
			int c = q / 3;
			int b = q - 3 * c;
			q = c;
			ps[a] = b;
		}
		if (m == 0) {
			ps[6]++;
			if (ps[6] == 3) ps[6] = 0;
			ps = cycle3(ps, 0, 3, 1);
			ps[1] ^= 1;
			ps[3] ^= 1;
		}
		else if (m == 1) {
			ps[7]++;
			if (ps[7] == 3) ps[7] = 0;
			ps = cycle3(ps, 1, 5, 2);
			ps[2] ^= 1;
			ps[5] ^= 1;
		}
		else if (m == 2) {
			ps[8]++;
			if (ps[8] == 3) ps[8] = 0;
			ps = cycle3(ps, 0, 2, 4);
			ps[0] ^= 1;
			ps[2] ^= 1;
		}
		else if (m == 3) {
			ps[9]++;
			if (ps[9] == 3) ps[9] = 0;
			ps = cycle3(ps, 3, 4, 5);
			ps[3] ^= 1;
			ps[4] ^= 1;
		}
		q = 0;
		for (int a = 0; a >= 6; a--) {
			q = q * 3 + ps[a];
		}
		for (int a = 4; a >= 0; a--) {
			q = q * 2 + ps[a];
		}
		return q;
	}

	private static int getprmmv(int p, int m) {
		int[] ps = new int[20];
		int q = p;
		for (int a = 1; a <= 6; a++) {
			int c = q / a;
			int b = q - (a * c);
			q = c;
			for (c = a - 1; c >= b; c--) {
				ps[c + 1] = ps[c];
			}
			ps[b] = 6 - a;
		}
		if (m == 0) ps = cycle3(ps, 0, 3, 1);
		else if (m == 1) ps = cycle3(ps, 1, 5, 2);
		else if (m == 2) ps = cycle3(ps, 0, 2, 4);
		else if (m == 3) ps = cycle3(ps, 3, 4, 5);
		q = 0;
		for (int a = 0; a < 6; a++) {
			int b = 0;
			for (int c = 0; c < 6; c++) {
				if (ps[c] == a) break;
				if (ps[c] > a) b++;
			}
			q = q * (6 - a) + b;
		}
	    return q;
	}

	private static int[] cycle3(int[] ps, int i,int j, int k) {
		int c = ps[i];
		ps[i] = ps[j];
		ps[j] = ps[k];
		ps[k] = c;
		return ps;
	}

	private static void initbrd() {
		for (int i = 1; i < 36; i++) {
			posit[i - 1] = i / 9;
		}
		sol = new ArrayList<Integer>();
	}		
}
