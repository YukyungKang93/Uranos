<%@page import="vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
	ReviewBean review = (ReviewBean) request.getAttribute("review");
	String nowPage = (String) request.getAttribute("page");
	String userId = (String) session.getAttribute("M_ID");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
$(document).ready(function() {
    
	   $("input:radio[name='Score']:radio[value='<%=review.getScore()%>']").prop('checked', true); 

});

function deleteSuccess() {
	var del = confirm("삭제하시겠습니까?");

	if (del) {
		alert("삭제되었습니다.");
		location.href="reviewDelete.do?Rev_num="+<%=review.getRev_num()%>+"&page="+<%=nowPage%>;
	} else {
		alert("취소되었습니다.");
	}
}


var tmpDate = new Date();
$("#Img").attr("src", "/img/shirt.jpg?"+tmeDate.getTime());

</script>

<style type="text/css">
.star-input>.input {
	text-align: center;
}

.star-input>.input, .star-input>.input>label:hover, .star-input>.input>input:focus+label,
	.star-input>.input>input:checked+label {
	display: inline-block;
	vertical-align: middle;
	background: url('img/star.png') no-repeat;
}

.star-input {
	width: 100%;
	text-align: center;
	padding: 15px 15px 30px 5px;
}

.star-input>.input {
	width: 150px;
	background-size: 150px;
	height: 28px;
	white-space: nowrap;
	overflow: hidden;
	position: relative;
}

.star-input>.input>input {
	position: absolute;
	width: 1px;
	height: 1px;
	opacity: 0;
}

star-input>.input.focus {
	outline: 1px dotted #ddd;
}

.star-input>.input>label {
	width: 30px;
	height: 0;
	padding: 28px 0 0 0;
	overflow: hidden;
	float: left;
	cursor: pointer;
	position: absolute;
	top: 0;
	left: 0;
}

.star-input>.input>label:hover, .star-input>.input>input:focus+label,
	.star-input>.input>input:checked+label {
	background-size: 150px;
	background-position: 0 bottom;
}

/* .star-input>.input>label:hover ~label{
	background-image: none;
} */

.star-input>.input>label[for="p1_2"] {
	width: 15px;
	z-index: 10;
}

.star-input>.input>label[for="p1"] {
	width: 30px;
	z-index: 9;
}

.star-input>.input>label[for="p2_2"] {
	width: 45px;
	z-index: 8;
}

.star-input>.input>label[for="p2"] {
	width: 60px;
	z-index: 7;
}

.star-input>.input>label[for="p3_2"] {
	width: 75px;
	z-index: 6;
}

.star-input>.input>label[for="p3"] {
	width: 90px;
	z-index: 5;
}

.star-input>.input>label[for="p4_2"] {
	width: 105px;
	z-index: 4;
}

.star-input>.input>label[for="p4"] {
	width: 120px;
	z-index: 3;
}

.star-input>.input>label[for="p5_2"] {
	width: 135px;
	z-index: 2;
}

.star-input>.input>label[for="p5"] {
	width: 150px;
	z-index: 1;
}

.star-input>output {
	display: inline-block;
	width: 60px;
	font-size: 18px;
	text-align: right;
	vertical-align: middle;
}
</style>
<style type="text/css">
#articleForm {
	width: 500px;
	height: 500px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

#basicInfoArea {
	height: 40px;
	text-align: center;
}

#articleContentArea {
	
	margin-top: 20px;
	height: 350px;
	text-align: center;
	overflow: auto;
}

#commandList {
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



</style>

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


</head>

