<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../common/common.jsp" %>
<!--  travleInsertForm.jsp -->

<style type="text/css">
	body{
		background-color : #FFEFEF;
	}
	
	table{
		background-color : white;
		border : 1px solid;
		border-collapse: collapse;
		width : 60%;
		height : 50%;
		margin : auto;
	}
	
	th {
		background-color: #FFB9B9;
	}
	
	tr, td, th {
		border : 1px solid;
	}
	
	h2{
		text-align : center;
	}
	
	.err{
		font-size : 9pt;
		color : red;
		font-weight : bold;
	}
</style>

<script type="text/javascript" src = "resources/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//alert("ready");
		
		$("#name_check").click(function(){
			//alert("name check");
			
			$.ajax({
				url : "name_check_proc.tv",
				type : "post",
				data : ({
					checkname : $('input[name="name"]').val()
				}),
				success : function(data){
					//alert("data : " + data);
				}
			});
		});
		
		$('input[name="name"]').keydown(function(){
			alert("keydown");
		})
		
	});
	
	function doList(){
		location.href = "list.tv";
	}
</script>

<%
	String [] area = {"유럽", "동남아", "일본", "중국"};
	String [] style = {"패키지", "크루즈", "자유여행", "골프여행"};
	String [] price = {"100~200", "200~300", "300~400", "400~500"};
%>

<h2>Travel Information Register</h2>
<form:form commandName = "travel" action = "insert.tv" method = "post">
	<table>
		<tr>
			<th>NAME</th>
			<td>
				<input type = "text" name = "name" value = "${travel.name }">
				<input type = "button" value = "중복 체크" id = "name_check">
				<span id = "name_message"></span>
				<form:errors path = "name" cssClass = "err"/>
			</td>
		</tr>
		
		<tr>
			<th>AGE</th>
			<td>
				<input type = "text" name = "age" value = "${travel.age }"> <form:errors path = "age" cssClass = "err"/>
			</td>
		</tr>
		
		<tr>
			<th>AREA</th>
			<td>
					<c:set var = "area" value = "<%=area %>"/>
					<c:forEach var = "i" begin = "0" end = "${fn:length(area) -1 }">
						<input type = "checkbox" name = "area" value = "${area[i] }" <c:if test = "${fn:contains(travel.area, area[i]) }">checked</c:if>>${area[i] }
					</c:forEach>
					<form:errors path = "area" cssClass = "err"/>
			</td>
		</tr>
		
		<tr>
			<th>STYLE</th>
			<td>
		 		<c:set var = "style" value = "<%=style %>"/>
				<c:forEach var = "i" begin = "0" end = "${fn:length(style) -1 }">
					<input type = "radio" name = "style" value = "${style[i] }"  <c:if test = "${travel.style eq style[i] }">checked</c:if>>${style[i] }
				</c:forEach> 
				<form:errors path = "style" cssClass = "err"/>
			</td>
		</tr>
		
		<tr>
			<th>PRICE</th>
			<td>
				<select name = "price">
					<option value = ""> 선택하세요 </option>
					
					<c:set var = "price" value = "<%=price %>"/>
				 		<c:forEach var = "i" begin = "0" end = "${fn:length(price) -1 }">
							<option value = "${price[i] }" <c:if test = "${travel.price eq price[i] }">selected</c:if> >${price[i] } </option>
						</c:forEach> 
				</select>
				<form:errors path = "price" cssClass = "err"/>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" style = "text-align : center">
				<input type = "submit" value = "ADD">
				<input type = "button" value = "SHOW" onClick = "doList()">
			</td>
		</tr>
		
	</table>
</form:form>