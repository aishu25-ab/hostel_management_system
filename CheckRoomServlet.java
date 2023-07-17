import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  
    
public class CheckRoomServlet extends HttpServlet  
{    
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
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
          "  <a href=\"complaintsandrequests.html\">Complaints and Requests</a>" +
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
             email=request.getParameter("email");
             String roomtype=request.getParameter("roomtype");
             String ac=request.getParameter("ac");
             String bathroom=request.getParameter("bathroom");
             if(roomtype.equals("single"))
             {  ResultSet rs=stmt.executeQuery("select * from singleroom where email=null and ac='" + ac + "' and bathroom='" + bathroom + "';");
                if(rs.next())
                {  PreparedStatement st = con.prepareStatement("update singleroom set email=? where roomno=?"); 
                   st.setString(1, email); 
                   st.setString(2, rs.getString("roomno"));
                   st.executeUpdate(); 
                   st=con.prepareStatement("delete from roomrequest where email=?");
                   st.setString(1,email);
                   st.executeUpdate();
                }
                else
                {  PreparedStatement st=con.prepareStatement("delete from roomrequest where email=?");
                   st.setString(1,email);
                   st.executeUpdate();
                }
             }
             else if(roomtype.equals("shared"))
             {  ResultSet rs=stmt.executeQuery("select * from sharedroom where (student1=null or student2=null or student3=null) and ac='" + ac + "' and bathroom='" + bathroom + "';");
                PreparedStatement st;
                if(rs.next())
                {  rs=stmt.executeQuery("select * from sharedroom where student1=null and ac='" + ac + "' and bathroom='" + bathroom + "';");
                   if(rs.next())
                   {  st = con.prepareStatement("update sharedroom set student1=? where roomno=?"); 
                      st.setString(1, email); 
                      st.setString(2, rs.getString("roomno"));
                      st.executeUpdate(); 
                      st=con.prepareStatement("delete from roomrequest where email=?");
                      st.setString(1,email);
                      st.executeUpdate();
                   }
                   else
                   {  rs=stmt.executeQuery("select * from sharedroom where student2=null and ac='" + ac + "' and bathroom='" + bathroom + "';");
                      if(rs.next())
                      {  st = con.prepareStatement("update sharedroom set student2=? where roomno=?"); 
                         st.setString(1, email); 
                         st.setString(2, rs.getString("roomno"));
                         st.executeUpdate(); 
                         st=con.prepareStatement("delete from roomrequest where email=?");
                         st.setString(1,email);
                         st.executeUpdate();
                      }
                      else
                      {  rs=stmt.executeQuery("select * from sharedroom where student3=null and ac='" + ac + "' and bathroom='" + bathroom + "';");
                         st = con.prepareStatement("update sharedroom set student3=? where roomno=?");
                         st.setString(1, email); 
                         st.setString(2, rs.getString("roomno"));
                         st.executeUpdate(); 
                         st=con.prepareStatement("delete from roomrequest where email=?");
                   st.setString(1,email);
                   st.executeUpdate();
                      } 
                   }
                   /*st.setString(1, email); 
                   st.setString(2, rs.getString("roomno"));
                   st.executeUpdate(); 
                   st=con.prepareStatement("delete from roomrequest where email=?");
                   st.setString(1,email);
                   st.executeUpdate();*/
                }
                else
                {  st=con.prepareStatement("delete from roomrequest where email=?");
                   st.setString(1,email);
                   st.executeUpdate();
                }
             }
             ResultSet rs = stmt.executeQuery("select student.email,name,year,roomtype,ac,bathroom,dateoccupy,duration from roomrequest,student where roomrequest.email=student.email;");  
             out.println("<table>");
             out.println("<tr><th>Name</th><th>Year</th><th>Room type</th><th>AC</th><th>Bathroom</th><th>Date of Occupying</th><th>Duration</th><tr>"); 
             while(rs.next())
             {  email=rs.getString("student.email");
                String name = rs.getString("name"); 
                int year = rs.getInt("year");    
                roomtype=rs.getString("roomtype");
                ac=rs.getString("ac");
                bathroom=rs.getString("bathroom");
                Date dateoccupy=rs.getDate("dateoccupy");
                String duration=rs.getString("duration");
                out.println("<tr><td>" + name + "</td><td>" + year + "</td><td>" + roomtype + "</td><td>" + ac + "</td><td>" + bathroom + "</td><td>" + dateoccupy + "</td><td>" + duration + "</td><td>" + "<form method=\"POST\" action=\"http://localhost:8080/hms/CheckRoomServlet\" id=\"form4\" onsubmit=\"function x(event){event.preventDefault();}\"><input type=\"hidden\" name=\"email\" value=\"" + email + "\"><input type=\"submit\" value=\"Check Room and Approve\"></form>" + "</td></tr>");
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
                
                