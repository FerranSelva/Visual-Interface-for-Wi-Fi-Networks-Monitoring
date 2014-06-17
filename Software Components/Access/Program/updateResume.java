package Program;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;




public class updateResume {
	
	public void update(String id, String estat, Timestamp t,long lastup, boolean firstiteration) throws Exception{
		
		String paststate = getUTDT.getstate(id);//past state
		
		//Upgrade Since.		
		if(paststate.equals("0")==true){	// First Iteration. There isn't past state.
			postResume.modifystate("since",t.toString() ,id);
		}
		else if (paststate.equals(estat)==false){	
			postResume.modifystate("since",t.toString() ,id);
		}
		else{
			
		}
		
		//Sends mail if the state is DOWN and the past estate was different to DOWN
		Sendmail sm = new Sendmail();
		if (paststate.equals("DOWN")==false && estat.equals("DOWN")){
			 sm.send(id, "APDown", "All correct");
		}
		
		double av=0;
		double TTime;
		double timedif=0;

		
		
		//Check if is the first iteration, there is no values on Data COllection so timedif = 0.0
		if (firstiteration ==true){
			timedif=0.0;	
		}
		else{
			timedif = ((double)t.getTime() - (double)lastup)/1000;//seconds
			
		}
			
		// Get the UPTIME and DOWNTIME from the database
		String sdt = getUTDT.getdowntime(id);
		String sup = getUTDT.getuptime(id);
		
		double dt;
		double up;
		
		//Checking the first Iteration 
		if (sdt.equals("0")==true){
			dt=0.0;
		}
		else{
			dt = Double.parseDouble(sdt);
		}
		
		//Checking the first Iteration 
		if (sup.equals("0")==true){
			up=0.0;
		}
		else{
			up = Double.parseDouble(sup);
		}


	
		//Update UPTIME, DOWNTIME, TOTALTIME and AVAILABILITY fields
			
			
			TTime = dt + up;// Totaltime before this iteration.

			if (estat.equals("UP")){ // If the state is UP --> upgrades the Uptime, the Totaltime and the Availability.
				up = up + timedif;
				TTime = TTime + timedif;
				av = up / TTime;
				postResume.modifytime("uptime", up,id);
				postResume.modifytime("totaltime", TTime,id);
				postResume.modifytime("availability", av,id);

			}
			else if (estat.equals("DOWN")){ // If the state is DOWN --> upgrades the Downtime, the Totaltime and the Availability.
				dt = dt + timedif;
				TTime = TTime + timedif;
				av = up / TTime;
				postResume.modifytime("downtime", dt,id);
				postResume.modifytime("totaltime", TTime,id);
				postResume.modifytime("availability", av,id);
			}
			

		
	}
}
