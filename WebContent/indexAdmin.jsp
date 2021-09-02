<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="vo.PageInfo"%>
<%@ page import="svc.index.IndexReservationService"%>
<%@ page import="vo.Reservation"%>
<%@ page import="vo.RegBean"%>

<%
	ArrayList<Reservation> adminList = (ArrayList<Reservation>) request.getAttribute("adminList");
	ArrayList<RegBean> regList = (ArrayList<RegBean>) request.getAttribute("regList");	
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>



<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>같이가치</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,600,600i,700,700i|Satisfy|Comic+Neue:300,300i,400,400i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">

<link href="cal.css" rel="stylesheet">

<style>
#calendar-wrap {
	padding: 100px;
}

#heightEdit {
	height: 800px;
}

#logoimg {
	height: 65px;
}

#auth {
	position: absolute;
	right: 350px;
}

#uraLogin, #uraInfo {
	position: absolute;
	right: 260px;
}

#uraJoin, #uraLogout {
	position: absolute;
	right: 180px;
}

#uraMemDel {
	position: absolute;
	right: 100px;
}

#layout {
	width: 100%;
	height: 500px;
}

#left {
	margin: 65px;
	width: 40%;
	float: left;
	box-sizing: border-box;
}

#right {
	margin: 65px;
	width: 40%;
	float: right;
	box-sizing: border-box;
}

table {
	margin: auto;
	width: 100%;
}

#tr_right {
	color: #FFFFF0;
	background: #444444;
	text-align: center;
}

#right_center {
	float: right;
	width: 50%;
	text-align: center;
}

#left_center {
	float: left;
	width: 50%;
	text-align: center;
}

#td_body{
	text-align: center;
}
a:link ,li:link{
	color: orange;
	text-decoration: none;
}

a:visited ,li:link{
	color: orange;
	text-decoration: none;
}
</style>



</head>

