import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.sql.*;  
    
public class AccountServlet extends HttpServlet  
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
             ResultSet rs = stmt.executeQuery("select * from "+table+" where email='"+email+"';");
             rs.next();
             
             if(table.equals("student")){
               out.print("<html>" +
          "<title>My account</title>" +
          "<head>" +
          "<style>\n" +
" h1,\n" +
" h2,\n" +
" body {\n" +
" color: white;\n" +
" }\n" +
" a {\n" +
" padding: 10px 30px 30px 20px;\n" +
" font-size: 2em;\n" +
" color: yellow;\n" +
" text-decoration: none;\n" +
" }\n" +
"\n" +
" img {\n" +
" vertical-align: middle;\n" +
" }\n" +
"\n" +
" h1 {\n" +
" font-family: \"Gill Sans\", \"Gill Sans MT\", Calibri, \"Trebuchet MS\",\n" +
" sans-serif;\n" +
" font-size: 2.5em;\n" +
" margin-top: 30px;\n" +
" margin-bottom: 10px;\n" +
" }\n" +
"\n" +
" p {\n" +
" font-size: 1.2em;\n" +
" margin-bottom: 20px;\n" +
" }\n" +
" /body {\n" +
" background-image: url(\"https://img.freepik.com/free-photo/vivid-blurred-colorful-wallpaper-background_58702-3798.jpg\");\n" +
" background-repeat: no-repeat;\n" +
" background-attachment: fixed;\n" +
" background-size: 100% 100%;\n" +
" }/\n" +
" body {\n" +
" background-color: black;\n" +
" }\n" +
" </style>" +
          "</head>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"studenthomepage.html\">Home</a>" +
          "  <a href=\"room.html\">Rooms</a>" +
          "  <a href=\"outpass.html\">Outpass</a>" +
          "  <a href=\"complaintsandrequests.html\">Complaints and requests</a>" +
          "  <a href=\"paymessbill.html\">Mess Bill</a>" +
          "  <a href=\"AccountServlet\">My account</a>" +
          "</p>" +
          "<hr noshade>" +
          "<br>" +
          "<h1>My Account</h1><br>" +
          "</body>" +
          "</html>");
               String name=rs.getString("name");
               String phoneno=rs.getString("phoneno");
               int year=rs.getInt("year");
               String dept=rs.getString("dept");
               String hostel=rs.getString("hostel");
               String roomno=rs.getString("roomno");
               String warden=rs.getString("warden");
               rs = stmt.executeQuery("select name from warden where email='"+warden+"';");
               rs.next();
               warden=rs.getString("name");
               out.println("<table><tr><th>Name</th><td>"+name+"</td></tr><tr><th>Email id</th><td>"+email+"</td></tr><tr><th>Phone number</th><td>"+phoneno+"</td></tr><tr><th>Year</th><td>"+year+"</td></tr><tr><th>Department</th><td>"+dept+"</td></tr><tr><th>Hostel</th><td>"+hostel+"</td></tr><tr><th>Room number</th><td>"+roomno+"</td></tr><tr><th>Warden</th><td>"+warden+"</td></tr></table>");
             }
             else if(table.equals("warden")){
               out.print("<html>" +
          "<title>My account</title>" +
          "<head>" +
          "<style>h1, h2, body { color: white; } a { padding: 10px 80px 60px 40px; font-size: 2em; color: yellow; text-decoration: none; } img { vertical-align: middle; } h1 { font-family: \"Gill Sans\", \"Gill Sans MT\", Calibri, \"Trebuchet MS\", sans-serif; font-size: 2.5em; margin-top: 30px; margin-bottom: 10px; } p { font-size: 1.2em; margin-bottom: 20px; } body { background-color: black; }</style>" +
          "</head>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"wardenhomepage.html\">Home</a>" +
          "  <a href=\"ShowOutpass\">Outpass</a>" +
          "  <a href=\"AccountServlet\">My account</a>" +
          "</p>" +
          "<hr noshade>" +
          "<br>" +
          "<h1>My Account</h1><br>" +
          "</body>" +
          "</html>");
               String name=rs.getString("name");
               String phoneno=rs.getString("phoneno");
               String dept=rs.getString("dept");
               String hostel=rs.getString("hostel");
               out.println("<table><tr><th>Name</th><td>"+name+"</td></tr><tr><th>Email id</th><td>"+email+"</td></tr><tr><th>Phone number</th><td>"+phoneno+"</td></tr><tr><th>Department</th><td>"+dept+"</td></tr><tr><th>Hostel</th><td>"+hostel+"</td></tr></table>");
             }
             else if(table.equals("supervisor")){
               out.print("<html>" +
          "<title>My account</title>" +
          "<head>" +
         "<style>\n"
        + "      h1,\n"
        + "      h2,\n"
        + "      body {\n"
        + "        color: white;\n"
        + "      }\n"
        + "      a {\n"
        + "        padding: 10px 30px 30px 20px;\n"
        + "        font-size: 2em;\n"
        + "        color: yellow;\n"
        + "        text-decoration: none;\n"
        + "      }\n"
        + "\n"
        + "      img {\n"
        + "        vertical-align: middle;\n"
        + "      }\n"
        + "\n"
        + "      h1 {\n"
        + "        font-family: \"Gill Sans\", \"Gill Sans MT\", Calibri, \"Trebuchet MS\",\n"
        + "          sans-serif;\n"
        + "        font-size: 2.5em;\n"
        + "        margin-top: 30px;\n"
        + "        margin-bottom: 10px;\n"
        + "      }\n"
        + "\n"
        + "      p {\n"
        + "        font-size: 1.2em;\n"
        + "        margin-bottom: 20px;\n"
        + "      }\n"
        + "      /*body {\n"
        + "        background-image: url(\"https://img.freepik.com/free-photo/vivid-blurred-colorful-wallpaper-background_58702-3798.jpg\");\n"
        + "        background-repeat: no-repeat;\n"
        + "        background-attachment: fixed;\n"
        + "        background-size: 100% 100%;\n"
        + "      }*/\n"
        + "      body {\n"
        + "        background-color: black;\n"
        + "      }\n"
        + "    </style>"+
          "</head>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"supervisorhomepage.html\">Home</a>" +
          "  <a href=\"RoomRequest\">Rooms</a>" +
          "  <a href=\"ComplaintsAndRequests\">Complaints and requests</a>" +
          "  <a href=\"AccountServlet\">My account</a>" +
          "</p>" +
          "<hr noshade>" +
          "<br>" +
          "<h1>My Account</h1><br>" +
          "</body>" +
          "</html>");
               String name=rs.getString("name");
               String phoneno=rs.getString("phoneno");
               String hostel=rs.getString("hostel");
               out.println("<table><tr><th>Name</th><td>"+name+"</td></tr><tr><th>Email id</th><td>"+email+"</td></tr><tr><th>Phone number</th><td>"+phoneno+"</td></tr><tr><th>Hostel</th><td>"+hostel+"</td></tr></table>");
             }
             else if(table.equals("security")){
                out.print("<html>" +
          "<title>My account</title>" +
          "<head>" +
          "<style>\n" +
      "      h1,\n" +
      "      h2,\n" +
      "      body {\n" +
      "        color: white;\n" +
      "      }\n" +
      "      a {\n" +
      "        padding: 30px 30px 30px 20px;\n" +
      "        font-size: 2em;\n" +
      "        color: yellow;\n" +
      "        text-decoration: none;\n" +
      "      }\n" +
      "\n" +
      "      img {\n" +
      "        vertical-align: middle;\n" +
      "      }\n" +
      "\n" +
      "      h1 {\n" +
      "        font-family: \"Gill Sans\", \"Gill Sans MT\", Calibri, \"Trebuchet MS\",\n" +
      "          sans-serif;\n" +
      "        font-size: 2.5em;\n" +
      "        margin-top: 30px;\n" +
      "        margin-bottom: 10px;\n" +
      "      }\n" +
      "\n" +
      "      p {\n" +
      "        font-size: 1.2em;\n" +
      "        margin-bottom: 20px;\n" +
      "      }\n" +
      "      /*body {\n" +
      "        background-image: url(\"https://img.freepik.com/free-photo/vivid-blurred-colorful-wallpaper-background_58702-3798.jpg\");\n" +
      "        background-repeat: no-repeat;\n" +
      "        background-attachment: fixed;\n" +
      "        background-size: 100% 100%;\n" +
      "      }*/\n" +
      "      body {\n" +
      "        background-color: black;\n" +
      "      }\n" +
      "    </style>" +
          "</head>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"securityhomepage.html\">Home</a>" +
          "  <a href=\"VerifyOutpass\">Outpass</a>" +
          "  <a href=\"AccountServlet\">My account</a>" +
          "</p>" +
          "<hr noshade>" +
          "<br>" +
          "<h1>My Account</h1><br>" +
          "</body>" +
          "</html>");
                String name=rs.getString("name");
                String phoneno=rs.getString("phoneno");
                out.println("<table><tr><th>Name</th><td>"+name+"</td></tr><tr><th>Email id</th><td>"+email+"</td></tr><tr><th>Phone number</th><td>"+phoneno+"</td></tr></table>");
            }
          else if(table.equals("messmanager")){
            out.print("<html>" +
          "<title>My account</title>" +
          "<head>" +
          "<style>\n"
        + "  h1, h2, body {\n"
        + "    color: white;\n"
        + "  }\n"
        + "  a {\n"
        + "    padding: 10px 30px 30px 20px;\n"
        + "    font-size: 2em;\n"
        + "    color: yellow;\n"
        + "    text-decoration: none;\n"
        + "  }\n"
        + "  img {\n"
        + "    vertical-align: middle;\n"
        + "  }\n"
        + "  h1 {\n"
        + "    font-family: \"Gill Sans\", \"Gill Sans MT\", Calibri, \"Trebuchet MS\",\n"
        + "      sans-serif;\n"
        + "    font-size: 2.5em;\n"
        + "    margin-top: 30px;\n"
        + "    margin-bottom: 10px;\n"
        + "  }\n"
        + "  p {\n"
        + "    font-size: 1.2em;\n"
        + "    margin-bottom: 20px;\n"
        + "  }\n"
        + "  /*body {\n"
        + "    background-image: url(\"https://img.freepik.com/free-photo/vivid-blurred-colorful-wallpaper-background_58702-3798.jpg\");\n"
        + "    background-repeat: no-repeat;\n"
        + "    background-attachment: fixed;\n"
        + "    background-size: 100% 100%;\n"
        + "  }*/\n"
        + "  body {\n"
        + "    background-color: black;\n"
        + "  }\n"
        + "</style>" +
          "</head>" +
          "<body>" +
          "<p>" +
          "  <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/b/be/Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg/800px-Sri_Sivasubramaniya_Nadar_College_of_Engineering.svg.png\" height=\"100px\" width=\"100px\">" +
          "  <a href=\"messmanagerhomepage.html\">Home</a>" +
          "  <a href=\"messbill.html\">Mess Bill</a>" +
          "  <a href=\"AccountServlet\">My account</a>" +
          "</p>" +
          "<hr noshade>" +
          "<br>" +
          "<h1>My Account</h1><br>" +
          "</body>" +
          "</html>");
                String name=rs.getString("name");
                String phoneno=rs.getString("phoneno");
                out.println("<table><tr><th>Name</th><td>"+name+"</td></tr><tr><th>Email id</th><td>"+email+"</td></tr><tr><th>Phone number</th><td>"+phoneno+"</td></tr></table>");
            }
          }
             
          catch(Exception e)
    {  out.println("Exception" + e);
    }
       }
}