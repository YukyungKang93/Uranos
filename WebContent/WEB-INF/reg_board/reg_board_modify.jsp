<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@page import="vo.RegBean"%>
<%
	RegBean article = (RegBean) request.getAttribute("article");
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.apache.catalina.connector.OutputBuffer"%>





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청수정</title>
<script type="text/javascript">
	
	window.onload = function selectcategory1(){
		  var length = document.getElementById("category").options.length;
		  for(i = 0 ; i < length ; i ++){
			  if(document.getElementById("category").options[i].value == "<%=article.getCategory()%>
	") {
				document.getElementById("category").options[i].selected = true;
				break;
			}
		}
	}
</script>


<script type="text/javascript">
	function modifyboard() {
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

	<section id="writeForm">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">


				<h2>신청 수정</h2>
				<form action="regModifyPro.do" method="post" name="modifyform">
					<input type="hidden" name="reg_num"
						value="<%=article.getReg_num()%>" />
					<table class="table table-striped">
						<tr>
							<td>카테고리</td>
							<td class="td_right"><select id="category" name="category"
								onChange="selectcategory1()" size="1">
									<option value="펫산책">펫산책</option>
									<option value="러닝/마라톤">러닝/마라톤</option>
									<option value="성곽순례">성곽순례</option>
									<option value="등산">등산</option>
							</select></td>
						</tr>


						<tr>
							<td>제목</td>
							<td><input type="text" class="form-control" name="p_name"
								type="text" id="p_name" value="<%=article.getP_name()%>"
								required="required" /></td>
						</tr>


						<tr>
							<td>작성자</td>
							<td><input type="text" class="form-control" name="m_id"
								id="m_id" value="<%=article.getM_id()%>" required="required"
								readonly /></td>
						</tr>
						<tr>
							<td>이미지</td>
							<td><input type="file" name="image"
								id="image"></td>
						</tr>
					
						<tr>
							<td class="td_left"><label for="reg_content">내 용</label></td>
							<td><textarea id="reg_content" name="reg_content" cols="40"
									rows="15"><%=article.getReg_content()%></textarea></td>
						</tr>


					</table>
					<section id="commandCell">
						<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp; <a
							href="javascript:history.go(-1)">[뒤로]</a>
					</section>
				</form>

			</div>
		</div>
	</section>
	<script>
		//CKEDITOR 적용 
		CKEDITOR.replace('reg_content', {

			width : '100%',
			height : '350'

		});
	</script>


</body>
<jsp:include page="/adminHeader.jsp" />
<jsp:include page="/footer.jsp" />
</html>