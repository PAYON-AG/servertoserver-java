package com.payon.servertoserver_java;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class InitialPayment_BRADESCO {
	public static void main(String[] args) throws IOException {
		System.out.println(new InitialPayment_BRADESCO().initialPayment());
	}
	private String initialPayment() throws IOException {
		URL url = new URL("https://test.oppwa.com/v1/payments");

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		String data = "authentication.userId=8a8294174b7ecb28014b9699220015cc"
				+ "&authentication.password=sy6KJsT8" 
				+ "&authentication.entityId=8a8294174b7ecb28014b9699a3cf15d1"
				+ "&amount=1.03"
				+ "&currency=BRL"
				+ "&paymentBrand=BRADESCO"
				+ "&paymentType=PA"
				+ "&customer.givenName=Braziliano"
				+ "&customer.surname=Babtiste"
				+ "&billing.city=Brasilia"
				+ "&billing.country=BR"
				+ "&billing.state=DE"
				+ "&billing.street1=Amazonstda"
				+ "&billing.postcode=12345678"
				+ "&customParameters[BRADESCO_cpfsacado]=11111111111"
				+ "&shopperResultUrl=https://docs.oppwa.com"
				+ "&testMode=EXTERNAL";

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
