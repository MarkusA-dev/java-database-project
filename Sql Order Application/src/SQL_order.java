
//Import required module for sql connection
import java.sql.*;

//Declaring the class i will be using for connecting and handling the SQL requests
public class SQL_order{
	
	//Declaring the  connection variable
	Connection conn = null;
	
	//Making the Connection String to connect to localhost:Northwind as a user i created for this. Encryption had to be turned off due to an issue with the TLS of SSL
	String conString = "jdbc:sqlserver://localhost;database=Northwind;user=pyuser;password=pypass;encrypt=false;";
	
	//Declaring the statement variable. this will is the variable that passes commands to the SQL server and gets the results
	Statement stmt = null;
	
	//Initialization function that gets the sqlServer JDBC driver class
	public SQL_order() 
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Method for trying the connection to the database with the previously made connection and connection string
	public void connect() 
	{
		//The connecting is surrounded by a try catch statement for error handling
		 try
	        {
			 	//Giving the previously created conn variable of the connection. the DriverManager.getConnection takes in the connection string and uses it to connect to the database
	            conn = DriverManager.getConnection(conString);
	            
	            //After connecting print into the console to confirm that the connection was successful
	            System.out.println("Successfully Connected");
	        }
	        catch (SQLException e)
	        {
	            System.out.println(e.toString());
	        }
	}
	
	//Method for closing the connection, only to be called after connect has been used
	public void CloseConnection()
    {
		//Again surrounded by try catch statement for exception handling
		try
        {
			//Passing the command to the conn variable to close the connection to the server
            this.conn.close();
            
            //Print to the console if the connection was successfully closed
            System.out.println("Connection successfully closed");
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }

    }
}