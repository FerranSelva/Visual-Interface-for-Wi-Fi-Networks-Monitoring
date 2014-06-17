package Program;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class getData {
	
	static Vector <String> IP= new Vector<String>(2);
	static Vector <String> ID= new Vector<String>(2);
	
	public static String getresult() throws Exception{// Method that gets a Result String that will contain the result of a 
													  //request to a Get Method Called getData.
		URL url;
	     HttpURLConnection conn;
	     BufferedReader rd;
	     String line;
	     String result = "";
	     String host="cfe.nets.upf.edu"; 
	     
	      try {
	    	 //URL of the WebService.
	         url = new URL("http://"+host+"/webservice/getData.php");
	         //Open the connection with specific url 
	         conn = (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET"); //Sets the Method Type
	         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         while ((line = rd.readLine()) != null) {
	            result += line; //Fill Up the string result.
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
	         sm.send("none", "ProgramStop",e.toString());
	      }
	      return result;
		
	}
	
	public static Vector<String> FindIP(String result) throws UnknownHostException, JSONException { // Method used to get a Vector with all the
																									// IPs of the Access Points
	      // Convert  Result (STRING) to JSONObject.
	      JSONObject json_data = new JSONObject(result);
	      
	      //Gets in the "data" field. this is the answer from de DATABASE
	      JSONObject data = json_data.getJSONObject("data");

	      
	      //Get the "Keys" from each Object. These "keys" are the unic mongo_id of each node.
	      JSONArray idmongo= data.names();
	      
	      //JSONOBject used to get the IP. This will change with each iteration 
	      //of the following for
	      JSONObject node;
	      
	      
	      //From each Node, gets the IP
	      for(int i =0; i<idmongo.length();i++){
	    	  	node=data.getJSONObject(idmongo.getString(i));

	    	  	//If needded prevent program stop when there are elements without IP field
	    	  	if(node.names().toString().contains("IP")==true){
	    	  		IP.add(node.getString("IP"));
	    	  	}
	      } 
	      return IP;
	}
	
	public static Vector<String> FindID(String result) throws UnknownHostException, JSONException {	// Method used to get a Vector with all the
																									// IDs of the Access Points
		 
	      // Convert  Result (STRING) to JSONObject.
	      JSONObject json_data = new JSONObject(result);
	      
	      //Gets in the "data" field. this is the answer from de DATABASE
	      JSONObject data = json_data.getJSONObject("data");
	      
	      //Get the "Keys" from each Object. These "keys" are the unic mongo_id of each node.
	      JSONArray idmongo= data.names();
	      
	      //JSONOBject used to get the ID. This will change with each iteration 
	      //of the following for
	      JSONObject node;
	      
	      //From each Node, gets the ID
	      for(int i =0; i<idmongo.length();i++){
	    	  	node=data.getJSONObject(idmongo.getString(i));
	    	  	
	    	  	//If needded prevent program stop when there are elements without IP field
	    	  	if(node.names().toString().contains("ID")==true){
	    	  		ID.add(node.getString("ID"));
	    	  	}
	      } 
	      return ID;
	}

}