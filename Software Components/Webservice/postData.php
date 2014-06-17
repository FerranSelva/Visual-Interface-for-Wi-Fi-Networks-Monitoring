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
	$collection = $db->DataPoblenou;  

	// Adds parameters that are given to insert a new document to Resume collection.
	
	$insertquery = array();
	
	$ID = $_POST['id'];	
	$insertquery['ID'] = $ID;
	
	if(!empty($_POST['status'])){
		$STATUS = $_POST['status'];		
		$insertquery['STATUS'] = $STATUS;
	}

	if(isset($_POST['timestamp'])){
		$timestamp = $_POST['timestamp'];
		$insertquery['TimeStamp'] = $timestamp;
	}
		
	//insert command with fields and content to be inserted.
	$collection->insert($insertquery);
	
	
	$idquery = array('ID'=>$ID);
	$sortquery = array('TimeStamp' => -1);

	$cursor = $collection->find($idquery);
	
	// Sort the cursor. Descendent order.
	$sortedcursor = $cursor->sort($sortquery);
	
 	$document= iterator_to_array($sortedcursor);
	
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