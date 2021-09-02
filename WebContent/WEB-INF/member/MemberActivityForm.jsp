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


#head1{
padding-top: 50px;
}
#headerBox {
    height: 130px;
    background-color: currentColor;
    top: 0px;
    width: 100%;
}

#wrap_detail{
margin-bottom: 254px;
}

#p_img{
	height: 100px;
}
#myRevList{
	width: 200px;
	display:inline-block;
}
#board{
    display: block;
    float: left;
    margin: 5px;
    width: 40%;
    text-align: center;
}
#reply{
    display: block;
    float: left;
    margin: 5px;
    width: 40%;
    text-align: center;
}
#program{
    margin: auto
    text-align: center;
}
table, th, td {
	border: 2px solid ;
}

table {
	width: 100%;
}

td {
	width: 50px;
	height: 50px;
}

table {
	border-collapse: collapse;
}
btn btn-info btn-sm {
    color: #000;
    background-color: #ffb03b;
    border-color: #ffb03b;
}  #menu{
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
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
 <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<title>내정보</title>
<%
	String pwdcheck = session.getAttribute("M_PW").toString();
	String id = session.getAttribute("M_ID").toString();
%>
</head>
<body style="overflow-x:hidden">
<div id="headerBox"></div>
	<header id="head1">
		<h1>내 정보</h1>
		<hr>
	</header>
	<% 
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
	
	<%
		ArrayList<String> revData = null;
		revData = (ArrayList<String>)request.getAttribute("revData");
		String refresh = (String)request.getAttribute("refresh");
		if(refresh != null){
%>
			
			<script>
					javascript:location.href='MemberModifyForm.do?myAct='+<%=myAct %>;
			</script>
			
			<% 
		}
	%>
	
	
	
	<div class="container">
	<fieldset id="detailAct">
	
				<h2>내가 예약한 프로그램 현황</h2>
				<hr>
					
        <div class="row">
        <% 
					int size = (revData.size())/4;					
					for(int i=0; i<size; i++){
					int	j=i*4; //0*3=0 , 1*3=3 , 2*3=6 , 3*3=9
					MemberDAO mDao = null;
					mDao = MemberDAO.getInstance();
				%>	
            <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                <div class="card card-inverse card-info">
                    <img class="card-img-top" src="upload/<%=revData.get(j+2) %>" width=250px; height=200px;>
                    <div class="card-block">
                      <br>  <h4 class="card-title"><%=revData.get(j) %></h4>
                        <div class="card-text">
                           <%=revData.get(j+1) %>
                        </div>
                    </div>
                    <div class="card-footer">
                     <button class="btn btn-info btn-sm"  style="background-color: #ffb03b; border-color: #ffb03b;" onclick='javascript:location.href="programDetail.do?p_num=<%=revData.get(j+3) %>"'>상세보기</button>
                        <button class="btn btn-info btn-sm" style="background-color: #ffb03b;
    border-color: #ffb03b;"  onclick="revDel<%=revData.get(j+3)%>();">취소</button>
                        <script> 
								function revDel<%=revData.get(j+3) %>(){
									var p_num_value = <%=revData.get(j+3)%>;
									var revDeleteCheck = "1";
									var con_test = confirm("정말 예약 취소하겠습니까?");
									if(con_test == true){
										javascript:location.href='MemberModifyForm.do?p_num_revDel='+p_num_value+'&revDeleteCheck='+revDeleteCheck;
									}
								}
							</script>
							
                    </div>
                </div><br>
            </div>       <% 
					}
        %>
        
        </div>
 
        </fieldset>
</div></div>



	<jsp:include page="/adminHeader.jsp" />
	<jsp:include page="/footer.jsp" />
</body>
</html>





