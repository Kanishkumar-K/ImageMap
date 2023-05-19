import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class logout extends HttpServlet { 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    response.setContentType("text/html"); 
    PrintWriter out = response.getWriter(); 
    Cookie sessionCookie = new Cookie("username", "");
    sessionCookie.setMaxAge(0);
    response.addCookie(sessionCookie);
    request.getRequestDispatcher("login.html").include(request, response); 
    out.close(); 
    } 
}
