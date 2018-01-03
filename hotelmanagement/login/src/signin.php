<?php
# This file (signin.php) is in ubuntu server. 
# Don't actually include this file in this android project. This file is archived for record.
# location: /var/www/html/signin.php
  
error_reporting(E_ALL); 
ini_set('display_errors', 1); 

# TODO: replace localhost, root, root_password, DB_name to actual information. It doesn't have to be root.
$link = mysqli_connect("localhost", "root", "root_password", "DB_name"); 

if (!$link) {
   echo "Failed to connect to MySQL: ";
   echo mysqli_connect_error();
   exit();
}  

mysqli_set_charset($link, "utf8");  

// reads and stores POSTed value
$u_id = isset($_POST['u_id']) ? $_POST['u_id'] : '';  
$u_pw = isset($_POST['u_pw']) ? $_POST['u_pw'] : '';  


if ($u_id != "" and $u_pw != "") {
    $sql = "INSERT INTO login VALUES ('$u_id','$u_pw')"; 
    $result = mysqli_query($link, $sql);  

    if ($result) {  
       echo "SQL Query: Succeed";  
    }
    else {  
       echo "SQL Query Error occurred: "; 
       echo mysqli_connect_error($link);
    }
} else {
    echo "Please enter login credentials";
}

mysqli_close($link);
?>

<?php
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<!DOCTYPE html>
<html>
	<head>
		<title>Hotel Management</title>
	</head>
    <body>
        <form action="" method="POST">
        	ID: <input type="text" id="u_id" name="u_id" />
        	PW: <input type="text" id="u_pw" name="u_pw" />
            <input type = "submit" />
        </form>
    </body>
</html>
<?php
}
?>