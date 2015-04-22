package com.payon.servertoserver_java;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class RegistrationDIRECTDEBIT_SEPA {
	public static void main(String[] args) throws IOException {
		System.out.println(new RegistrationDIRECTDEBIT_SEPA().registration());
	}
	private String registration() throws IOException {
		URL url = new URL("https://test.oppwa.com/v1/registrations");

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		String data = "authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8" 
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
				+ "&paymentBrand=DIRECTDEBIT_SEPA"
				+ "&bankAccount.bic=MARKDEF1100"
				+ "&bankAccount.iban=DE23100000001234567890"
				+ "&bankAccount.country=DE"
				+ "&bankAccount.holder=Jane Jones";

		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();
		int responseCode = conn.getResponseCode();
		InputStream is;
	    
		if (responseCode >= 400) is = conn.getErrorStream();
		else is = conn.getInputStream();
	    
		return IOUtils.toString(is);
	}
}
