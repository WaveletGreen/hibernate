<%@page import="entity.Employee"%>
<%@page import="service.impl.UserServiceImpl"%>
<%@page import="service.UserService"%>
<%@page import="util.UserQueryCondition"%>
<%@page import="util.StringUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String userName=request.getParameter("userName");
	String start=request.getParameter("start");
	String end=request.getParameter("end");
	//获取角色
    String strRoleId=request.getParameter("roleId");
    int roleId=0;
    if(!StringUtil.isNullOrEmpty(strRoleId)){
       roleId=Integer.parseInt(strRoleId);
    }
    //获取状态
    String strStatus=request.getParameter("status");
    int status=-1;
    if(!StringUtil.isNullOrEmpty(strStatus)){
        status=Integer.parseInt(strStatus);
    }
    
    UserQueryCondition condition=new UserQueryCondition();
    if(!StringUtil.isNullOrEmpty(userName)){
	    condition.setUserName("%"+userName+"%");
	}
	condition.setStart(start);
	condition.setEnd(end);
	condition.setRoleId(roleId);
	condition.setStatus(status);
	
	int pageSize=3;  //每页显示的最大记录数
	int pageIndex=1; //当前页码数;
	int totalPages=0; //总页数
	int prePageIndex=0;  //上一页
	int nextPageIndex=0; //下一页
	
	String strPageIndex=request.getParameter("pageIndex");
	if(!StringUtil.isNullOrEmpty(strPageIndex)){
	  pageIndex=Integer.parseInt(strPageIndex);
	}
	
	//创建业务对象，查询总页数和用户信息
	UserService userService=new UserServiceImpl();
	int totalRecords=userService.getByPage(condition).size();
	totalPages=totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
	List<Employee> userList=userService.getByPage(pageSize, pageIndex, condition);
	
	//计算上一页
	prePageIndex=pageIndex-1;
	if(prePageIndex<1){
	    prePageIndex=1;
	}
	
	//计算下一页
	nextPageIndex=pageIndex+1;
	if(nextPageIndex>totalPages){
	    nextPageIndex=totalPages;
	}
	
	//保存数据并实现页面跳转
	request.setAttribute("pageIndex", pageIndex);
	request.setAttribute("prePageIndex", prePageIndex);
	request.setAttribute("nextPageIndex", nextPageIndex);
	request.setAttribute("totalPages", totalPages);
	if(!StringUtil.isNullOrEmpty(userName)){
	    condition.setUserName(userName.replace("%", ""));
	}
	request.setAttribute("condition", condition);
	request.setAttribute("userList", userList);
	request.getRequestDispatcher("/jsp/admin/userList.jsp").forward(request, response);
%>
