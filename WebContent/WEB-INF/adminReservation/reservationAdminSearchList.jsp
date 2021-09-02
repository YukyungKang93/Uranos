<%@page import="vo.PageInfo"%>
<%@page import="vo.Reservation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	ArrayList<Reservation> reservationSearchList =(ArrayList<Reservation>)request.getAttribute("reservationSearchList");
	ArrayList<Reservation> searchList=(ArrayList<Reservation>)request.getAttribute("searchList");
	
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	
    int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
	
	String s_item = (String)request.getParameter("item");
	String s_searchText = (String)request.getParameter("searchText");
%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>예약현황관리</title>
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

a:visited {color: black; 
text-decoration: none;
}

</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">
	function clickRow(){
		var x=document.getElementById("abc");
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
function goData(){
    var form = document.userinput;   
    form.submit();
}
</script>

</head>

<body>
	<!-- 게시판 리스트 -->

	<section id="listForm"><br><br><br>
		<h2>
			예약현황 
		</h2>
<br>
<br>
	<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">


<section id="search" border="1" align="center">
	

<!--  검색 시작  --><form action="reservationList.do" method="post" name="userinput">
				<input type="radio" value="전체" onclick="goData()">전체
				</form>
		<form action="reservationSearch.do" method="get">
			<table border="0" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="center">						
						<select id="value" name="item">				
							<option value="itemAll">항목</option>
							<option value="p_num">프로그램번호</option>
							<option value="p_name">프로그램제목</option>
							<option value="m_id">아이디</option>
							<option value="m_name">고객명</option>
							<option value="startdate">시작일</option>
							<option value="resdate">예약일</option>							
						</select>
						<input id="searchText" name="searchText" type="search">
						<input type="submit" value="검색 ">
						
					</td>
				</tr>	
			
			</table>
		</form>	
   				
		<!-- 검색 종료  -->
	

</section>	


<section>
			<table id="reservationList">
				<%
					if (reservationSearchList != null && listCount > 0) {
				%>

				<tr id="tr_top">

					<td>프로그램번호</td>
					<td>프로그램제목</td>
					<td>아이디</td>
					<td>고객명</td>
					<td>시작일</td>
					<td>예약날짜</td>
				</tr>
				<%
					for (int i = 0; i < reservationSearchList.size(); i++) {
				%>
				<tr>	
					<td align="center"><a
						href="programDetail.do?p_num=<%=reservationSearchList.get(i).getP_num()%>"><%=reservationSearchList.get(i).getP_num()%></a></td>

					<td align="center">
						<%
							if (reservationSearchList.get(i).getRes_num() != 0) {
						%> <%
 	for (int a = 0; a <= reservationSearchList.get(i).getRes_num() * 2; a++) {
 %>
						<%
							}
									} else {
									}
						%>
						<a href="programDetail.do?p_num=<%=reservationSearchList.get(i).getP_num()%>">
							<%=reservationSearchList.get(i).getP_name()%></a>
					</td>


					<td align="center"><a
						href="programDetail.do?p_num=<%=reservationSearchList.get(i).getP_num()%>"><%=reservationSearchList.get(i).getM_id()%></a></td>

					<td align="center"><a
						href="programDetail.do?p_num=<%=reservationSearchList.get(i).getP_num()%>"><%=reservationSearchList.get(i).getM_name()%></a></td>
					<td align="center"><a
						href="programDetail.do?p_num=<%=reservationSearchList.get(i).getP_num()%>"><%=reservationSearchList.get(i).getStartdate()%></a></td>
					<td align="center"><a
						href="programDetail.do?p_num=<%=reservationSearchList.get(i).getP_num()%>"><%=reservationSearchList.get(i).getResdate()%></a></td>

				</tr>
				<%
					}
				%>


			</table>
		</section>



	


	<section id="pageList">
		<%if(nowPage<5){ %>
		&nbsp;
		<%}else if (nowPage>5){ %>
		<a href="reservationSearch.do?item=<%=s_item %>&searchText=<%=s_searchText %>&page
=<%=endPage-5%>">[이전]</a>&nbsp;
		<%} %>

		<%for(int a=startPage;a<=endPage;a++){
				if(a==nowPage){%>
		[<%=a %>]
		<%}else{ %>
		<a href="reservationSearch.do?item=<%=s_item %>&searchText=<%=s_searchText %>&page
=<%=a %>">[<%=a %>]
		</a>&nbsp;
		<%} %>
		<%} %>
		
		<%if(maxPage-startPage<5){ %>
		<a href="reservationSearch.do?item=<%=s_item %>&searchText=<%=s_searchText %>&page
=1">[처음]</a>
		<%}else if (nowPage<=maxPage){ %>
		<a href="reservationSearch.do?item=<%=s_item %>&searchText=<%=s_searchText %>&page
=<%=startPage+5%>">[다음]</a>
		<%} else if (nowPage+5>=maxPage) {%>
		<a href="reservationSearch.do?item=<%=s_item %>&searchText=<%=s_searchText %>&page
=<%=maxPage%>">[다음]</a>
		<%} %>
	</section>
	<%
    }
	else
	{
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