<body>
<footer id="footer">
		<div class="container">
			<div class="credits">
			<br><br><br><br><br><br>
			</div>
		</div>
	</footer>
	<section id="topbar"
		class="d-flex align-items-center fixed-top topbar-transparent">
		<div
			class="container-fluid container-xl d-flex align-items-center justify-content-center justify-content-lg-start">
			<%
				String auth = "";
				String name = (String) session.getAttribute("M_NAME");
				if (name != null) {
					auth = name + "님, 환영합니다.";
				}
			%>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div id="auth">
				<%=auth%>
			</div>

			<%
				if ((String) session.getAttribute("M_NAME") == null || !request.isRequestedSessionIdValid()) {
			%>

			<i id="uraLogin" class="bi bi-phone d-flex align-items-center"> <span>
					<a href="member/login.do">로그인</a>
			</span></i> &nbsp;&nbsp;&nbsp;&nbsp; <i id="uraJoin"
				class="bi bi-phone d-flex align-items-center"> <span> <a
					href="signup.do">회원가입</a>
			</span></i>
			<%
				} else {
			%>

			</span></i> <i id="uraLogout" class="bi bi-phone d-flex align-items-center">
				<span> <a href="logoutaction.do">로그아웃</a></span>
			</i>
			<%
				}
			%>
		</div>
	</section>

	<!-- ======= Header ======= -->
	<header id="header"
		class="fixed-top d-flex align-items-center header-transparent">
		<div
			class="container-fluid container-xl d-flex align-items-center justify-content-between">

			<div class="logo me-auto">
				<h1>
					<a href="adminindexpro.do"> <img id="logoimg" src="./img/logo.png"
						alt="같이가치 logo" title="같이가치">
					</a>
				</h1>
				<!-- Uncomment below if you prefer to use an image logo -->
				<!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
			</div>

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<li><a class="nav-link scrollto active" href="adminindexpro.do">Home</a></li>
					<%
						String admin = (String) session.getAttribute("M_ID");
						if (admin.equals("admin")) {
					%>

					<li><a class="nav-link scrollto" href="testaction.do">회원 관리</a></li>
					<li><a class="nav-link scrollto" href="programAdminList.do">프로그램 관리</a></li>
					<li><a class="nav-link scrollto" href="reservationList.do">프로그램 참가 현황</a></li>

					<li class="dropdown"><a href="#"><span>신청</span> <i
							class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a href="regWriteForm.do"><span>신청하기</span></a></li>
							<li><a href="regList.do">신청관리</a></li>
						</ul></li>

					<li><a class="nav-link scrollto" href="reviewList.do">후기</a></li>
					<li class="dropdown"><a href="#"><span>공지사항</span> <i
							class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a href="noticeList.do">공지사항</a></li>
							<li><a href="faqList.do">FAQ</a></li>
							<li><a href="qnaList.do">Q&amp;A</a></li>
						</ul></li>
					<%
						}
					%>

				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav>
		</div>
	</header>
	<!-- End Header -->
	<section id="layout" >
	<div id="total"  style= "width:100%";>
	
	
		<div><strong>프로그램 승인요청 내역</strong>
		<table>
			<tr id="tr_right">
				<td>카테고리</td>
				<td>프로그램명</td>
				<td>ID</td>
				<td>시작날짜</td>
				<td>신청날짜</td>
			</tr>
				<%
			for (RegBean reg : regList) {
			%>
			<tr>
				<td id="td_body"><%=reg.getCategory() %></td>
				<td id="td_body"><a href="regDetail.do?reg_num=<%=reg.getReg_num()%>"><%=reg.getP_name() %></a></td>
				<td id="td_body"><%=reg.getM_id() %></td>
				<td id="td_body"><%=reg.getStartdate() %></td>
				<td id="td_body"><%=reg.getReg_date() %></td>
			</tr>
			<%
			}
			%>
			</table></div>
			<br>
			<strong>프로그램 참가 내역</strong>
			<table>
			<tr id="tr_right">
				<td>번호</td>
				<td>프로그램명</td>
				<td>ID</td>
				<td>시작날짜</td>
				<td>신청날짜</td>
			</tr>
			<%
			for (Reservation r : adminList) {
			%>
			<tr>
				<td id="td_body"><%=r.getRes_num() %></td>
				<td id="td_body"><a href="reservationList.do"><%=r.getP_name() %></a></td>
				<td id="td_body"><%=r.getM_id() %></td>
				<td id="td_body"><%=r.getStartdate() %></td>
				<td id="td_body"><%=r.getResdate() %></td>
			</tr>
			<%
			}
			%>
			</table></div>
		</div></div>
	</section>
<!-- ======= Footer ======= -->
	<footer id="footer">
		<div class="container">
			<h1>
					<a href="index.jsp"> <img id="logoimg" src="./img/logo.png"
						alt="같이가치 logo" title="같이가치">
					</a>
				</h1>
			<div class="memeber-address">
			Uranos : 김민아&nbsp;  강유경&nbsp;  공병찬&nbsp;  김정빈&nbsp;  이정노 &nbsp;정혁희&nbsp;  지승빈<br>
			서울특별시 금천구 가산디지털2로 127-31 월드메르디앙4차 413호<br>
			이메일: alsdk9458@naver.com / 김민아-Tel : 010-2410-9597 			
			</div>
			
			<div class="social-links">
				<a href="#" class="twitter"><i class="bx bxl-twitter"></i></a> <a
					href="#" class="facebook"><i class="bx bxl-facebook"></i></a> <a
					href="#" class="instagram"><i class="bx bxl-instagram"></i></a> <a
					href="#" class="google-plus"><i class="bx bxl-skype"></i></a> <a
					href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
			</div>
			<div class="copyright">
				&copy; Copyright <strong><span>같이가치</span></strong>. All Rights
				Reserved
			</div>
			<div class="credits">
				<!-- All the links in the footer should remain intact. -->
				<!-- You can delete the links only if you purchased the pro version. -->
				<!-- Licensing information: https://bootstrapmade.com/license/ -->
				<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/delicious-free-restaurant-bootstrap-theme/ -->
				Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
			</div>
		</div>
	</footer>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>
	<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>

</html>