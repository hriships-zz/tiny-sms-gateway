<?php

header("Content-Type:application/json");

$req_id = $_REQUEST['id'];

if($req_id!=null){
        /*include your database lbrary here*/
        require_once 'lib/database.php';
        
        /*for secutiy and php magic quotes piurpose*/
	$id =  $database->escape_value($req_id);
        
                    $database->query("TRUNCATE regkey");
        $status =   $database->insert_query("INSERT INTO regkey(key_value) VALUES ('$id')");
        
        if($status){
            $_SESSION['key']= $id;
            echo json_encode(array("msg"=>$status),TRUE);
        }
        else{
            echo json_encode(array("msg"=>FALSE),TRUE);
        }
        
        $database->close_connection();
}
else {
	echo json_encode(array("msg"=>FALSE),TRUE);
}
?>