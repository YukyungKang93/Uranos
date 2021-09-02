<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.QnaBean"%>
<%@ page import="vo.Q_replyBean"%>
<%@ page import="vo.PageInfo"%>
<%@ page import="action.qnaAction.QnaReplyListAction"%>
<%@ page import="svc.qnaService.QnaReplyListService"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
	String id = (String) session.getAttribute("M_ID");
	String pw = (String) session.getAttribute("M_PW");
	String nowPage = (String) request.getAttribute("page");
	QnaBean article = (QnaBean) request.getAttribute("article");
	String qna_num = request.getParameter("qna_num");
	int qnanum = 1;
	if (qna_num != null) {
		qnanum = Integer.parseInt(qna_num);
	}

	QnaReplyListService messageListService = new QnaReplyListService();
	QnaReplyListAction viewData = messageListService.getMessageList(qnanum);
%>
<c:set var="viewData" value="<%=viewData%>" />





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>qna 게시판</title>
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
	width: 75%;
}

#commandList {
	text-align: center;
}

h3 {
	text-align: left;
}

#hight {
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

<script type="text/javascript">
	function confirmformboard() {
		deleteform.submit();
	}

	function deleteSuccess() {
		var del = confirm("삭제하시겠습니까?");

		if (del) {
			location.href="qnaDeletePro.do?qna_num="+<%=article.getQna_num()%>+"&page="+<%=nowPage%>;
		} else {
			alert("취소되었습니다.");
		}
	}
</script>

</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<section id="aa-product-category">
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="aa-product-catg-content">
						
						<div class="aa-product-catg-body">
							<h3>Q&amp;A 상세내용</h3>
							<div class="table-responsive">
								<table class="table">
									<tr>
										<th>글번호</th>
										<td><%=article.getQna_num()%></td>
									</tr>


									<tr>
										<th>제목</th>
										<td><%=article.getTitle()%></td>
									</tr>



									<tr>
										<th>작성자</th>
										<td><div><%=article.getM_id()%></div></td>
									</tr>

								</table>
							</div>
						</div>
						<section>
							<table class="table">
								<tr>
									<th>내용</th>
									<td><%=article.getQna_content()%></td>
								</tr>
							</table>
						</section>


					</div>
				</div>
			</div>
		</div>
	</section>


	<p id="co">comment</p>

	<section id="comment">
		<c:forEach var="message" items="${viewData.messageList}">
			<h5>&nbsp;&nbsp;&nbsp; 관리자 | ${message.reg_date}</h5>
			<br>
			<h5>&nbsp;&nbsp;&nbsp; ${message.content}</h5>
		</c:forEach>
	</section>


	<br>
	<form id="papajohns"
		action="qnaReplyPro.do?qna_num=<%=article.getQna_num()%>&m_id=<%=article.getM_id()%>"
		method="post">
		<section id="comment_form">
			<%
				if (id.equals("admin")) {
			%>
			<textarea id="con" name="content" style="width: 80%"
				placeholder="댓글을 입력해주세요" rows="4" required="required"></textarea><br>
			<button id="btn" type="submit">등록</button>
			<%
				}
			%>
		</section>

	</form>
	
		<div id="commandList">
			<%
				if (id.equals("admin")) {
			%>
			<a href="qnaModifyFormadmin.do?qna_num=<%=article.getQna_num()%>">[삭제]
			</a> <a href="qnaList.do">[목록]</a>&nbsp;&nbsp;
			<%
				} else {
			%>
			<a
				href="qnaModifyForm.do?qna_num=<%=article.getQna_num()%>&page=<%=nowPage%>">[수정]
			</a> <a href="#" onclick="deleteSuccess();">[삭제] </a> <a
				href="qnaList.do">[목록]</a>&nbsp;&nbsp;
			<%
				}
			%>
		</div>
		<br><br><br>
<%		
if(id.equals("admin")) { %>
<jsp:include page="/adminHeader.jsp" />
<% } else { %>
<jsp:include page="/header.jsp" />
<% } %>
<jsp:include page="/footer.jsp" />
</body>
</html>