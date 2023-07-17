import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  
    
public class OutpassDisplay extends HttpServlet  
{    
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
      {  
         PrintWriter out = response.getWriter();

         response.setContentType("text/html");  
         try 
         {  
             String dbDriver = "com.mysql.jdbc.Driver"; 
             Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/hms", "root", "admin");  
             // Here dsnname- mydsn,user id- system(for oracle 10g),password is pintu.  
             Statement stmt = con.createStatement();  
             //request.getRequestDispatcher("studenthomepage.html").include(request, response);  
             //Cookie ck[]=request.getCookies();
             //String email=ck[0].getValue();
             //String table=ck[0].getValue(); 
             HttpSession session=request.getSession(false);
             String email=(String)session.getAttribute("email");
             String table=(String)session.getAttribute("table");
             //response.sendRedirect("myaccount.html");
             ResultSet rs = stmt.executeQuery("select * from outpass where email='"+email+"';");
             rs.next();
               out.print("<html>" +
          "<title>Outpass</title>" +
          "<head>" +
          "    <style>" +
          "        a{" +
          "            padding: 10px 80px 0px 0px;" +
          "            font-size: 2.0em;" +
          "        }" +
          "    </style>" +
          "</head>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"studenthomepage.html\">Home</a>" +
          "  <a href=\"room.html\">Rooms</a>" +
          "  <a href=\"outpass.html\">Outpass</a>" +
          "  <a href=\"complaintsandrequests.html\">Complaints and requests</a>" +
          "  <a href=\"workshops.html\">Mess Bill</a>" +
          "  <a href=\"AccountServlet\">My account</a>" +
          "</p>" +
          "<hr noshade>" +
          "<br>" +
          "<h1>My Outpass</h1><br>" +
                   "</body>" +
          "</html>");
         
               Date dat=rs.getDate("date");
               Date fromdate=rs.getDate("fromdate");
               Date todate=rs.getDate("todate");
               Time outtime=rs.getTime("fromtime");
               Time intime=rs.getTime("totime");
               String reason=rs.getString("reason");
               String status=rs.getString("status");
              
          
               out.println("<table><tr><th>Email</th><td>"+email+"</td></tr><tr><th>Date</th><td>"+dat+"</td></tr><tr><th>FromDate</th><td>"+fromdate+"</td></tr><tr><th>Todate</th><td>"+todate+"</td></tr><tr><th>FromTime</th><td>"+outtime+"</td></tr><tr><th>Totime</th><td>"+intime+"</td></tr><tr><th>Reason</th><td>"+reason+"</td></tr><tr><th>Status</th><td>"+status+"</td></tr></table>");
             }
             
             
          catch(Exception e)
    {  out.println("Exception" + e);
    }
       }
}