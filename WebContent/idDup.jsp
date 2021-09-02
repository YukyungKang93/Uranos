<%@page import="dao.MemberDAO"%>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<style>


body { padding-top:20px;
backgroun-color=rgb(127,127,127)
 }


.form-control { margin-bottom: 20px; }

.margin{
    margin-top:20px;    
    margin-bottom:20px;
}


.themeBtn4 {
    background:rgb(254, 175, 59);
    color: ##FFC312 !important;
    display: inline-block;
    font-size: 10px;
    font-weight: 500;
    height: 25px;
    line-height: 0.8;
    padding: 9px 20px;
    text-transform: capitalize;
    border-radius: 1px;
    letter-spacing: 0.5px;
	border:0px !important;
	cursor:pointer;
	border-radius:100px;

}
.themeBtn4:hover {
 color: black;
background-color: white;
}

}


</style>
</head>
<body>

	
<div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-xs-12 col-sm-12 col-md-4 well well-sm">
     <div style="font-size: 20px; color: rgb(254, 175, 59);">
         아이디 중복 체크</div>
	 
	
	<%
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("M_ID");

		MemberDAO memberDao = MemberDAO.getInstance();

		int result2 = memberDao.joinIdCheck(id);
		if (result2 == 1) {
			out.print("사용가능한 아이디입니다");
		%>
		<div class="col-sm-10">
			<form action="idDup.jsp" method="post" name="wfr" class="form" >
				ID : <input type="text" name="M_ID" class="form-control" value="<%=id%>"> 
				<input type="button" value="아이디 사용하기"  class="themeBtn4" onclick="result();">
		<input type="button" value="    닫   기      "  class="themeBtn4" onclick="reclose();">
		</form>
	
	
	
	
	
	<%
		} else if (result2 == 0) {
			out.print("중복된 아이디입니다");
	%>
	<fieldset>
		<form action="idDup.jsp" method="post" name="wfr" class="form" >
			ID : <input type="text" name="M_ID" class="form-control"  value="<%=id%>"> 
			<input type="submit" value="중복 확인">
		</form>
	</fieldset>
	<input type="button" value="닫기"  class="themeBtn4" onclick="reclose();">
	
	
	
	﻿
	<%
		} else {
			out.print("에러 발생!!!(-1)");
		}
	%>

	

	<script>
	function unChecking(){
    	opener.document.f1.M_ID.value = "";
	}
	unChecking();
		function result() {
			opener.document.f1.M_ID.value = document.wfr.M_ID.value;
			﻿opener.document.f1.idDuplication.value = "Checked";
			window.close();
		}		﻿
		function reclose() {
			window.close();
		}		﻿
	</script>

</body>
</html>
