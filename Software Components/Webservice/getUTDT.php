<?php
	
	//process client request (VIA URL)

	header("Content-Type:application/json");

	$ID = $_GET['id'];
	
	// Host and DataBase name  
	$dbhost = '127.0.0.1';  
	$dbname = 'AP';  
	  
	// Connect to test database  
	$m = new Mongo("mongodb://$dbhost");  
	$db = $m->$dbname;  
	  
	// select the collection  
	$collection = $db->ResumePoblenou;  
	
	// Fields of the element.
	$ipquery = array('ID' => true,'UPTIME'=>true,'DOWNTIME'=>true,'STATUS'=>true);
	
	// Element that it's needed to get the Data
	$idquery = array('ID'=>$ID);
	  
	// pull a cursor query   
	$cursor = $collection->find($idquery);
	//$cursor = $collection->find();
	
	$ipcursor = $cursor->fields($ipquery);
	$document= iterator_to_array($cursor);
 	
	deliver_response(200,"Correcto",$document);

	function deliver_response($status,$status_message,$data){

		header("HTTP/1.1 $status $status_message");
		$response['status']=$status;
		$response['status_message']=$status_message;
		$response['data']=$data;

		$json_response=json_encode($response);

		echo $json_response;
	}


?>