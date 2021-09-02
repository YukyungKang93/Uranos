<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="vo.MemberBean"%>
<%@ page import="vo.MemberListBean"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="dao.MemberDAO"%>
<%@ page import="vo.MemberPageInfo"%>

<%
	String id = (String) session.getAttribute("M_ID");
%>



<!DOCTYPE html>
<html>
<head>

<!-- jQuery  -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- bootstrap JS -->
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- bootstrap CSS -->
<link
	href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="..../css/memberModi.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="..../css/memberModi.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">




<style>
body {
	color: #566787;
	background: #f5f5f5;
	font-family: 'Varela Round', sans-serif;
	font-size: 15px;
	padding-top: 20px;
}

.table-wrapper {
	background: #fff;
	padding: 20px 25px;
	margin: 30px auto;
	border-radius: 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
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
	font-size: 20px;
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
	background: rgb(53, 50, 45);
	padding: 16px 25px;
	margin: -20px -25px 10px;
	border-radius: 3px 3px 0 0;
}

.table-title h2 {
	margin: 5px 0 0;
	font-size: 15px;
}

table.table tr th, table.table tr td {
	border-color: #e9e9e9;
	padding: 12px 15px;
	vertical-align: middle;
}

table.table tr th:first-child {
	width: 30px;
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
	width: 100%;
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

#p_img {
	height: 100px;
}

#myRevList {
	width: 200px;
	display: inline-block;
}

#board {
	display: block;
	float: left;
	margin: 5px;
	width: 40%;
	text-align: center;
}

#reply {
	display: block;
	float: left;
	margin: 5px;
	width: 40%;
	text-align: center;
}

#program {
	margin: auto text-align: center;
}

table, th, td {
	border: 2px solid;
}

table {
	width: 100%;
}

td {
	width: 50px;
	height: 20px;
}

table {
	border-collapse: collapse;
}

btn btn-info btn-sm {
	color: #000;
	background-color: #ffb03b;
	border-color: #ffb03b;
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
	width: 350px;
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

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
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
	function getType() {
		var s = document.getElementById('search_type');
		var type = s.options[s.selectedIndex].value;
		
		document.getElementById('value').value = "";
		document.getElementById('type').value = type;
			
	}
	function columnnameClicked() {
		
	}
</script>

<script type="text/javascript">
	function clickRow(){
		var x=document.getElementById("abc");
	    x.style.fontSize = "25px";           
	    x.style.color = "blue";
	}
</script>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".dblclick").dblclick(function() {
		
	      });
		
		$(document).keydown(function(event) {
		    if ( event.keyCode == 27 || event.which == 27 ) {
		    	
		    }
		});
		
	    $('td').click(function(){
	    	
			});
	    });

</script>

<style>
.topFixBanner {
	text-align: left;
	background-color: #ffffff;
	padding: 20px 0px 20px 20px;
	width: 100%;
	border-bottom: #666666 solid 2px;
}

.topFixBannerFixed {
	position: fixed;
	top: 0px;
	text-align: left;
	padding-left: 20px;
}

#wi {
	
}

#logo {
	width:10%;
	padding-left: 20px;
	
}

</style>

<title>활동 내역</title>
</head>


