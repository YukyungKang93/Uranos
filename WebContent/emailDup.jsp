<%@page import="dao.MemberDAO"%>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idDup</title>
<script>
	function setId() {
		opener.f1.userId.value = document.f1.userId.value;
		opener.f1.idDupChk.value = 't';
		self.close();
	}

	<!--
//-->
</script>

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
         이메일 중복 체크</div>
	<%
		String id ="admin";
		request.setCharacterEncoding("UTF-8");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String sumEmail = email1 + "@" + email2;

		MemberDAO memberDao = MemberDAO.getInstance();

		int result = memberDao.joinEmailCheck(email1, email2, id);
		if (result == 1) {
		
	%>
		 <br><strong>사용 가능한 이메일입니다.</strong>
		 	<div class="col-sm-10"> 
		<fieldset>
		<form action="emailDup.jsp" method="post" name="wfr"  class="form">
			email : 
			<input type="text" name="email1" class="form-control"  value="<%=email1%>">
			@ <input type="text" name="email2" class="form-control"  value="<%=email2%>"> 
			<input type="button" value="이메일 사용하기" class="themeBtn4" onclick="result();">
				<input type="button" value="닫기" class="themeBtn4" onclick="reclose();">
		</form>
	</fieldset>

	<%
		} else if (result == 0) {
			
			
	%><br><strong>중복된 이메일입니다.</strong>
	
	<fieldset>
		<form action="emailDup.jsp" method="post" name="wfr" class="form">
			email : 
			<input type="text" class="form-control" name="email1" value="<%=email1%>">
			@ <input type="text" class="form-control" name="email2" value="<%=email2%>"> 
			<input type="submit" class="themeBtn4" value="중복 확인">
			<input type="button" value="닫기" class="themeBtn4" onclick="reclose();">
		</form>
	</fieldset>
	

	<%
		} else {
			out.print("에러 발생!!!(-1)");
		}
	%>



	
	<script>
		function result() {
			opener.document.f1.email1.value = document.wfr.email1.value;
			opener.document.f1.email2.value = document.wfr.email2.value;
			opener.document.f1.emailDuplication.value = "Checked";
			window.close();
		}
		function reclose(){
			window.close();
		}
		function unChecking(){
    		opener.document.f1.email1.value = "";
    		opener.document.f1.email2.value = "";
		}
		unChecking();
	</script>

</body>
</html>
