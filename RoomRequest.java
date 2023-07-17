import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  
    
public class RoomRequest extends HttpServlet  
{    
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
      {  
         PrintWriter out = response.getWriter();  
         response.setContentType("text/html");  
         out.print("<html>" +
          "<title>View Room Requests</title>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"supervisorhomepage.html\">Home</a>" +
          "  <a href=\"rooms.html\">Rooms</a>" +
          "  <a href=\"rooms.html\">Complaints and Requests</a>" +
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
             ResultSet rs = stmt.executeQuery("select student.email,name,year,roomtype,ac,bathroom,dateoccupy,duration from roomrequest,student where roomrequest.email=student.email;");  
             out.println("<table>");
             out.println("<tr><th>Name</th><th>Year</th><th>Room type</th><th>AC</th><th>Bathroom</th><th>Date of Occupying</th><th>Duration</th><tr>"); 
             while(rs.next())
             {  email=rs.getString("student.email");
                String name = rs.getString("name"); 
                int year = rs.getInt("year");    
                String roomtype=rs.getString("roomtype");
                String ac=rs.getString("ac");
                String bathroom=rs.getString("bathroom");
                Date dateoccupy=rs.getDate("dateoccupy");
                String duration=rs.getString("duration");
                out.println("<tr><td>" + name + "</td><td>" + year + "</td><td>" + roomtype + "</td><td>" + ac + "</td><td>" + bathroom + "</td><td>" + dateoccupy + "</td><td>" + duration + "</td><td>" + "<form method=\"POST\" action=\"http://localhost:8080/hms/CheckRoomServlet\" id=\"form4\" onsubmit=\"function x(event){event.preventDefault();}\"><input type=\"hidden\" name=\"email\" value=\"" + email + "\"><input type=\"hidden\" name=\"roomtype\" value=\"" + roomtype + "\"><input type=\"hidden\" name=\"ac\" value=\"" + ac + "\"><input type=\"hidden\" name=\"bathroom\" value=\"" + bathroom + "\"><input type=\"submit\" value=\"Check Room and Approve\"></form>" + "</td></tr>");
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
                
                