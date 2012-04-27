import java.util.ArrayList;
import org.jibble.pircbot.*;

public class taeng extends PircBot {
	public taeng() {
		this.setName("taeng");
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if (tell.check(sender)) {
			ArrayList<String> tells = tell.get(sender);
			
			double now = System.currentTimeMillis();
			String[] t;
			String m;
			for (int i = 0; i < tells.size(); i++) {
				t = tells.get(i).split(" ", 4);
				m = sender + ": [" + tell.time(now - Double.parseDouble(t[1])) + "] <" + t[0] + "> " + t[3];
				if (t[2] == "1") sendMessage(sender, m); // should be a private message
				else sendMessage(channel, m);
			}
			tell.clear(sender);
		}
		if (message.startsWith(",")) {
			if (message.startsWith("tell ", 1)) sendMessage(channel, tell.set(sender, message.substring(6), false));
			if (message.startsWith("2", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("3", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("4", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("5", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("6", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("7", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("8", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("azn", 1)) sendMessage(channel, azn.random());
		}
	}
}
