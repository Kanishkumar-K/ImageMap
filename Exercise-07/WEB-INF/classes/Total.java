import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class total extends HttpServlet { 
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    Cookie[] cookies = request.getCookies();
    String uname = null;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                uname = cookie.getValue();
                break;
            }
        }
    }
    out.println("<header style = 'text-align: right'>" + uname + "&nbsp; &nbsp; &nbsp; &nbsp;");
    out.println("<a href='logout' target='_blank'>Logout</a> ");
    out.println("</header><hr style = 'border:'2px solid black>");
    String[] selectedlocations = request.getParameterValues("selected");
    response.setContentType("text/html");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Checkout</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>locations: </h1>");
    if (selectedlocations != null) {
        double totalbudget = 0.0;
        out.println("<table cellspacing='0' width='350px' border='1'>");
        out.println("<tr><th>location</th><th>budget</th></tr>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spotts","root","");
            PreparedStatement stmt = conn.prepareStatement("SELECT budget FROM location WHERE name = ?");
            for (int i = 0; i < selectedlocations.length; i++) {
                stmt.setString(1, selectedlocations[i]);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    double budget = rs.getDouble("budget");
                    out.println("<tr><td>" + selectedlocations[i] + "</td><td>" + budget + "</td></tr>");
                    totalbudget += budget;
                } else {
                    out.println("<tr><td>" + selectedlocations[i] + "</td><td>budget not found</td></tr>");
                }
                rs.close();
            }
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            out.println("<p>Database error occurred.</p>");
        }
        out.println("</table>");
        out.println("<h1>Total budget: " + totalbudget + "</h1>");
     } else {
           out.println("<p>No travelling spots selected.</p>");
     }
     out.println("</body>");
     out.println("</html>");
}
}
