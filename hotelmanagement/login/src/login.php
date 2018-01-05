<?php
# This file (login.php) is in ubuntu server. 
# Don't actually include this file in this android project. This file is archived for record.
# location: /var/www/html/login.php
header('content-type: text/html; charset=utf-8'); 
$connect = mysqli_connect("localhost", "username", "password") or die("Cannot access to SQL server.");

mysqli_query("SET NAMES UTF8");
mysql_select_db("testhotel", $connect);


session_start();

$u_id = $_POST[u_id];
$sql = "SELECT u_pw FROM login WHERE u_id = '$u_id'";

$result = mysqli_query($sql);


// result of SQL query
if ($result) {
    $row = mysql_fetch_array($result);
    
    if (is_null($row[u_pw])) {
        echo "Cannot find ID";
    } else {
        echo "$row[u_pw]";
    }
} else {
    echo mysql_errno($connect);
}
?>