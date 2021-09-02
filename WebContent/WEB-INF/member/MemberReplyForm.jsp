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
<meta charset="UTF-8">
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
        margin: 100px auto;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    	width: 70%
    }
	.table-wrapper .btn {
		float: right;7
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
   
</style>
 <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
});
</script>

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
	<div id="wrap_detail"></div>
	
	<%
		ArrayList<String> revData = null;
		revData = (ArrayList<String>)request.getAttribute("revData");
	%>
	
	
	
	<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-9">
						<h2>내가 쓴 댓글</h2>
					</div>
                </div>
            </div>
				<div class="row">
                  
                    <div class="col-sm-12">
			
            <table class="table table-striped table-hover">
            
                <thead>
                    <tr>
                      <th>프로그램</th>
						<th>댓글 내용</th>
						<th>작성 날짜</th>						
						<th>수정 날짜</th>
						<th><input type="hidden" value="대댓글" name="sec_num" readonly="readonly"></th>
						
                    </tr>
                </thead>
                <tbody>
                <%
							ArrayList<String> repData = null;
							repData = (ArrayList<String>)request.getAttribute("repData");
	
							int myRepSize = (repData.size())/5;					
							for(int i=0; i<myRepSize; i++){
							int	j=i*5;
						%>	
            
            			<tr>
								<td><input type="text" value="<%= repData.get(j)%>" name="p_name" readonly="readonly"> </td>
								<td><input type="text" value="<%= repData.get(j+1)%>" name="content" readonly="readonly"></td>
								<td><input type="text" value="<%= repData.get(j+2)%>" name="req_date" readonly="readonly"></td>
								<td><input type="text" value="<%= repData.get(j+3)%>" name="up_date" readonly="readonly"></td>
								<td><input type="hidden" value="<%= repData.get(j+4)%>" name="sec_num" readonly="readonly"></td>
								<!-- <td><input type="button" value="바로가기" name="no" onclick=""></td>  -->
							</tr>
							<%
								}
							%>
					</table>
				</div>
		
	</div>				
						
                </div>
			</div>

	<jsp:include page="/adminHeader.jsp" />
	<jsp:include page="/footer.jsp" />
</body>
</html>





