import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  
    
public class ViewMessBill extends HttpServlet  
{    
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
      {  
         PrintWriter out = response.getWriter();  
         response.setContentType("text/html");  
         out.print("<html>" +
          "<title>View Outpasses</title>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"wardenhomepage.html\">Home</a>" +
          "  <a href=\"messbill.html\">Mess Bill</a>" +
          "  <a href=\"AccountServlet\">My account</a>" +
          "</p>" +
          "<hr noshade>" +
          "<br>"); 
         try 
         {  
             Class.forName("com.mysql.jdbc.Driver");  
             Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/hms", "root", "admin"); 
             HttpSession session=request.getSession(false);
             String email=(String)session.getAttribute("email"); 
             // Here dsnname- mydsn,user id- system(for oracle 10g),password is pintu.  
             Statement stmt = con.createStatement();  
             ResultSet rs = stmt.executeQuery("select student.email, name,year,month,amount,status from messbills,student where student.email=messbills.emailid");  
             //out.println("<table border=1 width=50% height=50%>");  
             out.println("<table>");
             out.println("<tr><th>Name</th><th>Year</th><th>Month</th><th>Amount</th><th>Status</th><tr>");  
             while (rs.next()) 
             {   String name = rs.getString("name"); 
                 int year = rs.getInt("year");    
                 String month = rs.getString("month");
                 String amount = rs.getString("amount"); 
                 String status = rs.getString("status");  
                 
                 out.println("<tr><td>" + name + "</td><td>" + year + "</td><td>" + month + "</td><td>" + amount + "</td><td>" + status + "</td></tr>");
             }  
             out.println("</table>");  
             out.println("</html></body>");  
             con.close();  
            }  
             catch (Exception e) 
            {  
             out.println(e);  
         }  
     }  
 }  