<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#form1 {
	display: inline;
}
</style>
</head>
<body>
	<h1>사원 정보 ${message eq "insert" ? "입력" : "수정"}</h1>
	<form id="form1" action="mainPage">
		<input type="submit" value="main">
	</form>
	<form id="form1" action="list">
		<input type="submit" value="List">
	</form>
	<hr>


	<c:choose>
		<c:when test="${message eq 'update'}">
			<form action="${message}" method=post>
				<table border=1>
					<tr>
						<th>Employee_id</th>
						<td><input type=text name=employeeId required
							value="${emp.employeeId}" ${empty emp ? "" : "readonly"}></td>
					</tr>
					<tr>
						<th>First_name</th>
						<td><input type=text name=firstName value="${emp.firstName}"></td>
					</tr>
					<tr>
						<th>Last_name</th>
						<td><input type=text name=lastName required
							value="${emp.lastName}"></td>
					</tr>

					<tr>
						<th>Email</th>
						<td><input type=text name=email required value="${emp.email}"
							${empty emp ? "" : "readonly"}></td>
					</tr>

					<tr>
						<th>Phone_number</th>
						<td><input type=text name=phoneNumber required
							value="${emp.phoneNumber}"></td>
					</tr>

					<tr>
						<th>Hire_date</th>
						<td><input type=date name=hireDate required
							value="${emp.hireDate}"></td>
					</tr>

					<tr>
						<!-- 				여기선 메서드의 키값과 벨류값을 적어줘야함 -->
						<th>Job_id</th>
						<td><select name=jobId>
								<c:forEach var="job" items="${jobList}">
									<option value="${job.jobId}"
										${emp.jobId eq job.jobId ?  "selectde" : ""}>
										${job.jobTitle}</option>
								</c:forEach>
						</select>
					</tr>

					<tr>
						<th>Salary</th>
						<td><input type=text name=salary value="${emp.salary}"></td>
					</tr>

					<tr>
						<th>Commission_pct</th>
						<td><input type=number name=commissionPct step=0.05 min=0
							max=0.95 value="${emp.commissionPct}"></td>
					</tr>

					<tr>
						<th>Manager_id</th>
						<td><select name=managerId>
								<c:forEach var="man" items="${manList}">
									<option value="${man.managerId}"
										${emp.managerId eq man.managerId ? "selected" : "" }>${man.managerName}</option>
								</c:forEach>
						</select>
					</tr>

					<tr>
						<th>Department_id</th>
						<td><select name=departmentId>
								<c:forEach var="dept" items="${deptList}">
									<option value="${dept.departmentId}"
										${emp.departmentId eq dept.departmentId ? "selected" : "" }>${dept.departmentName}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<th colspan=2><input type=submit value="수정">
						 <input	type=reset value="취소"></th>
					</tr>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<form:form action="./${message}" method="post" modelAttribute="emp">
				<table border=1>
					<tr>
						<th>Employee_id</th>
						<td><form:input path="employeeId" /> <form:errors
								path="employeeId" /></td>
					</tr>
					<tr>
						<th>First_name</th>
						<td><form:input path="firstName" /> <form:errors
								path="firstName" /></td>
					</tr>
					<tr>
						<th>Last_name</th>
						<td><form:input path="lastName" /> <form:errors
								path="lastName" /></td>
					</tr>

					<tr>
						<th>Email</th>
						<td><form:input path="email" /> <form:errors path="email" /></td>
					</tr>

					<tr>
						<th>Phone_number</th>
						<td><form:input path="phoneNumber" /> <form:errors
								path="phoneNumber" /></td>
					</tr>

					<tr>
						<th>Hire_date</th>
						<td><form:input path="hireDate" type="date"
								required="required" /> <form:errors path="hireDate" /></td>
					</tr>

					<tr>
						<!-- 				여기선 메서드의 키값과 벨류값을 적어줘야함 -->
						<th>Job_id</th>
						<td><form:select path="jobId">
								<c:forEach var="job" items="${jobList}">
									<option value="${job.jobId}">${job.jobTitle}</option>
								</c:forEach>
							</form:select></td>

						<%--<form:options items ="${jobList}" itemLabel="jbTitle" itemValue="jobId"/> --%>
					</tr>

					<tr>
						<th>Salary</th>
						<td><form:input path="salary" /> <form:errors path="salary" /></td>
					</tr>

					<tr>
						<th>Commission_pct</th>

						<td><form:input path="commissionPct" type="number" /> <form:errors
								path="commissionPct" /></td>
					</tr>

					<tr>
						<th>Manager_id</th>
						<td><form:select path="managerId">
								<c:forEach var="man" items="${manList}">
									<option value="${man.managerId}">${man.managerName}</option>
								</c:forEach>
							</form:select>
					</tr>

					<tr>
						<th>Department_id</th>
						<td><form:select path="departmentId">
								<c:forEach var="dept" items="${deptList}">
									<option value="${dept.departmentId}">${dept.departmentName}</option>
								</c:forEach>
							</form:select>
					</tr>

					<tr>
						<th colspan=2><input type=submit value="입력"> <input
							type=reset value="취소"></th>
					</tr>
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>
</body>
</html>