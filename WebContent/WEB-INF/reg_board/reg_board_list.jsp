<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="vo.PageInfo"%>
<%@ page import="vo.RegBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	String id = (String) session.getAttribute("M_ID");
	ArrayList<RegBean> articleList = (ArrayList<RegBean>) request.getAttribute("articleList");
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
<meta charset="UTF-8" />
<title>신청 리스트</title>
<style type="text/css">
#registForm {
	width: 500px;
	height: 600px;
	border: 1px solid #dddddd;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 80%;
}

#tr_top {
	color: #FFFFF0;
	background: #444444;
	text-align: center;
}

#tr_body {
	text-align: center;
}

#pageList {
	margin: auto;
	width: 500px;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}

#btn {
	margin: auto;
	width: 100%;
	text-align: right;
}

#commandCell {
	margin: auto;
	width: 80%;
	text-align: right;
}

a, ahover {
	color: #000000;
	text-decoration: none;
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

</head>
<body>
	<br>
	<br>
	<br>
	<br>


	<!-- 게시판 리스트 -->
	<section id="listForm">
		<h2>
			프로그램 신청 목록 <br> <br>
		</h2>
	


		<div id="commandCell">
			<input type="button" value="글쓰기"
				onClick="location.href='regWriteForm.do'" />
		</div>
		<br>


		<table>
			<%
				if (articleList != null && listCount > 0) {
			%>
			<tr id="tr_top">
				<td>번호</td>
				<td>카테고리</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>승인요청</td>
			</tr>
			<%
				for (int i = 0; i < articleList.size(); i++) {
			%>
			<tr id="tr_body">
				<td><%=articleList.get(i).getReg_num()%></td>
				<td><%=articleList.get(i).getCategory()%></td>
				<td><a
					href="regDetail.do?reg_num=<%=articleList.get(i).getReg_num()%>&page=<%=nowPage%>">
						<%=articleList.get(i).getP_name()%></a></td>
				<td><%=articleList.get(i).getM_id()%></td>
				<td><%=articleList.get(i).getReg_date()%></td>
				<td><%=articleList.get(i).getReg_state()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br>

	</section>
	<section id="pageList">
		<%
			if (nowPage <= 1) {
		%>
		[이전]&nbsp;
		<%
			} else {
		%>
		<a href="regList.do?page=<%=nowPage - 1%>">[이전]</a>&nbsp;
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
		<a href="regList.do?page=<%=a%>">[<%=a%>]
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
		<a href="regList.do?page=<%=nowPage + 1%>">[다음]</a>
		<%
			}
		%>

	</section>
	<%
		} else {
	%>
	<section id="emptyArea">
		등록된 글이 없습니다.<br> <br>

	</section>
	<%
		}
	%>
</body>
<jsp:include page="/adminHeader.jsp" />
<jsp:include page="/footer.jsp" />
</html>