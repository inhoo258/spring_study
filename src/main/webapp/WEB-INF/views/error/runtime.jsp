<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Error</h1>
<p>애플리케이션에 오류 발생. 담당자에게 연락하시려면 lee@gmail.com<br>
	Failed URL : ${url}<br>
	Exception : ${exception.message}<br>
	<c:forEach items="${exception.stackTrace}" var = "ste">
	 ${ste}
	</c:forEach>
</body>
</html>