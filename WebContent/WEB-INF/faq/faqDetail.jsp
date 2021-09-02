<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.Faq"%>
<%
	Faq article = (Faq) request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주하는 질문 게시판</title>
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

#articleImageArea {
	background: orange;
	margin-top: 10px;
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

	<section id="basicInfoArea">
		제 목 :
		<%=article.getF_title()%>
	</section>
	<section id="articleContentArea">
		<%=article.getF_content()%>
	</section>
	<section id="articleImageArea">
		<img src=upload/<%=article.getF_image()%>
			title="<%=article.getF_image()%>" alt="<%=article.getF_image()%>"
			width='400' height='300'>
	</section>
	<section id="commandList">
		<a href="faqList.do?page=<%=nowPage%>">[목록]</a>&nbsp;&nbsp;
	</section>
	<jsp:include page="/header.jsp" />
	<jsp:include page="/footer.jsp" />
</body>
</html>