<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="vo.MemberListBean" %>
<%@ page import="vo.MemberBean" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<%
	HttpSession session1 = request.getSession(true);
	
	String id = "";
	String name = "";
	String addr = "";
	String detail_addr = "";
	String ref_addr = "";
	String email = "";
	String phone = "";
	String gender = "";
	String birth = "";
	String button = "";
	
	if(session1.getAttribute("임시저장") == null){
		
		id = request.getParameter("idModify");
		name = request.getParameter("nameModify");
		addr = request.getParameter("sample6_address");
		detail_addr = request.getParameter("sample6_detailAddress");
		ref_addr = request.getParameter("sample6_extraAddress");
		email = request.getParameter("modEmail1") + "@" + request.getParameter("modEmail2");
		phone = request.getParameter("phoneModify");
		if(request.getParameter("genderModify")!=null||request.getParameter("genderModify")!=""){
			gender = request.getParameter("genderModify");
		}
		if(request.getParameter("YEAR")!=""&&request.getParameter("MONTH")!=""&&request.getParameter("DAY")!=""){
			birth = request.getParameter("YEAR") + "년" + request.getParameter("MONTH")+ "월" + request.getParameter("DAY") + "일";
		}
		button = request.getParameter("button");
		
	}else if(session1.getAttribute("임시저장") != null){
		MemberBean list = (MemberBean)session1.getAttribute("임시저장");
		id = list.getM_ID();
		name = list.getM_NAME();
		addr = list.getADDR();
		detail_addr = list.getDETAIL_ADDR();
		ref_addr = list.getREF_ADDR();
		email = list.getEMAIL();
		phone = list.getPHONE();
		gender = list.getGENDER();
		birth = list.getBIRTH();
		button = list.getM_PW_CONFIRM();
	}
	
%>
<meta charset="UTF-8">
<title>확인</title>

<script>
window.onfocus=function(){
}
window.onload=function(){
	
document.getElementById('List').value
//window.focus(); // 현재 window 즉 익스플러러를 윈도우 최상단에 위치
//window.moveTo(0,0); // 웹 페이지의 창 위치를 0,0 (왼쪽 최상단) 으로 고정
window.resizeTo(300,100); // 웹페이지의 크기를 가로 1280 , 세로 800 으로 고정(확장 및 축소)
//window.scrollTo(0,250); // 페이지 상단 광고를 바로 볼 수 있게 스크롤 위치를 조정

}

</script>
<!-- 버튼 -->

<link href="assets2/css/style.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<style>
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

.jg {
	font-family: 'Jeju Gothic', sans-serif;
}

#passForm {
	width: 450px;
	height: 220px;
	margin: auto;
	border: 10px solid orange;
	align: center;
	padding-top: 25px;
	border-radius: 100px;
}

#bubu {
	align: center;
}

#hr {
	border: 10px solid orange;
}

h3 {
	text-align: left;
	font-size: 15px;
}

p {
	text-align: center;
	font-size: 15px;
}

m_pw {
	padding-left: 50px;
}

#rgrg {
	align: center;
}

.readmore {
	font-weight: 600;
	font-size: 13px;
	letter-spacing: 1px;
	text-transform: uppercase;
	display: inline-block;
	padding: 12px 30px;
	border-radius: 50px;
	transition: 0.5s;
	line-height: 1;
	margin: 0 10px;
	-webkit-animation-delay: 0.8s;
	animation-delay: 0.8s;
	border: 2px solid #ffb03b;
}

#pass{
	width: 300px;
	align: center;
	}




</style>

<section id="aa-catg-head-banner">
	<div class="aa-catg-head-banner-area">
		<div class="container"></div>
	</div>
</section>


</head>
<body>
	<form action="clientmodify.do" method="post">
	<%
	
	if(request.getParameter("newPass")==null || request.getParameter("newPass")==""){
		%>
		
		<input type="hidden" name="button" value="<%=button%>">
		<input type="hidden" name="id" value="<%=id%>">
		<input type="hidden" name="name" value="<%=name%>">
		<input type="hidden" name="addr" value="<%=addr%>">
		<input type="hidden" name="detail_addr" value="<%=detail_addr%>">
		<input type="hidden" name="ref_addr" value="<%=ref_addr%>">
		<input type="hidden" name="email" value="<%=email%>">
		<input type="hidden" name="phone" value="<%=phone%>">
		<input type="hidden" name="gender" value="<%=gender%>">
		<input type="hidden" name="birth" value="<%=birth%>">
		
		<%}else{%>
		
		<input type="hidden" name="button" value="<%=button%>">
		<input type="hidden" name="pass" value="<%=request.getParameter("newPass")%>">
		<input type="hidden" name="id" value="<%=id%>">
		<input type="hidden" name="name" value="<%=name%>">
		<input type="hidden" name="addr" value="<%=addr%>">
		<input type="hidden" name="detail_addr" value="<%=detail_addr%>">
		<input type="hidden" name="ref_addr" value="<%=ref_addr%>">
		<input type="hidden" name="email" value="<%=email%>">
		<input type="hidden" name="phone" value="<%=phone%>">
		<input type="hidden" name="gender" value="<%=gender%>">
		<input type="hidden" name="birth" value="<%=birth%>">
		
	<% }%>
	<br>
	<br>
	<br>
		<br>
		
	<section id="passForm">
		<p class="jg">관리자 암호를 입력하세요</p><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="pass"type="password" name="password" required><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="bu"type="submit" value="확인" class="readmore"> &nbsp;&nbsp;
		<a href="javascript:history.back();"><input type="button" value="취소" class="readmore"></a>
		
	</section>	
		
		
		
	</form>
	
	
	<!-- wwww -->
	
	
	
	
	
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
<jsp:include page="/adminHeader.jsp" />
<jsp:include page="/footer.jsp" />
</body>
</html>