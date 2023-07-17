import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.sql.*; 

public class SupervisorHomeServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                           throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        try {
           //request.getRequestDispatcher("studenthomepage.html").include(request, response);  
           //Cookie ck[]=request.getCookies();  
           //response.addCookie(ck[0]);
           //response.addCookie(ck[1]);
           response.sendRedirect("supervisorhomepage.html");
           HttpSession session=request.getSession(false);
        }
        catch(Exception e)
       {  out.println("Exception" + e);
       }
   }
}