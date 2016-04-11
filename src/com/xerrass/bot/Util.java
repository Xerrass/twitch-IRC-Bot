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

public class Util {

	

	public static boolean checkBadWords(String message) {
		
		return false;
	}
	
	public static boolean isChannelOnline(String s) {
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
