<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@page import="vo.PageInfo"%>
<%@page import="vo.ProgramBean"%>
<%@page import="vo.Notice"%>
<%@page import="svc.index.LatestProgramService"%>
<%@page import="svc.index.ProgramBestService"%>
<%@page import="svc.index.IndexNoticeListService"%>
<%@page import="vo.Faq"%>
<%@page import="svc.index.IndexFaqListService"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="util.JdbcUtil.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<%
ProgramBestService programBestService = new ProgramBestService();
ArrayList<ProgramBean> programBestList = programBestService.getProgramList();
LatestProgramService latestProgramService = new LatestProgramService();
ArrayList<ProgramBean> programLatestList = latestProgramService.getProgramList();		

IndexNoticeListService indexNoticeListService = new  IndexNoticeListService();
ArrayList<Notice> indexNoticeList =  indexNoticeListService.getIndexNoticeList();
IndexFaqListService indexFaqListService = new  IndexFaqListService();
ArrayList<Faq> indexFaqList =  indexFaqListService.getIndexFaqList();
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
<link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,600,600i,700,700i|Satisfy|Comic+Neue:300,300i,400,400i,700,700i"
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

a:link, li:link {
	color: orange;
	text-decoration: none;
}

a:visited, li:link {
	color: orange;
	text-decoration: none;
}
</style>
 <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>

