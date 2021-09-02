<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.Faq"%>
<%
	Faq article = (Faq)request.getAttribute("article");
String nowPage = (String)request.getParameter("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>MVC 게시판</title>
<script type="text/javascript">
function modifyboard(){
	modifyform.submit();
}
</script>
<style>
   #registForm{
      width: 500px;
      height: 600px;
      border : 1px solid red; 
      margin:auto; 
   }   
   h2{
      text-align: center;
   }

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

				<h2 class="text-center">FAQ 수정</h2>
				

				<form action="faqModifyPro.do" 
					method="post" name="modifyform">
					<input type = "hidden" name = "f_num" value= "<%=article.getF_num()%>"/>
					<table class="table table-striped">

						<tr>
							<td>작성자</td>
							<td><input type="text" class="form-control" name="f_writer"
								id="f_writer" value="관리자" disabled /></td>
						</tr>


						<tr>
							<td>제목</td>
							<td><input type="text" class="form-control" name="f_title"
								type="text" id="f_title" value="<%=article.getF_title()%>"/></td>
						</tr>



						<tr>
							<td class="td_left"><label for="f_image">이미지</label></td>
							<td class="td_right"><input name="f_image" type="file"
								accept="image/*" id="f_image" multiple /></td>
						</tr>



						<tr>
							<td class="td_left"><label for="f_content">내 용</label></td>
							<td><textarea id="f_content" name="f_content" cols="50"
									rows="10" required="required"><%=article.getF_content()%></textarea></td>
						</tr>
					</table>
					
					<section id="commandCell">
						<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
						<a href="javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp; 
					</section>
				</form>
			</div>
		</div>
	</section>








	<script>
		//CKEDITOR 적용 
		CKEDITOR.replace('f_content', {

			width : '100%',
			height : '350'

		});
	</script>
	
</body>

<!-- 게시판 등록 -->
<jsp:include page="/adminHeader.jsp" />
<jsp:include page="/footer.jsp" />

</html>