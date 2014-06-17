package Program;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Timestamp;


public class postData {
	
	public static void insertdata(String id, String status, java.sql.Timestamp tsmp) throws Exception {
																	// Method that gets a Result String that will contain the result of a 
		  															//request to a POST Method Called postData.
		
		String host="cfe.nets.upf.edu";
		String url = "http://"+host+"/webservice/postData.php";
		//URL obj = new URL(url);
		//System.out.println(obj);
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

		//add reuqest header
		con.setRequestMethod("POST");

		//Parameters to be inserted to de Data Collection.
		String urlParameters = "id="+ id + "&status="+status+"&timestamp="+tsmp.toString();

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
