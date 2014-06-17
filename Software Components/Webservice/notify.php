<?php

	require_once "Mail.php";
	require_once "Mail/mime.php";
	
	$ID = $_POST['id'];
	$TYPE = $_POST['type'];
	$e = $_POST['e'];//Exception
	$to= 'bub.project.apcontrol@gmail.com';	
	$from= 'bub.project.apcontrol@gmail.com';
	$host='ssl://smtp.gmail.com';
	$port="465";
	$username='bub.project.apcontrol';
	$password='apcontrol';

	
	if($TYPE=='ProgramStop'){
		$body= 'The Access program stopped.
		 Please restart the Raspberry PI. Exception: '.$e;
		$subject='Program Stopped';
	}

	if($TYPE=='APDown'){
		$body= 'The access point with ID:'.$ID.' is DOWN.';
		$subject= $ID.' is Down';
	}
	
	$headers = array ('From' => $from,
   		'To' => $to,
   		'Subject' => $subject);
	
	$smtp = Mail::factory('smtp',
   		array ('host' => $host,
     		'port' => $port,
		'auth' => true,
     		'username' => $username,
     		'password' => $password));

	$mail_object = $smtp->send($to, $headers, $body);

 if (PEAR::isError($mail_object)) {
   echo("<p>" . $mail_object->getMessage() . "</p>");
  } else {
   echo("<p>Message successfully sent!</p>");
  }

?>
