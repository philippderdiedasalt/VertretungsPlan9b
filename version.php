<?php
    //Verbindung zur Datenbank herstellen
    mysql_connect("mysql.hostinger.de", "u187208455_9b","9berta") or die ("Verbindung nicht möglich");
    mysql_select_db("u187208455_app") or die ("Datenbank existiert nicht");
 
    //Alle Kundendaten selektieren
    $q = mysql_query("SELECT android FROM version");
    while($e=mysql_fetch_assoc($q))
    $output[]=$e;
    print(json_encode($output));
    mysql_close();
?>		
