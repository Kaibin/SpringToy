<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>文章管理</title>

	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#article_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</head>

<body>
	<form id="inputForm" action="${ctx}/article/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${article.id}"/>
		<fieldset>
			<legend><small>文章任务</small></legend>
			<div class="control-group">
				<label for="article_title" class="control-label">标题:</label>
				<div class="controls">
					<input type="text" id="article_title" name="title"  value="${article.title}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="description" class="control-label">描述:</label>
				<div class="controls">
					<textarea id="description" name="description" class="input-large">${article.description}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label for=author class="control-label">作者:</label>
				<div class="controls">
					<input type="text" id="author" name="author" value="${article.author}" class="input-large required" minlength="3"/>
				</div>
			</div>
			<div class="control-group">
				<label for="content" class="control-label">内容:</label>
				<div class="controls">
					<textarea id="content" name="content" class="input-large">${article.content}</textarea>
				</div>
			</div>	
			<div class="control-group">
				<label for=link class="control-label">链接:</label>
				<div class="controls">
					<input type="text" id="link" name="link" value="${article.link}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
</body>
</html>