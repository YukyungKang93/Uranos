<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="vo.PageInfo"%>
<%@ page import="vo.QnaBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	String id = (String) session.getAttribute("M_ID");
	ArrayList<QnaBean> articleList = (ArrayList<QnaBean>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>



<!DOCTYPE html>
<html>
<head>




<script type="text/javascript">
	function warning() {
		alert("로그인을 해주세요");
	}
</script>
</head>
<body>
	<style>
.btn {
	align: center;
}

.aa-product-catg-body {
	align: center;
}

.container {
	font-size: 15px;
}

#pageList {
	font-size: 15px;
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





	<section id="aa-catg-head-banner">
		<div class="aa-catg-head-banner-area">
			<div class="container"></div>
		</div>
	</section>




	<section id="aa-product-category" align="left">
		<div class="container">

			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">

					<div class="aa-product-catg-content">

						<div class="aa-product-catg-body">
							<h2>Q&amp;A 게시판</h2>
							<br>
							<div id="commandCell" align="right">
								<%
									if (id != null && !id.equals("admin")) {
								%>
								<input type="button" value="글쓰기" class="readmore"
									onClick="location.href='qnaWriteForm.do'" />
								<%
									}
								%>
							</div>
							<br> <br>

							<div class="table-responsive">
								<table class="table">
									<%
										if (articleList != null && listCount > 0) {
									%>

									<tr>
										<td>번호</td>
										<td>제목</td>
										<td>작성자</td>
										<td>날짜</td>
										<td>조회수</td>
									</tr>

									<%
										if (id == null) {
									%>
									<%
										for (int i = 0; i < articleList.size(); i++) {
									%>


									<tr>
										<td id="td_body"><%=articleList.get(i).getQna_num()%></td>
										<td id="td_body"><a
											href="url.do?URL=qnaList.do?page=<%=nowPage%>"
											onclick="warning();"> <%=articleList.get(i).getTitle()%></a></td>
										<td id="td_body"><%=articleList.get(i).getM_id()%></td>
										<td id="td_body"><%=articleList.get(i).getQna_date()%></td>
										<td id="td_body"><%=articleList.get(i).getQna_readcount()%></td>
									</tr>

									<%
										}
									%>
									<%
										} else if (id.equals("admin")) {
									%>
									<%
										for (int i = 0; i < articleList.size(); i++) {
									%>
									<tr>
										<td id="td_body"><%=articleList.get(i).getQna_num()%></td>
										<td id="td_body"><a
											href="qnaDetail.do?qna_num=<%=articleList.get(i).getQna_num()%>&page=<%=nowPage%>">
												<%=articleList.get(i).getTitle()%></a></td>
										<td id="td_body"><%=articleList.get(i).getM_id()%></td>
										<td id="td_body"><%=articleList.get(i).getQna_date()%></td>
										<td id="td_body"><%=articleList.get(i).getQna_readcount()%></td>
									</tr>
									<%
										}
									%>
									<%
										} else {
									%>
									<%
										for (int i = 0; i < articleList.size(); i++) {
									%>
									<tr>
										<td id="td_body"><%=articleList.get(i).getQna_num()%></td>
										<td id="td_body">
										<a href="qna_board_viewPw.do?qna_num=<%=articleList.get(i).getQna_num()%>&page=<%=nowPage%>">
											<%=articleList.get(i).getTitle()%></a></td>
										<td id="td_body"><%=articleList.get(i).getM_id()%></td>
										<td id="td_body"><%=articleList.get(i).getQna_date()%></td>
										<td id="td_body"><%=articleList.get(i).getQna_readcount()%></td>
									</tr>
									<%
										}
									%>
									<%
										}
									%>
								</table>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>


	</section>


	<section id="pageList" align="center">
		<%
			if (nowPage <= 1) {
		%>
		[이전]&nbsp;
		<%
			} else {
		%>
		<a href="qnaList.do?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
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
		<a href="qnaList.do?page=<%=a%>">[<%=a%>]
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
		<a href="qnaList.do?page=<%=nowPage + 1%>">[다음]</a>
		<%
			}
		%>
	</section>
	<%
		} else {
	%>
	<section id="emptyArea">
		등록된 글이 없습니다.<br> <br>
		<section id="btn">
			<%
				if (id != null) {
			%>
			<input type="button" value="글쓰기"
				onClick="location.href='qnaWriteForm.do'" />
			<%
				}
			%>
		</section>
	</section>
	<%
		}
	%>


	<!-- / product category -->
<% if(id == null){ %>
<jsp:include page="/header.jsp" />
<% } else if(id.equals("admin")) { %>
<jsp:include page="/adminHeader.jsp" />
<% } else { %>
<jsp:include page="/header.jsp" />
<% } %>
<jsp:include page="/footer.jsp" />

</body>
</html>