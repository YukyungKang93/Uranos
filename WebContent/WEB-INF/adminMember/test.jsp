<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="vo.MemberBean" %>
<%@ page import="vo.MemberListBean" %>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="dao.MemberDAO"%>
<%@ page import="vo.MemberPageInfo"%>

<%

boolean a = session.getAttribute("data") != null;

MemberBean dataBean = (MemberBean)session.getAttribute("data");

MemberPageInfo listPage = new MemberPageInfo(dataBean.getCount(), dataBean.getCurrent_page());
listPage = (MemberPageInfo)session.getAttribute("list");
	
ArrayList<MemberListBean> dataList = dataBean.getData();

%>
<!DOCTYPE html>
<html>
<head>

<!-- jQuery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- bootstrap JS -->
<script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

<!-- bootstrap CSS -->
<link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">

<style>
h1 {
	text-align: center;
}

fieldset {
	margin: auto;
	width: 950px;
}

#web_mgm {
	position: absolute;
	width: 100%;
	height: 100%;
	z-index: 100;
}

#web_mgm #search {
	margin: auto;
	width: 100%;
	height: 20px;
}

#web_mgm #listForm {
	width: 1500px;
}

#updateForm {
	background-color: white;
}

#web_mgm #updateForm {
	position: absolute;
	width: 950px;
	z-index: 100000;
	left: -100%;
	bottom: 300px;
	margin: auto;
}

table, th, td {
	border: 2px solid ;
}

table {
	width: 100%;
}

td {
	width: 50px;
	height: 50px;
}

table {
	border-collapse: collapse;
}

#web_mgm.shadow {
	box-shadow: 5px 5px 10px;
}

#web_mgm #listForm.level0 {
	position: absolute;
	width: 950px;
	left: 0;
}

#web_mgm #updateForm.level0 {
	position: absolute;
	width: 90%;
	height: 50%;
	left: 11px;
	margin: auto;
}

#web_mgm #updateForm.level1 {
	position: absolute;
	width: 90%;
	height: 50%;
	left: -100%;
	
}
</style>

<style type="text/css">
.div2 {
        border: 1px solid;
        align-items: center;
        justify-content: center;
      }

.clicked {
  color: ;
}
</style>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   <script>
       function sample6_execDaumPostcode() {
           new daum.Postcode({
               oncomplete: function(data) {

                   var addr = ''; 
                   var extraAddr = ''; 
   
                   if (data.userSelectedType === 'R') {
                       addr = data.roadAddress;
                   } else { 
                       addr = data.jibunAddress;
                   }
   
                   if(data.userSelectedType === 'R'){

                       if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                           extraAddr += data.bname;
                       }
                       if(data.buildingName !== '' && data.apartment === 'Y'){
                           extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                       }
                       if(extraAddr !== ''){
                           extraAddr = ' (' + extraAddr + ')';
                       }
                       document.getElementById("sample6_extraAddress").value = extraAddr;
                   
                   } else {
                       document.getElementById("sample6_extraAddress").value = '';
                   }
   
                   document.getElementById('sample6_postcode').value = data.zonecode;
                   document.getElementById("sample6_address").value = addr;
                   document.getElementById("sample6_detailAddress").focus();
               }
           }).open();
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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {
		$(".dblclick").click(function() {
			var div2 = document.getElementsByClassName("div2");

	        function handleClick(event) {
	          console.log(event.target);
	          // console.log(this);
	          // 콘솔창을 보면 둘다 동일한 값이 나온다

	          console.log(event.target.classList);

	          if (event.target.classList[1] === "clicked") {
	            event.target.classList.remove("clicked");
	          } else {
	            for (var i = 0; i < div2.length; i++) {
	              div2[i].classList.remove("clicked");
	            }

	            event.target.classList.add("clicked");
	          }
	        }

	        function init() {
	          for (var i = 0; i < div2.length; i++) {
	            div2[i].addEventListener("click", handleClick);
	          }
	        }

	        init();

			var str = ""
			var tdArr = new Array();	// 배열 선언
			var checkBtn = $(this);			
			var tdindex = this.cellIndex+1;
	        var trindex = $('tr').index($(this).parent())+1;
			
			// checkBtn.parent() : checkBtn의 부모는 <td>이다.
			// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkBtn.parent();
			var td = tr.children();
			
			console.log("클릭한 Row의 모든 데이터 : "+td.text());
			
			var id = td.eq(0).text();
			
			location.href="adminMemberDetail.do?check=" + id + "&type=정보수정";
			
		 });
		$(".updateForm").click(function() {
			var id = $(this).attr("data-rol");
			$("#updateForm").removeClass("level1");
			$("#updateForm").addClass("level0");
		});
		$(".formClear").click(function() {
			$("#updateForm").removeClass("level0");
			$("#updateForm").addClass("level1");
		});
	});
