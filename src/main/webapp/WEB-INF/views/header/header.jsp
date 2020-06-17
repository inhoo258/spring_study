<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.input{
		width: 200px;
		height: 50px;
		background-color: skyblue;
		display: inline;
		font-size: 20px;
	}
	
	hr{
		width: 900px;
	}
</style>
</head>
<body>
	<header>
		<h1 align="center" style="font-size: 5em">Study</h1>
		<hr>
		<div align="center">
			<sec:authorize access="isAnonymous()">
				<input class="input" type="button" onclick="location.href='<c:url value='/login'/>'" value="Login">
				<input class="input" type="button" onclick="location.href='<c:url value='/member/insert'/>'" value="Join">
				<input class="input" type="button" onclick="location.href='<c:url value='/hr/list'/>'" style="display: inline;" value="List">
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<input class="input" type="button" onclick="location.href='<c:url value='/file'/>'" value="File/UpDown">
				<input class="input" type="button" onclick="location.href='<c:url value='/member/mypage?userId=${sessionScope.userId}'/>'" value="MyPage">
				<input class="input" type="button" onclick="location.href='<c:url value='/hr/list'/>'" value="List">
				<form action=<c:url value='/logout'/> method=post style="display: inline;">
					<input class="input" type=submit value=LogOut>
				</form>
			</sec:authorize>
		</div>
		<hr>
	</header>
</body>
</html>

		
