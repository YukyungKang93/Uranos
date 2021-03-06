<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="vo.MemberBean"%>
<%@ page import="vo.MemberListBean"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="dao.MemberDAO"%>
<%@ page import="vo.MemberPageInfo"%>
<!DOCTYPE html>
<html>
<head>

<!-- jQuery  -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- bootstrap JS -->
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

<!-- bootstrap CSS -->
<link
	href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="..../css/memberModi.css">

<style>
body {
	padding-top: 100px;
}

#p_img {
	height: 100px;
}

#myRevList {
	width: 200px;
	display: inline-block;
}
</style>

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<style>
body {
	padding-top: 20px;
	backgroun-color
	=rgb(127,127,127)
}

.form-control {
	margin-bottom: 20px;
	font-size: 15px;
}

.margin {
	margin-top: 20px;
	margin-bottom: 20px;
}

.themeBtn4 {
	background: rgb(254, 175, 59);
	color: ##FFC312 !important;
	display: inline-block;
	font-size: 20px;
	font-weight: 500;
	height: 50px;
	line-height: 0.8;
	padding: 18px 60px;
	text-transform: capitalize;
	border-radius: 1px;
	letter-spacing: 0.5px;
	border: 0px !important;
	cursor: pointer;
	border-radius: 100px;
}

.themeBtn4:hover {
	color: black;
	background-color: white;
}

strong {
	font-size: 10px;
}

b {
	font-size: 15px;
}

label {
	font-size: 15px;
}

#travel.form-control {
	height: calc(3.25rem + 2px);
}

#logo {
	width:10%;
	padding-left: 20px;
	
}


#menu {
	list-style-type: none;
	width: 100%;
	height: 40px;
}

li {
	padding-top: 10px;
}

.box {
	width: 200px;
	height: 20px;
	line-height: 30px;
	margin-left: 10px;
	float: left;
	font-size: 15px;
}

.menuall {
	margin-left: 20px;
}



