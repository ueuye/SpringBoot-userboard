<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addBoard.jsp</title>
</head>
<body>
	<h1>게시글입력</h1>
	<form method="post" action="/board/addBoard" enctype="multipart/form-data">
		<table>
			<tr>
				<td>localName</td>
				<td>
					<input type="text" name="localName">
				</td>
			</tr>
			<tr>
				<td>boardTitle</td>
				<td>
					<input type="text" name="boardTitle">
				</td>
			</tr>
			<tr>
				<td>boardContent</td>
				<td>
					<textarea cols="30" rows="3" name="boardContent"></textarea>
				</td>
			</tr>
			<tr>
				<td>memberId</td>
				<td>
					<input type="text" name="memberId">
				</td>
			</tr>
			<tr>
				<td>파일업로드</td>
				<td><input type="file" name="multipartFile" multiple="multiple"></td>
			</tr>
		</table>
		<button type="submit">입력</button>
	</form>
</body>
</html>