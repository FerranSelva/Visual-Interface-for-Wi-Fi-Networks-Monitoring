package Program;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class postResume {
	
	public static void modifystate(String field, String valor, String id) throws Exception {
	String host="cfe.nets.upf.edu";
	String url = "http://"+host+"/webservice/postResume.php";

	HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

	//add reuqest header
	con.setRequestMethod("POST");

	String urlParameters = field+"="+valor+"&id="+id;

	// Send post request
	con.setDoOutput(true);
	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	wr.writeBytes(urlParameters);
	wr.flush();
	wr.close();

	int responseCode = con.getResponseCode();

	BufferedReader in = new BufferedReader(
	        new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();

	}
	
	public static void modifytime(String field, double valor, String id) throws Exception {
		String host="cfe.nets.upf.edu";
		String url = "http://"+host+"/webservice/postResume.php";

		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

		//add reuqest header
		con.setRequestMethod("POST");

		//Parameters to be inserted to de Data Collection.
		String urlParameters = field+"="+valor+"&id="+id;

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
