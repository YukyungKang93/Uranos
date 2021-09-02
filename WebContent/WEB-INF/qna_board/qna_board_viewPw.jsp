<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	int qna_num = (Integer) request.getAttribute("qna_num");
	String nowPage = (String) request.getAttribute("page");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qna 게시판</title>


<!-- 버튼 -->

<link href="assets2/css/style.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<style>
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

.jg {
	font-family: 'Jeju Gothic', sans-serif;
}

#passForm {
	width: 450px;
	height: 220px;
	margin: auto;
	border: 10px solid orange;
	align: center;
	padding-top: 25px;
	border-radius: 100px;
}

#bubu {
	align: center;
}

#hr {
	border: 10px solid orange;
}

h3 {
	text-align: left;
	font-size: 15px;
}

p {
	text-align: center;
	font-size: 15px;
}

m_pw {
	padding-left: 50px;
}

#rgrg {
	align: center;
}

.readmore {
	font-weight: 600;
	font-size: 13px;
	letter-spacing: 1px;
	text-transform: uppercase;
	display: inline-block;
	padding: 12px 30px;
	border-radius: 50px;
	transition: 0.5s;
	line-height: 1;
	margin: 0 10px;
	-webkit-animation-delay: 0.8s;
	animation-delay: 0.8s;
	border: 2px solid #ffb03b;
}
</style>
</head>


<section id="aa-catg-head-banner">
	<div class="aa-catg-head-banner-area">
		<div class="container"></div>
	</div>
</section>


<body>

<section id="aa-product-category" align="left">
		<div class="container">

			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">

					<div class="aa-product-catg-content">

						<div class="aa-product-catg-body">



	
		<h2>Q&amp;A 게시판</h2>



	<br>
	<br>
	<section id="passForm">
		<form name="deleteForm"
			action="qnaViewpro.do?qna_num=<%=qna_num%>&page=<%=nowPage%>"
			method="post">
			<input type="hidden" name="page" value="<%=nowPage%>" />
			<table>
				<div id="rgrg">
					<p class="jg">비밀번호를 입력하세요</p>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="m_pw" type="password" size="55px"><br> <br>
						</td>
					</tr>
					<tr id="bubu">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="확인" class="readmore" />&nbsp;&nbsp;
							<input type="button" value="돌아가기"
							onClick="javascript:history.go(-1)" class="readmore" />
						</td>
					</tr>
					<br>
				</div>
			</table>
		</form>
	</section>
	
	
	
	<!-- ssss -->
	</div>
	</div>
	</div>
	</div>
	</div>
	</section>




	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/header.jsp" />
	<jsp:include page="/footer.jsp" />
</body>
</html>