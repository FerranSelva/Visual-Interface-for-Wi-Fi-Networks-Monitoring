package Program;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;


public class getUTDT {
	
	
	
	 static String getuptime (String id) throws Exception {	// Method that gets a Result String that will contain the result of a 
		  													//request to a Get Method Called getUTDT.

		URL url;
	     HttpURLConnection conn;
	     BufferedReader rd;
	     String line;
	     String result = "";
	     String host="cfe.nets.upf.edu";
	      try {
	    	 //URL of the WebService.
	         url = new URL("http://"+host+"/webservice/getUTDT.php?id="+id);
	         //Open the connection with a specific url 
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET"); // Sets the method type.
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;	//Fill Up the string result.
	         }
	         rd.close();
	      }
	      catch (IOException e) {
	         e.printStackTrace();
	         Sendmail sm = new Sendmail();
	         sm.send("none", "ProgramStop",e.toString());//Calls the class that will send the mail to notificate the program Stop
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	         Sendmail sm = new Sendmail();
	         sm.send("none", "ProgramStop",e.toString());//Calls the class that will send the mail to notificate the program Stop
	      }
	      // Convert  Result (STRING) to JSONObject.
	      JSONObject json_data = new JSONObject(result);
	      
	      //Gets in the "data" field. this is the answer from de DATABASE
	      JSONObject data = json_data.getJSONObject("data");
	      
	      JSONArray idmongo= data.names();
	      JSONObject element = data.getJSONObject(idmongo.getString(0)); // Get the first element.
	      String uptime = element.getString("UPTIME"); // Gets the UPTIME Value from this element.
	      
	      return uptime;
	      
	}
	 
	 static String getdowntime (String id) throws Exception {	// Method that gets a Result String that will contain the result of a 
																//request to a Get Method Called getUTDT.

		URL url;
	     HttpURLConnection conn;
	     BufferedReader rd;
	     String line;
	     String result = "";
	     String host="cfe.nets.upf.edu";
	      
	      try {
	    	 //URL of the WebService.
	         url = new URL("http://"+host+"/webservice/getUTDT.php?id="+id);
	         //Open the connection witha specific url 
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
	      }
	      catch (IOException e) {
	         e.printStackTrace();
	         Sendmail sm = new Sendmail();
	         sm.send("none", "ProgramStop",e.toString());//Calls the class that will send the mail to notificate the program Stop
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	         Sendmail sm = new Sendmail();
	         sm.send("none", "ProgramStop",e.toString());//Calls the class that will send the mail to notificate the program Stop
	      }
	      System.out.println(result);
	      // Convert  Result (STRING) to JSONObject.
	      JSONObject json_data = new JSONObject(result);
	      
	      //Gets in the "data" field. this is the answer from de DATABASE
	      JSONObject data = json_data.getJSONObject("data");   
	        
	      JSONArray idmongo= data.names();
	      JSONObject element = data.getJSONObject(idmongo.getString(0)); // Get the first element.

	      String downtime = element.getString("DOWNTIME");// Gets the DOWNTIME Value from this element.

	      return downtime;
	      
	}
	 
	 static String getstate (String id) throws Exception {	// Method that gets a Result String that will contain the result of a 
															//request to a Get Method Called getUTDT.

		URL url;
	     HttpURLConnection conn;
	     BufferedReader rd;
	     String line;
	     String result = "";
	     
	     String host="cfe.nets.upf.edu";
	      
	      try {
	    	 //URL of the WebService.
	         url = new URL("http://"+host+"/webservice/getUTDT.php?id="+id);
	         //Open the connection with a specific url 
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line;
	         }
	         rd.close();
	      }
	      catch (IOException e) {
	         e.printStackTrace();
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	      }
	      // Convert  Result (STRING) to JSONObject.
	      JSONObject json_data = new JSONObject(result);
	      System.out.println(json_data.toString() + id);
	      //Gets in the "data" field. this is the answer from the DATABASE
	      JSONObject data = json_data.getJSONObject("data");   
	        
	      JSONArray idmongo= data.names();
	      JSONObject element = data.getJSONObject(idmongo.getString(0));
	      System.out.println(element);
	      String status = element.getString("STATUS"); // Gets the DOWNTIME Value from this element.

	      return status;
	 }
}
