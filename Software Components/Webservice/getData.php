<?php
	
	//process client request (VIA URL)

	header("Content-Type:application/json");


	// Config  
	$dbhost = '127.0.0.1';  
	$dbname = 'AP';  
	  
	// Connect to test database  
	$m = new Mongo("mongodb://$dbhost");  
	$db = $m->$dbname;  
	  
	// select the collection  
	$collection = $db->ResumePoblenou;  
	
	$ipquery = array('IP' => true,'ID'=>true);
	$ipquery = array('IP' => true,'ID'=>true);
	  
	// pull a cursor query  
	$cursor = $collection->find();
	
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