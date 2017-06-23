<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  if(session.getAttribute("loginedUserName")==null){
    response.sendRedirect("/news/jsp/ch02/login.jsp");
  }
%>
