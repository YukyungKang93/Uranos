<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.P_replyBean"%>

<%
	P_replyBean reply = (P_replyBean) request.getAttribute("reply");
	String user_id = (String) session.getAttribute("M_ID");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>

<script type="text/javascript">
	function goBack() {
		window.history.back();
	}
</script>

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

<style type="text/css">
#registForm {
	width: 500px;
	height: 400px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

h3 {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

#commandCell {
	text-align: center;
}

.m_id1 {
	border: 0px;
}

#replyForm {
	width: 450px;
	height: 150px;
	margin: auto;
	border: 10px solid orange;
	align: center;
	padding-top: 25px;
	padding-bottom: 25px;
	border-radius: 100px;
}

#rep {
	width: 100%;
	align: center;
}

#mimi {
	padding-left: 50px;
	padding-right: 50px;
	text-align: center;
	margin: auto;
}

con2 {
	padding-right: 50px;
	width: 100%;
}

.rereplForm {
	width: 60%;
}

#papajohns {
	width: 50%;
	height: 79%;
	margin: auto;
	border: 10px solid orange;
	align: center;
	font-size: 15px;
	border-radius: 100px;
}

p {
	text-align: left;
}
</style>




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


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">

				<form id="papajohns" role="form" name="replyModifyPro" action="replyModifyPro.do">

					<input type="hidden" value="<%=reply.getGroup_num()%>"
						name="group_num"> <input type="hidden"
						value="<%=reply.getP_num()%>" name="p_num"> <input
						type="hidden" value="<%=reply.getReply_num()%>" name="reply_num">

					<div class="form-group">

						<section id="rereplForm" class="table table-striped">
							<h3 class="jg">댓글 수정</h3>

							<tr>
								<p class="jg">작성자:</p>
								<td><input type="text" readonly name="m_id" readonly
									id="m_id" value="<%=reply.getM_id()%>" /></td>
							</tr>



							<%--sessionid --%>

							
							<textarea id="con2" name="content" style="width: 100% " 
									placeholder="<%=reply.getContent()%>"required="required"></textarea>

							<br>

							<section id="commandCell">
								<input type="submit" value="등록" class="readmore"> <input
									type="button" value="취소" onClick="goBack()" class="readmore">

							</section>
					</div>

				</form>
			</div>
		</div>



	</div>
</body>
<jsp:include page="/header.jsp" />
<jsp:include page="/footer.jsp" />
</html>