package edu.deakin.sit218.coachwebapp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.cfg.Configuration;

import edu.deakin.sit218.coachwebapp.entity.Client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestJDBC {
	
	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost/qadb?" +
                "user=qadbadmin&password=qadbadmin";
		try {
			System.out.println("Connecting to Database");
			Connection conn =
		       DriverManager.getConnection(jdbcURL);
			conn.close();
			System.out.println("Connection successful!");
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		//Create a session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Client.class)
				.buildSessionFactory();
		//Create session
		Session session = factory.getCurrentSession();
		
		Client client = new Client("How many legs do dogs have?", "Four");
		
		try {
		//start a transaction
		session.beginTransaction();
		
		//save the client object
		session.save(client);
		
		//commit transaction
		session.getTransaction().commit();
		}finally {
			session.close();
			factory.close();
		}

	}	
	
}