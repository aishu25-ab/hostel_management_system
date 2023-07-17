import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  
    
public class ViewService extends HttpServlet  
{    
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
      {  
         PrintWriter out = response.getWriter();  
         response.setContentType("text/html");  
         out.print("<html>" +
          "<title>View Service Requests</title>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"supervisorhomepage.html\">Home</a>" +
          "  <a href=\"rooms.html\">Rooms</a>" +
          "  <a href=\"complaintsandrequestssupervisor.html\">Rooms</a>" +
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
             ResultSet rs = stmt.executeQuery("select splreq.email, name,year,hostel,roomno,reqdetails from splreq,student where student.email=splreq.email;");  
             //out.println("<table border=1 width=50% height=50%>");  
             out.println("<table>");
             out.println("<tr><th>Name</th><th>Year</th><th>Hostel name</th><th>Room number</th><th>Request details</th><tr>");  
             while (rs.next()) 
             {   String name = rs.getString("name"); 
                 int year = rs.getInt("year");    
                 String hostel = rs.getString("hostel");
                 String roomno = rs.getString("roomno"); 
                 String reqdetails = rs.getString("reqdetails"); 
                 
                 out.println("<tr><td>" + name + "</td><td>" + year + "</td><td>" + hostel + "</td><td>" + roomno + "</td><td>" + reqdetails + "</td></tr>");


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