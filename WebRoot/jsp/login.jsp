<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--
  //读取cookie
  
  Cookie[] cookies=request.getCookies();
  if(cookies!=null){
    for(int i=0;i<cookies.length;i++){
      Cookie cookie=cookies[i];
      if(cookie.getName().equals("userName")){
        request.setAttribute("loginedUserName", cookie.getValue());
        request.getRequestDispatcher("/jsp/ch02/loginSuccess.jsp").forward(request, response);
        //response.sendRedirect("/news/jsp/ch02/loginSuccess.jsp");
        break;
      }
    }
  }
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>杭州华育——用户登录</title>
    
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
    <h3>杭州华育——用户登录</h3>
  <form name="form1" method="post" action="servlet/doLoginServlet">
    <table width="60%" border="1" cellspacing="0" cellpadding="10">
      <tr>
        <td>用户名：</td>
        <td>
          <input type="text" name="userName" id="txtUserName">
        </td>
      </tr>
      <tr>
        <td>密码：</td>
        <td>
          <input type="password" name="password" id="txtPassword">
        </td>
      </tr>
      <tr>
        <td>自动登录</td>
        <td>
          <select name="period">
            <option value="-1">==请选择==</option>
            <option value="7">一周之内</option>
            <option value="30">一月之内</option>
            <option value="360">一年之内</option>  
          </select>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>
          <input type="submit" value="提交">
          <input type="reset" value="重置">
        </td>
      </tr>
    </table>
  </form>
  <font color="red">
    <%
      if(request.getAttribute("msg")!=null){
    %>
    <%=request.getAttribute("msg") %>
    <%
      }
    %>
  </font>
  </body>
</html>