<body style="overflow-x: hidden">
		
	<div id="logo" >
	<a href="index.jsp"> <img id="logoimg" src="./img/logo.png"
			alt="같이가치 logo" title="같이가치" width="200px">
		</a>
	</div>


	<fieldset id="updateForm" class="shadow row	">


		<div class="box">
			<ul id="menu" class="menuall">
				<br>
				<br>
				<a href="testaction.do">
					<li class="menuall"><i class="far fa-address-card"></i>&nbsp;전체
						조회</li>
				</a>

				<a
					href="adminMemberDetail.do?check=<%=request.getAttribute("id")%>&type=정보수정">
					<li id="li1" class="menuall"><i class="far fa-calendar-alt"></i>&nbsp;회원정보
						수정</li>
				</a>

				<a
					href="adminMemberDetail.do?check=<%=request.getAttribute("id")%>&type=활동내역">
					<li id="li1" class="menuall"><i class="far fa-comments"></i>&nbsp;활동
						내역</li>
				</a>

				</a>
			</ul>
		</div>

		<div class="container">
			<fieldset id="detailAct">
				<hr>
				<h2>예약 프로그램 현황</h2>
				<form>
					<div class="row">
						<%
							MemberBean bean = (MemberBean) session.getAttribute("data");
							ArrayList<String> revData = new ArrayList<String>();
							revData = (ArrayList<String>) session.getAttribute("rev");
							int size = (revData.size()) / 4;
							for (int i = 0; i < size; i++) {
								int j = i * 4; //0*3=0 , 1*3=3 , 2*3=6 , 3*3=9
								MemberDAO mDao = null;
								mDao = MemberDAO.getInstance();
						%>
						<div class="col-sm-6 col-md-4 col-lg-3 mt-4">
							<div class="card card-inverse card-info">
								<img class="card-img-top" src="upload/<%=revData.get(j + 2)%>"
									width=200px; height=200px;>
								<div class="card-block">
									<br>
									<h4 class="card-title"><%=revData.get(j)%></h4>
									<div class="card-text">
										<%=revData.get(j + 1)%>
									</div>
								</div>
								<div class="card-footer">
									<button class="btn btn-info btn-sm"
										style="background-color: #ffb03b; border-color: #ffb03b;"
										"javascript:location.href='programDetail.do?p_num=<%=revData.get(j + 3)%>'">상세보기</button>
									<button class="btn btn-info btn-sm"
										style="background-color: #ffb03b; border-color: #ffb03b;"
										onclick="revDel<%=revData.get(j + 3)%>();">취소</button>
									<script> 
								function revDel<%=revData.get(j + 3)%>(){
									var p_num_value = <%=revData.get(j + 3)%>;
									var revDeleteCheck = "1";
									var con_test = confirm("정말 예약 취소하겠습니까?");
									if(con_test == true){
										javascript:location.href='MemberModifyForm.do?p_num_revDel='+p_num_value+'&revDeleteCheck='+revDeleteCheck;
									}
								}
							</script>

								</div>
							</div>
						</div>
						<%
							}
						%>

					</div>
				</form>
			</fieldset>





			<%
				revData = (ArrayList<String>) request.getAttribute("revData");
			%>

			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-sm-4">
							<h2>신청 프로그램 현황</h2>
						</div>
					</div>
				</div>
				<div class="row">

					<div class="col-sm-12">

						<table id="wi" class="table table-striped table-hover">

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
									proData = (ArrayList<String>) request.getAttribute("proData");

									int myProSize = (proData.size()) / 4;
									for (int i = 0; i < myProSize; i++) {
										int j = i * 4;
										String status1 = "s1";
										String status2 = "s2";
								%>
								<tr>

									<td><%=proData.get(j)%></td>
									<td><%=proData.get(j + 1)%></td>
									<td><%=proData.get(j + 2)%></td>
									<td><%=proData.get(j + 3)%></td>
									<td><a href="#" onclick="<%=status1 + j + 3%>f()"
										class="view" title="View Details" data-toggle="tooltip"><i
											class="material-icons">&#xE5C8;</i></a></td>

								</tr>


								<script>
							var getValue<%=status1 + j + 3%> = document.getElementById("<%=status1 + j + 3%>").value;
							console.log(<%=status1 + j + 3%>);
							function <%=status1 + j + 3%>f(){						
								if(getValue<%=status1 + j + 3%> === '승인완료'){
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
			</div>



			<%
				revData = (ArrayList<String>) request.getAttribute("revData");
			%>



			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-sm-9">
							<h2>댓글 작성 현황</h2>
						</div>
					</div>
				</div>
				<div class="row">

					<div class="col-sm-12" style="width: 100%">

						<table class="table table-striped table-hover">

							<thead>
								<tr>
									<th>프로그램</th>
									<th>댓글 내용</th>
									<th>작성 날짜</th>
									<th>수정 날짜</th>
									<th><input type="hidden" value="대댓글" name="sec_num"
										readonly="readonly"></th>

								</tr>
							</thead>
							<tbody>
								<%
									ArrayList<String> repData = null;
									repData = (ArrayList<String>) request.getAttribute("repData");

									int myRepSize = (repData.size()) / 5;
									for (int i = 0; i < myRepSize; i++) {
										int j = i * 5;
								%>

								<tr>
									<td><%=repData.get(j)%></td>
									<td><%=repData.get(j + 1)%></td>
									<td><%=repData.get(j + 2)%></td>
									<td><%=repData.get(j + 3)%></td>
									<td><%=repData.get(j + 4)%></td>
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
</body>

</html>