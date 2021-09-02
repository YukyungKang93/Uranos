<%@page import="java.util.ArrayList"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	String pwdcheck = session.getAttribute("M_PW").toString();

String m_name = (String)request.getAttribute("M_NAME");
	String m_id = (String)request.getAttribute("M_ID");
	String addr = (String)request.getAttribute("ADDR");
	String detail_addr = (String)request.getAttribute("DETAIL_ADDR");
	String ref_addr = (String)request.getAttribute("REF_ADDR");
	String email1 = (String)request.getAttribute("EMAIL1");
	String email2 = (String)request.getAttribute("EMAIL2");
	String phone = (String)request.getAttribute("PHONE");
	String gender = (String)request.getAttribute("GENDER");
	String birth = (String)request.getAttribute("BIRTH");
	String year = "";
	String month = "";
	String day = "";
	
	if(birth.length()!=0){
		year = birth.substring(0,4);
		month = birth.substring(5,7);
		day = birth.substring(8,10);
	}
	
	String myDetail = "myDetail"; 
	String myAct = "myAct"; 
	String myApply = "myApply"; 
	String myReply = "myReply"; 
	String myMod = "myMod";
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="..../css/memberModi.css">
<style>

#p_img{ height: 100px; }
#myRevList{width: 200px; display:inline-block; }
</style>
<title>내정보</title>
	<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
	<script>
		window.onload = function(){
		if(<%=gender.equals("남자")%>){
			$("input:radio[name='GENDER']:radio[value='남자']").prop("checked", true);
		}else if(<%=gender.equals("여자")%>){
			$("input:radio[name='GENDER']:radio[value='여자']").prop("checked", true);
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

		$(document).ready(function(){
			$('input[type="checkbox"][name="modGen"]').click(function(){
		  		if($(this).prop('checked')){
		    		$('input[type="checkbox"][name="modGen"]').prop('checked', false);
	  	   			 $(this).prop('checked',true);
	  			}
	 		});
		});	 

		$(document).ready(function(){
			$('#pwdcheck1').focusout(function(){
				var pwdcheck1 = $("#pwdcheck1").val();
		    	if(pwdcheck1 != '<%=pwdcheck%>'){
		    		alert("현재 암호와 다릅니다.");
		    		$("#pwdcheck1").val("");
	    		} 
			});
			$('#pwdcheck2').focusout(function () {
				var pwdcheck2 = $("#pwdcheck2").val();				  
		        if ( pwdcheck2 == '<%=pwdcheck%>' ) {
		                alert("현재 암호와 같습니다. 새로운 암호를 입력해주세요.");
	                	$("#pwdcheck2").val("");
        		} 
	        });   
		});	 
				
				
		function emailCheck(email1, email2) {
			if (email1 == "") { 
				alert("이메일 아이디을 입력하세요.");
				document.f1.modEmail1.focus();
			} else if(email2 == ""){
				alert("이메일 주소를 입력하세요.");
				doucment.f1.modEmail2.focus();
			}else {
				url = "mEmailDup.jsp?email1=" + email1 + "&email2=" + email2;
				window.open(url, "post", "width=560, height=250");
			}
		}
				
		function getEmail() {
			var s = document.getElementById("travel");		  
			var email = s.options[s.selectedIndex].value;
			if(email == "직접입력"){
				document.getElementById('modEmail2').readOnly = false;
				document.getElementById('modEmail2').value = "";
			}else{
				document.getElementById('modEmail2').readOnly = true;
				document.getElementById('modEmail2').value = email;
			}	  	  
		}
		
		function submitConfirm(){
			if(document.f1.emailDuplication.value == "emailUncheck"){		   
				alert("이메일 중복체크가 안되었습니다.");
				return false;
			}else if(document.f1.emailDuplication.value == "Checked"){
				var m_pwStr = <%=pwdcheck%>;
				var userPw = prompt("암호인증", "");
				if(userPw != m_pwStr || userPw == null){
					alert("인증에 실패하였습니다. 암호를 확인하세요.");
					return false;
				}else{
					return true;
				}
			}else{
				alert("중복체크에서 알수 없는 에러입니다. 관리자에게 문의하세요.");
				return false;
				}
			}
				
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
				        }else{
				            document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
				            document.getElementById('check').style.color='red';
				        }
				    }
				}
		</script>


<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<style>


#head1{
padding-top: 50px;
}
 
 #headerBox {
    height: 130px;
    background-color: currentColor;
    top: 0px;
    width: 100%;
}


.form-control { margin-bottom: 20px; font-size: 15px; }

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
   #menu{
      list-style-type: none;
      width: 100%;
      height: 40px;
   }

   li{
       padding-top: 10px;
   }

   .box{
      width: 350px;
      height: 20px;

      line-height: 30px;
      margin-left: 10px;
      float: left;
      font-size: 15px;
   }


   .menuall{
      margin-left: 20px;
   }

</style>

 <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body style="overflow-x:hidden">
