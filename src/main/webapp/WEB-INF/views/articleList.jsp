<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>文章管理${ctx }</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">x</button>${message}</div>
	</c:if>
	
	<tags:sort/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>文章</th><th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${articles.content}" var="article">
			<tr>
				<td><a href="${ctx}/article/update/${article.id}">${article.title}</a></td>
				<td><a href="${ctx}/article/delete/${article.id}">删除</a></td>
			</tr>
		</c:forEach>	
		</tbody>
	</table>
	
	
	<div><a class="tbn" href="${ctx}/article/create">创建</a></div>
</body>
</html>