<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ProvinceAndCity.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">



</head>

<body>
	<form action="servlet/getCities">
		<dl>
			<dt>浙江省</dt>

			<dd>
				<select name="province">
					<c:forEach items="${sessionScope.cityList }" var="city">
						<option value="${city.cityID }" class="input-text">${city.cityName}
			</option>
					</c:forEach>

				</select>
			</dd>
		</dl>
		<input type="submit" name="delet" value="删除" />
	</form>
</body>
</html>
