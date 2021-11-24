<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PHP factorial</title>
</head>
<body>

<?php

function iter_factorial($n) {
    $fact = 1;
    do {
        $fact *= $n;
        $n--;
    }while ($n > 1);

    return $fact;
}

function rec_factorial($n) {

    if ($n === 1){
        return $n;
    }
    return $n * rec_factorial($n - 1);
}

$n1 = 4;
$n2 = 5;
echo "<p>The factorial of ".$n1." is ".iter_factorial($n1).
    " (iterative mode)</p>";
echo "<p>The factorial of ".$n2." is ".rec_factorial($n2).
    " (recursive mode)</p>";

?>

</body>
</html>