</style>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
		$(document).ready(function(){
			$('input[type="checkbox"][name="modGen"]').click(function(){
		  		if($(this).prop('checked')){
		    		$('input[type="checkbox"][name="modGen"]').prop('checked', false);
	  	   			 $(this).prop('checked',true);
	  			}
	 		});
		});					
				
		function emailCheck(email1, email2) {
			if (email1 == "") { 
				alert("????????? ???????????? ???????????????.");
				document.f1.modEmail1.focus();
			} else if(email2 == ""){
				alert("????????? ????????? ???????????????.");
				doucment.f1.modEmail2.focus();
			}else {
				url = "mEmailDup.jsp?email1=" + email1 + "&email2=" + email2;
				window.open(url, "post", "width=350, height=220");
			}
		}
				
		function getEmail() {
			var s = document.getElementById("travel");		  
			var email = s.options[s.selectedIndex].value;
			if(email == "????????????"){
				document.getElementById('modEmail2').readOnly = false;
				document.getElementById('modEmail2').value = "";
			}else{
				document.getElementById('modEmail2').readOnly = true;
				document.getElementById('modEmail2').value = email;
			}	  	  
		}
		
		function submitConfirm(){
			if(document.f1.emailDuplication.value == "emailUncheck"){		   
				alert("????????? ??????????????? ??????????????????.");
				return false;
			}else{
				alert("?????????????????? ?????? ?????? ???????????????. ??????????????? ???????????????.");
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
	                    	if(data.bname !== '' && /[???|???|???]$/g.test(data.bname)){
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
			        window.alert('??????????????? 6?????? ??????, 16?????? ????????? ?????? ???????????????.');
			        document.getElementById('pw').value='';
			    }
			    for(var i=0;i<SC.length;i++){
			        if(pw.indexOf(SC[i]) != -1){
			            check_SC = 1;
			        }
			    }
				    if(check_SC == 0){
				        window.alert('!,@,#,$,% ??? ??????????????? ????????? ?????? ????????????.')
				        document.getElementById('pw').value='';
				    }
				    if(document.getElementById('pw').value !='' && document.getElementById('pwCheck').value!=''){
				        if(document.getElementById('pw').value==document.getElementById('pwCheck').value){
				            document.getElementById('check').innerHTML='??????????????? ???????????????.'
				            document.getElementById('check').style.color='blue';
				        }else{
				            document.getElementById('check').innerHTML='??????????????? ???????????? ????????????.';
				            document.getElementById('check').style.color='red';
				        }
				    }
				}
		</script>

<!-- <style>
	.topFixBanner {text-align: left; background-color: #ffffff; padding: 20px 0px 20px 20px; width: 100%; border-bottom:#666666 solid 2px; }

    .topFixBannerFixed {position: fixed; top: 0px; text-align:left; padding-left:20px; }

</style> -->

<title>?????? ?????? ?????????</title>
</head>

<body style="overflow-x: hidden">


	<%
		String[] email = ((String) request.getAttribute("email1")).split("@");
		String email1 = email[0];
		String email2 = email[1];
		String year = "";
		String month = "";
		String day = "";

		if (((String) request.getAttribute("birth")).length() == 10) {
			year = ((String) request.getAttribute("birth")).substring(0, 4);
			month = ((String) request.getAttribute("birth")).substring(5, 7);
			day = ((String) request.getAttribute("birth")).substring(8, 10);
		}
	%>

	<fieldset id="modInfo">
		

		<div id="logo">
			<a href="index.jsp"> <img id="logoimg" src="./img/logo.png"
				alt="???????????? logo" title="????????????" width="200px">
			</a>
		</div>
		<fieldset id="updateForm" class="shadow row	">
<br>
<br>
<br>
<br>



			<div class="box">
			<ul id="menu" class="menuall">
				<br>
				<br>
				<a href="testaction.do">
					<li class="menuall"><i class="far fa-address-card"></i>&nbsp;??????
						??????</li>
				</a>

				<a
					href="adminMemberDetail.do?check=<%=request.getAttribute("id")%>&type=????????????">
					<li id="li1" class="menuall"><i class="far fa-calendar-alt"></i>&nbsp;????????????
						??????</li>
				</a>

				<a
					href="adminMemberDetail.do?check=<%=request.getAttribute("id")%>&type=????????????">
					<li id="li1" class="menuall"><i class="far fa-comments"></i>&nbsp;??????
						??????</li>
				</a>

				</a>
			</ul>
		</div>




	<div class="container h-100">
		<fieldset id="modInfo">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-xs-12 col-sm-12 col-md-6 well well-sm">
					<div style="font-size: 30px; color: rgb(254, 175, 59);">

						<form action="managerpass.do" name="f1" method="post"
							name="modifyform">


							<b>??? ??????: </b><br /> <input type="password" class="form-control"
								id="pwdcheck" name="newPass" onchange="check_pw()"
								pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_-+=[]{}~?:;`|/]).{4,10}$">

							<b>?????????: </b><br /> <input type="text" class="form-control"
								name="idModify" value="<%=request.getAttribute("id")%>"
								readonly="readonly"> <b>??????: </b><br /> <input
								type="text" class="form-control" name="nameModify"
								value="<%=request.getAttribute("name")%>"> <b>?????????: </b><input
								type="text" id="modEmail1" class="form-control" name="modEmail1"
								value="<%=email1%>"
								onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9]/gi,'');">@
							<input type="text" class="form-control" id="modEmail2"
								name="modEmail2" value="<%=email2%>"
								onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9.]/gi,'');" />
							<select id="travel" class="form-control" onclick="getEmail()">
								<option selected="selected">????????????</option>
								<option value="naver.com">naver.com</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="gmail.com">gmail.com</option>
							</select> <input type="button" name="EMAIL_CHECK" class="form-control"
								value="????????????"
								onclick="emailCheck(this.form.modEmail1.value, this.form.modEmail2.value);">
							<input type="hidden" name="emailDuplication" value="emailUncheck" />
							<b>??????: </b> <input type="hidden" id="sample6_postcode"
								onKeyup="this.value=this.value.replace(/[^???-???0-9]/gi,'');"
								placeholder="????????????"> <input type="text"
								class="form-control" name="sample6_address" id="sample6_address"
								value="<%=request.getAttribute("addr")%>"
								onKeyup="this.value=this.value.replace(/[^???-???0-9]/gi,'');"
								placeholder="??????"> <input type="button"
								onclick="sample6_execDaumPostcode()" class="form-control"
								value="?????? ??????"> <input type="text"
								name="sample6_detailAddress" id="sample6_detailAddress"
								class="form-control"
								value="<%=request.getAttribute("detail_addr")%>"
								onKeyup="this.value=this.value.replace(/[^???-???0-9]/gi,'');"
								placeholder="????????????"> <input type="text"
								class="form-control" name="sample6_extraAddress"
								id="sample6_extraAddress"
								value="<%=request.getAttribute("ref_addr")%>"
								onKeyup="this.value=this.value.replace(/[^???-???0-9()]/gi,'');"
								placeholder="????????????"> <b>????????????: </b><br /> <input
								type="tel" class="form-control" name="phoneModify"
								value="<%=request.getAttribute("phone")%>" maxlength='11'>


							<b>??????</b> <label> <input type="radio" id="genderModify"
								name="genderModify" value="??????" /> ??????
							</label> <label> <input type="radio" id="genderModify"
								name="genderModify" value="??????" /> ??????
							</label><br>
							<sup><em><strong>*?????? ????????? ????????????.</strong></em></sup> <br>
							<b>????????????</b>
							<div class="row">
								<div class="col-xs-4 col-md-4">
									<input type="text" class="form-control" id="YEAR" name="YEAR"
										onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
										maxlength='4' placeholder="???" />
								</div>
								<div class="col-xs-4 col-md-4">
									<input type="text" class="form-control" id="MONTH" name="MONTH"
										placeholder="???" maxlength='2' />
								</div>
								<div class="col-xs-4 col-md-4">
									<input type="text" class="form-control" id="DAY" name="DAY"
										placeholder="???" maxlength='2' />
								</div>


								<br> <sup><em><strong>&nbsp;&nbsp;&nbsp;&nbsp;*??????
											????????? ????????????.</strong></em></sup>
							</div>
							<br>
							<input type="submit" class="themeBtn4" name="button" value="??????">
							<input type="submit" class="themeBtn4" name="button" value="??????">


						</form>
					</div>
				</div>
			</div>
</fieldset>
		</fieldset>
	</div>
	<%-- <jsp:include page="/adminHeader.jsp" />
		<jsp:include page="/footer.jsp" /> --%>
</body>
</html>