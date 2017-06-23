<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>杭州华育新闻发布系统——管理控制台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/admin_index.css">

  </head>
  
  <body>
    <!-- 页眉 -->
    <div id="header">
      <%@include file="/common/header.jsp" %>
    </div>
    <div id="main">
      <!-- 左边导航菜单栏 -->
      <div id="sidebar">
        <%@include file="/common/left.jsp" %>
      </div>      
      <div id="content"></div>
      <div id="clear"></div>
    </div>
    <!-- 页脚 -->
    <div id="footer">
      <%@include file="/common/footer.jsp" %>
    </div>
  </body>
</html>
