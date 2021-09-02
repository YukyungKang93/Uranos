<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.MemberBean"%>
<%@ page import="vo.RegBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	RegBean article = (RegBean)request.getAttribute("article");
	String id = (String) session.getAttribute("M_ID");
	String name = (String) session.getAttribute("M_NAME");
	SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
	Date time = new Date();
	String time1 = format1.format(time);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로그램 신청 게시판</title>

<script type="text/javascript">

function selectcategory(){
 document.boardform.category.value = document.boardform.category.options[document.boardform.category.selectedIndex].value;
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



</style>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
 
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
 
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- CKEDITOR  -->
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>



</head>
<br>
<br>
<br>
<br>
<br>

<body>
<!-- 게시판 등록 -->	
	<section id="writeForm">
	<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
		<h2 class="text-center" >신청하기</h2>
		<form action="regWritePro.do" method="post" enctype="multipart/form-data" name="boardform">
		
			<table class="table table-striped">
			<input type = "hidden" name = "m_name" value = "<%=name%>"/>
				<tr>
					<td class="td_left"><label for="category">카테고리</label></td>
					<td class="td_right"><select id="category" name="category" onChange="selectcategory()" size="1">
						<option value="펫산책">펫산책</option>
						<option value="러닝/마라톤">러닝/마라톤</option>
						<option value="성곽순례">성곽순례</option>
						<option value="등산">등산</option>
					</select></td>
				</tr>
				
				
				
				<tr>
					<td class="td_left"><label for="p_name">제목</label></td>
					<td class="td_right"><input name="p_name" type="text" id="p_name" required="required" /></td>
				</tr>
				
				
				
				<tr>
					<td class="td_left"><label for="startdate">시작날짜</label></td>
					<td class="td_right"><input type="Date" name="startdate" id="startdate" required="required" min="<%=time1%>" max="2099-12-31" /></td>
				</tr>
				
				
				
				<tr>
					<td class="td_left"><label for="m_id">작성자</label></td>
					<td class="td_right"><input type="text" name="m_id" id="m_id" value ="<%=id%>" required="required" readonly/></td>
				</tr>
				
				
				
				
				<tr>
					<td class="td_left"><label for="total_number">인원수</label></td>
					<td class="td_right"><input type="number" name="total_number" id="total_number" required="required" /></td>
				</tr>
				
				
				
				<tr>
					<td class="td_left"><label for="image">파일 첨부</label></td>
					<td class="td_right"><input type="file" accept="image/*" name="image" id="image"></td>
				</tr>
				
				
				
				<tr>
                	<td>글내용</td>
                	<td><textarea rows="10" cols="50" name="reg_content"  class="form-control" ></textarea></td>
            	</tr>
				
				
				
			</table>
			
			
			
			
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp; 
				
				
				
				
				<%
					if (id.equals("admin")){
				%>
				<input type="button" value="글목록" onClick="location.href='regList.do'" /> 
				<% 
					}
				%>
			</section>
		</form>
	</div>
</div>		
	</section>
	<script>
//CKEDITOR 적용 
CKEDITOR.replace('reg_content', {
         
    width:'100%',
    height:'350'
         
});
 
</script>
	<br><br><br><br>
	<!-- 게시판 등록 -->
<% if(id.equals("admin")) { %>
<jsp:include page="/adminHeader.jsp" />
<% } else { %>
<jsp:include page="/header.jsp" />
<% } %>
<jsp:include page="/footer.jsp" />
</body>
</html>