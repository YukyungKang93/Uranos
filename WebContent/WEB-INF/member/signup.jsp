<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session = "true" %>
<%@ page import = "dao.MemberDAO" %>
<%@ page import = "vo.MemberBean" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "util.JdbcUtil" %>



<!DOCTYPE html>
<html><head>

<script>
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
	function male(){
		//체크 해제할 라디오버튼 불러오기
        var GENDER = document.getElementsByName("GENDER");
        $("input:radio[name='GENDER']:radio[value='남자']").prop("checked", true);
		$("input:radio[name='GENDER']:radio[value='여자']").prop("checked", false);
		$("input:radio[name='GENDER']:radio[value='선택안함']").prop("checked", false);
        
	}
	function female(){
		//체크 해제할 라디오버튼 불러오기
        var GENDER = document.getElementsByName("GENDER");
        $("input:radio[name='GENDER']:radio[value='남자']").prop("checked", false);
		$("input:radio[name='GENDER']:radio[value='여자']").prop("checked", true);
		$("input:radio[name='GENDER']:radio[value='선택안함']").prop("checked", false);
        
	}
	function cancelAll(){
		var GENDER = document.getElementsByName("GENDER");
		$("input:radio[name='GENDER']:radio[value='남자']").prop("checked", false);
		$("input:radio[name='GENDER']:radio[value='여자']").prop("checked", false);
		$("input:radio[name='GENDER']:radio[value='선택안함']").prop("checked", true);
	}
</script>

<script type="text/javascript">
function idCheck(id) {
    if (id == "") {
       alert("아이디를 먼저 입력하세요.");
       document.f1.M_ID.focus() 
    } else {
       url = "idDup.jsp?M_ID=" + id;
       window.open(url, "post", "width=400, height=220");
    }
 }
   function emailCheck(email1, email2) {
      if (email1 == "") { 
         alert("이메일 아이디을 입력하세요.");
         document.f1.email1.focus()
      } else if(email2 == ""){
         alert("이메일 주소를 입력하세요.")
         doucment.f1.email2.focus()
      } 
      else {
         url = "emailDup.jsp?email1=" + email1 + "&email2=" + email2;
         window.open(url, "post", "width=560, height=250");
      }
   }
   
</script>

   <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   <script>
       function sample6_execDaumPostcode() {
           new daum.Postcode({
               oncomplete: function(data) {

                   var addr = ''; 
                   var extraAddr = ''; 
   
                   if (data.userSelectedType === 'R') {
                       addr = data.roadAddress;
                   } else { 
                       addr = data.jibunAddress;
                   }
   
                   if(data.userSelectedType === 'R'){

                       if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                           extraAddr += data.bname;
                       }
                       if(data.buildingName !== '' && data.apartment === 'Y'){
                           extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                       }
                       if(extraAddr !== ''){
                           extraAddr = ' (' + extraAddr + ')';
                       }
                       document.getElementById("sample6_extraAddress").value = extraAddr;
                   
                   } else {
                       document.getElementById("sample6_extraAddress").value = '';
                   }
   
                   document.getElementById('sample6_postcode').value = data.zonecode;
                   document.getElementById("sample6_address").value = addr;
                   document.getElementById("sample6_detailAddress").focus();
               }
           }).open();
       }
   </script>
   <script type="text/javascript">
   ﻿
   function dupCheck(){
	if(document.f1.idDuplication.value == "idUncheck"){
	   alert("아이디 중복체크가 안되었습니다.");
	   return false;
   }else if(document.f1.emailDuplication.value == "emailUncheck"){
	   alert("이메일 중복체크가 안되었습니다.");
	   return false;
   }else if(document.f1.idDuplication.value == "Checked" && document.f1.emailDuplication.value == "Checked"){
	   return true;
   }else{
	   alert("중복체크에서 알수 없는 에러입니다. 관리자에게 문의하세요.");
	   return false;
	   }
   }

      function check_pw(){
          
          var pw = document.getElementById('pw').value;
          var SC = ["!","@","#","$","%"];
          var check_SC = 0;
      
          if(pw.length < 6 || pw.length> 16){
              window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
              document.getElementById('pw').value='';
          }
          for(var i=0;i<SC.length;i++){
              if(pw.indexOf(SC[i]) != -1){
                  check_SC = 1;
              }
          }
          if(check_SC == 0){
              window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
              document.getElementById('pw').value='';
          }
          if(document.getElementById('pw').value !='' && document.getElementById('pwCheck').value!=''){
              if(document.getElementById('pw').value==document.getElementById('pwCheck').value){
                  document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                  document.getElementById('check').style.color='blue';
              }
              else{
                  document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                  document.getElementById('check').style.color='red';
              }
          }
      }
   </script>
   


<meta charset="UTF-8">
<title>회원 가입</title>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
$(document).ready(function(){
$('input[type="checkbox"][name="GENDER"]').click(function(){
	  if($(this).prop('checked')){
	    	$('input[type="checkbox"][name="GENDER"]').prop('checked', false);
  	    $(this).prop('checked',true);
  	}
 });
});	 
</script>


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


.form-control { margin-bottom: 20px; font-size:15px; }

.margin{
    margin-top:20px;    
    margin-bottom:20px;
}


.themeBtn4 {
    background:rgb(254, 175, 59);
    color: ##FFC312 !important;
    display: inline-block;
    font-size: 15px;
    font-weight: 500;
    height: 50px;
    line-height: 0.8;
    padding: 18px 60px;
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
strong {
font-size:10px;
}
b {
font-size:15px;
}
label{
font-size:15px;}

select.form-control:not([size]):not([multiple]) {
    height: calc(3.25rem + 2px);
}
}


