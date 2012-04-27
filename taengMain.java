public class taengMain {
	public static void main(String[] args) throws Exception {
		taeng taengbot = new taeng();
		taengbot.setVerbose(true);
		taengbot.connect("irc.freenode.net");
		taengbot.joinChannel("#aronpmlab");
		taengbot.setMessageDelay(100);
		//System.out.println(tell.time(System.currentTimeMillis() - 1335508772360d));
		// Timing testing
		/*
		double start = System.currentTimeMillis();
		System.out.println((System.currentTimeMillis() - start) + "ms");
		System.out.println(System.currentTimeMillis());
		*/
	}
}
