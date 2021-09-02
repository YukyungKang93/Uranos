<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="vo.ReviewBean"%>
<%@page import="vo.P_replyBean"%>
<%@page import="vo.ProgramBean"%>
<%@page import="dao.ProgramDAO"%>

<%@page import="vo.PageInfo"%>
<%@page import="vo.ReviewBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	String user_id = (String) session.getAttribute("M_ID");

	ProgramBean program = (ProgramBean) request.getAttribute("program");
	//String nowPage = (String) request.getAttribute("page");
	ArrayList<P_replyBean> replyList = (ArrayList<P_replyBean>) request.getAttribute("replyList");

	ArrayList<ReviewBean> ReviewList = (ArrayList<ReviewBean>) request.getAttribute("reviewList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int listCount = pageInfo.getListCount();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();

	P_replyBean reply = (P_replyBean) request.getAttribute("reply");
	ProgramDAO programdao = (ProgramDAO) request.getAttribute("programdao");
%>
<!DOCTYPE html>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>프로그램 상세정보</title>

<!--  끝났지롱 필수기능 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script> -->
<script src="<c:url value='/js/common1.js'/>" charset="utf-8"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>


<%-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
    
	   $("input:radio[name='Score']:radio[value='<%=review.getScore()%>
	']")
								.prop('checked', true);

					});
</script> --%>
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
	background: orange;
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

.nav {
	margin-top: 0px;
	position: sticky;
	top: 70px;
	background-color: white;
	color: initial;
}

.nav-item {
	font-size: 15px;
	color: initial;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}

