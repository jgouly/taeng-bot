import java.io.IOException;
import java.util.ArrayList;
import org.jibble.pircbot.*;

public class taeng extends PircBot {
	public taeng() {
		this.setName("taeng");
		this.setAutoNickChange(true);
	}
	
	public void checkCommands(boolean pm, String sender, String message, String channel, String hostname) {
		// seen & nick tracking will be here
		
		// is user banned? stop here
		
		
		// check if sender has ,tell messages
		if (tell.check(sender)) {
			ArrayList<String> tells = tell.get(sender);
			for (int i = 0; i < tells.size(); i++) {
				String[] t = tells.get(i).split(" ", 4);
				String m = sender + ": [" + tell.time(System.currentTimeMillis() - Double.parseDouble(t[1])) + "] <" + t[0] + "> " + t[3];
				if (t[2].equals("1")) sendMessage(sender, m);
				else sendMessage(channel, m);
			}
			tell.clear(sender);
		}
		
		// check if sender has reminders
		if (remind.check(sender)) {
			ArrayList<String> rem = remind.get(sender);
			for (int i = 0; i < rem.size(); i++) {
				String[] r = rem.get(i).split(" ", 2);
				String m = sender + ": " + r[1];
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
			
			// other puzzles
			if (message.startsWith("mega", 1)) sendMessage(channel, scrambles.handler("mega"));
			if (message.startsWith("py", 1)) sendMessage(channel, scrambles.handler("py"));
			if (message.startsWith("sq", 1)) sendMessage(channel, scrambles.handler("sq1"));
			if (message.startsWith("cl", 1)) sendMessage(channel, scrambles.handler("cl"));
			
			// cube subsets
			if (message.startsWith("roux", 1)) sendMessage(channel, scrambles.handler("mu"));
			if (message.startsWith("zbll", 1)) sendMessage(channel, scrambles.handler("zb"));
			if (message.startsWith("ll", 1)) sendMessage(channel, scrambles.handler("ll"));
			if (message.startsWith("ls", 1)) sendMessage(channel, scrambles.handler("ls"));
			if (message.startsWith("k4", 1)) sendMessage(channel, scrambles.handler("k4"));
			
			// misc functions
			if (message.startsWith("azn", 1)) sendMessage(channel, azn.random());
			if (message.startsWith("ping", 1)) sendMessage(channel, "pong");
			if (message.startsWith("sup", 1)) sendMessage(channel, "hi " + sender +"!");
			if (message.startsWith("wiki", 1)) sendMessage(channel, "http://www.speedsolving.com/wiki/index.php?search=" + message.split(" ", 2)[1].replaceAll(" ", "+") + "&go=Go");
			
			// wca functions
			if (message.startsWith("wca", 1))
			
			// help function
			if (message.startsWith("help", 1)) sendMessage(channel, "Go here for documentation: http://aronpm.cubing.net/bot/help.txt");
			
			// operator only function
			if (hostname.startsWith("")) {
				if (message.startsWith("quit", 1)) System.exit(0);
				if (message.startsWith("change", 1)) this.setName(message.substring(8));
				if (message.startsWith("join", 1)) joinChannel(message.substring(6));
				if (message.startsWith("part", 1)) partChannel(message.substring(8));
			}
		}
	}
	
	public void onPrivateMessage(String sender, String login, String hostname, String message) {
		checkCommands(true, sender, message, sender, hostname);
	}
	
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		checkCommands(false, sender, message, channel, hostname);
	}
	
	public void onNickChange(String oldNick, String login, String hostname, String newNick) {
		
	}

	public void onJoin(String channel, String sender, String login, String hostname) {
		if (sender != this.getName()) {
			// tell person that they have messages
			if (tell.check(sender.toLowerCase())) sendMessage(channel, sender + ": you have pending ,tells");
		}
	}
	
	public void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
		
	}
	
	public void onPart(String channel, String sender, String login, String hostname) {
		
	}
	
	public void onDisconnect() {
		try {
			this.connect("");
		} catch (NickAlreadyInUseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IrcException e) {
			e.printStackTrace();
		}
	
		joinChannel("");
	}
}
