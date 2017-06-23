<%@page import="service.impl.UserServiceImpl"%>
<%@page import="service.UserService"%>
<%@page import="entity.Employee"%>
<%@page import="java.sql.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String userName = request.getParameter("userName");
	String password = request.getParameter("password");

	Employee user = new Employee();
	user.setEmpName(userName);
	user.setPassword(password);

	//创建业务对象，调用业务对象的方法进行处理，返回结果
	UserService userService = new UserServiceImpl();
	// Employee result=userService.login(user);
	Employee result = null;
	if (result != null) {
		session.setAttribute("loginedUser", result);
		response.sendRedirect("/news/jsp/ch04/loginSuccess.jsp");
	} else {
		request.setAttribute("msg", "用户名或密码错误");
		request.getRequestDispatcher("/jsp/ch04/login.jsp").forward(
				request, response);
	}
%>

