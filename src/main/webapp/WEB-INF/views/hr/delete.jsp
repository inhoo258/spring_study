<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>삭제하려는 ${emp.firstName} ${emp.lastName}은</h3>
	<h3>${cnt} 명의 매니저 이고</h3>
	<h3>${deptcnt} 개의 부서를 책임지고 있습니다</h3>
	<h1>정말 삭제 하시겠습니까?</h1>
	
	<form action="delete?empId=${emp.employeeId}" method="post">
		<input type="submit" value="삭제">
	</form>
	<form action="${emp.employeeId}">
		<input type="submit" value="취소">
	</form>
	
</body>
</html>