<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="logoutaction.do" method="post">
		<label for="M_PW">비밀번호</label><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="password" name="password">
		
	</form>
	
	<input type="submit" value="로그아웃" onClick="console.log('a')">
	<a href="javascript:history.back();"><input type="button" id="cancel" value="취소" ></a>	
</body>
</html>