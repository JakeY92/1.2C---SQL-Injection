package edu.deakin.sit218.coachwebapp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionTest {

	public static void main(String[] args) {
		//Ask user to give his/her name
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your area of knowledge: ");
		String question = scanner.nextLine();
		scanner.close();
		
        String jdbcURL = "jdbc:mysql://localhost/qadb?" +
                "user=qadbadmin&password=qadbadmin";
		try {
			System.out.println("Connecting to Database");
			Connection conn =
		       DriverManager.getConnection(jdbcURL);
			
			//Perform a query to select all users whose name
			//is equal to the name entered in the command line
			Statement statement =  conn.createStatement();
			String query = "SELECT * FROM client WHERE question like '%"+
					question+"%'";
			System.out.print("Executying query: ");
			System.out.println(query);
			ResultSet result = statement.executeQuery(query);

			//Displaying query results
			while (result.next()) {
		        int clientID = result.getInt("qaid");
		        String answer = result.getString("answer");
		        question = result.getString("question");
		        System.out.println(clientID + ", " + question + ", " + answer);
			}
			System.out.println("Connection successful!");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
	
}
