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
	$collection = $db->DataPoblenou;  
	
	$ID = $_GET['id'];
	
	$idquery = array('ID'=>$ID);
	$sortquery = array('TimeStamp' => -1);

	  
	// This will return documents with the Value of "ID" equal to $ID variable
	$cursor = $collection->find($idquery);
	
	// Sort the cursor. Descendent order.
	$sortedcursor = $cursor->sort($sortquery);
	
 	$document = $cursor->getNext();
 	//$document= iterator_to_array($sortedcursor);
 	
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