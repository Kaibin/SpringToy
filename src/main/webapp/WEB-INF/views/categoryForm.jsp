<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>分类管理</title>
</head>
<body>
	<form id="categoryForm" action="${ctx}/category/create" method="post"
		class="form-horizontal">
		<fieldset>
			<legend><small>管理分类</small></legend>
		
			<div class="control-group">
				<label for="name" class="control-label">名称:</label>
				<div class="controls">
					<input type="text" id="category_name" name="name">
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