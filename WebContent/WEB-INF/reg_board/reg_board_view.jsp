<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="vo.RegBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="vo.PageInfo"%>
<%@ page import="java.util.*"%>




<%
	RegBean article = (RegBean) request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
	String userId = (String) session.getAttribute("M_ID");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
	function confirmformboard() {
		confirmform.submit();
	}
</script>

<title>신청 상세보기</title>
<style type="text/css">
#comment {
	width: 45%;
	border: 1px solid #aaaaaa;
	margin: auto;
}

#articleForm {
	width: 80%;
	height: 60%;
	border: 1px solid #aaaaaa;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 100%;
}

.td_left {
	width: 150px;
	background: #eeeeee;
	text-align: center;
}

.td_right {
	width: 300px;
	background: #dddddd;
	text-align: center;
}

#articleContentArea {
	background: white;
	margin-top: 20px;
	height: 200px;
	text-align: left;
	overflow: auto;
}

#articleImageArea {
	background: white;
	overflow: auto;
	margin-top: 10px;
	text-align: center;
}

#replyContentArea {
	background: white;
	margin-top: 20px;
	height: 20px;
	text-align: left;
	overflow: auto;
}

#tr_top {
	background: #eeeeee;
	text-align: center;
}

#comment_form {
	text-align: center;
	width: 500px;
}

#commandList {
	text-align: center;
}

h3 {
	text-align: left;
}

#height {
	height: 50px;
}

#right {
	text-align: center;
}

#left {
	text-align: center;
}

#center {
	width: 60%;
}

#id {
	text-align: left;
}

#btn {
	width: 50px;
	height: 30px;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

#co {
	text-align: center;
}

#papajohns {
	padding-left: 20%;
}
</style>



</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<form action="regConfirmPro.do" method="post" name="confirmform">
			<input type="hidden" name="reg_num" value="<%=article.getReg_num()%>" />
			<input type="hidden" name="category" value="<%=article.getCategory()%>" />
			<input type="hidden" name="p_name" value="<%=article.getP_name()%>" />
			<input type="hidden" name="startdate" value="<%=article.getStartdate()%>" />
			<input type="hidden" name="m_id" value="<%=article.getM_id()%>" />
			<input type="hidden" name="total_number" value="<%=article.getTotal_number()%>" />
			<input type="hidden" name="reg_content" value="<%=article.getReg_content()%>" />
			<input type="hidden" name="image" value="<%=article.getImage()%>" />
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="aa-product-catg-content">

						<div class="aa-product-catg-body">
							<h3>신청 상세보기</h3>
							<div class="table-responsive">

								<table class="table">
									<tr>
										<th>제목</th>
										<td><%=article.getP_name()%></td>
									</tr>

									<tr>
										<th>작성자</th>
										<td><div><%=article.getM_id()%></div></td>
									</tr>

									<tr>
										<th>인원수</th>
										<td><%=article.getTotal_number()%></td>
									</tr>

									<tr>
										<th>시작날짜</th>
										<td><%=article.getStartdate()%></td>
									</tr>

									<tr>
										<th>승인상태</th>
										<td><%=article.getReg_state()%></td>
									</tr>


								</table>
							</div>
						</div>
						<section>
							<table class="table">
								<tr>
									<th>내용</th>
									<td><%=article.getReg_content()%></td>
								</tr>
							</table>
						</section>


						<section id="articleImageArea">
							<img src=upload/<%=article.getImage()%>
								title="<%=article.getImage()%>" alt="<%=article.getImage()%>"
								width='400' height='300'>
						</section>


					</div>
				</div>
			</div>
		</div>
		</form>
		<section id="commandList">
			<c:set value="<%=article.getM_id()%>" var="m_id"></c:set>
			<c:set value="<%=userId%>" var="userId"></c:set>
			<c:choose>
				<c:when test="${'admin' eq userId}">
					<a href="regModifyForm.do?reg_num=<%=article.getReg_num()%>">[수정]</a>
					<a href="javascript:confirmformboard()">[승인]</a>
					<a href="regList.do">[목록]</a>&nbsp;&nbsp;
			</c:when>
				<c:when test="${m_id eq userId}">
					<a href="regModifyForm.do?reg_num=<%=article.getReg_num()%>">[수정]</a>
					<a href="regList.do">[목록]</a>&nbsp;&nbsp;
			</c:when>
				<c:otherwise>
					<a href="regList.do">[목록]</a>&nbsp;&nbsp;
			</c:otherwise>
			</c:choose>
		</section>

		</form>
</body>
<jsp:include page="/adminHeader.jsp" />
<jsp:include page="/footer.jsp" />
</html>