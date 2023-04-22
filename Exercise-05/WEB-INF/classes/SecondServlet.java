import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
public class SecondServlet extends HttpServlet {  
public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
          
        //Getting the value from the hidden field  
        String n=request.getParameter("uname");  
        out.print("Hello "+n);  

        out.print("<form action='CounterServlet'>");  
        
        out.print("</form>");  
  
        out.close();  
                }catch(Exception e){System.out.println(e);}  
    }  
}  