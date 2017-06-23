<%@page import="entity.Employee"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <%
        Employee user=null;
        if(session.getAttribute("loginedUser")!=null){
            user=(Employee)session.getAttribute("loginedUser");
        }  
    %>
    <h3><%=user.getEmpName() %>，恭喜您，登录成功！</h3>
    <%
        if(user.getRoleId()==1){
    %>
    <a href="jsp/admin/index.jsp">进入管理控制台</a>
    <%    
        }
    %> 
    <p><a href="jsp/ch04/doLogout.jsp">注销</a></p> 
    <hr/>
    <%
      int count=0;
      /*if(application.getAttribute("count")==null){  //代表第一次访问
        count++;        
      }else{  //代表不是第一次访问
        count=(Integer)application.getAttribute("count");
        count++;
      }*/
      
      if(application.getAttribute("count")!=null){
        count=(Integer)application.getAttribute("count");
      }
      application.setAttribute("count", ++count);
    %>
    <p>您是访问该站点的第<%=count %>个用户</p>   
    <a href="jsp/ch02/toDemo.jsp">访问WEB-INF路径下的资源</a> 
  </body>
</html>
