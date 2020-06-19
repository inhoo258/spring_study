<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<jsp:include page="header/header.jsp"/>
	<div align="center">
<%-- 	<sec:authorize access="isAnonymous()">  --%>
		<fieldset style="width:400px; height: 200px;">
		<legend>로그인</legend>
		
		<table>
			<form action="loginCheck" method="post">
			<tr>
				<th colspan="3"><h3>User&nbsp;&nbsp;&nbsp;ID :</h3></th><th colspan="2"><input style="height: 30px;" type=text name=id></th>
			</tr>
			<tr>
				<th colspan="3"><h3>Password :</h3></th><th colspan="2"><input style="height: 30px;" type=password name=pw></th>
			</tr>
			<tr>
<!-- 				<th colspan="2"><br></th> -->
			</tr>
			<tr>
				<th colspan="4" style="width:300px;">${message}</th>
				<th colspan="2" align="right"><input style="width: 140px; height: 35px;" type=submit value=Login></th>
			</tr>
			</form>
		</table>
		
		</fieldset>
<%-- 	</sec:authorize> --%>
	</div>
	
	<div align="center">
	<sec:authorize access="isAuthenticated()">
	${sessionScope.userId} 님 환영합니다
		<a href="<c:url value="/hr/index" />">메인페이지</a>
		${pageContext.request.contextPath}
		<form action="${pageContext.request.contextPath}/logout" method="post">
			<input type=submit value=" 로그아웃 ">
		</form>
	</sec:authorize>
	</div>
</body>
</html>