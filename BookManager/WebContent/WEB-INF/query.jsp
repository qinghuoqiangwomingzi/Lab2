<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>作者查询</title>
</head>
<body>
	<s:form action="showAction">
					<br><s:property value="authordata"></s:property>
					<s:textfield label="作者名" name="bookname" value="%{#request.user.bookname}" ></s:textfield>
                    <s:submit value="查询"></s:submit>
    </s:form>
	
</body>
</html>