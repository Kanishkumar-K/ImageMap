<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    response.setContentType("text/html");  
    
    Cookie sessionCookie = new Cookie("username", "");
    sessionCookie.setMaxAge(0);
    response.addCookie(sessionCookie);
    request.getRequestDispatcher("login.html").include(request, response);  
%>
