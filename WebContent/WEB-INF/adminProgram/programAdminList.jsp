<%@page import="vo.PageInfo"%>
<%@page import="vo.ProgramBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	ArrayList<ProgramBean> programAdminList = (ArrayList<ProgramBean>) request.getAttribute("programAdminList");
	ArrayList<ProgramBean> searchList = (ArrayList<ProgramBean>) request.getAttribute("searchList");

	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");

	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();

	String s_category = (String) request.getParameter("category");
	String s_value = (String) request.getParameter("value");
	String s_searchText = (String) request.getParameter("searchText");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>프로그램 목록 관리</title>
<style type="text/css">
#registForm {
	width: 500px;
	height: 600px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 800px;
}

#tr_top {
	color: #FFFFF0;
	background: #444444;
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

#search {
	padding-top: 5px;
	padding-bottom: 5px;
}

#list {
	text-color: yellow;
}

#web_mgm #listForm {
	width: 800px;
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<script type="text/javascript">
	function clickRow() {
		var x = document.getElementById("abc");
		x.style.fontSize = "25px";
		x.style.color = "blue";
	}
</script>

<script type="text/javascript">
	function getType() {
		var s = document.getElementById('search_type');
		var type = s.options[s.selectedIndex].value;

		document.getElementById('value').value = "";
		document.getElementById('type').value = type;

	}
	function columnnameClicked() {

	}
</script>
<script type="text/javascript">
	function goData() {
		var form = document.userinput;
		form.submit();
	}
</script>
</head>
<br> <br>
<br> 
<body>
	<!-- 게시판 리스트 -->

	<section id="listForm">
		<h2>프로그램 목록 관리</h2>
		<br> 
	<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">

		<section id="search" border="1" align="center">
			


			<!--  검색 시작  -->
			<form action="programAdminList.do" method="post" name="userinput">
				<input type="radio" value="전체" onclick="goData()">전체
			</form>
			<form action="programSearch.do" method="get">
				<table border="0" cellpadding="0" cellspacing="0" width="700">
					<tr>
						<td align="center"><select id="category" name="category">

								<option value="categoryAll" id="type">카테고리</option>
								<option value="러닝/마라톤" name="marathon" id="marathon">러닝/마라톤</option>
								<option value="등산" id="hiking">등산</option>
								<option value="펫산책" id="petTour">펫산책</option>
								<option value="성곽순례">성곽순례</option>
						</select> <select id="value" name="item">
								<option value="itemAll">항목</option>
								<option value="p_name">프로그램명</option>
								<option value="startdate">날짜</option>
								<option value="count">남은인원</option>
								<option value="total_number">모집인원</option>
						</select> <input id="searchText" name="searchText" type="search"> <input
							type="submit" value="검색 "></td>
					</tr>

				</table>
			</form>

			<!-- 검색 종료  -->


		</section>




		<section>
			<table id="programList">
				<%
					if (programAdminList != null && listCount > 0) {
				%>

				<tr id="tr_top">
					<td>이미지</td>
					<td>번호</td>
					<td>카테고리</td>
					<td>프로그램명</td>
					<td>날짜</td>
					<td>남은인원</td>
					<td>모집인원</td>
				</tr>




				<%
					for (int i = 0; i < programAdminList.size(); i++) {
				%>
				<tr>

					<td>
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<img alt="<%=programAdminList.get(i).getImage()%>" title="<%=programAdminList.get(i).getImage()%>" src=upload/<%=programAdminList.get(i).getImage()%> width='100'
										height='80' />
								</div>
							</div>
						</div>
					<td align="center"><a
						href="programDetail.do?p_num=<%=programAdminList.get(i).getP_num()%>"><%=programAdminList.get(i).getP_num()%></a></td>

					<td align="center">
						<%
							if (programAdminList.get(i).getP_num() != 0) {
						%> <%
 	for (int a = 0; a <= programAdminList.get(i).getP_num() * 2; a++) {
 %>
						<%
							}
									} else {
									}
						%><a
						href="programDetail.do?p_num=<%=programAdminList.get(i).getP_num()%>">

							<%=programAdminList.get(i).getCategory()%>

					</a>
					</td>


					<td align="center"><a
						href="programDetail.do?p_num=<%=programAdminList.get(i).getP_num()%>"><%=programAdminList.get(i).getP_name()%></a></td>

					<td align="center"><a
						href="programDetail.do?p_num=<%=programAdminList.get(i).getP_num()%>"><%=programAdminList.get(i).getStartdate()%></a></td>
					<td align="center"><a
						href="programDetail.do?p_num=<%=programAdminList.get(i).getP_num()%>"><%=programAdminList.get(i).getCount()%></a></td>
					<td align="center"><a
						href="programDetail.do?p_num=<%=programAdminList.get(i).getP_num()%>"><%=programAdminList.get(i).getTotal_number()%></td>

				</tr>
				<%
					}
				%>


			</table>
		</section>






		<section id="pageList">
			<%
				if (nowPage < 5) {
			%>
			&nbsp;
			<%
				} else if (nowPage > 5) {
			%>
			<a href="programAdminList.do?page=<%=endPage - 5%>">[이전]</a>&nbsp;
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
			<a href="programAdminList.do?page=<%=a%>">[<%=a%>]
			</a>&nbsp;
			<%
				}
			%>
			<%
				}
			%>

			<%
				if (maxPage - startPage < 5) {
			%>
			<a href="programAdminList.do?page=1">[처음]</a>
			<%
				} else if (nowPage <= maxPage) {
			%>
			<a href="programAdminList.do?page=<%=startPage + 5%>">[다음]</a>
			<%
				} else if (nowPage + 5 >= maxPage) {
			%>
			<a href="programAdminList.do?page=<%=maxPage%>">[다음]</a>
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
	</div>	
	</div>	
	</section>
		<jsp:include page="/adminHeader.jsp" />
		<jsp:include page="/footer.jsp" />
</body>
</html>