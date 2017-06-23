<%@page import="entity.Employee"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录成功</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	欢迎${sessionScope.loginedUser.empName}<br/>
	<c:if test="${sessionScope.loginedUser.empName=='admin' }">
		<a href="jsp/admin/index.jsp">进入管理控制台</a>

	</c:if>
	<p>
		<a href="jsp/ch04/doLogout.jsp">注销</a>
	</p>
	<hr />
	<a href="jsp/ch02/toDemo.jsp">访问WEB-INF路径下的资源</a>
</body>
</html>
