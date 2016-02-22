<?php

/*
 * Description of SmsManager
 *
 * @author root
 */
class SmsManager {
    /*variables to send sms*/
    public  $to;
    public  $message;
    private $key;
    
    /*Initialize the key*/
    function __construct() {
        /*This is google project API key*/
        $this->key="your API key here";
    }
    
    /*function that will return registration id of android device*/
    private function getId(){
        require_once 'database.php';
        $id = $database->query("SELECT key_value AS reg_id FROM regkey");
        
        if($id['reg_id']){
            return array($id['reg_id']);
        }
        else {
            return false;
        }
        
    }
    
    /*send message to goolge cloud messaging server*/
    public function sendSMS($to,$message)
    {
        $this->to       = $to;
        $this->message  = $message;
        
        $headers = array("Content-Type:" . "application/json","charset=utf-8","Authorization:" . "key=".$this->key);
        $data = array(
            'data'              => array('to'=>  $this->to,'message'=>  $this->message),
            'registration_ids'  => $this->getId()
        );

        $ch = curl_init();

        curl_setopt( $ch, CURLOPT_HTTPHEADER, $headers );
        curl_setopt( $ch, CURLOPT_URL, "https://android.googleapis.com/gcm/send" );
        curl_setopt( $ch, CURLOPT_SSL_VERIFYHOST, 0 );
        curl_setopt( $ch, CURLOPT_SSL_VERIFYPEER, 0 );
        curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
        curl_setopt( $ch, CURLOPT_POSTFIELDS, json_encode($data) );

        $response = curl_exec($ch);
        curl_close($ch);

        return $response;
    }
}

?>