</style>
</head>
<body oncopy="return false" oncut="return false" onpaste="return false">

<div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-xs-12 col-sm-12 col-md-8 well well-sm">
     <div style="font-size: 30px; color: rgb(254, 175, 59);">
         <i class="far fa-grin"></i>회원가입</div>
       <form action="signupaction.do" onsubmit="return dupCheck();" method="post" name="f1" autocomplete="off" class="form" role="form">

 			  <br><input type="text" class="form-control" name="M_NAME" onKeyup="this.value=this.value.replace(/[^ㄱ-힣a-zA-Z]/gi,'');" placeholder="*이름(한글 또는 영문 기입)" required/>


            <br><div class="row">
                <div class="col-xs-6 col-md-6">
                     <input  type="text" id="M_ID" class="form-control" name="M_ID" onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/gi,'');" minlength="5" maxlength="20" required
    placeholder="*아이디" autofocus />
                </div>
                <div class="col-xs-6 col-md-6">
                   <input type="button" class="form-control" name ="ID_CHECK" value="중복확인" onclick="idCheck(this.form.M_ID.value);">
      <input type="hidden" class="form-control"  name="idDuplication" value="idUncheck"/>   
                </div>
            </div><br>

            <input type="password"class="form-control" placeholder="*비밀번호" id="M_PW" name="M_PW" 
      onchange="check_pw()" pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_-+=[]{}~?:;`|/]).{4,10}$" minlength="6" required/>

       <input type="password"  placeholder="*비밀번호 확인"class="form-control"  id="M_PW_CHECK" name="M_PW_CHECK" onchange="check_pw()" minlength="6" required/>


     <br><input type="text" id="email1"  placeholder="*이메일" class="form-control"  name="email1" onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/gi,'');" required/>@
      <input type="text" id="email2" class="form-control"  name="email2" value=""onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9.]/gi,'');" required/>
         <select class="form-control" id = "travel" name="travel" onclick="getEmail()">
            <option selected="selected" id="0" >직접입력</option>
            <option id="1" value="naver.com">naver.com</option>
            <option id="2" value="hanmail.net">hanmail.net</option>
            <option id="3" value="gmail.com">gmail.com</option>
         </select>
		 <input type="button" class="form-control" name ="EMAIL_CHECK" value="중복확인" onclick="emailCheck(this.form.email1.value, this.form.email2.value);">
     	 <input type="hidden" name="emailDuplication" value="emailUncheck"/>


  <br>  <input type="hidden" id="sample6_postcode" onKeyup="this.value=this.value.replace(/[^ㄱ-힣0-9]/gi,'');" placeholder="우편번호" required>
<input type="text" class="form-control"name="sample6_address" id="sample6_address" onKeyup="this.value=this.value.replace(/[^ㄱ-힣0-9]/gi,'');" placeholder="주소" required>
      <input type="button" class="form-control" onclick="sample6_execDaumPostcode()" value="주소 검색" required>
      <input type="text" class="form-control" name="sample6_detailAddress" id="sample6_detailAddress" onKeyup="this.value=this.value.replace(/[^ㄱ-힣0-9]/gi,'');" placeholder="상세주소">
      <input type="text" class="form-control" name="sample6_extraAddress" id="sample6_extraAddress" onKeyup="this.value=this.value.replace(/[^ㄱ-힣0-9()]/gi,'');" placeholder="참고항목">


<br><input type="tel" class="form-control" placeholder="전화번호" id="PHONE" name="PHONE" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength='11' required/>
<sup><em><strong>*'-'없이 전화번호를 입력해주세요.</strong></em></sup><br><br>

<br>
<b>성별</b>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="radio-inline">
                <input type="radio"  id="남자" name="GENDER" value="남자" onclick="male();"/>
                남자
            </label>
         &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;  <label class="radio-inline">
                <input type="radio" id="여자" name="GENDER" value="여자" onclick="female();"/>
              여자
            </label>
            <label class="radio-inline">
                <input type="radio" name="GENDER" value="" onclick="cancelAll();"/>
		선택안함
            </label>
<br><br><sup><em><strong>*필수 입력이 아닙니다.</strong></em></sup>
    

<br><br><br><b>생년월일</b>
 <div class="row">
                <div class="col-xs-4 col-md-4">
                     <input  type="text"  class="form-control"  id="YEAR" name="YEAR" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength='4'
    placeholder="년" />
                </div>
                <div class="col-xs-4 col-md-4">
                   <input type="text" class="form-control"  id="MONTH" name="MONTH" placeholder="월" maxlength='2'/>
                </div>
                  <div class="col-xs-4 col-md-4">
                   <input type="text" class="form-control"  id="DAY" name="DAY" placeholder="일" maxlength='2'/>
                </div>
    
    
     <br><br> <sup><em><strong>&nbsp;&nbsp;&nbsp;&nbsp;*필수 입력이 아닙니다.</strong></em></sup>
      <br/><br/>
      </div>
      <div class="container-fluid  margin">
            <button class="themeBtn4" type="submit" value="가입">
             가입 </button>&nbsp;&nbsp;&nbsp;&nbsp;
                 <a href="javascript:history.back();"><input  class="themeBtn4" type="button" id="cancel" value="취소" ></a>   
            </form>
        </div>
        </div>
    </div>

</body>

</html>