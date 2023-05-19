package com.jfr;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.System.*;

@WebService(serviceName = "jfw")
public class jfw  extends HttpServlet {


    @WebMethod(operationName = "fetch")
     public String[][] fetch() {                 
         
         String[][] str = new String[4][4];
        HttpServletResponse response = null;
       String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3307/tourism";

        // Database credentials
        String USER = "root";
        String PASS = "";

        String Location = "Database Result";

        System.out.println(Location);
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            connection conn = DriverManager.getconnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM spots;";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(" <style> th, td { ");
            System.out.println("padding-top: 10px;");
            System.out.println(" padding-bottom: 20px;");
            System.out.println("padding-left: 30px;");
            System.out.println("padding-right: 40px;");

            System.out.println("} </style><center><div><table border = 1 >");
            System.out.println("<tr><td> Location </td>'");
            System.out.println("<td> No of persons </td>");
            System.out.println("<td> country </td>");
            System.out.println("<td> Amount </td></tr>");
 
            
            int i = 0;
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                String location = rs.getString("Location");
                String year = rs.getString("No of persons");
                String co = rs.getString("country");
                String amt = rs.getString("Amount");

                // Display values
                System.out.println("<tr> <td>" + location + " </td>");
                System.out.println("<td>" + year + "</td>");
                System.out.println("<td>" + co + "</td>");
                System.out.println("<td >" + amt + "</td>");
                System.out.println(
                        "<td > ");
                System.out.println("</td></tr> ");
                int j = 0;
                
                str[i][j] = location;
                str[i][j+1] = year;
                str[i][j+2] = co;
                str[i][j+3] = amt;
                i++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Do not connect to DB - Error:" + e);
        }
        return str;
    }

       
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}

    
