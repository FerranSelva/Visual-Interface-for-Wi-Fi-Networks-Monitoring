<?php
	
	//process client request (VIA URL)

	header("Content-Type:application/json");
	
	// Host and DataBase name  
	$dbhost = '127.0.0.1';  
	$dbname = 'AP';  
	  
	// Connect to test database  
	$m = new Mongo("mongodb://$dbhost");  
	$db = $m->$dbname;  
	  
	// select the collection  
	$collection = $db->ResumePoblenou;  

	
	// Adds parameters that are given to update the Resume table.
	// Parameters non empty will be added to updatequery variable 
	
	$updatequery = array();
	
	if(!empty($_POST['status'])){
		$STATUS = $_POST['status'];		
		$updatequery['STATUS'] = $STATUS;
	}

	if(isset($_POST['uptime'])){
		$UPTIME = $_POST['uptime'];
		$updatequery['UPTIME'] = $UPTIME;
	}
	
	if(isset($_POST['downtime'])){
		$DOWNTIME = $_POST['downtime'];
		$updatequery['DOWNTIME'] = $DOWNTIME;
	}
	
	if(isset($_POST['since'])){
		$SINCE = $_POST['since'];
		$updatequery['SINCE'] = $SINCE;
	}
	
	if(isset($_POST['totaltime'])){
		$TOTALTIME = $_POST['totaltime'];
		$updatequery['TOTALTIME'] = $TOTALTIME;
	}
	
	if(isset($_POST['availability'])){
		$av = $_POST['availability'];
		$updatequery['AVAILABILITY'] = $av;
	}
	
	$ID = $_POST['id'];
	
	//Update command: 1st Argument: document to be updated, 2nd Argument: Fields and content to be modified.
	
	$collection->update(array("ID" => $ID), array('$set' => $updatequery));



?>