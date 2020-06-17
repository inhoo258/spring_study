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
	<h1>부서별 총 인원수</h1>
	<form action="mainPage">
		<input type="submit" value ="main">
	</form>
	<hr>
	<table border=2>
		<tr>
			<th>부서번호</th>
			<th>인원수</th>
		</tr>
		<c:forEach var="cnt" items="${dept_cnt}">
			<tr>
				<th>${empty cnt.DeptId ? "부서없음" : cnt.DeptId}</th>
				<th>${cnt.cnt}명</th>
			</tr>
		</c:forEach>
	</table>
	
	

</body>
</html>