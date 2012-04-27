import java.util.ArrayList;
import org.jibble.pircbot.*;

public class taeng extends PircBot {
	public taeng() {
		this.setName("taeng");
	}
	
	public void checkCommands(boolean pm, String sender, String message, String channel) {
		// check if sender has ,tell messages
		if (tell.check(sender.toLowerCase())) {
			ArrayList<String> tells = tell.get(sender.toLowerCase());
			double now = System.currentTimeMillis();
			String[] t;
			String m;
			for (int i = 0; i < tells.size(); i++) {
				t = tells.get(i).split(" ", 4);
				m = sender + ": [" + tell.time(now - Double.parseDouble(t[1])) + "] <" + t[0] + "> " + t[3];
				if (t[2].equals("1")) sendMessage(sender, m);
				else sendMessage(channel, m);
			}
			tell.clear(sender);
		}
		
		// check if sender has reminders
		if (remind.check(sender.toLowerCase())) {
			ArrayList<String> rem = remind.get(sender.toLowerCase());
			String[] r;
			String m;
			for (int i = 0; i < rem.size(); i++) {
				r = rem.get(i).split(" ", 2);
				m = sender + ": " + r[1];
				sendMessage(channel, m);
			}
			tell.clear(sender.toLowerCase());
		}
		
		// check message for bot commands
		if (message.startsWith(",")) {
			// memo functions
			if (message.startsWith("tell ", 1)) sendMessage(channel, tell.set(sender, message.substring(6), false));
			if (message.startsWith("ptell ", 1)) sendMessage(channel, tell.set(sender, message.substring(7), true));
			if (message.startsWith("remind ", 1)) sendMessage(channel, remind.set(sender, message.substring(8)));
			
			// scramble functions
			// cubes
			if (message.startsWith("2", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("3", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("4", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("5", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("6", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("7", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			if (message.startsWith("8", 1)) sendMessage(channel, scrambles.handler(message.substring(1)));
			
			// cube subsets
			if (message.startsWith("roux", 1)) sendMessage(channel, scrambles.handler("mu"));
			if (message.startsWith("zbll", 1)) sendMessage(channel, scrambles.handler("zb"));
			if (message.startsWith("ll", 1)) sendMessage(channel, scrambles.handler("ll"));
			if (message.startsWith("lsll", 1)) sendMessage(channel, scrambles.handler("ls"));
			if (message.startsWith("k4", 1)) sendMessage(channel, scrambles.handler("k4"));
			
			// misc functions
			if (message.startsWith("azn", 1)) sendMessage(channel, azn.random());
		}
	}
	
	public void onPrivateMessage(String sender, String login, String hostname, String message) {
		checkCommands(true, sender, message, sender);
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		checkCommands(false, sender, message, channel);
	}
}
