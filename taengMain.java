public class taengMain {
	public static void main(String[] args) throws Exception {
		taeng taengbot = new taeng();
		taengbot.setVerbose(true);
		//taengbot.connect("irc.freenode.net");
		//taengbot.joinChannel("#aronpmlab");
		taengbot.setMessageDelay(100);
	}
}
