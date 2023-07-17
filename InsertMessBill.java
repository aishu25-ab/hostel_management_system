import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.*;

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

// Import Database Connection Class file 
//import code.DatabaseConnection; 

// Servlet Name 
//@WebServlet("/InsertData") 
public class InsertMessBill extends HttpServlet { 
	private static final long serialVersionUID = 1L; 

	protected void doPost(HttpServletRequest request, 
HttpServletResponse response) 
		throws ServletException, IOException 
	{ 
		try { 

			// Initialize the database 
			String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = "jdbc:mysql:// localhost:3306/hms"; 
        // Database name to access 
        String dbUsername = "root"; 
        String dbPassword = "admin"; 
  
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL, 
                                                     dbUsername,  
                                                     dbPassword); 

			// Create a SQL query to insert data into demo table 
			// demo table consists of two columns, so two '?' is used 
			PreparedStatement st = con 
				.prepareStatement("insert into messbills values(?, ?, ?, ?)"); 

			// For the first parameter, 
			// get the data using request object 
			// sets the data to st pointer 
			st.setString(1, request.getParameter("email")); 
               st.setString(2, request.getParameter("month")); 
			st.setString(3, request.getParameter("amount")); 
			st.setString(4, request.getParameter("status")); 

			// Execute the insert command using executeUpdate() 
			// to make changes in database 
			st.executeUpdate(); 

			// Close all the connections 
			st.close(); 
			con.close(); 

			// Get a writer pointer 
			// to display the successful result 
			PrintWriter out = response.getWriter(); 
			out.println("<b>Successfully Inserted</b>"); 
		} 
		catch (Exception e) { 
                  System.out.println(e);
			e.printStackTrace(); 
		} 
	} 
} 