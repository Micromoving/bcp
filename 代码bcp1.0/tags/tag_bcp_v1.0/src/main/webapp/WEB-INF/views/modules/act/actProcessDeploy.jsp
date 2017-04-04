<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>部署流程 - 流程管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
</script>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">

				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>流程管理</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="act:process:edit">
												<a href="${ctx}/act/process/"
													class="btn btn-primary pull-right"> 流程管理 </a>
											</shiro:hasPermission>
											<shiro:hasPermission name="act:process:edit">
												<a href="${ctx}/act/process/deploy/"
													class="btn btn-primary pull-right"> 部署流程 </a>
											</shiro:hasPermission>
											<shiro:hasPermission name="act:process:edit">
												<a href="${ctx}/act/process/running/"
													class="btn btn-primary pull-right"> 运行中的流程 </a>
											</shiro:hasPermission>

										</div>
									</div>
								</div>
							</div>
							<form id="inputForm" action="${ctx}/act/process/deploy"
								method="post" enctype="multipart/form-data"
								class="form-horizontal">
								<div class="control-group">
									<label class="control-label">流程分类：</label>
									<div class="controls">
										<select id="category" name="category"
											class="required input-medium">
											<c:forEach items="${fns:getDictList('act_category')}"
												var="dict">
												<option value="${dict.value}">${dict.label}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">流程文件：</label>
									<div class="controls">
										<input type="file" id="file" name="file" class="required" />
										<span class="help-inline">支持文件格式：zip、bar、bpmn、bpmn20.xml</span>
									</div>
								</div>

								<!--/提交按钮-->
								<div class="control-group">
									<div class="controls">
										<button type="submit" class="btn">提交</button>
									</div>
								</div>
								<br />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>