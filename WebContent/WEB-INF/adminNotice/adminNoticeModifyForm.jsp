<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="vo.Notice"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.apache.catalina.connector.OutputBuffer"%>

<%
	Notice article = (Notice) request.getAttribute("article");
	String nowPage = (String) request.getParameter("page");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>




<script type="text/javascript">
	function modifynotice() {
		modifyform.submit();
	}
</script>





<style>
#writeForm {
	ailgn: center;
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
	<section id="writeForm">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">

				<h2 class="text-center">공지사항 수정</h2>
				<form action="noticeModifyPro.do" method="post" name="modifyform">
					<input type="hidden" name="n_num" value="<%=article.getN_num()%>" />
					<table class="table table-striped">

						<tr>
							<td class="td_left"><label for="n_writer">글쓴이</label></td>
							<td class="td_right"><input type="text" name="n_writer"
								id="n_writer" disabled value="<%=article.getN_writer()%>" /></td>
						</tr>


						<tr>
							<td>제목</td>
							<td><input type="text" class="form-control" name="n_title"
								type="text" id="n_title" required="required"
								value="<%=article.getN_title()%>" /></td>
						</tr>



						<tr>
							<td class="td_left"><label for="n_content">내 용</label></td>
							<td><textarea id="n_content" name="n_content" cols="50"
									rows="10" required="required"><%=article.getN_content()%></textarea></td>
						</tr>



					</table>

					<section id="commandCell">
						<a href="javascript:modifynotice()">[수정]</a>&nbsp;&nbsp; <a
							href="noticeDeletePro.do?n_num=<%=article.getN_num()%>&page=<%=nowPage%>">[삭제]</a>&nbsp;&nbsp;
						<a href="javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp;

					</section>

				</form>
			</div>
		</div>
	</section>


	<script>
		//CKEDITOR 적용 
		CKEDITOR.replace('n_content', {

			width : '100%',
			height : '350'

		});
	</script>
</body>

<!-- 게시판 등록 -->
		<jsp:include page="/adminHeader.jsp" />
		<jsp:include page="/footer.jsp" />

</html>