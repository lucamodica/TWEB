<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Teacher</title>
</head>
<body>

<?php

    //DB connection
    $conn = new mysqli('', '',
        '', '');
    if ($conn->connect_error){
        echo "Connection failed: " . $conn->connect_error;
    }
    else{

        echo "<p>Connected to the DB!</p>";

        //Retrieve credentials inserted
        $acc = $_POST["account"];
        $pass = $_POST["password"];

        //Try to login
        $s = "select account, role from utente
                where account = '".$acc."' and password = '".strtoupper(md5($pass))."'";
        $ris = $conn->query($s);

        if (!$ris) {
            echo "<p>There was an error running the query [" . $conn->error . "]</p>";
        }
        else{
            $row = $ris -> fetch_assoc();

            if (empty($row)){
                echo "<p>Credential (account = ".$acc.", password = ".md5($pass).") not correct.</p>";
            }
            else{
                echo "<p>".$row["account"]." successfully logged in! (role: ".$row["role"].")</p>";
            }
        }




    }




?>

</body>
</html>