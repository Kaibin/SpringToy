<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>文章管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">x</button>
			${message}
		</div>
	</c:if>

	<div class="row">
		<div class=" dropdown span2">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 类别:
				${categories[categoryId].name}<b class="caret"></b>
			</a>
			<ul class="dropdown-menu">
				<c:forEach items="${categories}" var="entry">
					<li><a href="?category=${entry.id}">${entry.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<tags:sort />
		
		<div class="offset4 span4">
			<form class="form-search" action="${ctx}/article/search">
				<input type="text" name="searchTerm" class="input-medium" value="${param.searchTerm}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>

	</div>

	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文章</th>
				<th>管理</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articles.content}" var="article">
				<tr>
					<td><a href="${ctx}/article/update/${article.id}">${article.title}</a></td>
					<td><a href="${ctx}/article/delete/${article.id}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<tags:pagination page="${articles}" paginationSize="5" />

	<div>
		<a class="btn" href="${ctx}/article/create">新建文章</a> <a class="btn"
			href="${ctx}/category/create">新建分类</a>
	</div>
</body>
</html>