<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@page import="vo.ProgramBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	ProgramBean program = (ProgramBean) request.getAttribute("program");
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그램 수정</title>


<script type="text/javascript">
	function modifyboard() {
		modifyform.submit();
	}
</script>

<script type="text/javascript">
	function selectcategory() {
		document.boardform.P_name.value = document.boardform.P_name.options[document.boardform.P_name.selectedIndex].value;
	}
</script>


<style type="text/css">
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

td{
	font-size: 13px;
}

table {
	margin: auto;
	width: 800px;
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
	

	<section id="writeForm">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<h2 class="text-center">프로그램 수정</h2>
				<br> <br>
				<form action="programModifyPro.do" method="post" name="modifyform"
					enctype="multipart/form-data">
					<input type="hidden" name="p_num" value="<%=program.getP_num()%>" />

					<table class="table table-striped" >

						<tr>
							<td>제목</td>
							<td><input type="text" class="form-control" name="p_name"
								type="text" id="p_name" value="<%=program.getP_name()%>"
								required="required" /></td>
						</tr>

						<tr>
							<td><label for="category">카테고리</td>
							<td class="td_right"><select id="category" name="category"
								onChange="selectcategory()" size="1">
									<option value="러닝/마라톤" selected="selected">러닝/마라톤</option>
									<option value="등산">등산</option>
									<option value="펫산책">펫산책</option>
									<option value="성곽순례">성곽순례</option>

							</select></td>
						</tr>


						<tr>
							<td class="td_left"><label for="content">내 용</label></td>
							<td class="td_right"><textarea id="content" name="content"
									value="<%=program.getContent()%>"
									style="text-align: left; width: 300px; height: 200px"></textarea>
							</td>
						</tr>


						<tr>
							<td class="td_left"><label for="content">이미지</label></td>
							<td class="td_right"><input type="file" id="file"
								name="file" value="<%=program.getImage()%>" /></td>
						</tr>


						<tr>
							<td class="td_left"><label for="startdate">시작일</label></td>
							<td class="td_right"><input type="date" id="startdate"
								name="startdate" value="${program.startdate}" /></td>
						</tr>
						
					
						<tr>
							<td>남은인원</td>
							<td><input type="number" class="form-control" id="count" 
								name="count" required
								value="<%=program.getCount()%>" /></td>
						</tr>


						<tr>
							<td>총인원</td>
							<td><input type="number" class="form-control" id="total_number"  
								name="total_number" required
								value="<%=program.getTotal_number()%>" /></td>
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
CKEDITOR.replace('content', {
         
    width:'100%',
    height:'350'
         
});
	
</script>
</body>
		<jsp:include page="/adminHeader.jsp" />
		<jsp:include page="/footer.jsp" />
</html>