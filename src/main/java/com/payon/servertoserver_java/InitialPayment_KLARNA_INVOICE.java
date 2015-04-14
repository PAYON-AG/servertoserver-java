package com.payon.servertoserver_java;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

public class InitialPayment_KLARNA_INVOICE {
	public static void main(String[] args) throws IOException {
		System.out.println(new InitialPayment_KLARNA_INVOICE().initialPayment());
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
				+ "&amount=92.12"
				+ "&currency=EUR"
				+ "&paymentBrand=KLARNA_INVOICE"
				+ "&paymentType=PA"
				+ "&customer.givenName=Jane"
				+ "&customer.surname=Jones"
				+ "&billing.country=SE"
				+ "&cart.items[0].merchantItemId=1" 
				+ "&cart.items[0].discount=0.00"	
				+ "&cart.items[0].quantity=5"
				+ "&cart.items[0].name=Product 1"
				+ "&cart.items[0].price=1.00"
				+ "&cart.items[0].tax=6.00"
				+ "&shopperResultUrl=https://docs.oppwa.com";

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