<body>

	<main id="main">

	<section class="section" style="margin-top: 30px;">
		<div class="container">
			<div class="row mb-4 align-items-center">
				<div class="col-md-6" data-aos="fade-up">
					<h2>Detail Review</h2>
				</div>
			</div>
		</div>

		<div class="site-section pb-0">
			<div class="container">
				<div class="row align-items-stretch">
				
					<div class="col-md-8" data-aos="fade-up" >
						<img id="Img" src="upload/<%=review.getImage()%>" title="<%=review.getImage()%>" alt="<%=review.getImage()%>" alt="Image" class="img-fluid" width="1000px">
					</div>
					
					<div class="col-md-3 ml-auto" data-aos="fade-up"
						data-aos-delay="100">
						<div class="sticky-content" >
							<h3 class="h3"><%=review.getTitle()%></h3>
							<div class="star-input">
								<span class="input"> <input type="radio" name="Score"
								value="1" id="p1" disabled="disabled"> <label for="p1">1</label>
								<input type="radio" name="Score" value="0.5" id="p1_2"
								disabled="disabled"> <label for="p1_2">0.5</label> <input
								type="radio" name="Score" value="2" id="p2" disabled="disabled">
								<label for="p2">2</label> <input type="radio" name="Score"
								value="1.5" id="p2_2" disabled="disabled"> <label for="p2_2">1.5</label>
								<input type="radio" name="Score" value="3" id="p3"
								disabled="disabled"> <label for="p3">3</label> <input
								type="radio" name="Score" value="2.5" id="p3_2" disabled="disabled">
								<label for="p3_2">2.5</label> <input type="radio" name="Score"
								value="4" id="p4" disabled="disabled"> <label for="p4">4</label>
								<input type="radio" name="Score" value="3.5" id="p4_2"
								disabled="disabled"> <label for="p4_2">3.5</label> <input
								type="radio" name="Score" value="5" id="p5" disabled="disabled">
								<label for="p5">5</label> <input type="radio" name="Score"
								value="4.5" id="p5_2" disabled="disabled"> <label for="p5_2">4.5</label>
								</span>
						</div>
							
							<ul class="list-unstyled list-line mb-5">
								<section id="basicInfoArea">
			프로그램 :
			<%=review.getP_name()%>
		</section>
		<section id="basicInfoArea">
			제 목 :
			<%=review.getTitle()%>
		</section>
		<section id="basicInfoArea">
			작성자 :
			<%=review.getM_id()%>
		</section>
		
		
							</ul>

							<p>
								<%-- <a href="reviewModifyForm.do?Rev_num=<%=review.getRev_num()%>" class="readmore">Visit Website</a>
								<a href="reviewDeleteSuccess.do?Rev_num=<%=review.getRev_num()%>&page=<%=nowPage%>" class="readmore">Visit Website</a>
								<a href="reviewList.do" class="readmore">Visit Website</a> --%>
							</p>
						</div>
					</div>
				</div>
			</div>
	</section>




		<section id="articleContentArea">
			<pre><%=review.getRev_content()%></pre>
		
		</section>
		
		
<section id="commandList">
		<c:set value="<%=review.getM_id()%>" var="m_id"></c:set>
		<c:set value="<%=userId%>" var="userId"></c:set>
		<c:choose>
		<c:when test="${'admin' eq userId}">
			<a href="adminReviewDeleteForm.do?Rev_num=<%=review.getRev_num()%>">[삭제]</a>
			<a href="reviewList.do">[목록]</a>&nbsp;&nbsp;
		</c:when>
		<c:when test="${m_id eq userId}">
		<a href="reviewModifyForm.do?Rev_num=<%=review.getRev_num()%>">[수정]</a>
		<a href="#" onclick="deleteSuccess();">[삭제]</a>
		<a href="reviewList.do">[목록]</a>&nbsp;&nbsp;
		</c:when>
		<c:otherwise>
		<a href="reviewList.do">[목록]</a>&nbsp;&nbsp;
		</c:otherwise>
		</c:choose>
	</section>
	
	
	
			

	
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
<% if(userId == null){ %>
<jsp:include page="/header.jsp" />
<% } else if(userId.equals("admin")) { %>
<jsp:include page="/adminHeader.jsp" />
<% } else { %>
<jsp:include page="/header.jsp" />
<% } %>
<jsp:include page="/footer.jsp" />
</html>