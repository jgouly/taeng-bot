public class taengMain {
	public static void main(String[] args) throws Exception {
		taeng taengbot = new taeng();
		taengbot.setVerbose(true);
		taengbot.connect("");
		taengbot.joinChannel("");
		taengbot.setMessageDelay(100);
	}
}
