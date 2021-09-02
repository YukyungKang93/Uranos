<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String ID = (String) session.getAttribute("M_ID");
	String fail = (String) session.getAttribute("fail");
	String url = (String) session.getAttribute("URL");
	int p_num = -1;
	if(session.getAttribute("p_num")!=null){
		p_num = (Integer) session.getAttribute("p_num");
	}		
	String alert = null;

	if (fail == null) {
		alert = "";
	} else if (fail != null) {
		alert = fail;
	}
	session.invalidate();
	HttpSession session1 = request.getSession(true);
	
	session1.setAttribute("URL", url);
	session1.setAttribute("p_num", p_num);
%>


<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->



<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
	function doBlink() {
		var blink = document.all.tags("BLINK");
		for (var i = 0; i < blink.length; i++) {
			blink[i].style.visibility = blink[i].style.visibility == "" ? "hidden"
					: "";
		}
	}
	function startBlink() {
		if (document.all) {
			setInterval("doBlink()", 350);
		}
	}
	window.onload = startBlink;
</script>
<script type="text/javascript">
	function showidPopup() {
		window.open("/idsearch.jsp", "a",
				"width=700, height=500, left=100, top=50");
	}
</script>
<script type="text/javascript">
	function showpasswordPopup() {
		window.open("/passwordsearch.jsp", "a",
				"width=700, height=500, left=100, top=50");
	}
</script>
<meta charset="UTF-8">
<title>로그인</title>
 <!--Made with love by Mutiullah Samim -->
   
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="styles.css">
<style>
@import url('https://fonts.googleapis.com/css?family=Numans');

html,body{
background-image: <%--url('img/together4.jpg');--%>
url('http://getwallpapers.com/wallpaper/full/a/5/d/544750.jpg');
background-size: cover;
background-repeat: no-repeat;
height: 100%;
font-family: 'Numans', sans-serif;
}

.container{
height: 100%;
align-content: center;
}

.card{
height: 370px;
margin-top: auto;
margin-bottom: auto;
width: 400px;
background-color: rgba(0,0,0,0.5) !important;
}

.social_icon span{
font-size: 60px;
margin-left: 10px;
color: #FFC312;
}

.social_icon span:hover{
color: white;
cursor: pointer;
}

.card-header h3{
color: white;
}

.social_icon{
position: absolute;
right: 20px;
top: -45px;
}

.input-group-prepend span{
width: 50px;
background-color: #FFC312;
color: black;
border:0 !important;
}

input:focus{
outline: 0 0 0 0  !important;
box-shadow: 0 0 0 0 !important;

}

.remember{
color: white;
}

.remember input
{
width: 20px;
height: 20px;
margin-left: 15px;
margin-right: 5px;
}

.login_btn{
color: black;
background-color: #FFC312;
width: 100px;
}

.login_btn:hover{
color: black;
background-color: white;
}

.links{
color: white;
}

.links a{
margin-left: 4px;
}
</style>
</head>
<body oncopy="return false" oncut="return false" onpaste="return false">
	<form action="loginaction.do" method="post" autocomplete="off">
	<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Sign In</h3>
			</div>
			<div class="card-body">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="text" name="M_ID" class="form-control" 	onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/gi,'');"
			required placeholder="아이디">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" name="M_PW" class="form-control" placeholder="비밀번호" required >
					</div>
						<div id="fail" style="color: red"><%=alert%></div>
				
					<div class="form-group">
						<input type="submit" value="로그인" class="btn float-right login_btn">
					</div>
					
	
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					<a href="/signup.do" id="signUp">회원가입</a>
				</div>
					<div class="d-flex justify-content-center">
					<a href="#" onclick="showidPopup();" id="id_search">아이디 찾기</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="#" onclick="showpasswordPopup();" id="password_search">비밀번호 찾기</a>
				</div>
					
			</div>
		</div>
	</div>
</div>
</form>

</body>
</html>