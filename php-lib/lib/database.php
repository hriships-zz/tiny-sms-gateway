<?php

require_once("config.php");

class MySQLDatabase {
	
private $connection;
private $magic_quotes_active;
private $real_escape_string_exists;

	
    function __construct() {
        $this->open_connection();
        $this->magic_quotes_active = get_magic_quotes_gpc();
        $this->real_escape_string_exists = function_exists( "mysql_real_escape_string" );
    }

    public function open_connection() {
            $this->connection = mysql_connect(DB_SERVER, DB_USER, DB_PASS);
            if (!$this->connection) {
                    die("Database connection failed: " . mysql_error());
            } else {
                    $db_select = mysql_select_db(DB_NAME, $this->connection);
                    if (!$db_select) {
                            die("Database selection failed: " . mysql_error());
                    }
            }
    }

    public function close_connection() {
            if(isset($this->connection)) {
                    mysql_close($this->connection);
                    unset($this->connection);
            }
    }

    public function query($sql){
        
        $result = mysql_query($sql, $this->connection);
        $this->confirm_query($result);
        $data = $this->fetch_array($result);
        return $data;
    }
    
    public function insert_query($sql){
        
        $result = mysql_query($sql, $this->connection);
        $this->confirm_query($result);
        return $result;
    }

    public function escape_value( $value ) {
            if( $this->real_escape_string_exists ) { // PHP v4.3.0 or higher
                    // undo any magic quote effects so mysql_real_escape_string can do the work
                    if( $this->magic_quotes_active ) { $value = stripslashes( $value ); }
                    $value = mysql_real_escape_string( $value );
            } else { // before PHP v4.3.0
                    // if magic quotes aren't already on then add slashes manually
                    if( !$this->magic_quotes_active ) { $value = addslashes( $value ); }
                    // if magic quotes are active, then the slashes already exist
            }
            return $value;
    }

    public function fetch_array($result_set){
         return mysql_fetch_array($result_set);

    }

    private function confirm_query($resultset){
        if(!$resultset){
            print("datbase query failed".mysql_error());
            die("last database query :".$this->last_query);
        }

    }

}

$database = new MySQLDatabase();


?>