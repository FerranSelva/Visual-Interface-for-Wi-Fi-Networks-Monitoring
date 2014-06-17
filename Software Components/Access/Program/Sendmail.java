package Program;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Sendmail {
	
	public static void send(String id, String type, String e) throws Exception{
		String host="cfe.nets.upf.edu";
		String url = "http://"+host+"/webservice/notify.php";

		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
	
		//add request method
		con.setRequestMethod("POST");
	
		//
		String urlParameters = "id="+ id + "&type="+type + "&e="+e;
		System.out.println("Sending mail:"+ urlParameters);
	
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
	
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
	
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
	in.close();
	}
	
}
