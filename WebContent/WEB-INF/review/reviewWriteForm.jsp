<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  

    
<%@page import="java.sql.Connection"%>
<%@page import="org.apache.catalina.connector.OutputBuffer"%>
<%@ page import="vo.ReviewBean"%>

<%
	ReviewBean review = (ReviewBean)request.getAttribute("review");
	String id = (String) session.getAttribute("M_ID");
%>


<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Review 작성</title>

<script type="text/javascript">
	function selectcategory() {
		document.boardform.P_name.value = document.boardform.P_name.options[document.boardform.P_name.selectedIndex].value;
	}
</script>

<style type="text/css">
.star-input {
	padding: 25px 15px;
}

.star-input>.input {
	text-align: center;
	margin-top: 10px;
}

.star-input>.input, .star-input>.input>label:hover, .star-input>.input>input:focus+label,
	.star-input>.input>input:checked+label {
	display: inline-block;
	vertical-align: middle;
	background: url('img/star.png') no-repeat;
}

.star-input {
	width: 100%;
	text-align: center;
}

.star-input>.input {
	width: 150px;
	background-size: 150px;
	height: 28px;
	white-space: nowrap;
	overflow: hidden;
	position: relative;
}

.star-input>.input>input {
	position: absolute;
	width: 1px;
	height: 1px;
	opacity: 0;
}

star-input>.input.focus {
	outline: 1px dotted #ddd;
}

.star-input>.input>label {
	width: 30px;
	height: 0;
	padding: 28px 0 0 0;
	overflow: hidden;
	float: left;
	cursor: pointer;
	position: absolute;
	top: 0;
	left: 0;
}

.star-input>.input>label:hover, .star-input>.input>input:focus+label,
	.star-input>.input>input:checked+label {
	background-size: 150px;
	background-position: 0 bottom;
}

.star-input>.input>label:hover ~label {
	background-image: none;
}

.star-input>.input>label[for="p1_2"] {
	width: 15px;
	z-index: 10;
}

.star-input>.input>label[for="p1"] {
	width: 30px;
	z-index: 9;
}

.star-input>.input>label[for="p2_2"] {
	width: 45px;
	z-index: 8;
}

.star-input>.input>label[for="p2"] {
	width: 60px;
	z-index: 7;
}

.star-input>.input>label[for="p3_2"] {
	width: 75px;
	z-index: 6;
}

.star-input>.input>label[for="p3"] {
	width: 90px;
	z-index: 5;
}

.star-input>.input>label[for="p4_2"] {
	width: 105px;
	z-index: 4;
}

.star-input>.input>label[for="p4"] {
	width: 120px;
	z-index: 3;
}

.star-input>.input>label[for="p5_2"] {
	width: 135px;
	z-index: 2;
}

.star-input>.input>label[for="p5"] {
	width: 150px;
	z-index: 1;
}

.star-input>output {
	display: inline-block;
	width: 60px;
	font-size: 18px;
	text-align: right;
	vertical-align: middle;
}




#commandCell {
	text-align: center;
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





<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
 
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
 
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- CKEDITOR  -->
<script src="//cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>




</head>
<body>
 
 
<br>
<br>
<br>
<br> 
<br>
<br>
<br>
<br>

<section id="writeForm">
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">
        <h2 class="text-center" >후기 작성</h2>
        <form action="reviewWritePro.do" method="post"
			enctype="multipart/form-data" name="boardform">
			
          <table class="table table-striped">
            <tr>
                <td>ID</td>
               
				<td class="td_right"><input type="text" name="M_id" id="M_id" value="<%=id%>"
						required="required" readonly="readonly" /></td>	
						
						
			</tr>
            
            <tr>
				<td class="td_left"><label for="P_name">카테고리</label></td>
				<td class="td_right"><select id="P_name" name="P_name"
					onChange="selectcategory()" size="1">
						<option value="러닝/마라톤" selected="selected">러닝/마라톤</option>
						<option value="등산">등산</option>
						<option value="펫산책">펫산책</option>
						<option value="성곽순례">성곽순례</option>
						
					</select></td>
			</tr>
			
			
             <tr>
                <td>제목</td>
                <td><input type="text"  class="form-control" name="Title" type="text"
						id="Title" required="required" /></td>
            </tr>
            
            
          		 <tr>
					<td class="td_left"><label for="Image">이미지  </label></td>
					<td class="td_right"><input type="file" accept="image/*"
						name="Image" id="Image" /></td>
				</tr>
            
            
             
            <tr>
                <td>글내용</td>
                <td><textarea rows="10" cols="50" name="Rev_content"  class="form-control" ></textarea></td>
            </tr>
            
              <tr>
                <td>평점</td>
                <td><div class="star-input" align="left">
				<span class="input"> <input type="radio" name="Score"
					value="1" id="p1"> <label for="p1">1</label> <input
					type="radio" name="Score" value="0.5" id="p1_2"> <label
					for="p1_2">0.5</label> <input type="radio" name="Score" value="2"
					id="p2"> <label for="p2">2</label> <input type="radio"
					name="Score" value="1.5" id="p2_2"> <label for="p2_2">1.5</label>
					<input type="radio" name="Score" value="3" id="p3"> <label
					for="p3">3</label> <input type="radio" name="Score" value="2.5"
					id="p3_2"> <label for="p3_2">2.5</label> <input
					type="radio" name="Score" value="4" id="p4"> <label
					for="p4">4</label> <input type="radio" name="Score" value="3.5"
					id="p4_2"> <label for="p4_2">3.5</label> <input
					type="radio" name="Score" value="5" id="p5"> <label
					for="p5">5</label> <input type="radio" name="Score" value="4.5"
					id="p5_2"> <label for="p5_2">4.5</label>
				</span>
			</div></td>
            </tr>
            
          </table>
           <section id="commandCell">
				<input type="submit" value="등록" /> &nbsp;&nbsp; <input type="reset"
					value="다시쓰기" /> &nbsp;&nbsp; <input type="button" value="목록보기"
					onClick="window.location.href='reviewList.do'" />
			</section>
		
        </form>
    </div>
</div>
</section>

  
 
<script>
//CKEDITOR 적용 
CKEDITOR.replace('Rev_content', {
         
    width:'100%',
    height:'350'
         
});
 
</script>

</body>
 <jsp:include page="/header.jsp" />
<jsp:include page="/footer.jsp" />
</html>