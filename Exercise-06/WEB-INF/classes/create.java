import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class create extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        Statement stmt = null;
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String dept = request.getParameter("dept");
        String dept = request.getParameter("sem");
        String marks = request.getParameter("marks");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "kanish@ip06");
            stmt = conn.createStatement();

            String sql = "INSERT INTO studentDB(id,name,dept,sem,marks) values(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, dept);
            pstmt.setString(4, sem);
            pstmt.setString(5, marks);
            int rowsAffected = pstmt.executeUpdate();

            // Display the number of rows affected by the DELETE statement
            out.println("<html><body>");
            out.println("<h3>Rows affected: " + rowsAffected + "</h3>");
            out.println("</body></html>");

            // Clean up resources
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}