<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="../header/header.jsp"/>
	<div align="center">
		<h1>${msg eq "max" ? "부서별 최고 연봉" : msg eq "list" ? "전체 사원 목록"  : "부서별 평균보다 연봉이 높은 직원"}</h1>
		<hr>
	</div>
	<div align="center">
	<table border=1>
		<tr>
			<td colspan="11"><a href="insert"> ${msg eq "list" ? "사원 정보 입력" : "" } </a></td>
		</tr>
		<tr>
			<th>Employee_id</th>
			<th>First_name</th>
			<th>Last_name</th>
			<th>Email</th>
			<th>Phone_Number</th>
			<th>HireDate</th>
			<th>Job_Id</th>
			<th>Salary</th>
			<th>CommissionPct</th>
			<th>Manager_Id</th>
			<th>Department_Id</th>
		</tr>
		<c:forEach var="emp" items="${list}">
			<%-- ${ -- 세션 페이지 등등 찾아올때 맞는는 코드를 써준다 --Scope.list } --%>
			<tr>
				<td><a href="${emp.employeeId}">${emp.employeeId}</a></td>
				<%--emp.employeeId get메서드를 가져오는것임--%>
				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td>${emp.email}</td>
				<td>${emp.phoneNumber}</td>
				<td>${emp.hireDate}</td>
				<td>${emp.jobId}</td>
				<td>${emp.salary}</td>
				<td>${emp.commissionPct}</td>
				<td>${emp.managerId}</td>
				<td>${emp.departmentId}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>