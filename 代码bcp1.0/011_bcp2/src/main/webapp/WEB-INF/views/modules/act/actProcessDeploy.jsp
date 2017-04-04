<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
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
<body class="tag-index" data-mod="tag">
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
			<div class="row">
				<!-- 路径 -->
				<div class="formbar">
					<b> <a href="${ctx}/home">首页</a>&nbsp;&gt;&nbsp;<a
						href="${ctx}/sys/menu/bizMenu?id=400">系统管理</a>&nbsp;&gt;&nbsp;<a href="">流程管理</a>&nbsp;&gt;&nbsp;<a href="">部署流程</a>&nbsp;
					</b>
				</div>
				<div class="formbar t-line">
					<a href="${ctx}/act/process/" class="btn-con"> 流程管理 </a> <a
						href="${ctx}/act/process/deploy/" class="btn-con"> 部署流程 </a> <a
						href="${ctx}/act/process/running/" class="btn-con"> 运行中的流程 </a>
				</div>
				<form id="inputForm" action="${ctx}/act/process/deploy"
					method="post" enctype="multipart/form-data"
					class="formbar t-line b-line">
					<select id="category" name="category" class="sfQuery">
						<option value="selected">流程分类</option>
						<c:forEach items="${fns:getDictList('act_category')}" var="dict">
							<option value="${dict.value}">${dict.label}</option>
						</c:forEach>
					</select> <br> <br><br>
					<input type="file" id="file" name="file" class="sfQuery"
						style="width: 17%" placeholder="流程文件" /> <span
						class="help-inline" style="line-height: 3;">支持文件格式：zip、bar、bpmn、bpmn20.xml</span> <br>
					<br>
					<!--/提交按钮-->
					<button type="submit" class="btn-con mgtp-4 btn-bw-0">提交</button>
					<br />
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>