package com.payon.servertoserver_java;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class DeleteRegistration {
	public static void main(String[] args) throws IOException {
		System.out.println(new DeleteRegistration().deleteRegistration("8a82944a4cfff62d014d05516d151691"));
	}
	private String deleteRegistration(String registrationId) throws IOException {
		URL url = new URL("https://test.oppwa.com/v1/registrations/" + registrationId
				+ "?authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8" 
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
		);

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("DELETE");

		int responseCode = conn.getResponseCode();
		InputStream is;
	    
		if (responseCode >= 400) is = conn.getErrorStream();
		else is = conn.getInputStream();
	    
		return IOUtils.toString(is);
	}
}
