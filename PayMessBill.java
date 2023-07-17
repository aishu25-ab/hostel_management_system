import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  

public class PayMessBill extends HttpServlet { 
	private static final long serialVersionUID = 1L; 

	protected void doPost(HttpServletRequest request, 
HttpServletResponse response) 
		throws ServletException, IOException 
	{ 
		try { 

			// Initialize the database 
			String dbDriver = "com.mysql.jdbc.Driver"; 
            String dbURL = "jdbc:mysql://localhost:3306/hms"; 
            String dbUsername = "root"; 
            String dbPassword = "Saibaba@4341"; 
        HttpSession session=request.getSession(false);
        String email=(String)session.getAttribute("email");;
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL, dbUsername,   dbPassword);
        email = request.getParameter("email");
        String month = request.getParameter("month"); 
        String query = "SELECT amount, status FROM messbills WHERE email = ? and month = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, email);
        stmt.setString(2, month);
        ResultSet rs = stmt.executeQuery();
			PrintWriter out = response.getWriter(); 
            rs.next();
            out.println("<html><body>amount and status:<br>");
            out.println(rs.getString("amount"));
            out.println(rs.getString("status"));
            out.println("<br><form action= \"http://localhost:8086/hms/Payment\" method=\"post\"><button type= \"submit\">Pay</button></form></body></html>");
            con.close(); 
 
		} 
		catch (Exception e) { 
                  System.out.println(e);
			e.printStackTrace(); 
		} 
	} 
} 