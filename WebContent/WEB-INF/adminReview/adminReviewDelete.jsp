<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.ReviewBean"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.apache.catalina.connector.OutputBuffer"%>



<%
	ReviewBean review = (ReviewBean) request.getAttribute("review");
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review 수정</title>
<script type="text/javascript">
	function deleteboard() {
		deleteform.submit();
	}
</script>

<script type="text/javascript">
	function select(e) {
		// 선택된 데이터의 텍스트값 가져오기
		const text = e.options[e.selectedIndex].text;

		console.log(e.options);

		// 선택한 텍스트 출력
		document.getElementById('Rev_contentResult').innerText = text
				+ " 기재로 인하여 삭제되었습니다.";
	}
</script>

<!-- <script type="text/javascript">
	function selectcategory() {
		document.deleteform.P_name.value = document.deleteform.P_name.options[document.deleteform.P_name.selectedIndex].value;
	}
</script> -->



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

#commandCell {
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

#Rev_contentResult{
	width:100%;
	height: 80%;
}


</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- CKEDITOR  -->
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>





</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<!-- 게시판 등록 -->

	<%
		String id = (String) session.getAttribute("M_ID");
	%>


	<section id="writeForm">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">


				<h2>REVIEW 삭제</h2>
				<form action="adminReviewDeletePro.do" method="post"
					name="deleteform">
					<input type="hidden" name="Rev_num"
						value="<%=review.getRev_num()%>" /> <input type="hidden"
						name="Image" value="delete.png" />

					<table class="table table-striped">


						<tr>
							<td>별점</td>
							<td><input type="text" class="form-control" name="Score"
								id="Score" value="0" readonly />
						<tr>
						<tr>
							<td>카테고리</td>
							<td><input type="text" class="form-control" name="P_name"
								id="P_name" value="삭제" readonly>
						</tr>

						<tr>
							<td>작성자</td>
							<td><input type="text" class="form-control" name="M_id"
								id="M_id" value="관리자" readonly /></td>
						</tr>

						<tr>
							<td>제목</td>
							<td><input type="text" class="form-control" name="Title"
								type="text" id="Title" value="관리자에 의해 삭제되었습니다." readonly /></td>
						</tr>


						<tr>
							<td class="td_left"><label for="Rev_content">삭제 원인</label></td>
							<td><select id="Rev_content" onChange="select(this)"
								size="1">
									<option value="선택해주세요.">선택해주세요.</option>
									<option value="욕설">욕설글</option>
									<option value="광고">광고성 글</option>
									<option value="음란물">음란물</option>
							</select></td>
						</tr>


						<tr>
							<td>내 용</td>
							<td><textarea id="Rev_contentResult" name="Rev_content" cols="50"
									rows="10" readonly><%=review.getRev_content()%></textarea></td>
						</tr>


					</table>
					<section id="commandCell">
						<a href="javascript:deleteboard()">[수정]</a>&nbsp;&nbsp; <a
							href="javascript:history.go(-1)">[뒤로]</a>
					</section>
				</form>

			</div>
		</div>
	</section>
	<script>
		//CKEDITOR 적용 
		CKEDITOR.replace('qna_content', {

			width : '100%',
			height : '350'

		});
	</script>


</body>
<jsp:include page="/adminHeader.jsp" />
<jsp:include page="/footer.jsp" />
</html>