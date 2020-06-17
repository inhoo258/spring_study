<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.div {
	height: 350px;
	width: 400px;
	background-color: skyblue;
	border-style: solid;
}

h1 {
	text-align: center;
}
</style>
</head>
<body>
	<div class="div" align="center">
		<h1>Main Page</h1>
		<table border="2">
			<tr>
				<td>1.<a href="cnt">총 사원 인원수</a>
				</td>
			</tr>
			<tr>
				<td>2.<a href="dept_cnt">부서별 인원수</a></td>
			</tr>
			<tr>
				<td>
					<form action="cnt">
						부서별 인원 수<br> <input type="text" placeholder="부서 Id 입력"
							name="deptId"> <input type="submit" value="검색">
					</form>
				</td>
			</tr>
			<tr>
				<td>3.<a href="${msglist}">전체 사원 정보</a></td>
			</tr>
			<tr>
				<td>4.<a href="insert">신규 사원 정보 입력</a></td>
			</tr>
			<tr>
				<td>5.<a href="${msgmax}">부서별 최고 급여자 조회</a></td>
			</tr>
			<tr>
				<td>6.<a href="${msgavg}">부서별 연봉이 부서별 평균 이상인 사람</a></td>
			</tr>
		</table>
	</div>

</body>
</html>