<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script type="text/javascript">
	function reclose() {
		window.close();
	}
</script>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


</head>
	<br><%
		String ID = (String)session.getAttribute("M_ID1");
		String NAME = (String)session.getAttribute("M_NAME1"); 
		
		session.invalidate();
		%>
<body>
<div class="container">
 <div class="alert alert-warning">

         <strong><%=NAME %>님의 ID는<%=ID %>입니다.</strong>  <input type="button"  class="btn btn-xs btn-warning pull-right" value="닫기" onclick="reclose();">
    </div>
    </div>

		
</body>
</html>