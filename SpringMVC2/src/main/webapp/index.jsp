<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>文件上传</title>
</head>
<body>
	<form action="upload2.do" method="post" enctype="multipart/form-data">
		<input type="file" name="fileName">
		<input type="submit" value="上传">
	</form>
	
</body>
</html>