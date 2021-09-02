<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="vo.P_replyBean"%>
<%
	P_replyBean reply = (P_replyBean)request.getAttribute("reply");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>댓글 수정</title>
<%String user_id = (String) session.getAttribute("M_ID");
 %>
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
			 <form role="form" name="replyModifyform" action="replyModifyPro.do">
			<input type="hidden" value="<%=reply.getGroup_num()%>" name="group_num">
			<input type="hidden" value="<%=reply.getP_num()%>" name="p_num">
			<input type="hidden" value="<%=reply.getReply_num()%>" name="reply_num">
			
				<div class="form-group">
					 <h3>댓글 수정</h3>
						<table>
	<tr>
		<td class="td_left">
			<label for = "m_id">작성자</label>
		</td>
		<td class="td_right">
			<input type = "text" name="m_id" readonly id = "m_id" value = "<%=reply.getM_id()%>"/>
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "reply_content">내 용</label>
		</td>
		<td>
		<textarea id="modi_content" name="content" style="width: 100%"
		placeholder="<%=reply.getContent()%>" rows="2"><%=reply.getContent()%></textarea></td>
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