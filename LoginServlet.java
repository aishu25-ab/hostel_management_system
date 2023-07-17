import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.sql.*; 

public class LoginServlet extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                           throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        try {
        String dbDriver = "com.mysql.jdbc.Driver"; 
        String dbURL = "jdbc:mysql:// localhost:3306/hms"; 
        // Database name to access 
        String dbUsername = "root"; 
        String dbPassword = "admin"; 
  
        Class.forName(dbDriver); 
        Connection con = DriverManager.getConnection(dbURL, 
                                                     dbUsername,  
                                                     dbPassword);
          
        //request.getRequestDispatcher("link.html").include(request, response);  
          
        String name=request.getParameter("username");  
        String pw=request.getParameter("pass");  
        Statement stmt = con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from student where email='"+name+"' and password='"+pw+"';");
        HttpSession session=request.getSession();
        //rs.next();
        //out.print(rs.getString("email"));
        if(rs.next()){
        //if(rs.getString("email")!=null && rs.getString("password")!=null){
          //out.print("You are successfully logged in!");
          //request.getRequestDispatcher("studenthomepage.html").include(request, response);  
          //Cookie ck=new Cookie("email",name);
          //Cookie ck2=new Cookie("table","student");
          //response.addCookie(ck);
          //response.addCookie(ck2);
          session.setAttribute("email",name);
          session.setAttribute("table","student");
          response.sendRedirect("studenthomepage.html");
        }
        else{
          rs=stmt.executeQuery("select * from warden where email='"+name+"' and password='"+pw+"';");
          if(rs.next()){
          //if(rs.getString("email")!=null && rs.getString("password")!=null){
          //out.print("You are successfully logged in!");
          //Cookie ck=new Cookie("email",name);
          //Cookie ck2=new Cookie("table","warden");
          //response.addCookie(ck);
          //response.addCookie(ck2);
          session.setAttribute("email",name);
          session.setAttribute("table","warden");
          response.sendRedirect("wardenhomepage.html");
          }
          else{
          rs=stmt.executeQuery("select * from supervisor where email='"+name+"' and password='"+pw+"';");
          if(rs.next()){
          //if(rs.getString("email")!=null && rs.getString("password")!=null){
          //out.print("You are successfully logged in!");
          //Cookie ck=new Cookie("email",name);
          //Cookie ck2=new Cookie("table","supervisor");
          //response.addCookie(ck);
          //response.addCookie(ck2);
          session.setAttribute("email",name);
          session.setAttribute("table","supervisor");
          response.sendRedirect("supervisorhomepage.html");
          }
          else{
           rs=stmt.executeQuery("select * from security where email='"+name+"' and password='"+pw+"';");
          if(rs.next()){
          //if(rs.getString("email")!=null && rs.getString("password")!=null){
          //out.print("You are successfully logged in!");
          //Cookie ck=new Cookie("email",name);
          //Cookie ck2=new Cookie("table","security");
          //response.addCookie(ck);
          //response.addCookie(ck2);
          session.setAttribute("email",name);
          session.setAttribute("table","security");
          response.sendRedirect("securityhomepage.html");
          }
          else{
            rs=stmt.executeQuery("select * from messmanager where email='"+name+"' and password='"+pw+"';");
            if(rs.next()){
            //if(rs.getString("email")!=null && rs.getString("password")!=null){
            //out.print("You are successfully logged in!");
            //Cookie ck=new Cookie("email",name);
            //Cookie ck2=new Cookie("table","security");
            //response.addCookie(ck);
            //response.addCookie(ck2);
            session.setAttribute("email",name);
            session.setAttribute("table","security");
            response.sendRedirect("messmanagerhomepage.html");
            }
            else{
            request.getRequestDispatcher("login.html").include(request, response);  
            out.print("Incorrect username/password!");
            }
        }
        }
        }
}
        /*if(password.equals("admin123")){  
            out.print("You are successfully logged in!");  
            out.print("<br>Welcome, "+name);  
              
            Cookie ck=new Cookie("name",name);  
            response.addCookie(ck);  
        }else{  
            out.print("sorry, username or password error!");  
            request.getRequestDispatcher("login.html").include(request, response);  
        }*/  
          
        con.close();  
    }
    catch(Exception e)
    {  out.println("Exception" + e);
    }
    }  
  
}