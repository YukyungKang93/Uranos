<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<%@page import="vo.PageInfo"%>
<%@page import="vo.ProgramBean"%>

<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
String id = (String) session.getAttribute("M_ID");
String category = request.getParameter("category");
	ArrayList<ProgramBean> hikingList = (ArrayList<ProgramBean>) request.getAttribute("hikingList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	ProgramBean program = (ProgramBean) request.getAttribute("program");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<br>
<br>
<br>
<br>
<title>같이가치_등산프로그램</title>
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

a:visited {color: black; 
text-decoration: none;
}

container{

}


</style>

</head>
<meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title></title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets2/img/favicon.png" rel="icon">
  <link href="assets2/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=https://fonts.googleapis.com/css?family=Inconsolata:400,500,600,700|Raleway:400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets2/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets2/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets2/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets2/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets2/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: MyPortfolio - v4.3.0
  * Template URL: https://bootstrapmade.com/myportfolio-bootstrap-portfolio-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->




<body>
	<!--프로그램 리스트 -->
		
	<section class="section site-portfolio" >
      <div class="container" style="width: 82%;">
        <div class="row mb-5 align-items-center">
          <div class="col-md-12 col-lg-6 mb-4 mb-lg-0" data-aos="fade-up">
            <h2>Hiking List</h2>
          </div>
		
        </div>
	
			<%
				for (int i = 0; i < hikingList.size(); i++) {
					if (i % 3 == 0) {
			%>
			
				<%
					}
				%>
					
	<div id="program">		
		<div id="portfolio-grid" class="row no-gutter" data-aos="fade-up" data-aos-delay="200"  >
          <div class="item web col-sm-6 col-md-4 col-lg-4 mb-4">
          		<h3><%=hikingList.get(i).getP_name()%></h3>
                <span><%=hikingList.get(i).getCategory()%></span>
            <a href="programDetail.do?p_num=<%=hikingList.get(i).getP_num()%>" class="item-wrap fancybox">
              <div class="work-info">
                <h3><%=hikingList.get(i).getP_name()%></h3>
                <span><%=hikingList.get(i).getCategory()%></span>
          		</div>
              <img src= upload/<%=hikingList.get(i).getImage()%> alt="<%=hikingList.get(i).getImage()%>" title="<%=hikingList.get(i).getImage()%>" class="img-fluid" onclick="javascript:location.href='programDetail.do?p_num=<%=hikingList.get(i).getP_num()%>';">
     </a>
          </div>
         <%
					if (i % 3 == 2) {
				%>
			<%
				}
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
		<a href="hikingList.do?category=<%=category%>&page=<%=nowPage - 1%>">[이전]</a>&nbsp;
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
		<a href="hikingList.do?category=<%=category%>&page=<%=a%>">[<%=a%>]
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
		<a href="hikingList.do?category=<%=category%>&page=<%=nowPage + 1%>">[다음]</a>
		<%
			}
		%>
	</section>
	
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets2/vendor/aos/aos.js"></script>
  <script src="assets2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets2/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets2/vendor/php-email-form/validate.js"></script>
  <script src="assets2/vendor/swiper/swiper-bundle.min.js"></script>

  <!-- Template Main JS File -->
  <script src="assets2/js/main.js"></script>
	
<% if(id == null){ %>
<jsp:include page="/header.jsp" />
<% } else if(id.equals("admin")) { %>
<jsp:include page="/adminHeader.jsp" />
<% } else { %>
<jsp:include page="/header.jsp" />
<% } %>
<jsp:include page="/footer.jsp" />
</body>	
</html>