body {
	color: #009933;
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

	<main id="main">

	<section class="section" style="margin-top: 30px;">

		<div class="container">
			<div class="row mb-4 align-items-center">
				<div class="col-md-6" data-aos="fade-up">
					<h2>프로그램 상세정보</h2>
				</div>
			</div>
		</div>



		<div class="site-section pb-0">
			<div class="container">
				<div class="row align-items-stretch">
					<div class="col-md-8" data-aos="fade-up">
						<img src=upload/<%=program.getImage()%> width="70%" height="60%"
							alt="<%=program.getImage()%>" title="<%=program.getImage()%>" class="img-fluid">
					</div>


					<div class="col-md-3 ml-auto" data-aos="fade-up"
						data-aos-delay="100">
						<div class="sticky-content">

							<h4 class="h4 mb-3"></h4>
							<ul class="list-unstyled list-line mb-5">

								<div>
									<font size="4"><%=program.getP_name()%></font>
								</div>

								<br>
								<div>
									<font size="3"><%=program.getCategory()%></font>
								</div>


								<div id="startdate" align="left">
									<br>프로그램 시작일<font size="5"><br><%=program.getStartdate()%></font>
								</div>



								<div id="count"
									style="font-weight: 600px; font-Size: 24px; line-height: 42px; position: relative;">
									<br>남은인원/모집인원<br>
									<font size="20">&nbsp;&nbsp;&nbsp;&nbsp;<%=program.getCount()%>/<%=program.getTotal_number()%></font>명
								</div>



								<div>
									<form name="form1" method="post"
										action="reservation.do?p_num=<%=program.getP_num()%>"
										style="position: relative;" align="right">
										<br> <a href="programList.do" hidden class="readmore">프로그램
											목록</a>

<script>
if(${program.count} == 0) {
	$('input[name="test"]').attr('value',"마감");
	$('input').attr('disabled', true);

	}
</script>

									</form>



									<script type="text/javascript">

$(function(){
	$('#dele').click(function(){
		if(!confirm('정말 삭제하시겠습니까?')){
			return false;
		}
	}
	)
})



</script>




									<form
										action="modifyProgramForm.do?p_num=<%=program.getP_num()%>"
										method="post" name="modify" title="수정폼"
										style="position: relative;" align="right">
										<input type="submit" value="수정하기" name="modify">
									</form>
									<form name="delete" id="dele" method="post"
										action="deleteProgram.do?p_num=<%=program.getP_num()%>"
										style="position: relative;" align="right">
										<input type="submit" value="삭제" title="삭제"
											onclick="confirmModal()">
									</form>






									<%-- 
function confirmModal() {
    window.confirm("정말 삭제할랍니까?");
  
    
  }--%>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>





	</section>

	<!-- 네비게이션바 -->

	<section class="navi" data-aos="fade-up">
		<div class="row align-items-stretch">
			<div class="navigation2" align="center" id="f1">
				<ul class="nav nav-tabs" id="myTab" role="tablist"
					style="width: 80%">
					<li class="nav-item" role="presentation" style="width: 33%"><a
						class="nav-link" id="home-tab" data-toggle="tab" href="#f1"
						role="tab" aria-controls="detailInfo">상세정보</a></li>

					<li class="nav-item" role="presentation" style="width: 33%"><a
						class="nav-link" id="profile-tab" href="#f2" role="tab"
						aria-controls="profile" aria-selected="false">프로그램 문의</a></li>



					<li class="nav-item" role="presentation" style="width: 33%"><a
						class="nav-link" id="contact-tab" href="#f4" role="tab"
						aria-controls="re" aria-selected="false">후기</a></li>

				</ul>
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="detailInfo"
						role="tabpanel" aria-labelledby="detailInfo-tab">
						<br> <br> <br>
						<td><%=program.getP_name()%></td> <br> <br> <br> <img
							src=upload/<%=program.getImage()%> alt="<%=program.getImage()%>" title="<%=program.getImage()%>" class="img-fluid"
							align="center"><br> <br> <br> <br>
						<pre id="p_content"><%=program.getContent()%> </pre>
						<br> <br> <br> <br>
					</div>
					<!-- 상세정보 -->

				</div>


				<br> <br>

				<div align="center" id="f2">
					<ul class="nav nav-tabs" id="myTab" role="tablist"
						style="width: 60%">


						<li class="nav-item" role="presentation" style="width: 25%"><a
							href="#f" role="tab" aria-controls="home1" aria-selected="false"
							hidden>상세정보</a></li>


						<li class="nav-item" role="presentation" style="width: 25%"><a
							class="nav-link" id="profile-tab" data-toggle="tab" href="#f2"
							role="tab" aria-controls="profile1" aria-selected="true" hidden>프로그램
								문의</a></li>


						<li class="nav-item" role="presentation" style="width: 25%"><a
							href="#f3" role="tab" aria-controls="guide1"
							aria-selected="false" hidden>신청 가이드</a></li>


						<li class="nav-item" role="presentation" style="width: 25%"><a
							href="#f4" role="tab" aria-controls="re1" aria-selected="false"
							hidden>후기</a></li>


					</ul>
					<div class="tab-content" id="myTabContent">

						<div class="tab-pane fade show active" role="tabpanel"
							aria-labelledby="profile-tab1">
							<!-- 프로그램 문의 -->
							<br> <br> <br> <br> <br> <br> <br>
							<br> <br>
							<div class="comment-wrapper">
								<form id="replyform"
									action="replyForm.do?p_num=<%=program.getP_num()%>&m_id=<%=user_id%>"
									method="post">

									<textarea id="con" name="content" style="width: 70%"
										placeholder="댓글을 입력해주세요" rows="2"></textarea>

									<br>
									<button id="btn" type="submit">등록</button>
								</form>


								<div class="row bootstrap snippets">
									<div class="col-md-12">
										<div class="clearfix"></div>
										<div class="xans-element- xans-product xans-product-review">
											<div class="ec-base-table typeList">
												<br> <br>
												<h3>전체댓글</h3>
												<form></form>
												<table border="1" width="70%">
													<%
														if (replyList != null && listCount > 0) {
													%>
													<colgroup>
														<col style="width: 120px;">
														<col style="width: auto;">
														<col style="width: 120px">
														<col style="width: 120px;">
													</colgroup>
													<thead>
														<tr>
															<th scope="col">작성자</th>
															<th scope="col">내용</th>
															<th scope="col">작성일자</th>
															<th scope="col"></th>
														</tr>
													</thead>
													<tbody id="my-tbody">
														<tr>
															<%
																for (int i = 0; i < replyList.size(); i++) {
															%>

															<td scope="col">
																<%
																	if (replyList.get(i).getReply_num() != replyList.get(i).getGroup_num()) {
																%> ↳ <%=replyList.get(i).getM_id()%> <%
 	} else {
 %> <%=replyList.get(i).getM_id()%> <%
 	}
 %>
															
															<td scope="col"><%=replyList.get(i).getContent()%>
															<td scope="col"><%=replyList.get(i).getReg_date()%></td>

															<td>



																<form name="re_replyForm" method="post">
																	<a
																		href="re_reply.do?reply_num=<%=replyList.get(i).getReply_num()%>&p_num=<%=replyList.get(i).getP_num()%>&m_id=<%=user_id%>">댓글
																		쓰기</a> <a
																		href="replyAdminDelete.do?reply_num=<%=replyList.get(i).getReply_num()%>&p_num=<%=replyList.get(i).getP_num()%>&m_id=<%=user_id%>">삭제</a>
																</form> <%
 	if (replyList.get(i).getM_id().equals("admin")) {
 %>
																<form name="modifyForm" method="post">
																	<a
																		href="replyModify.do?reply_num=<%=replyList.get(i).getReply_num()%>&p_num=<%=replyList.get(i).getP_num()%>">수정</a>
																</form>
																<%
																	}
																%>

															</td>
														</tr>
													</tbody>
													<%
														}
													%>

												</table>



											</div>



											<div id="PAGE_NAVI">
												<section id="pageList">
													<%
														if (nowPage <= 1) {
													%>
													[이전]&nbsp;
													<%
														} else {
													%>
													<a
														href="programDetail.do?p_num=${program.p_num}&page=<%=nowPage-1 %>">[이전]</a>&nbsp;
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
													<a
														href="programDetail.do?p_num=${program.p_num}&page=<%=a %>">[<%=a%>]
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
													<a
														href="programDetail.do?p_num=${program.p_num}&page=<%=nowPage+1 %>">[다음]</a>
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

											</div>




										</div>
									</div>
								</div>

								<br> <br>

								<div align="center" id="f3">
									<ul class="nav nav-tabs" role="tablist" style="width: 60%">
										<li class="nav-item" role="presentation" style="width: 25%"><a
											id="detailInfo-tab" href="#f1" role="tab"
											aria-controls="detailInfo2" aria-selected="false" hidden>상세정보</a></li>
										<li class="nav-item" role="presentation" style="width: 25%"><a
											href="#f2" role="tab" aria-controls="profile2"
											aria-selected="false" hidden>프로그램 문의</a></li>
										<li class="nav-item" role="presentation" style="width: 25%"><a
											data-toggle="tab" href="#f3" role="tab"
											aria-controls="contact2" aria-selected="true" hidden>신청
												가이드</a></li>
										<li class="nav-item" role="presentation" style="width: 25%"><a
											href="#f4" role="tab" aria-controls="re2"
											aria-selected="false" hidden>후기</a></li>
									</ul>
									<div class="tab-content" id="myTabContent">


										<div class="tab-pane fade show active" id="contact2"
											role="tabpanel" aria-labelledby="guide-tab" align="left"
											class="img-fluid" style="margin-left: 300px">
											<br> <br> <br> <br> <span id="f3"></span>
											<%@ include file="/WEB-INF/program/guide.jsp"%>
										</div>

									</div>
								</div>

								<br> <br>
								<div align="center" id="f4">
									<ul class="nav nav-tabs" role="tablist" style="width: 60%">
										<li class="nav-item" role="presentation" style="width: 25%"><a
											id="detailInfo-tab" href="#" role="tab"
											aria-controls="detailInfo2" aria-selected="false" hidden>상세정보</a></li>
										<li class="nav-item" role="presentation" style="width: 25%"><a
											href="#f2" role="tab" aria-controls="profile2"
											aria-selected="false" hidden>프로그램 문의</a></li>
										<li class="nav-item" role="presentation" style="width: 25%"><a
											data-toggle="tab" href="#f3
				 role="
											tab" aria-controls="contact2" aria-selected="true" hidden>신청
												가이드</a></li>
										<li class="nav-item" role="presentation" style="width: 25%"><a
											href="#f4" role="tab" aria-controls="re2"
											aria-selected="false" hidden>후기</a></li>
									</ul>

									<div class="tab-content" id="myTabContent">

										<div class="tab-pane fade show active" id="re3"
											role="tabpanel" aria-labelledby="contact-tab">
											<!-- 상품평 -->

											<div class="xans-element- xans-product xans-product-review"
												id="f4">
												<div class="ec-base-table typeList">
													<br>
													<h3>PROGRAM REVIEW</h3>
													<p class="desc">
														최근 등록된
														<%=program.getCategory()%>
														리뷰
													</p>
													<table border="1" width="60%">
														<caption></caption>
														<colgroup>
															<col style="width: 70px;">
															<col style="width: auto">
															<col style="width: 120px;">
															<col style="width: 120px;">
															<col style="width: 80px;" class="displaynone">
														</colgroup>
														<%
															if (ReviewList != null) {
														%>

														<tr id="tr_top">
															<td>번호</td>
															<td>프로그램</td>
															<td>제목</td>
															<td>작성자</td>
															<td>날짜</td>
															<td>조회수</td>
															<td>평점</td>
														</tr>

														<%
															for (int i = 0; i < ReviewList.size(); i++) {
																	if (ReviewList.get(i).getP_name().trim().equals(program.getCategory().trim())) {
														%>
														<tr>
															<td><%=ReviewList.get(i).getRev_num()%></td>
															<td><%=ReviewList.get(i).getP_name()%></td>

															<td><a
																href="reviewDetail.do?Rev_num=<%=ReviewList.get(i).getRev_num()%>&page=<%=nowPage%>">
																	<%=ReviewList.get(i).getTitle()%>
															</a></td>

															<td><%=ReviewList.get(i).getM_id()%></td>
															<td><%=ReviewList.get(i).getRev_date()%></td>
															<td><%=ReviewList.get(i).getRev_readcount()%></td>
															<td><%=ReviewList.get(i).getScore() + "점"%></td>
														</tr>
														<%
															}
																}
														%>
														<%
															} else {
														%>
														<section id="emptyArea">등록된 글이 없습니다.</section>
														<%
															}
														%>
													</table>
												</div>
											</div>

											<div id="PAGE_NAVI"></div>
											<input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX" />
											<div style="margin-left: 800px">
												<button type="button" class="btn btn-secondary btn-sm"
													onclick="location.href ='reviewList.do'">전체보기</button>
											</div>
										</div>
									</div>
								</div>
								<form id="commonForm" name="commonForm"></form>
							</div>
						</div>
	</section>





	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a> <!-- Vendor JS Files --> <script
		src="assets2/vendor/aos/aos.js"></script> <script
		src="assets2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script> <script
		src="assets2/vendor/isotope-layout/isotope.pkgd.min.js"></script> <script
		src="assets2/vendor/php-email-form/validate.js"></script> <script
		src="assets2/vendor/swiper/swiper-bundle.min.js"></script> <!-- Template Main JS File -->
	<script src="assets2/js/main.js"></script> </main>

</body>
		<jsp:include page="/adminHeader.jsp" />
		<jsp:include page="/footer.jsp" />

</html>