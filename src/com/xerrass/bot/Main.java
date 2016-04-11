package com.xerrass.bot;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

public class Main {
	public static void main(String[] args) throws Exception {

		// Now start our bot up.
		Bot bot = new Bot();

		// Enable debugging output.
		bot.setVerbose(true);

		try {
			bot.connect("irc.twitch.tv", 6667, "oauth:f52li159je6wiw96scp3q28ymizla2"); // Not
																						// the
																						// key
																						// I'm
																						// using
		} catch (NickAlreadyInUseException e) {
			System.err.println("Nickname is currently in use");
		} catch (IrcException e) {
			System.err.println("Server did not accept connection");
			e.printStackTrace();
		}
		// Join the #pircbot channel.
		bot.joinChannel("#xerrass");
//		bot.sendMessage("#xerrass", "Hallo");
		
	}

}
