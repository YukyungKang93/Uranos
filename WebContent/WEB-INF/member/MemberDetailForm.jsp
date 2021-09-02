<%@page import="java.util.ArrayList"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="..../css/memberModi.css">
<style>

#p_img{
	height: 100px;
}
#myRevList{
	width: 200px;
	display:inline-block;
}


</style>
<title>내정보</title>
<%
	String pwdcheck = session.getAttribute("M_PW").toString();
	String id = session.getAttribute("M_ID").toString();
	
	if(request.getAttribute("deleteProtect") != null) {
		%>
		<script>
		alert('예약정보가 있습니다. 예약정보를 취소해주세요.');
		</script>
		<%
	}else{
		System.out.println("boolnull"); 
	}
%>



<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<script type="text/javascript">

function success() {
	var del = confirm("탈퇴하시겠습니까?");

	if (del) {
		alert("탈퇴되었습니다.");
		location.href="delete.do";
	} else {
		alert("취소되었습니다.");
		location.href="#";
	}
}

</script>

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
}
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
	<% 
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
		String myDetail = "myDetail"; 
		String myAct = "myAct"; 
		String myApply = "myApply"; 
		String myReply = "myReply"; 
		String myMod = "myMod";  
	%>
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
	<div id="wrap_detail">
	<div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      
        <div class="col-xs-12 col-sm-12 col-md-6 well well-sm">
        	
     <div style="font-size: 30px; color: rgb(254, 175, 59);">
         
        <i class="far fa-grin"></i>내 정보</i></div>
         <form action="delete.do"  method="post" name="detailInfo" autocomplete="off" class="form" role="form">
	
					이름: <br /> 
					<input type="text" readonly class="form-control" value="<%= m_name%>" readonly="readonly" ><br /> 
					
					아이디: <br /> 
					<input type="text" readonly class="form-control"  value="<%= m_id%>" readonly="readonly"><br /> 
					
					
					
					주소: <br /> 
					<input type="text" readonly class="form-control"  name="address" value="<%= addr%>" placeholder="주소" readonly="readonly"> 
						

					이메일: <br /> 
					<input type="text" readonly class="form-control"  value="<%= email1%>" name="email1" readonly="readonly">
					<input type="text" readonly class="form-control"  value="@<%= email2%>" name="email2" readonly="readonly"><br />


					전화번호: <br /> 
					<input type="tel" readonly class="form-control"  value="<%= phone%>" name="Phone" readonly="readonly"> <br />
					
					성별: <br /> 
					<input type="text" readonly class="form-control"   value="<%= gender%>" name="Gend" readonly="readonly"> <br />
					
					생년월일: <br /> 
					<input type="text" readonly class="form-control"  value="<%= birth%>" name="Bir" readonly="readonly"> <br />
				</p>
				<input type="button"  class="themeBtn4" value="회원탈퇴" onclick="success();">
			</form>
		</fieldset>
</div></div></div>
		</div>
		<jsp:include page="/adminHeader.jsp" />
	<jsp:include page="/footer.jsp" />
</body>
</html>


