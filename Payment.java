import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  
public class Payment extends HttpServlet { 
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
            String dbPassword = "admin"; 
  
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL, dbUsername,   dbPassword);
        PrintWriter out = response.getWriter(); 
        HttpSession session=request.getSession(false);
            String email=(String)session.getAttribute("email");
        out.print("<html><body><p>Paid Successfully!</p>");
        String query = "update messbills set status = ? where email=" +email;
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, "Paid");
        ResultSet rs = stmt.executeQuery();
        String querya = "select status from messbills where email =" +email;
        PreparedStatement stmta = con.prepareStatement(querya);
        ResultSet rsa = stmta.executeQuery();
            rs.next();
            out.println("<p>New status after paying is:<br>");
            out.println(rs.getString("status"));
            out.println("</body></html>");
            con.close(); 
 
		} 
		catch (Exception e) { 
                  System.out.println(e);
			e.printStackTrace(); 
		} 
	} 
} 