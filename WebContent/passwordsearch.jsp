<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>

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
<body>
	<div class="container">
	<div class="row">
	&nbsp;&nbsp;&nbsp;&nbsp;<h3>비밀번호 찾기</h3>
	</div>
    <div  class="row">
     <div class="col-lg-6">
      <div class="input-group">
        <span class="input-group-addon beautiful">
	<form action="passwordsearch.do" method="post" autocomplete="off">
	
		<label for="M_NAME"><b>이&nbsp;&nbsp;&nbsp;름</b></label><br/>
    	&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="text" id="M_NAME"name="M_NAME" onKeyup="this.value=this.value.replace(/[^ㄱ-힣a-zA-Z]/gi,'');" required/><br/><br/>  
	
		<label for="M_ID"><b>아이디</b></label><br/>
	    &nbsp;&nbsp;&nbsp;&nbsp;
	    <input type="text" id="M_ID" name="M_ID" onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/gi,'');" required/><br/><br/>    
		
		<label for="email1"><b>이메일</b></label><br/>      
	    &nbsp;&nbsp;&nbsp;&nbsp;
	    <input type="text" id="email1" name="email1" onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/gi,'');" required/>@
	    <input type="text" id="email2" name="email2" value=""onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9.]/gi,'');" required/>
      
         <select id = "travel" name="travel" onclick="getEmail()">
            <option selected="selected" id="0" >직접입력</option>
            <option id="1" value="naver.com">naver.com</option>
            <option id="2" value="hanmail.net">hanmail.net</option>
            <option id="3" value="gmail.com">gmail.com</option>
         </select><br><br><br>
	<input type="submit" class="themeBtn4" value="확인">
	</form>
	</span>
	</div>
	</div>
	</div>
	</div>
</body>
</html>