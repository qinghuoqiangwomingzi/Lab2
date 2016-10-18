<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书信息</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>书名</th>
			<th>索引号</th>
			<th>删除</th>
			<th>修改</th>			
		</tr>
		<s:iterator value="#request.books" var="tempBook">
			<tr>
				<td><s:a href="bookAction?bookname=%{#tempBook.bookname}"><s:property value="#tempBook.bookname"/></s:a></td> 
				<td><s:property value="#tempBook.ISBN"/></td>
				<td><s:a href="deleteAction?bookname=%{#tempBook.bookname}">删除</s:a></td>
				<td><s:a href="selectAction?bookname=%{#tempBook.bookname}">修改</s:a></td>
			</tr>
		</s:iterator>
		<tr> 
			<td colspan="4" align="center">
			<s:form action="queryAction">
                    <s:submit value="查询"></s:submit>
            </s:form>
			</td>
		</tr>
	</table>
</body>
</html>