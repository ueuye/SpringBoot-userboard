<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeBoard.jsp</title>
</head>
<body>
	<h1>게시물 삭제</h1>
	<form method="post" action="/board/removeBoard">
		<table>
			<tr>
				<td>boardNo</td>
				<td><input type="text" name="boardNo"></td>
			</tr>
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId"></td>
			</tr>
		</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>