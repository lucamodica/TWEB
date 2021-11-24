<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Teacher</title>
</head>
<body>

<?php

    //DB connection
    $conn = new mysqli('svr.educationhost.cloud', 'rjjmdpfx_test',
        'pelataCaramellata42!', 'rjjmdpfx_test');
    if ($conn->connect_error){
        echo "Connection failed: " . $conn->connect_error;
    }
    else{

        echo "<p>Connected to the DB!</p>";

        //Retrieve credentials inserted
        $acc = $_POST["account"];
        $pass = $_POST["password"];

        //Login
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
                echo "<p>".$row["account"]." successfully logged in! (role: ".$row["role"].")</p><br>";

                //Fetch all teacher from DB
                $t = "select * from sdfds";
                $ris = $conn->query($t);

                if (!$ris){
                    echo "<p>There was an error running the query [" . $conn->error . "]</p>";
                }
                else{
                    echo $ris->num_rows;
                    $row = $ris -> fetch_assoc();

                    if (empty($row)){
                        echo "<p>There are no teacher at the moment</p>";
                    }
                    else{
                        echo "<ul>";

                        while ($r = $row){
                            echo "<li>".$row."</li>";
                        }

                        echo "</ul>";
                    }
                }

            }
        }

    }




?>

</body>
</html>