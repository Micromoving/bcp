<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		top.$.jBox.tip.mess = null;
	});
	function page(n, s) {
		location = '${ctx}/act/process/running/?pageNo=' + n + '&pageSize=' + s;
	}
	function updateCategory(id, category) {
		$.jBox($("#categoryBox").html(), {
			title : "设置分类",
			buttons : {
				"关闭" : true
			},
			submit : function() {
			}
		});
		$("#categoryBoxId").val(id);
		$("#categoryBoxCategory").val(category);
	}
</script>
<script type="text/template" id="categoryBox">
		<form id="categoryForm" action="${ctx}/act/process/updateCategory" method="post" enctype="multipart/form-data"
			style="text-align:center;" class="form-search" onsubmit="loading('正在设置，请稍等...');"><br/>
			<input id="categoryBoxId" type="hidden" name="procDefId" value="" />
			<select id="categoryBoxCategory" name="category">
				<c:forEach items="${fns:getDictList('act_category')}" var="dict">
					<option value="${dict.value}">${dict.label}</option>
				</c:forEach>
			</select>
			<br/><br/>　　
			<input id="categorySubmit" class="btn btn-primary" type="submit" value="   保    存   "/>　　
		</form>
	</script>
</head>
<body class="tag-index" data-mod="tag">
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
			<div class="row">
				<div class="formbar">
					<b> <a href="${ctx}/home">首页</a>&nbsp;&gt;&nbsp;<a
						href="${ctx}/sys/menu/bizMenu?id=400">系统管理</a>&nbsp;&gt;&nbsp;<a href="">流程管理</a>&nbsp;&gt;&nbsp;<a href="">运行中的流程</a>&nbsp;
					</b>
				</div>
				<div class="formbar t-line">
					<a href="${ctx}/act/process/" class="btn-con"> 流程管理 </a> <a
						href="${ctx}/act/process/deploy/" class="btn-con"> 部署流程 </a> <a
						href="${ctx}/act/process/running/" class="btn-con"> 运行中的流程 </a>
				</div>
				<form:form id="searchForm" action="${ctx}/act/process/running/"
					method="post" class="formbar t-line b-line">
					<input type="text" id="procInsId" name="procInsId"
						value="${procInsId}" class="sfQuery" placeholder="流程实例ID" />
					<input type="text" id="procDefKey" name="procDefKey"
						value="${procDefKey}" class="sfQuery" placeholder="流程定义Key" />
					&nbsp;
					<input id="btnSubmit" class="btn-con mgtp-4 btn-bw-0" type="submit"
						value="查询" />
				</form:form>
				<!-- 内容 -->
				<div class="formbar">
					${page.message }
					<table class="listtbl">
						<thead>
							<tr>
								<th>ID</th>
								<th>当前环节</th>
								<th>是否挂起</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="procIns">
								<tr>
									<td>执&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行ID：${procIns.id}
										<br /> 流程实例ID：${procIns.processInstanceId}<br />
										流程定义ID：${procIns.processDefinitionId}
									</td>
									<td>${procIns.activityId}</td>
									<td>${procIns.suspended}</td>
									<td><shiro:hasPermission name="act:process:edit">
											<a
												href="${ctx}/act/process/deleteProcIns?procInsId=${procIns.processInstanceId}&reason="
												onclick="return promptx('删除流程','删除原因',this.href);">删除流程</a>
										</shiro:hasPermission>&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					${page}
				</div>
			</div>
			<div class="text-center"></div>
		</div>
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
