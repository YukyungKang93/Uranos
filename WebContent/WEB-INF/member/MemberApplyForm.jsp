<%@page import="java.util.ArrayList"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Order Details Table with Search Filter</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="..../css/memberModi.css">
<style>



      body {
        color: #566787;
		background: #f5f5f5;
		font-family: 'Varela Round', sans-serif;
		font-size: 20px;
	}
	
	#head1{
padding-top: 50px;
}
	
	#headerBox {
    height: 130px;
    background-color: currentColor;
    top: 0px;
    width: 100%;
}
	.table-wrapper {
        background: #fff;
        padding: 20px 25px;
        margin: 30px auto;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
        margin-bottom: 140px;
    }
	.table-wrapper .btn {
		float: right;
		color: #333;
    	background-color: #fff;
		border-radius: 3px;
		border: none;
		outline: none !important;
		margin-left: 10px;
	}
	.table-wrapper .btn:hover {
        color: #333;
		background: #f2f2f2;
	}
	.table-wrapper .btn.btn-primary {
		color: #fff;
		background: #03A9F4;
	}
	.table-wrapper .btn.btn-primary:hover {
		background: #03a3e7;
	}
	.table-title .btn {		
		font-size: 13px;
		border: none;
	}
	.table-title .btn i {
		float: left;
		font-size: 21px;
		margin-right: 5px;
	}
	.table-title .btn span {
		float: left;
		margin-top: 2px;
	}
	.table-title {
		color: #fff;
		background: rgb(53,50,45);		
		padding: 16px 25px;
		margin: -20px -25px 10px;
		border-radius: 3px 3px 0 0;
    }
    .table-title h2 {
		margin: 5px 0 0;
		font-size: 20px;
	}
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
		padding: 12px 15px;
		vertical-align: middle;
		
    }
	table.table tr th:first-child {
		width: 60px;
	}
	table.table tr th:last-child {
		width: 80px;
	}
    table.table-striped tbody tr:nth-of-type(odd) {
    	background-color: #fcfcfc;
	}
	table.table td a {
		font-weight: bold;
		color: #566787;
		display: inline-block;
		text-decoration: none;

	}
	table.table td a:hover {
		color: #2196F3;
	}
	table.table td a.view {        
		width: 30px;
		height: 30px;
		color: rgb(254, 175, 59);
		border: 2px solid;
		border-radius: 30px;
		text-align: center;
		
    }
    table.table td a.view i {
        font-size: 22px;
		margin: 2px 0 0 1px;
    }   
	
	.status {
		font-size: 30px;
		margin: 2px 2px 0 0;
		display: inline-block;
		vertical-align: middle;
		line-height: 10px;
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
   
   .row{
   width:139%; 
   }

   
</style>

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
	%>
	
	<div class="container" style="width:60%;">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-4">
						<h2>내가 신청한 프로그램 현황</h2>
					</div>
                </div>
            </div>
				<div class="row">
                  
                    <div class="col-sm-9">
							
            <table class="table table-striped table-hover">
            
                <thead>
                    <tr>
               
                        <th>카테고리</th>
                      <th>프로그램명</th>
						<th>날짜</th>
						<th>상태</th>						
						<th>바로가기</th>
                    </tr>
                </thead>
                <tbody>
              <%
							ArrayList<String> proData = null;
							proData = (ArrayList<String>)request.getAttribute("proData");

							int myProSize = (proData.size())/4;					
							for(int i=0; i<myProSize; i++){
							int	j=i*4;
							String status1 = "s1";
							String status2 = "s2";
						%>	

                   			<td><%=proData.get(j) %> </td>
							<td><%=proData.get(j+1) %></td>
							<td><%=proData.get(j+2) %></td>
							<td id="<%=status1+j+3 %>"> <%=proData.get(j+3) %></td>
							<td>  <a href="#" onclick="<%=status1+j+3 %>f()" class="view" title="View Details" data-toggle="tooltip"><i class="material-icons">&#xE5C8;</i></a></td>

                    <script>
							var getValue<%=status1+j+3%> = document.getElementById("<%=status1+j+3 %>").value;
							console.log(<%=status1+j+3%>);
							function <%=status1+j+3 %>f(){						
								if(getValue<%=status1+j+3%> === '승인완료'){
									javascript:location.href='programList.do';
								}else{
									alert('관리자가 고객님의 신청 프로그램을 열심히 검토중이에요!');
								}
							}
						</script>
							<%
							}
						%>
                </tbody>
            </table>
			
 </div>
 </div>
 </div></div>

	<jsp:include page="/adminHeader.jsp" />
	<jsp:include page="/footer.jsp" />
</body>
</html>