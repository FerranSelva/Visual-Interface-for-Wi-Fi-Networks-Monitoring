package Program;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import java.util.Vector;

import org.json.JSONException;



public class ConnectionMongo {
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String result = getData.getresult(); 
		Vector <String> IP= getData.FindIP(result);
		Vector <String> ID= getData.FindID(result);
		
		Sendmail sm = new Sendmail();
		Ping ping = new Ping();
		
		updateResume upr = new updateResume();
		int i=0;
		
		try{ 	// Testing version. This version will do just a iteration. 
				// For the normal operation of this code, remove the first if and 
				// its else. 
			while(true){
				if(i==1){
					break;
				}
				else{
				for(int u=0; u<IP.size();u++){	//Check id there the system can ping a direccion
					 if (ping.doPing(IP.get(u).toString())){ 
						 java.util.Date Date = new java.util.Date(); //
						 Timestamp t = new Timestamp(Date.getTime());// Timestamp of the actual moment
						 
						//Check if there is Documents on the Data Collection for a specific IP
						 String lastResult = getlastupdate.getlastresult(ID.get(u));//Gets the result string to get the lastTimestamp
						 long lastupdate;
						 if(getlastupdate.isDataempty(lastResult)==false){ // Check id the Data response is empty
							 //If data have documents calls getlastTimestamp to get the last time when the node
							 //state was updated.
							 lastupdate = getlastupdate.getlastTimestamp(lastResult).getTime();
							// Calls method to upgrade uptime, downtime, totaltime, availability, since
							 upr.update(ID.get(u), "UP", t, lastupdate ,false);
							 
						 }
						 else{ 
							// Calls method to upgrade uptime, downtime, totaltime, availability, since
							 lastupdate= (long) 0.0;
							 upr.update(ID.get(u), "UP", t, lastupdate ,true);
						 }
						// Calls method to modify state
						 postResume.modifystate("status", "UP", ID.get(u));
						 
						//insert the document to the Data collection.
						 postData.insertdata(ID.get(u), "UP", t);
						 System.out.println("PING OK! a " + ID.get(u).toString());				 
					 }
				 	else{
						 java.util.Date Date1 = new java.util.Date(); //Inicializa Data del sistema
						 Timestamp t2 = new Timestamp(Date1.getTime());
						
						 
						//Check if there is Documents on the Data Collection for a specific IP
						 String lastResult2 = getlastupdate.getlastresult(ID.get(u));//Gets the result string to get the lastTimestamp
						 long lastupdate2;
						 if(getlastupdate.isDataempty(lastResult2)==false){ // Check id the Data response is empty
							 //If data have documents calls getlastTimestamp to get the last time when the node
							 //state was updated.
							 lastupdate2 = getlastupdate.getlastTimestamp(lastResult2).getTime();
							// Calls method to upgrade uptime, downtime, totaltime, availability, since
							 upr.update(ID.get(u), "DOWN", t2, lastupdate2, false);
						 }
						 else{				 
							// Calls method to upgrade uptime, downtime, totaltime, availability, since.
							 lastupdate2 = (long) 0.0;
							 upr.update(ID.get(u), "DOWN", t2, lastupdate2,true);
						 }
					 
					// Calls method to modify state
					 postResume.modifystate("status", "DOWN", ID.get(u));
					 
					 //insert thea document to the Data collection.
					 postData.insertdata(ID.get(u), "DOWN", t2);
					 System.out.println("PING FAIL! a " + ID.get(u).toString()); 
					 
				 	}
				}
				}
				i++;
				Thread.sleep(3000); // Sleeping time of the program before begin a new iteration
			}
		}
		catch(Exception e){
			System.out.println("Access Program Stopped" + e); //
			sm.send("none", "ProgramStop",e.toString());//Calls the class that will send the mail to notificate the program Stop
			
		}
		
	}
}
