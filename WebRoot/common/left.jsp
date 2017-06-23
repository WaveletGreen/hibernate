<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<ul class="nav">
  <li>新闻类别管理
    <ul class="subnav">
      <li><a href="#">添加一级类别</a></li>
      <li><a href="#">查询一级类别</a></li>
      <li><a href="#">添加二级类别</a></li>
      <li><a href="#">查询二级类别</a></li>
    </ul>
  </li>
  <li>新闻管理
    <ul class="subnav">
      <li><a href="#">添加新闻</a></li>
      <li><a href="#">查询新闻</a></li>
    </ul>
  </li>
  <li>用户管理
    <ul class="subnav">
      <li><a href="jsp/admin/userAdd.jsp">添加用户</a></li>
      <li><a href="servlet/getUserListServlet">查询用户</a></li>
    </ul>
  </li>
</ul>