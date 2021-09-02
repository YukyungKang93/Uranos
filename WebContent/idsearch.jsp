<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="/WEB-INF/member/hiden_inputText.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	function getEmail() {
	  var s = document.getElementById("travel");
	  var email = s.options[s.selectedIndex].value;
	  
	  if(email == "직접입력"){
		  document.getElementById('email2').readOnly = false;
		  document.getElementById("email2").value = "";
	  }else{
		  document.getElementById('email2').readOnly = true;
		  document.getElementById("email2").value = email;
	  }	  	  
	}
</script>

<script type="text/javascript">
	function hidden() {
		var s = document.getElementById("cer");
		var email = s.options[s.selectedIndex].value;
	
		console.log(s);
	}
</script>

<script>

window.onload = function(){
	document.getElementById("withPHONE").style.display = "none";
	document.getElementById("withEMAIL").style.display = "none";
}

</script>

<script type="text/javascript">
 function emailShow() {
  document.getElementById("withEMAIL").style.display = "block";
  document.getElementById("withPHONE").style.display = "none";
 }
 </script>
 
 <script type="text/javascript">
 function phoneShow() {
	 document.getElementById("withEMAIL").style.display = "none";
	 document.getElementById("withPHONE").style.display = "block";
 }
</script>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
.themeBtn4 {
    background:rgb(254, 175, 59);
    color: ##FFC312 !important;
    display: inline-block;
    font-size: 20px;
    font-weight: 500;
    height: 30px;
    line-height: 0.8;
    padding: 10px 20px;
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


</style>

</head>
<body oncopy="return false" oncut="return false" onpaste="return false">
	<div class="container">
	<div class="row">
		&nbsp;&nbsp;&nbsp;&nbsp;<h3>아이디 찾기</h3>
	</div>
<p>
    <div  class="row">
     <div class="col-lg-6">
      <div class="input-group">
        <span class="input-group-addon beautiful">
	<input type="radio" id="EMAIL" name="cer" value="EMAIL" onclick="emailShow();"/> <label for="EMAIL">회원정보에 등록한 이메일로 인증</label><br>
<form action="/member/emailId.do" id="withEMAIL" method="post" autocomplete="off">
   		<label for="M_NAME1"><b>이&nbsp;&nbsp;&nbsp;름</b></label>
    	&nbsp;
		<input type="text" id="M_NAME1" name="M_NAME1" onKeyup="this.value=this.value.replace(/[^ㄱ-힣a-zA-Z]/gi,'');" required/><br/><br/>
   		
   		<label for="email1"><b>이메일</b></label>      
	    &nbsp;
	   <input type="text" id="email1" name="email1" onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/gi,'');" required/> @
	   <input type="text" id="email2" name="email2" value=""onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9.]/gi,'');" required/>
	    
         <select id = "travel" name="travel" onclick="getEmail()">
            <option selected="selected" id="0" >직접입력</option>
            <option id="1" value="naver.com">naver.com</option>
            <option id="2" value="hanmail.net">hanmail.net</option>
            <option id="3" value="gmail.com">gmail.com</option>
         </select><br><br><br>
   		
		<input type="submit" class="themeBtn4" value="확인">
</form><br/><br/>
	<input type="radio" id="PHONE" name="cer" value="PHONE" onclick="phoneShow();"/> <label for="PHONE">회원정보에 등록한 휴대전화로 인증</label>	
<form action="/member/phoneId.do" id="withPHONE" method="post" autocomplete="off">
	<div id="휴대전화인증">
   		<label for="M_NAME1"><b>이&nbsp;&nbsp;&nbsp;름</b></label>
   		&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="text" id="M_NAME2" name="M_NAME2" onKeyup="this.value=this.value.replace(/[^ㄱ-힣a-zA-Z]/gi,'');" required/><br/><br/>
   		
		 <label for="PHONE"><b>전화번호</b></label>
     	&nbsp;&nbsp;&nbsp;&nbsp;<input type="tel" id="PHONE" name="PHONE" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" required/><br/><br/><br/>
		
		<input type="submit" class="themeBtn4" value="확인">
	
	</div>

</form>
    </span>
	</div>
	</div>
	</div>
	
	</div>
	
</body>
</html>