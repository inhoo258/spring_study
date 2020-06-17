<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<jsp:include page="header/header.jsp"/>
	<div align="center">
	<c:choose>
		<c:when test="${not empty sessionScope.userId}">
			<h3 style="display: inline;">${sessionScope.userId}</h3>님 접속하셧습니다. 
		</c:when>
		<c:otherwise>
			비로그인 ... 로그인 해 
		</c:otherwise>
	</c:choose>
	</div>
	
</body>
</body>
</html>
