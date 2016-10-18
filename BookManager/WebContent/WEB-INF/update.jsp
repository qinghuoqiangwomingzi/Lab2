<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书信息更新</title>
</head>
<body>
	<s:form action="updateAction">
		<s:textfield label="书名" name="bookname" value="%{#request.user.bookname}"></s:textfield>
		<s:textfield label="索引号" name="ISBN"  value="%{#request.user.ISBN}"></s:textfield>
		<s:submit value="更新"></s:submit>
	</s:form>
</body>
</html>