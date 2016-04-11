package com.xerrass.bot;

import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {
	public Bot() {
		this.setName("HowdyBot");
		this.setLogin("HowdyBot");
	}

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {

		if (Util.checkBadWords(message)) {
			this.deVoice(channel, sender);
		}
		
		if (message.startsWith("!id")) {
			switch (message) {
			case "!id Steam":
				this.sendMessage("#xerrass", "http://steamcommunity.com/profiles/76561198031504704/");
				break;

			default:
				this.sendMessage("#xerrass", "haenge eine Platform an e.g. Steam");
				break;
			}
		}

		if (message.startsWith("!hardware ")) {
			this.sendMessage("#xerrass",
					"Intel i7-4770k 3,5Ghz OC(4,2ghz) | ASUS GTX 660OC | 12GB DDR3 1600 3x4g Tripplechanel | 2x 21,5\" 1080pHD");
		}

		
		if (message.startsWith("!musik")) {
			this.sendMessage("#xerrass", "Zurzeit leider nicht Functional!");
		}
		if (message.startsWith("!ts")) {
			this.sendMessage("#xerrass", "Adresse:94.250.223.9:15140");
		}
		if (message.startsWith("!multi")) {
			String link = "http://multitwitch.tv/";
			if (Util.isChannelOnline("thorty88"))
				link += "thorty88/";

			if (Util.isChannelOnline("xerrass"))
				link += "xerrass/";

			if (Util.isChannelOnline("Vitalis2166"))
				link += "Vitalis2166/";

			this.sendMessage("#xerrass", link);
		}
		if (message.contains("!commands")) {
			this.sendMessage("#xerrass", "!ts !multi");
			this.sendMessage("#xerrass", "!id !hardware");
			// this.sendMessage("#xerrass", "!musik");
		}

		super.onMessage(channel, sender, login, hostname, message);
	}


	@Override
	protected void onJoin(String channel, String sender, String login, String hostname) {
		this.sendMessage("" + sender, "Wilkommen " + sender);
		super.onJoin(channel, sender, login, hostname);
	}

	
}
