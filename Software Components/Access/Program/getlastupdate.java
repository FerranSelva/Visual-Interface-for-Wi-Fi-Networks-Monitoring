package Program;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;


public class getlastupdate {
	
	public static String getlastresult(String id) throws Exception { // Method that gets a Result String that will contain the result of a 
		  															 //request to a Get Method Called getlast.
		URL url;
	     HttpURLConnection conn;
	     BufferedReader rd;
	     String line;
	     String result = "";
	     String host="cfe.nets.upf.edu";
	      
	      try {
	    	 //URL of the WebService.
	         url = new URL("http://"+host+"/webservice/getlast.php?id="+id);
	         //Open the connection with specific url 
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET"); // Sets the Method Type 
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line; // Fill Up the string result. 
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
	      return result;	
	}
	
	static Timestamp getlastTimestamp (String resultt) throws Exception {

	      // Convert  Result (STRING) to JSONObject.
	      JSONObject json_data = new JSONObject(resultt);
	      
	      //Gets in the "data" field. this is the answer from de DATABASE  
	      JSONObject data = json_data.getJSONObject("data");   
	              
	      //Gets the Timestamp Value with a String format.
	      String tmsstring =data.getString("TimeStamp"); 
	      
	      //Translates from String to TimeStamp format the value obtained.
	      Timestamp lasttimestamp = Timestamp.valueOf(tmsstring);

	      return lasttimestamp;
	      
	}
	
	static boolean isDataempty (String resulti) throws Exception {

		
	      // Convert  Result (STRING) to JSONObject.
	      JSONObject json_data = new JSONObject(resulti);
	     
	      //Check The data content to a TimeStamp key. Return the result of this check
	      if(json_data.get("data").toString().contains("TimeStamp")==true){
	    	  return false;
	      }
	      else{
	    	  return true;
	      }
	      
	}

}



