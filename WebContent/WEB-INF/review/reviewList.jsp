
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="vo.PageInfo"%>
<%@page import="vo.ReviewBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	ArrayList<ReviewBean> ReviewList = (ArrayList<ReviewBean>) request.getAttribute("reviewList");
	ReviewBean review = (ReviewBean)request.getAttribute("review");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	String id = (String) session.getAttribute("M_ID");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>리뷰 목록</title>
<style type="text/css">
#registForm {
	width: 500px;
	height: 600px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

#tr_top {
	background: orange;
	text-align: center;
}

#pageList {
	margin: auto;
	width: 500px;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}




</style>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>후기게시판</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets2/img/favicon.png" rel="icon">
<link href="assets2/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=https://fonts.googleapis.com/css?family=Inconsolata:400,500,600,700|Raleway:400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets2/vendor/aos/aos.css" rel="stylesheet">
<link href="assets2/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets2/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets2/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets2/css/style.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: MyPortfolio - v4.3.0
  * Template URL: https://bootstrapmade.com/myportfolio-bootstrap-portfolio-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->







</head>

<body>
	<!--프로그램 리스트 -->

	<section class="section site-portfolio">
		<div class="container" style="width: 82%;">
			<div class="row mb-5 align-items-center">
				<div class="col-md-12 col-lg-6 mb-4 mb-lg-0" data-aos="fade-up">
					<br><br><h2>REVIEW LIST</h2>
				</div>
				

				&nbsp;&nbsp;&nbsp;
				
					<!-- 쓰기 -->

	<div align="right">
		<%
			if (id != null && !id.equals("admin")) {
		%>
		<input type="button" value="리뷰 작성하기" class="readmore"
			onClick="location.href='reviewWrite.do'" />
		<%
			}
		%>

	</div>





				<%
					if (ReviewList != null && listCount > 0) {
				%>
			</div>

			<%
				for (int i = 0; i < ReviewList.size(); i++) {
			%>

			<div id="program" width="300px" height="300px">
				<div id="portfolio-grid" class="row no-gutter" data-aos="fade-up"
					data-aos-delay="200">
					<div class="item web col-sm-6 col-md-4 col-lg-4 mb-4">
						<a
							href="reviewDetail.do?Rev_num=<%=ReviewList.get(i).getRev_num()%>"
							class="item-wrap fancybox">

							<div class="work-info">
								<h3><%=ReviewList.get(i).getTitle()%></h3>
								<span><%=ReviewList.get(i).getP_name()%></span>
							</div> <img src=upload/<%=ReviewList.get(i).getImage()%> width="500px"
							height="500px" class="img-fluid"
							onclick="javascript:location.href='reviewDetail.do?rev_num=<%=ReviewList.get(i).getRev_num()%>';">
						</a> <br>
						<h3>
							제목:
							<%=ReviewList.get(i).getTitle()%></h3>
						<p>
							카테고리:
							<%=ReviewList.get(i).getP_name()%></p>
						<p>
							작성자:
							<%=ReviewList.get(i).getM_id()%></p>

					</div>
					<%
						}
					%>

				</div>

			</div>
		</div>
	</section>



	<section id="pageList">
		<%
			if (nowPage <= 1) {
		%>
		[이전]&nbsp;
		<%
			} else {
		%>
		<a href="reviewList.do?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
		<%
			}
		%>

		<%
			for (int a = startPage; a <= endPage; a++) {
					if (a == nowPage) {
		%>
		[<%=a%>]
		<%
			} else {
		%>
		<a href="reviewList.do?page=<%=a%>">[<%=a%>]
		</a>&nbsp;
		<%
			}
		%>
		<%
			}
		%>

		<%
			if (nowPage >= maxPage) {
		%>
		[다음]
		<%
			} else {
		%>
		<a href="reviewList.do?page=<%=nowPage + 1%>">[다음]</a>
		<%
			}
		%>

	




	</section>
	<%
		} else {
	%>
	<section id="emptyArea">등록된 글이 없습니다.</section>
	<%
		}
	%>

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="assets2/vendor/aos/aos.js"></script>
	<script src="assets2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets2/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="assets2/vendor/php-email-form/validate.js"></script>
	<script src="assets2/vendor/swiper/swiper-bundle.min.js"></script>

	<!-- Template Main JS File -->
	<script src="assets2/js/main.js"></script>

</body>
<% if(id == null){ %>
<jsp:include page="/header.jsp" />
<% } else if(id.equals("admin")) { %>
<jsp:include page="/adminHeader.jsp" />
<% } else { %>
<jsp:include page="/header.jsp" />
<% } %>
<jsp:include page="/footer.jsp" />
</html>