<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	function reclose() {
		window.close();
	}
</script>

<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


</head>
<body oncopy="return false" oncut="return false" onpaste="return false">
		<%
		String NAME = (String)session.getAttribute("M_NAME2");
		String PASS = (String)session.getAttribute("S_PASS");
		
		session.invalidate();
		%>
	
	
	<div class="container">
<br> <div class="alert alert-warning">
         <strong>임시 비밀번호: <%=NAME %>님의 임시 비밀번호는 <%=PASS %>입니다.</strong> 
         <input type="button"  class="btn btn-xs btn-warning pull-right" value="닫기" onclick="reclose();">
    </div>
    </div>
</body>
</html>