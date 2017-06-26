<%@page import="util.DateUtil"%>
<%@page import="util.UserQueryCondition"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	UserQueryCondition condition = (UserQueryCondition) request.getAttribute("condition");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>"/>

<title>杭州华育新闻发布系统——管理控制台</title>

<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link rel="stylesheet" type="text/css" href="css/admin_index.css"></link>
<script type="text/javascript">
	function doDelete(userId) {
		if (confirm("您确定要删除吗?")) {
			location.href = "/news/jsp/admin/doDelete.jsp?userId=" + userId;
		}
	}

	//通过超链接实现表单提交
	function doSubmit(pageIndex) {
		//给隐藏表单域设置查询的页码数
		document.getElementById("txtPageIndex").value = pageIndex;
		//手动调用表单的提交方法
		document.getElementById("frmUserList").submit();
	}
</script>
</head>

<body>
	<!-- 页眉 -->
	<div id="header">
		<%@include file="/common/header.jsp"%>
	</div>
	<div id="main">
		<!-- 左边导航菜单栏 -->
		<div id="sidebar">
			<%@include file="/common/left.jsp"%>
		</div>
		<div id="content">
			<form id="frmUserList" method="post"
				action="servlet/getUserListServlet">
				<input id="txtPageIndex" type="hidden" name="pageIndex" value="1" />
				<table>
					<tr>
						<td>用户名：<input type="text" name="userName"
							value="<%=condition.getUserName() == null ? "" : condition.getUserName()%>" /></td>
						<td>注册时间：<input type="text" name="start"
							value="<%=condition.getStart() == null ? "" : condition.getStart()%>" />
							-<input type="text" name="end"
							value="<%=condition.getEnd() == null ? "" : condition.getEnd()%>" />（yyyy-mm-dd）
						</td>
					</tr>
					<tr>
						<td>角色： <select name="roleId">
								<option value="0">全部</option>
								<option value="1" <%if (condition.getRoleId() == 1) {%>
									selected="selected" <%}%>>系统管理员</option>
								<option value="2" <%if (condition.getRoleId() == 2) {%>
									selected="selected" <%}%>>普通用户</option>
						</select>
						</td>
						<td>状态： <select name="status">
								<option value="-1">全部</option>
								<option value="1" <%if (condition.getStatus() == 1) {%>
									selected="selected" <%}%>>正常</option>
								<option value="0" <%if (condition.getStatus() == 0) {%>
									selected="selected" <%}%>>禁用</option>
						</select> <input type="submit" value="查询" />
						</td>
					</tr>
				</table>
			</form>
			<table width="100%" border="1">
				<tr>
					<td>ID</td>
					<td>用户名</td>
					<td>注册时间</td>
					<td>照片</td>
					<td>角色</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
				<%
					//UserService userService=new UserService();
					//List<User> userList= userService.getAll();

					List<Employee> userList = (List<Employee>) request.getAttribute("userList");
					if (userList == null || userList.isEmpty()) {
				%>
				<tr>
					<td colspan="6">没有您要查找的数据</td>
				</tr>
				<%
					} else {
						for (int i = 0; i < userList.size(); i++) {
							Employee user = userList.get(i);
				%>
				<tr>
					<td><%=user.getEmpId()%></td>
					<td><%=user.getEmpName()%></td>
					<td><%=DateUtil.format(user.getBirthday())%></td>
					<td><%=user.getSex()%></td>
					<td><%=user.getRoleId() == 1 ? "系统管理员" : "普通用户"%></td>
					<td><%=user.getStatus() == 1 ? "正常" : "禁用"%></td>
					<td>
						<%-- <a href="jsp/admin/doDelete.jsp?userId=<%=user.getUserId() %>">删除</a> --%>
						<a href="javascript:doDelete(<%=user.getEmpId()%>);">删除</a> <a
						href="jsp/admin/toUpdate.jsp?userId=<%=user.getEmpId()%>">更新</a>
					</td>
				</tr>
				<%
					}
					}
				%>
				<tr>
					<td colspan="7" align="right">
						<!-- <a href="jsp/admin/doUserList.jsp?pageIndex=1">首页</a> --> <a
						href="javascript:doSubmit(1);">首页</a> <%
 	if ((Integer) request.getAttribute("pageIndex") != 1) {
 %> <%-- <a href="jsp/admin/doUserList.jsp?pageIndex=<%=request.getAttribute("prePageIndex") %>">上一页</a> --%>
						<a
						href='javascript:doSubmit(<%=request.getAttribute("prePageIndex")%>);'>上一页</a>
						<%
							}
							if ((int) (Integer) request.getAttribute("pageIndex") != (int) (Integer) request
									.getAttribute("totalPages")) {
						%> <%-- <a href="jsp/admin/doUserList.jsp?pageIndex=<%=request.getAttribute("nextPageIndex") %>">下一页</a> --%>
						<a
						href='javascript:doSubmit(<%=request.getAttribute("nextPageIndex")%>);'>下一页</a>
						<%
							}
						%> <%--<a href="jsp/admin/doUserList.jsp?pageIndex=<%=request.getAttribute("totalPages") %>">末页</a> --%>
						<a
						href='javascript:doSubmit(<%=request.getAttribute("totalPages")%>)'>末页</a>
						&nbsp;第<%=request.getAttribute("pageIndex")%>页/共<%=request.getAttribute("totalPages")%>页
					</td>
				</tr>
			</table>
		</div>
		<div id="clear"></div>
	</div>
	<!-- 页脚 -->
	<div id="footer">
		<%@include file="/common/footer.jsp"%>
	</div>
</body>
</html>
