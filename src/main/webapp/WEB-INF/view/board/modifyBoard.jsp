<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyBoard.jsp</title>
</head>
<body>
	<h1>게시물 수정</h1>
	<form method="post" action="/board/modifyBoard" >
		<input type="hidden" name="boardNo" value="${board.boardNo }">
		<table>
			<tr>
				<td>localName</td>
				<td>
					<input type="text" name="localName" value="${board.localName }">
				</td>
			</tr>
			<tr>
				<td>boardTitle</td>
				<td>
					<input type="text" name="boardTitle" value="${board.boardTitle }">
				</td>
			</tr>
			<tr>
				<td>boardContent</td>
				<td>
					<textarea cols="30" rows="3" name="boardContent">${board.boardContent }</textarea>
				</td>
			</tr>
			<tr>
				<td>memberId</td>
				<td>
					<input type="text" name="memberId" value="${board.memberId }" readonly="readonly">
				</td>
			</tr>
		</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>