<div id="headerBox"></div>
	<header id="head1">
		<h1>내 정보</h1>
		<hr>
	</header>

     <div class="box">
               <ul id="menu" class="menuall">
               <br><br>
                  <a href="MemberModifyForm.do?myDetail="+<%=myDetail %>>
                     <li  class="menuall"><i class="far fa-address-card"></i>&nbsp;내 정보</li>
                  </a>      
                                   
                           <a href="MemberModifyForm.do?myAct="+<%=myAct %>>
                              <li id="li1" class="menuall" ><i class="far fa-calendar-alt"></i>&nbsp;나의 예약</li>
                           </a>
                           <a href="MemberModifyForm.do?myApply="+<%=myApply %>>
                           <li id="li1" class="menuall"><i class="far fa-meh-rolling-eyes"></i>&nbsp;나의 신청</li>
                           </a>
                           <a href="MemberModifyForm.do?myReply="+<%=myReply %>>
                           <li id="li1" class="menuall"><i class="far fa-comments"></i>&nbsp;나의 댓글</li>
                           </a>
                      
                     </li>
                     <a href="MemberModifyForm.do?myMod="+<%=myMod %>>
                        <li id="li1" class="menuall"><i class="fas fa-user-edit"></i>&nbsp;내 정보수정</li>
                     </a>
               </ul>
            </div>
		<div id="wrap_mod">
	
				<div class="container h-100">
			<fieldset id="modInfo">
			  <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-xs-12 col-sm-12 col-md-6 well well-sm">
     <div style="font-size: 30px; color: rgb(254, 175, 59);">
			
			  <i class="far fa-grin"></i>내 정보 수정</i></div>
				<form action="ModifyService.do"  name="f1" method="post" name="modifyform" class="form" role="form">
					<p class="pw">
						<b> 현재 암호: </b><br /> 
					<input type="password" id="pwdcheck1" class="form-control"  name="curPw" onchange="check_pw()" 
						pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_-+=[]{}~?:;`|/]).{4,10}$" > <br /> 
					<b>새 암호: </b><br /> 
						<input type="password" id="pwdcheck2" class="form-control"  name="newPw" onchange="check_pw()"
							pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_-+=[]{}~?:;`|/]).{4,10}$" > <br />
					</p>
						<b>이름: </b><br /> 
						<input type="text" name="modName" class="form-control" value="<%= m_name%>" readonly="readonly"> <br /> 
					
						
					 <b>이메일: </b><br /> 
						<input type="text" id="modEmail1" name="modEmail1" class="form-control" value="<%= email1%>"  onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/gi,'');">@ 
						<input type="text" id="modEmail2" name="modEmail2" class="form-control" value="<%= email2%>" onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9.]/gi,'');" /> 
						<select id="travel" class="form-control" onclick="getEmail()">
							 <option selected="selected" id="0" >직접입력</option>
            <option id="1" value="naver.com">naver.com</option>
            <option id="2" value="hanmail.net">hanmail.net</option>
            <option id="3" value="gmail.com">gmail.com</option>
						</select>
						<input type="button"  class="form-control" name="EMAIL_CHECK" value="중복확인" onclick="emailCheck(this.form.modEmail1.value, this.form.modEmail2.value);">
						<input type="hidden" name="emailDuplication" value="emailUncheck"/><br/>
					
					<b>주소: </b><br /> 
						<input type="hidden" id="sample6_postcode" class="form-control" onKeyup="this.value=this.value.replace(/[^ㄱ-힣0-9]/gi,'');" placeholder="우편번호" > 
						<input type="text" name="address" class="form-control" value="<%= addr%>" id="sample6_address" onKeyup="this.value=this.value.replace(/[^ㄱ-힣0-9]/gi,'');"
						placeholder="주소" > 
						<input type="button" onclick="sample6_execDaumPostcode()" class="form-control"  value="주소 검색">
						<input type="text" name="detailAddress" id="sample6_detailAddress"  class="form-control" value="<%= detail_addr%>" onKeyup="this.value=this.value.replace(/[^ㄱ-힣0-9]/gi,'');"
						placeholder="상세주소"> 
						<input type="text" name="extraAddress" id="sample6_extraAddress" class="form-control" value="<%= ref_addr%>" onKeyup="this.value=this.value.replace(/[^ㄱ-힣0-9()]/gi,'');"
						placeholder="참고항목"><br> 
					
					
					<p>
						<b>전화번호: </b><br /> 
         				<input type="tel"  class="form-control" value="<%= phone%>" name="modPhone" maxlength='11'> <br /> 
      				</p>
					
					
		   			<p>
      					<b>성별</b>
 &nbsp;&nbsp;<label class="radio-inline">
                <input type="radio" id="" name="GENDER" value="남자" onclick="male();"/>
		남자
            </label>
            <label class="radio-inline">
                <input type="radio" name="GENDER" value="여자" onclick="female();"/>
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
     value="<%=year%>" />
                </div>
                <div class="col-xs-4 col-md-4">
                   <input type="text" class="form-control"  id="MONTH" name="MONTH" placeholder="월" maxlength='2'  value="<%=month%>"/>
                </div>
                  <div class="col-xs-4 col-md-4">
                   <input type="text" class="form-control"  id="DAY" name="DAY" placeholder="일" maxlength='2'  value="<%=day%>"/>
                </div>
    
     <br> <sup><em><strong>&nbsp;&nbsp;&nbsp;&nbsp;*필수 입력이 아닙니다.</strong></em></sup>
      <br/><br/>
      </div>

					<p>
						<input type="submit" class="themeBtn4" name="mod" value="적용하기">
					</p>
			</form>
		</fieldset>
	</div>
	<jsp:include page="/adminHeader.jsp" />
	<jsp:include page="/footer.jsp" />
</body>
</html>





