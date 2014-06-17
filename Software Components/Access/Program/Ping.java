package Program;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Ping {

	 public boolean doPing(String ipAP) throws Exception{
		  
			    try {
			    	// Run the Command ""ping -c 3 " + ipA"
			        Process process = Runtime.getRuntime().exec(
			                "ping -c 3 " + ipAP);
			        
			        // Get the response to this Command.
			        //
			        BufferedReader CMDreader = new BufferedReader(new InputStreamReader(
			        			process.getInputStream()));
			        String linia;
			        String respuesta = "";
			       while ((linia = CMDreader.readLine()) != null) {
			        	respuesta += linia + "\n";
			        }
			        process.destroy();
			        
			        //If the Response contains "min/avg"
			        if(respuesta.contains("min/avg")){ 
			        	return true;
			        }
			        else{
			        	return false;
			        	}
			        
			    } catch (IOException e) {
			        e.printStackTrace();
			        Sendmail sm = new Sendmail();
			         sm.send("none", "ProgramStop",e.toString()); //Calls the class that will send the mail to notificate the program Stop
			        return false;
			    }
			   

		 }
	
}

