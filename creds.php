<HTML>
<HEAD>
<TITLE> Stock Ticker </TITLE>
<HEAD>
	<BODY>
		<?php
		   //phpinfo();
			$dbcnx = @mysql_connect("localhost", "sqluser", "password");
			if (!$dbcnx) {
		 		echo( "<P>Unable to connect to the database server at this time.</P>" );
		  		exit();
			}
			
			mysql_select_db("hackerati", $dbcnx);
			if (! @mysql_select_db("hackerati") ) {
		   	echo( "<P>Unable to locate the database at this time.</P>" );
		  		exit();
			}
		?>
		
		<P> Here are all the stocks in our database: </P>
		
		<?php
			$result = mysql_query(
			          "SELECT * FROM stockprices WHERE stockName = 'Google Inc.'");
			if (!$result) {
			  echo("<P>Error performing query: " .
			       mysql_error() . "</P>");
			  exit();
			}
			
			while ($row = mysql_fetch_array($result) ) {
			  //Use these returned rows for display:	
			  //e.g. echo("<P>" . $row["stockName"] . $row["ask"] . "</P>");
			  //upcoming will be pushed to json objects for use with D3
			}		
		?>		
	</body>
</html>