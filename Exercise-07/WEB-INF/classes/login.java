import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class login extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        Statement stmt = null;
        PrintWriter out = response.getWriter();

        String uname = request.getParameter("uname");

        Cookie ck = new Cookie("username", uname);
        response.addCookie(ck);

        out.println("<header style = 'text-align: right'>" + uname + "&nbsp; &nbsp; &nbsp; &nbsp;");
        out.println("<a href='logout'>Logout</a> ");
        out.println("</header><hr style = 'border:'3px solid black>");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "");
            if (conn == null) {
                out.println("<h1> Issues in Connection</h1>");
            } else {
                out.println("<h1>Connection established<h1>");
            }
            String sql;
            // display all the students' marks
            stmt = conn.createStatement();
            sql = "SELECT * FROM product";
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            out.println("<form action='total' method='post'>");
            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr>");
            out.println("<td> Location </td>");
            out.println("<td> Budget</td>");
            out.println("<td> Checked </td>");

            out.println("</tr>");

            String name = "";
            String budget = "";
            while (rs.next()) {
                name = rs.getString("name");
                budget = rs.getString("budget");

                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + budget + "</td>");
                out.println("<td><input type='checkbox' name='selected' value = '" + name + "'></td>");

                out.println("</tr>");

            }
            out.println("</table>");
            out.println("<input type='submit' value='Calculate Total'></form>");
            out.println("</body></html>");
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Do not connect to DB - Error:" + e);
        }
    }
}
