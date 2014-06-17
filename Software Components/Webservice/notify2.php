<?php
require_once "Mail.php";

$from = '<bub.project.apcontrol@gmail.com>';
$to = '<bub.project.apcontrol@gmail.com>';
$subject = 'Hi!';
$body = "Hi,\n\nHow are you?";

$headers = array(
    'From' => $from,
    'To' => $to,
    'Subject' => $subject
);

$smtp = Mail::factory('smtp', array(
        'host' => 'tls://smtp.gmail.com',
        'port' => '587',
        'auth' => true,
        'username' => 'bub.project.apcontrol@gmail.com',
        'password' => 'apcontrol'
    ));

$mail = $smtp->send($to, $headers, $body);


if (PEAR::isError($mail)) {
    echo('<p>' . $mail->getMessage() . '</p>');
} else {
    echo('<p>Message successfully sent!</p>');
}
?>