<body>

	<!-- ======= Top Bar ======= -->
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


			<i id="uraInfo" class="bi bi-phone d-flex align-items-center"> <span>
					<a href="MemberModifyForm.do">내 정보</a>
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
				<%
					String id = (String) session.getAttribute("M_ID");
					if (id == null) {
				%>
				<h1>
					<a href="index.jsp"> <img id="logoimg" src="./img/logo.png"
						alt="같이가치 logo" title="같이가치">
					</a>
				</h1>
				<%
					} else if (id.equals("admin")) {
				%>
				<h1>
					<a href="adminindexpro.do"> <img id="logoimg"
						src="./img/logo.png" alt="같이가치 logo" title="같이가치">
					</a>
				</h1>
				<%
					} else {
				%>
				<h1>
					<a href="index.jsp"> <img id="logoimg" src="./img/logo.png"
						alt="같이가치 logo" title="같이가치">
					</a>
				</h1>
				<%
					}
				%>
				<!-- Uncomment below if you prefer to use an image logo -->
				<!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
			</div>

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<li><a class="nav-link scrollto active" href="index.jsp">Home</a></li>
					<%
						String admin = (String) session.getAttribute("M_ID");

						if (admin == null) {
					%>
					<li class="dropdown"><a href="programList.do"><span>프로그램</span>
							<i class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a href="riddingList.do?category=러닝/마라톤">러닝/마라톤</a></li>
							<li><a href="hikingList.do?category=등산">등산</a></li>
							<li><a href="petTourList.do?category=펫산책">펫 산책</a></li>
							<li><a href="castleList.do?category=성곽순례">성곽 순례</a></li>

						</ul></li>
					<li class="dropdown"><a href="url.do?URL=regWriteForm.do"><span>신청하기</span> </a>
						</li>

					<li><a class="nav-link scrollto" href="reviewList.do">후기</a></li>
					<li class="dropdown"><a href="#"><span>공지사항</span> <i
							class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a href="noticeList.do">공지사항</a></li>
							<li><a href="faqList.do">FAQ</a></li>
							<li><a href="qnaList.do">Q&amp;A</a></li>
						</ul></li>
					<%
						} else if (admin.equals("admin")) {
					%>

					<li><a class="nav-link scrollto" href="testaction.do">회원관리</a></li>
					<li><a class="nav-link scrollto" href="programAdminList.do">프로그램관리</a></li>
					<li class="dropdown"><a href="programList.do"><span>프로그램</span>
							<i class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a href="riddingList.do?category=러닝/마라톤">러닝/마라톤</a></li>
							<li><a href="hikingList.do?category=등산">등산</a></li>
							<li><a href="petTourList.do?category=펫산책">펫 산책</a></li>
							<li><a href="castleList.do?category=성곽순례">성곽 순례</a></li>


						</ul></li>
					<li class="dropdown"><a href="regWriteForm.do"><span>신청하기</span> </a>
						</li>

					<li><a class="nav-link scrollto" href="reviewList.do">후기</a></li>
					<li class="dropdown"><a href="#"><span>공지사항</span> <i
							class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a href="noticeList.do">공지사항</a></li>
							<li><a href="faqList.do">FAQ</a></li>
							<li><a href="qnaList.do">Q&amp;A</a></li>
						</ul></li>
					<%
						} else {
					%>
					<li class="dropdown"><a href="programList.do"><span>프로그램</span>
							<i class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a href="riddingList.do?category=러닝/마라톤">러닝/마라톤</a></li>
							<li><a href="hikingList.do?category=등산">등산</a></li>
							<li><a href="petTourList.do?category=펫산책">펫 산책</a></li>
							<li><a href="castleList.do?category=성곽순례">성곽 순례</a></li>

						</ul></li>
					<li class="dropdown"><a href="regWriteForm.do"><span>신청하기</span> </a>
						</li>
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
			<!-- .navbar -->

		</div>
	</header>
	<!-- End Header -->

	<!-- ======= Hero Section ======= -->
	<section id="hero">
		<div class="hero-container">
			<div id="heroCarousel" data-bs-interval="5000"
				class="carousel slide carousel-fade" data-bs-ride="carousel">

				<ol class="carousel-indicators" id="hero-carousel-indicators"></ol>

				<div class="carousel-inner" role="listbox">

					<!-- Slide 1 -->
					<div class="carousel-item active"
						style="background: url(assets/img/slide/slide-1.jpg);">
						<div class="carousel-container">
							<div class="carousel-content">
								<h2 class="animate__animated animate__fadeInDown">
									<span>성곽순례</span> 모집중
								</h2>
								<p class="animate__animated animate__fadeInUp"></p>
								<div>
									<a href="castleList.do"
										class="btn-menu animate__animated animate__fadeInUp scrollto">바로가기</a>
								</div>
							</div>
						</div>
					</div>

					<!-- Slide 2 -->
					<div class="carousel-item"
						style="background: url(assets/img/slide/slide-2.jpg);">
						<div class="carousel-container">
							<div class="carousel-content">
								<h2 class="animate__animated animate__fadeInDown">
									<span>펫 산책</span> 모집중
								</h2>
								<p class="animate__animated animate__fadeInUp"></p>
								<div>
									<a href="petTourList.do"
										class="btn-menu animate__animated animate__fadeInUp scrollto">바로가기</a>
								</div>
							</div>
						</div>
					</div>

					<!-- Slide 3 -->
					<div class="carousel-item"
						style="background: url(assets/img/slide/slide-3.jpg);">
						<div class="carousel-background">
							<img src="assets/img/slide/slide-3.jpg" alt="">
						</div>
						<div class="carousel-container">
							<div class="carousel-content">
								<h2 class="animate__animated animate__fadeInDown">
									<span>등산</span> 모집중
								</h2>
								<p class="animate__animated animate__fadeInUp"></p>
								<div>
									<a href="hikingList.do"
										class="btn-menu animate__animated animate__fadeInUp scrollto">바로가기</a>
								</div>
							</div>
						</div>
					</div>

					<!-- Slide 4 -->
					<div class="carousel-item"
						style="background: url(assets/img/slide/slide-3.jpg);">
						<div class="carousel-background">
							<img src="assets/img/slide/slide-3.jpg" alt="">
						</div>
						<div class="carousel-container">
							<div class="carousel-content">
								<h2 class="animate__animated animate__fadeInDown">
									<span>러닝/마라톤</span> 모집중
								</h2>
								<p class="animate__animated animate__fadeInUp"></p>
								<div>
									<a href="riddingList.do"
										class="btn-menu animate__animated animate__fadeInUp scrollto">바로가기</a>
								</div>
							</div>
						</div>
					</div>

				</div>

				<a class="carousel-control-prev" href="#heroCarousel" role="button"
					data-bs-slide="prev"> <span
					class="carousel-control-prev-icon bi bi-chevron-left"
					aria-hidden="true"></span>
				</a> <a class="carousel-control-next" href="#heroCarousel" role="button"
					data-bs-slide="next"> <span
					class="carousel-control-next-icon bi bi-chevron-right"
					aria-hidden="true"></span>
				</a>

			</div>
		</div>
	</section>
	<!-- End Hero -->

<br><br><div class="container-fluid">
		<div class="row">
		<br><br><div class="col-md-6">
				<h2 align="center">공지사항<button></button></h2> 
			<table class="table">
					<thead>
						<tr>
						<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
						
						</tr>
					</thead>
					<tbody>
						<%
						for (int i = 0; i < indexNoticeList.size(); i++) {
					%>
					
						<tr>
							<td><a href="noticeList.do"><%=indexNoticeList.get(i).getN_num()%></a></td>
							<td><a href="noticeList.do"><%=indexNoticeList.get(i).getN_title()%></a> </td>
							<td><a href="noticeList.do"><%=indexNoticeList.get(i).getN_writer()%></a></td>
							
						</tr>
						<%
						
					%>
					<%
					
					%>
					
					<%
						}
					%>
					</tbody>
				</table>
			</div>
			<br><br><div class="col-md-6">
				<h2 align="center">자주하는 질문<button></button></h2> 
			<table class="table">
					<thead>
						<tr>
						<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
						
						</tr>
					</thead>
					<tbody>
						<%
						for (int i = 0; i < indexFaqList.size(); i++) {
					%>
					
						<tr>
							<td><a href="faqList.do"><%=indexFaqList.get(i).getF_num()%></a></td>
							<td><a href="faqList.do"><%=indexFaqList.get(i).getF_title()%></a> </td>
							<td><a href="faqList.do"><%=indexFaqList.get(i).getF_writer()%></a></td>
							
						</tr>
						<%
						
					%>
					<%
					
					%>
					
					<%
						}
					%>
					</tbody>
				</table>
			</div>
			<br><br><div class="col-md-6">
				<h2 align="center">베스트 프로그램</h2>
				<table class="table">
					<thead>
						<tr>
							<th>프로그램</th>
							<th>제목</th>
							<th>작성자</th>
							<th>날짜</th>
							<th>모집인원</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < programBestList.size(); i++) {
						%>
						<tr>
							<td align="center"><a
								href="programDetail.do?p_num=<%=programBestList.get(i).getP_num()%>"><%=programBestList.get(i).getCategory()%></a></td>
							<td align="center"><a
								href="programDetail.do?p_num=<%=programBestList.get(i).getP_num()%>"><%=programBestList.get(i).getP_name()%></a></td>
							<td align="center"><a
								href="programDetail.do?p_num=<%=programBestList.get(i).getP_num()%>"><%=programBestList.get(i).getM_id()%></a></td>
							<td align="center"><a
								href="programDetail.do?p_num=<%=programBestList.get(i).getP_num()%>"><%=programBestList.get(i).getStartdate()%></a></td>
							<td align="center"><a
								href="programDetail.do?p_num=<%=programBestList.get(i).getP_num()%>"><%=programBestList.get(i).getTotal_number()%></a></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		<br><br><div class="col-md-6">
				<h2 align="center">최근 프로그램</h2>
				<table class="table">
					<thead>
						<tr>
							<th>카테고리</th>
						<th>프로그램명</th>
						<th>날짜</th>
						<th>모집인원</th>
						</tr>
					</thead>
					<tbody>
						
					<%
						for (int i = 0; i < programLatestList.size(); i++) {
					%>
					<tr>
						<td align="center"><a
							href="programDetail.do?p_num=<%=programLatestList.get(i).getP_num()%>"><%=programLatestList.get(i).getCategory()%></a></td>

						<td align="center">
							<%
								if (programLatestList.get(i).getP_num() != 0) {
							%> <%
 	for (int a = 0; a <= programLatestList.get(i).getP_num() * 2; a++) {
 %> <%
 	}
 		} else {
 		}
 %><a
							href="programDetail.do?p_num=<%=programLatestList.get(i).getP_num()%>">

								<%=programLatestList.get(i).getP_name()%>

						</a>
						</td>


						<td align="center"><a
							href="programDetail.do?p_num=<%=programLatestList.get(i).getP_num()%>"><%=programLatestList.get(i).getStartdate()%></a></td>

						<td align="center"><a
							href="programDetail.do?p_num=<%=programLatestList.get(i).getP_num()%>"><%=programLatestList.get(i).getTotal_number()%></a></td>

					</tr>
					<%
						}
					%>

					</tbody>
				</table>
			</div>
	</div>
	</div>	
	
	
	
	
	
	
	
	

	<script src="MemberIndex/js/jquery.min.js"></script>
	<script src="MemberIndex/js/bootstrap.min.js"></script>
	<script src="MemberIndex/js/scripts.js"></script>


	<!-- ======= Gallery Section ======= --> <!-- End Gallery Section --> <!-- ======= Chefs Section ======= -->


	<!-- End Chefs Section --> <!-- ======= Testimonials Section ======= -->

	<!-- End testimonial item --> <!-- End testimonial item --> <!-- End testimonial item -->

	<!-- End testimonial item --> <!-- End testimonial item --> <!-- End Testimonials Section -->

	<!-- ======= Contact Section ======= -->
	<section id="contact" class="contact">
		<div class="container">

			<div class="section-title"></div>
		</div>

		<div class="map"></div>

		<div class="container mt-5">

			<div id="heightEdit"></div>



		</div>
	</section>

	<!-- End Contact Section --> </main>
	<!-- End #main -->

<!-- ======= Footer ======= -->
	<footer id="footer">
		<div class="container">
			<h1>
					<a href="index.jsp"> <img id="logoimg" src="./img/logo.png"
						alt="같이가치 logo" title="같이가치">
					</a>
				</h1>
			<div class="memeber-address">
			Uranos : 김민아 / 강유경 / 공병찬 / 김정빈 / 이정노/ 정혁희 / 지승빈<br>
			서울특별시 금천구 가산디지털2로 127-31 월드메르디앙4차 413호<br></div>
			
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