<?php
/*To send Message include SMS manager*/
require_once 'lib/SmsManager.php';

/*Create instance of SmsManager class*/
$sms=new SmsManager();

/*Call method with sender number and message as string argument*/
$response = $sms->sendSMS('Sender Number', 'message');

echo $response;
?>
