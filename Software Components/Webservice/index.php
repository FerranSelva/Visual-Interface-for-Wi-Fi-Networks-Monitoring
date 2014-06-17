<?php
	
	//process client request (VIA URL)

	header("Content-Type:application/json");

	if(!empty($_GET['name'])){
		//
		$name=$_GET['name'];
		deliver_response(200,"Correcto",$name);
	}
	else {

		//throw invalid request
	}

	function deliver_response($status,$status_message,$data){

		header("HTTP/1.1 $status $status_message");
		$response['status']=$status;
		$response['status_message']=$status_message;
		$response['data']=$data;

		$json_response=json_encode($response);

		echo $json_response;
	}
?>