<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../common/common.jsp" %>

<!-- travel/travelList.jsp -->

<style type="text/css">
	body{
		background-color : #FFEFEF;
	}
	
	table{
		background-color : white;
		border : 1px solid;
		border-collapse: collapse;
		width : 80%;
		margin : auto;
	}	
	
	h2, h4{
		text-align : center;
	}
	
	th {
		background-color:  #FFB9B9;
	}
	
	tr, td, th {
		border : 1px solid;
		text-align: center;
		height : 40px;
	}
</style>

<script type="text/javascript">
	function insert(){
		location.href = "insert.tv";
	}
	
	function doDelete(num, pageNumber){
		location.href = "delete.tv?num=" + num + "&pageNumber=" + pageNumber;
	}
	
	function doUpdate(num, pageNumber){
		location.href = "update.tv?num=" + num+ "&pageNumber=" + pageNumber;
	}
</script>

<h2>Travel Information List</h2>
<h4>Total Count : ${pageInfo.totalCount }</h4>

<%-- 현재 클릭 페이지 번호 : ${pageInfo.pageNumber } --%>

<center>
	<form action = "list.tv" method = "get">
		<select name = "whatColumn">
			<option value = "all">전체 검색</option>
			<option value = "area">지역</option>
			<option value = "style">여행 타입</option>
		</select>
		
		<input type = "text" name = "keyword" >
		<input type = "submit" value = "SEARCH">
	</form>
</center>

<table>
	<tr>
		<th colspan="8" style = "text-align : right">
			<input type = "button" value = "ADD" onClick = "insert()">
		</th>
	</tr>

	<tr>
		<th>NUM</th>
		<th>NAME</th>
		<th>AGE</th>
		<th>AREA</th>
		<th>STYLE</th>
		<th>PRICE</th>
		<th>UPDATE</th>
		<th>DELETE</th>
	</tr>
	
	<c:forEach var = "travel" items = "${travelLists }">
		<tr>
			<td>${travel.num }</td>
			<td>${travel.name }</td>
			<td>${travel.age }</td>
			<td>${travel.area }</td>
			<td>${travel.style }</td>
			<td>${travel.price }</td>
			<td><input type = "button" value = "update" onClick = "doUpdate(${travel.num }, ${pageInfo.pageNumber })"></td>
			<td><input type = "button" value = "delete" onClick = "doDelete(${travel.num }, ${pageInfo.pageNumber })"></td>
		</tr>
	</c:forEach>
</table>

<br>

<center>
	${pageInfo.pagingHtml } 
</center>