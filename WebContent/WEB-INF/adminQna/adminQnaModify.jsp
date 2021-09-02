<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="vo.QnaBean"%>
<%
	QnaBean article = (QnaBean)request.getAttribute("article");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
	<title>admin 수정폼</title>
	<script type="text/javascript">
	function modifyboard(){
		modifyform.submit();
	}
	</script>
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

<section id = "writeForm">

		<div class="row">
    		<div class="col-md-2"></div>
    		<div class="col-md-8">
    		
<h2>게시판글수정</h2>
<form action="qnaModifyPro.do" method="post" name = "modifyform"
>
<input type = "hidden" name = "qna_num" value = "<%=article.getQna_num()%>"/>
<input type = "hidden" name = "m_pw" value = "<%=article.getM_pw()%>"/>
<table class="table table-striped">
	<tr>
		<td>제 목</td>
		<td><input name="title" type="text" id = "title" value ="관리자에 의해 삭제된 글 " readonly/></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type = "text" name="m_id" id = "m_id" value = "관리자" readonly/></td>
	</tr>
	<tr>
		<td>내 용</td>
		<td><textarea id = "qna_content" name="qna_content" cols="40" rows="15" readonly>관리자에 의해 삭제된 글 입니다</textarea></td>
	</tr>
</table>
	<section id = "commandCell">
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>
	</section>
</form>
</section>

	<script>
//CKEDITOR 적용 
CKEDITOR.replace('qna_content', {
         
    width:'100%',
    height:'350'
         
});
	
</script>

		<jsp:include page="/adminHeader.jsp" />
		<jsp:include page="/footer.jsp" />
</body>
</html>