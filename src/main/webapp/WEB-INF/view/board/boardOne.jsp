<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardOne.jsp</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>localName</td>
			<td>${board.localName }</td>
		</tr>
		<tr>
			<td>boardTitle</td>
			<td>${board.boardTitle }</td>
		</tr>
		<tr>
			<td>boardContent</td>
			<td>${board.boardContent }</td>
		</tr>
		<tr>
			<td>image</td>
			<td>
				<c:forEach var="b" items="${boardFileList}">
					<img src="${pageContext.request.contextPath}/upload/${b.saveFilename}" alt="IMG-REVIEW" style="width:80px; height:80px;">
					<br>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>memberId</td>
			<td>${board.memberId }</td>
		</tr>
		<tr>
			<td>createdate</td>
			<td>${board.createdate }</td>
		</tr>
		<tr>
			<td>updatedate</td>
			<td>${board.updatedate }</td>
		</tr>
	</table>
	<a href="/board/modifyBoard?boardNo=${board.boardNo }">수정</a>
	<a href="/board/removeBoard?boardNo=${board.boardNo }">삭제</a>
</body>
</html>