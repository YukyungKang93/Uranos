<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="vo.ProgramBean"%>
<%@page import="vo.P_replyBean"%>
<%@page import="vo.PageInfo"%>
<%@page import="dao.ProgramDAO"%>
<%@ page import="java.util.*"%>



<%
String user_id = (String) session.getAttribute("M_ID");

ArrayList<P_replyBean> replyList = (ArrayList<P_replyBean>) request.getAttribute("replyList");
	

	P_replyBean reply = (P_replyBean) request.getAttribute("reply");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 작성</title>
<script type="text/javascript">
function goBack() {
    window.history.back();
}
    </script>

</head>
	<body>
	<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			
            <form role="form" name="re_replyform" action="re_replyPro.do">
			<input type="hidden" value="<%=reply.getGroup_num()%>" name="group_num">
			<input type="hidden" value="<%=reply.getP_num()%>" name="p_num">
			<input type="hidden" value="<%=reply.getReply_num()%>" name="reply_num">
			<input type="hidden" value="<%=user_id%>" name="m_id">
			
					<div class="form-group">	 
					 <h3>댓글 작성</h3>
						<table>
	<tr>
		<td class="td_left">
			<input type = "text" name="m_id1" readonly id = "m_id1" value = "<%=reply.getM_id()%>"/>
		</td>
	<td class="td_right">
						<textarea id="con" name="content2" style="width: 100%"
							placeholder="<%=reply.getContent()%>" readonly rows="2"></textarea>
				</td>
				</tr>
				<tr>
		<td class="td_left">
			<input type = "text" readonly name="m_id2" id = "m_id" value = "<%=user_id%>"/><%--sessionid --%>
		</td>
		<td class="td_right">
		<textarea id="con2" name="content" style="width: 100%"
							placeholder="댓글을 입력해주세요" rows="2"></textarea>
					</td>
					</tr>
</table>
</div>
			<input type="submit" value ="등록" >
		<input type="button" value="취소" onClick="goBack()"> 
				
				</form>
		</div>
	</div>
</div>
</body>
</html>