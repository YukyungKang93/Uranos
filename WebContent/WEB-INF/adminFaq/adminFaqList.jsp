<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.Faq"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	ArrayList<Faq> articleList = (ArrayList<Faq>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>자주하는 질문</title>

<meta name="description"
	content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<style type="text/css">
h4 {
	text-align: center;
}

#box {
	width: 60%;
}

.pageList {
	align: center;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a.btn.btn-primary {

background-color: #eceeef;
    border-color: #e9ecef;
}
</style>
</head>

<body>



	<div id="box" class="container-fluid">
		<div class="row">
			<div class="col-md-12">


				<table class="table table-bordered">
					<section id="listForm">

						<%
							if (articleList != null && listCount > 0) {
						%>

						<br> <br> <br> <br>
						<h4>FAQ</h4>
						<div align="right">
							<input id="notibutton" type="button" value="작성하기"
								onClick="location.href='faqWriteForm.do'" />
						</div>

						<tr id="tr_top">

							<td>제목</td>
							<td>작성자</td>
						</tr>

						<%
							for (int i = 0; i < articleList.size(); i++) {
						%>

						<tr>
							<td><div class="container mt-12">
									<a class="btn btn-primary" data-toggle="collapse"
										data-target="#demo<%=i%>"><%=articleList.get(i).getF_title()%></a>
									<div id="demo<%=i%>" class="collapse">
										<%
											if (articleList.get(i).getF_image() != null) {
										%>
										<a
											href="faqModifyForm.do?f_num=<%=articleList.get(i).getF_num()%>&page=<%=nowPage%>"><pre><%=articleList.get(i).getF_content()%></pre></a>
										<img src=upload/<%=articleList.get(i).getF_image()%>
											title="<%=articleList.get(i).getF_image()%>"
											alt="<%=articleList.get(i).getF_image()%>" width='800'
											height='300'>
										<%
											} else {
										%>
										<a
											href="faqModifyForm.do?f_num=<%=articleList.get(i).getF_num()%>&page=<%=nowPage%>"><pre><%=articleList.get(i).getF_content()%></pre></a>
										<%
											}
										%>
									</div>
								</div></td>
							<td><%=articleList.get(i).getF_writer()%></td>
						</tr>
						<%
							}
						%>
					
				</table>
				</section>

				<section id="pageList" align="center">

					<%
						if (nowPage <= 1) {
					%>
					[이전]&nbsp;
					<%
						} else {
					%>
					<a href="faqList.do?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
					<%
						}
					%>

					<%
						for (int a = startPage; a <= endPage; a++) {
								if (a == nowPage) {
					%>
					[<%=a%>]
					<%
						} else {
					%>
					<a href="faqList.do?page=<%=a%>">[<%=a%>]
					</a>&nbsp;
					<%
						}
					%>
					<%
						}
					%>

					<%
						if (nowPage >= maxPage) {
					%>
					[다음]
					<%
						} else {
					%>
					<a href="faqList.do?page=<%=nowPage + 1%>">[다음]</a>
					<%
						}
					%>
				</section>
				<%
					} else {
				%>
				<section id="emptyArea">등록된 글이 없습니다.</section>
				<%
					}
				%>
				<script src="js/jquery.min.js"></script>
				<script src="js/bootstrap.min.js"></script>
				<script src="js/scripts.js"></script>
			</div>
		</div>
	</div>
	<jsp:include page="/adminHeader.jsp" />
	<jsp:include page="/footer.jsp" />
</body>
</html>