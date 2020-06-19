<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<script src = "//code.jquery.com/jquery-3.5.1.min.js" ></script>
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
	<sec:authentication var="auth" property="authorities"/>
	<c:set var = "length" value="${fn:length(userInfo.auth)}"/>
	<c:set var = "authority" value="${fn:substring(userInfo.auth , 5 , length)}"/>
	<jsp:include page="../header/header.jsp"/>
	
		<c:choose>
			<c:when test="${message eq 'update'}">
				<div align="center">
					<table border="1">
					<form action="<c:url value ='/member/update.info'/>">
					<input type="hidden" name="userId" value="${userInfo.userId }">
					<input type="hidden" name="userAuth" value="${auth}">
					<tr>
						<th width="300px">User ID</th>
						<th width="300px">${userInfo.userId}</th>
					</tr>
					<tr>
						<th>User Password</th>
						<th><input class="input_text" type="text" name="password" id="pwd"></th>
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
					<c:choose>
						<c:when test="${auth eq '[ROLE_USER]'}">
							<tr>
								<th>User Enabled</th>
								<th>${userInfo.enabled ? "활성화" : "비활성화"}</th>
							</tr>
							<tr>
								<th>User Authority</th>
								<th>${authority}</th>
							</tr>
							</table>
							
							<hr>
							<div align="center">
							<input class="input" type="submit" value="Update">
							</form>
							<form action="" name="form" style="display: inline;">
								<input type="hidden" name="userId" value="${userInfo.userId }">
								<input type="hidden" name="userAuth" value="${auth}">
								<input type="hidden" name="password" value="" id="inputpwd">
								<input class="input" type="submit" value="회원 탈퇴" id="form">
							</form>
							</div>
						</c:when>
						<c:when test="${auth eq '[ROLE_ADMIN]'}">
							<tr>
								<th>User Enabled</th>
								<th>${userInfo.enabled ? "enable  <input type='button' value=disable  onclick='authUpdate()'" : "disable  <input type='button' value=enable  onclick='authUpdate()'"}</th>
							</tr>
							<tr>
								<th>User Authority</th>
								<th>${authority}</th>
							</tr>
							</table>
							
							<hr>
							<div align="center">
							<input class="input" type="submit" value="Update">
							</form>
							<form action="" name="form" style="display: inline;">
								<input type="hidden" name="userId" value="${userInfo.userId }">
								<input type="hidden" name="userAuth" value="${auth}">
								<input type="hidden" name="password" value="" id="inputpwd">
								<input class="input" type="submit" value="회원 탈퇴" id="form">
							</form>							
							</div>
						</c:when>
						<c:when test="${auth eq '[ROLE_MASTER]'}">
							<tr>
								<th>User Enabled</th>
								<th>${userInfo.enabled ? "활성화  <input type='button' value=비활성화  onclick='authUpdate()'" : "비활성화  <input type='button' value=비활성화  onclick='authUpdate()'"}</th>
							</tr>
							<tr>
								<th>User Authority</th>
								<th>
									<select name="auth">
										<option value="ROLE_USER">USER
										<option value="ROLE_ADMIN">ADMIN
									</select>
								</th>
							</tr>
							</table>
							
							<hr>
							<div align="center">
							<input class="input" type="submit" value="Update">
							</form>
							<form action="" name="form" style="display: inline;">
								<input type="hidden" name="userId" value="${userInfo.userId }">
								<input type="hidden" name="userAuth" value="${auth}">
								<input type="hidden" name="password" value="" id="inputpwd">
								<input class="input" type="submit" value="회원 탈퇴" id="form">
							</form>
							</div>
						</c:when>
					</c:choose>
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
	
	<script type="text/javascript">
		function authUpdate() {
			location.href="<c:url value='/member/auth/update?auth=${userInfo.enabled ? "0" : "1"}&userId=${userInfo.userId}'/>";
		}
		
		$(document).ready(function() {
			$("#form").click(function () {
				var pwd = document.getElementById('pwd').value
				document.getElementById('inputpwd').value=pwd
				document.form.action="member.delete?"
				
			})
		})
		
	</script>
	
	
</body>
</html>