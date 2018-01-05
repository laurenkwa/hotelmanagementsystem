<?php
# This file (login.php) is in ubuntu server. 
# Don't actually include this file in this android project. This file is archived for record.
# location: /var/www/html/login.php
error_reporting(E_ALL); 
ini_set('display_errors', 1); 


# TODO: replace localhost, root, root_password, DB_name to actual information. It doesn't have to be root.
$link = mysqli_connect("localhost", "root", "root_password", "DB_name"); 

if (!$link) {
   echo "Failed to connect to SQL: ";
   echo mysqli_connect_error();
   exit();
}  

mysqli_set_charset($link, "utf8"); 


// Reads and stores POSTed value
$u_id = isset($_POST['u_id']) ? $_POST['u_id'] : '';
$u_pw = isset($_POST['u_pw']) ? $_POST['u_pw'] : '';

$db_id = "";
$db_pw = "";


if ($u_id != "" && $u_pw != "") {
    $sql = "SELECT * FROM login WHERE u_id='$u_id'";
    $result = mysqli_query($link, $sql);
    $data = array();

    $data = mysqli_fetch_row($result);
    $db_id = $data[0];
    $db_pw = $data[1];

    if ($result) {
        echo "SQL Query: Succeed";

        if ($db_id == $u_id && $db_pw == $u_pw) {
            # TODO: replace index.php to actual destination page.
            header("Location: index.php");
            die();
        } else {
            echo "The id and password you entered did not match our records. Please double-check and try again.";
        }
    } else {
        echo "SQL Query Error occurred: "; 
        echo mysqli_connect_error($link);
    }
} else {
    echo "Please enter valid ID and password.";
}

mysqli_close($link);
?>

<?php
if (!$android) {
?>

<!doctype html>
<html>
    <body>
        <form action="" method="POST">
            ID: <input type="text" id="u_id" name="u_id" />
            PW: <input type="text" id="u_pw" name="u_pw" />
            <input type="submit" />
        </form>
    </body>
</html>

<?php
}
?>