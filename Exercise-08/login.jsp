<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <%
        Connection conn=null;
        Statement stmt=null;
        String uname = request.getParameter("uname"); 
        Cookie ck = new Cookie("username", uname);
        response.addCookie(ck); 
        out.println("<a href='logout.jsp' target='_blank' style='text-align:right ;'>Logout</a><br><hr style='border:3px solid black;'> ");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spot", "root", "");
            if(conn==null) {
                out.println("<h1> Issues in Connection</h1>");
            }
            String sql;
            stmt = conn.createStatement();
            sql = "SELECT * FROM locations";
            ResultSet rs = stmt.executeQuery(sql);
    %>
    <h3>locationss Available</h3>
    <form action='total.jsp' method='post'>
        <table cellspacing='0' width='350px' border='1'>
            <tr>
                <td> locations Name </td>
                <td> locations travel_expense </td>
                <td> Checked </td>
            </tr>

            <%
                String name = "";
                String travel_expense = "";
                while(rs.next()) {
                    name = rs.getString("name");
                    travel_expense = rs.getString("travel_expense");
            %>

            <tr>
                <td><%= name %></td>
                <td><%= travel_expense %></td>
                <td><input type='checkbox' name='selected' value = '<%= name %>'></td>
            </tr>

            <%
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch(Exception e) {
                System.out.print("Do not connect to DB - Error:"+e);
            }
            out.println("</table>");
            out.println("<br><input type='submit' value='Calculate Total'></form>");
    %>
</body>
</html>
