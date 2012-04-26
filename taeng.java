import org.jibble.pircbot.*;

public class taeng extends PircBot {
	public taeng() {
		this.setName("javataeng");
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if (message.startsWith(",")) {
			if (message.startsWith("2", 1)) sendMessage(channel, scrambles.handler("2"));
		}
	}
}
