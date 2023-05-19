<%@page import="java.io.*,  java.util.*,javax.servlet.*,javax.servlet.http.*,java.sql.*"%>
<%
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
    out.println("<a href='logout.jsp' target='_blank'>Logout</a><hr style='border:3px solid black'> ");
    String[] selectedlocations = request.getParameterValues("selected");
    response.setContentType("text/html");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Selected locations</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>"+uname+", here are the selected locations</h1>");
    if (selectedlocations != null) {
        double totaltravel_budget = 0.0;
        out.println("<table cellspacing='0' width='350px' border='1'>");
        out.println("<tr><th>Product</th><th>travel_budget</th></tr>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spot","root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT travel_budget FROM product WHERE name = ?");
            for (int i = 0; i < selectedlocations.length; i++) {
                stmt.setString(1, selectedlocations[i]);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    double travel_budget = rs.getDouble("travel_budget");
                    out.println("<tr><td>" + selectedlocations[i] + "</td><td>" + travel_budget + "</td></tr>");
                    totaltravel_budget += travel_budget;
                } else {
                    out.println("<tr><td>" + selectedlocations[i] + "</td><td>travel_budget not found</td></tr>");
                }
                rs.close();
            }
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            out.println("<p>Database error occurred.</p>");
        }
        out.println("</table>");
        out.println("<h1>Total travel_budget: " + totaltravel_budget + "</h1>");
    } else {
        out.println("<p>No locations selected.</p>");
    }
    out.println("</body>");
    out.println("</html>");
%>
