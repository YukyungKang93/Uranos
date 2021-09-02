<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.Notice"%>
<%
	Notice article = (Notice)request.getAttribute("article");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style>
#articleForm {
	width: 500px;
	height: 500px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

#basicInfoArea {
	height: 40px;
	text-align: center;
}

#articleContentArea {
	background: orange;
	margin-top: 20px;
	height: 350px;
	text-align: center;
	overflow: auto;
}

#commandList {
	margin: auto;
	width: 500px;
	text-align: center;
}
</style>
</head>

<body>
	<section id="articleForm">
		<h2>게시글 상세보기</h2>
		<section id="basicInfoArea">
			제 목 :
			<%=article.getN_title()%>
		</section>
		<section id="articleContentArea">
			<%=article.getN_content() %>
		</section>
	</section>
	<section id="commandList">
 <a href="noticeList.do?page=<%=nowPage%>">[목록]</a>&nbsp;&nbsp;
	</section>
</body>
</html>