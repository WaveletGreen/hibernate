<%@page import="entity.Employee"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    Employee loginedUser=null;
    if(session.getAttribute("loginedUser")!=null){
        loginedUser=(Employee)session.getAttribute("loginedUser");
    } else{
        response.sendRedirect("/news/jsp/ch04/login.jsp");
        return;
    } 
%>
<div class="logo">
	<img src="images/logo.jpg" />
</div>
<h3>杭州华育新闻发布系统——管理控制台</h3>
<div class="logo_info">
	系统管理员：<a href="#"><%=loginedUser.getEmpName()%></a>&nbsp;&nbsp;&nbsp; <a
		href="jsp/ch04/doLogout.jsp">注销</a>
</div>