</script>

<script type="text/javascript">
	function arrange() {
	}
</script>

<script type="text/javascript">
	function clickRow(){
		var x=document.getElementById("abc");
	    x.style.fontSize = "25px";           
	    x.style.color = "blue";
	}
</script>

<style>
	.topFixBanner {text-align: left; background-color: #ffffff; padding: 20px 0px 20px 20px; width: 100%; border-bottom:#666666 solid 2px; }

    .topFixBannerFixed {position: fixed; top: 0px; text-align:left; padding-left:20px; }

</style>

<title>같이가치</title>
</head>
		
<body>
 <jsp:include page="/adminHeader.jsp" />
	<div id="web_mgm" ><br><br>
		<h1>회원관리</h1>
		<fieldset>
			<form action="testaction.do" name="searchForm">
			<div>
				<div id="search" style="width:600px; height:150px;float: left;" >
					<span>검색어</span> <select id="search_type" onclick="getType()">
						<option id="ALL" value="ALL">전체</option>
						<option id="m_id" value="M_ID">아이디</option>
						<option id="name" value="M_NAME">이름</option>
						<option id="email" value="EMAIL">이메일</option>
					</select> 
					<input type="hidden" id="type" name="type" value=""> 
					<input type="text" id="value" name="value" value="" size="50"> 
					<input type="submit" value="검색">
				</div>

			</div>				
			</form>
					
		</fieldset>


		<fieldset id="listForm" class="level1">
			<form action="" method="POST">
				<%
					HttpSession Session = request.getSession(true);
				%>
				<div class="row" >
				<table id="example-table-1" class="table table-bordered table-hover text-center">
					<tr>
						<th onclick="alert('아이디')" style="text-align: center; width: 9%"><input type="submit" value="아이디"  style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="idupdown" value="0"></th>
						<th onclick="alert('이름')" style="text-align: center; width: 6%"><input type="submit" value="이름"  style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="nameupdown" value="0"></th>
						<th onclick="alert('주소')" style="text-align: center; border-right: none; width: 23%"><input type="submit" value="주소"  style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="addrupdown" value="0"></th>
						<th onclick="alert('상세 주소')" style="text-align: center; width: 8%"><input type="submit" value="상세주소"  style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="addrupdown" value="0"></th>
						<th onclick="alert('참고 사항')" style="text-align: center; border-left:none; width: 5%"><input type="submit" value="참고 주소"  style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="addrupdown" value="0"></th>
						<th onclick="alert('이메일')" style="text-align: center; width: 16%"><input type="submit" value="이메일"  style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="emailupdown" value="0"></th>
						<th onclick="alert('전화번호')" style="text-align: center; width: 9%"><input type="submit" value="전화번호"  style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="phoneupdown" value="0"></th>	
						<th onclick="alert('성별')" style="text-align: center; width: 5%"><input type="submit" value="성별" style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="genderupdown" value="0"></th>
						<th onclick="alert('생일')" style="text-align: center; width: 7%"><input type="submit" value="생일" style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="birthupdown" value="0"></th>
						<th onclick="alert('생성일자')" style="text-align: center; width: 13%"><input type="submit" value="생성일자" style="background-color:transparent;  border:0px transparent solid;">
							<input type="hidden" id="regdateupdown" value="0"></th>
					</tr>
					
					<%						
					
					
					
					if(dataBean.getCount() == 0){
							}else{
								out.println(dataBean.getCount() + "개의 항목이 검색 되었습니다.");
							}
							
							
							
							int current_page = dataBean.getCurrent_page();
							
							int showcount = dataBean.getShow_count();
							
							if(session.getAttribute("data") != null){
							if(listPage.hasResult()){
								for (int i = 0; i < showcount; i++) {
					%>
										
					<tr  class="checkBtn div2">
						<td class="dblclick" data-rol="menu1"><%=dataList.get(i).getM_ID()%></td>
						<td class="dblclick" data-rol="menu1"><%=dataList.get(i).getM_NAME()%></td>
						<td class="dblclick" data-rol="menu1" style="border-right:none"><%=dataList.get(i).getADDR()%></td>
						<td class="dblclick" data-rol="menu1"><%=dataList.get(i).getDETAIL_ADDR()%></td>
						<td class="dblclick" data-rol="menu1" style="border-left:none"><%=dataList.get(i).getREF_ADDR()%></td>
						<td class="dblclick" data-rol="menu1"><%=dataList.get(i).getEMAIL()%></td>
						<td class="dblclick" data-rol="menu1"><%=dataList.get(i).getPHONE()%></td>
						<td class="dblclick" data-rol="menu1"><%=dataList.get(i).getGENDER()%></td>
						<td class="dblclick" data-rol="menu1"><%=dataList.get(i).getBIRTH()%></td>
						<td class="dblclick" data-rol="menu1"><%=dataList.get(i).getREGDATE()%></td>
					</tr>
					
					<%
							}
						}
						else{
							%>
						<tr>
							<td colspan="10"><center>검색결과가 없습니다.</center></td>
						</tr>
						<%				
						}
					}else{
							%>
					<tr>
							<td colspan="10"><center>검색결과가 없습니다.</center></td>
					</tr>
					<%				
						}
					
							
				    //int num = Integer.parseInt(Num);
				    //out.println("value: " + num);
					%>
				</table>
				<div class="col-lg-12" id="ex2_Result1" ></div> 
				<div class="col-lg-12" id="ex2_Result2" ></div>
				</div>
				<div>
			<%
				int count = dataBean.getCount()%10;	// totalpage
				int start_page = listPage.getStart_page();
				int end_page = listPage.getEnd_page();
				int max_page = listPage.getTotal_page();
				int now_page = listPage.getCurrent_page();
				MemberBean bean = (MemberBean)session.getAttribute("data");
				String type = bean.get_search();
				String value = bean.get_value();
				
				if(start_page == 0){
					
				}else{
				
				if(now_page > 5 && end_page <= max_page){
			%>
				<a href="testaction.do?page=<%= start_page - 5 %>&type=<%=type%>&value=<%=value%>">[이전]</a>
			<%
				}
				for(int i = start_page; i <= end_page; i++){
					if(i == now_page){
			%>	
				[<%=i%>]
			<%
					}else{
			%>
			<a href="testaction.do?page=<%=i %>&type=<%=type%>&value=<%=value%>">[<%= i %>]</a>
			<%						
					}
				}
			if(end_page < max_page && max_page > 5){
					if(now_page <= max_page){
			%>
				<a href="testaction.do?page=<%= start_page + 5 %>&type=<%=type%>&value=<%=value%>">[다음]</a>
			<%				
					}else if(now_page + 5 >= max_page){
			%>
				<a href="testaction.do?page=<%= max_page %>&type=<%=type%>&value=<%=value%>">[다음]</a>
			<%			
					}
				}					
			}
			%>

		</div>
			</form>
		</fieldset>
	</div>
	
</body>
</html>