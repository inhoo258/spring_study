<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../header/header.jsp"/>
	<div align="center">
	<div style="width: 1000px; " align="right">
		<form action="<c:url value='/member/list'/>">
		검색 : <input type="text" name = "select">
		<input type="submit" value="검색">
		</form>	
		<br>
	</div>
	</div>
	<div align="center">
		<table border="1" style="width: 1000px;">
			<tr style="height: 50px;">
				<th>UserId</th>
				<th>UserName</th>
				<th>Email</th>
				<th>Address</th>
				<th>Enabled</th>
				<th>Authority</th>
			</tr>
			<c:forEach var="userInfo" items="${memberList}">
				<tr>
					<th><a href='<c:url value="/member/update?userId=${userInfo.userId}"/>'>${userInfo.userId}</a></th>
					<th>${userInfo.name}</th>
					<th>${userInfo.email}</th>
					<th>${userInfo.address}</th>
					<th>${userInfo.enabled}</th>
					<th>${userInfo.auth}</th>
				</tr>		
			</c:forEach>
		</table>
	</div>

</body>
</html>