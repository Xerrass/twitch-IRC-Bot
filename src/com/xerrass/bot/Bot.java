package com.xerrass.bot;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {
	public Bot() {
		this.setName("HowdyBot");
		this.setLogin("HowdyBot");
	}

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		
		if (message.startsWith("!id")){
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
			this.sendMessage("#xerrass", "Intel i7-4770k 3,5Ghz OC(4,2ghz) | ASUS GTX 660OC | 12GB DDR3 1600 3x4g Tripplechanel | 2x 21,5\" 1080pHD");
		}
		
		if (message.contains("bitch")) {
			this.deVoice(channel, sender);
		}
		if (message.startsWith("!musik")) {
			this.sendMessage("#xerrass", "Zurzeit leider nicht Functional!");
		}
		if (message.startsWith("!ts")) {
			this.sendMessage("#xerrass", "Adresse:94.250.223.9:15140");
		}
		if (message.startsWith("!multi")) {
			String link = "http://multitwitch.tv/";
			if (isChannelOnline("thorty88"))
				link += "thorty88/";

			if (isChannelOnline("xerrass"))
				link += "xerrass/";

			if (isChannelOnline("Vitalis2166"))
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
		this.sendMessage(""+sender, "Wilkommen " + sender);
		super.onJoin(channel, sender, login, hostname);
	}

	public boolean isChannelOnline(String s) {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			HttpGet htg = new HttpGet("https://api.twitch.tv/kraken/streams/" + s);
			// this.log("" + htg.getRequestLine());

			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						try {
							return entity != null ? EntityUtils.toString(entity) : null;
						} catch (ParseException ex) {
							throw new ClientProtocolException(ex);
						}
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}

			};
			try {
				String responseBody = httpclient.execute(htg, responseHandler);
				if (responseBody.contains("viewers")) {
					return true;
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}
}
