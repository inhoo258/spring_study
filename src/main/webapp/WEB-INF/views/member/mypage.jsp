<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	th{
	height: 40px;
	}
	.input_text{
		width: 300px;
		height: 30px;
	}
</style>
</head>
<body>
	<jsp:include page="../header/header.jsp"/>
		<c:choose>
			<c:when test="${message eq 'update'}">
				<div align="center">
					<table border="1">
					<form action="<c:url value ='/member/update.info'/>">
					<input type="hidden" name="userId" value="${userInfo.userId }">
					<tr>
						<th width="300px">User ID</th>
						<th width="300px">${userInfo.userId}</th>
					</tr>
					<tr>
						<th>User Password</th>
						<th><input class="input_text" type="text" name="password"></th>
					</tr>
					<tr>
						<th>User Name</th>
						<th><input class="input_text" type="text" name = "name" value="${userInfo.name}"></th>
					</tr>
					<tr>
						<th>User Email</th>
						<th><input class="input_text" type="text" name = "email" value="${userInfo.email}"></th>
					</tr>
					<tr>
						<th>User Address</th>
						<th><input class="input_text" type="text" name = "address" value="${userInfo.address}"></th>
					</tr>
					<tr>
						<th>User Enabled</th>
						<th>${userInfo.enabled ? "활성화" : "비활성화"}</th>
					</tr>
					<c:set var = "length" value="${fn:length(userInfo.auth)}"/>
					<c:set var = "authority" value="${fn:substring(userInfo.auth , length-4 , length)}"/>
					<tr>
						<th>User Authority</th>
						<th>${authority}</th>
					</tr>
					</table>
				</div>
				<hr>
				<div align="center">
					<input class="input" type="submit" value="Update">
					</form>
					<input class="input" type="button" onclick="location.href='<c:url value ='/member/member.delete?userId=${userInfo.userId}'/>'" value="회원 탈퇴">
				</div>
		</c:when>
		<c:otherwise>
			<div align="center">
				<table border="1">
					<tr>
						<th width="300px">User ID</th>
						<th width="300px">${userInfo.userId}</th>
					</tr>
					<tr>
						<th>User Name</th>
						<th>${userInfo.name}</th>
					</tr>
					<tr>
						<th>User Email</th>
						<th>${userInfo.email}</th>
					</tr>
					<tr>
						<th>User Address</th>
						<th>${userInfo.address}</th>
					</tr>
					<tr>
						<th>User Enabled</th>
						<th>${userInfo.enabled ? "활성화" : "비활성화"}</th>
					</tr>
					<c:set var = "length" value="${fn:length(userInfo.auth)}"/>
					<c:set var = "authority" value="${fn:substring(userInfo.auth , length-4 , length)}"/>
					<tr>
						<th>User Authority</th>
						<th>${authority}</th>
					</tr>
				</table>
			</div>
			<hr>
			<div align="center">
				<input class="input" type="button" onclick="location.href='<c:url value='/member/update?userId=${userInfo.userId}'/>'" value = "Update">
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>