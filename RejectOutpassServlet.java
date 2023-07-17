import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  
    
public class RejectOutpassServlet extends HttpServlet  
{    
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
      {  
         PrintWriter out = response.getWriter();  
         response.setContentType("text/html");  
         out.print("<html>" +
          "<title>View Outpasses</title>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"wardenhomepage.html\">Home</a>" +
          "  <a href=\"ShowOutpass\">Outpass</a>" +
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
             PreparedStatement st = con.prepareStatement("update outpass set status=? where email=?"); 
             st.setString(1, "rejected"); 
             st.setString(2, request.getParameter("email"));
             st.executeUpdate(); 
             ResultSet rs = stmt.executeQuery("select outpass.email,name,year,hostel,roomno,fromdate,todate,fromtime,totime,reason from outpass,student where student.email=outpass.email and student.warden='"+email+"' and status='requested';");  
             //out.println("<table border=1 width=50% height=50%>");  
             out.println("<table>");
             out.println("<tr><th>Name</th><th>Year</th><th>Hostel name</th><th>Room number</th><th>From Date</th><th>From Time</th><th>To Date</th><th>To Time</th><th>Reason</th><tr>"); 
             while (rs.next()) 
             {  
                 email=rs.getString("outpass.email");
                 String name = rs.getString("name"); 
                 int year = rs.getInt("year");    
                 String hostel = rs.getString("hostel");
                 String roomno = rs.getString("roomno"); 
                 String fromdate = rs.getString("fromdate"); 
                 String fromtime = rs.getString("fromtime"); 
                 String todate = rs.getString("todate");  
                 String totime = rs.getString("totime");  
                 String reason = rs.getString("reason");  
                 
                 out.println("<tr><td>" + name + "</td><td>" + year + "</td><td>" + hostel + "</td><td>" + roomno + "</td><td>" + fromdate + "</td><td>" + fromtime + "</td><td>" + todate + "</td><td>" + totime + "</td><td>" + reason + "</td><td>" + "<form method=\"POST\" action=\"http://localhost:8080/hms/ApproveOutpassServlet\" id=\"form4\" onsubmit=\"function x(event){event.preventDefault();}\"><input type=\"hidden\" name=\"email\" value=\"" + email + "\"><input type=\"submit\" value=\"Approve\"></form>" + "</td><td>" + "<form method=\"POST\" action=\"http://localhost:8080/hms/RejectOutpassServlet\" id=\"form5\" onsubmit=\"function x(event){event.preventDefault();}\"><input type=\"hidden\" name=\"email\" value=\"" + email + "\"><input type=\"submit\" value=\"Reject\"></form>" + "</td></tr>");
 
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