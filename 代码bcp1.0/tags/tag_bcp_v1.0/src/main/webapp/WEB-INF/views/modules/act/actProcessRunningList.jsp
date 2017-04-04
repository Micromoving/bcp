<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>运行中的流程</title>
<meta name="decorator" content="default" />
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
							<form id="searchForm" action="${ctx}/act/process/running/"
								method="post" class="breadcrumb form-search">
								<label>流程实例ID：</label><input type="text" id="procInsId"
									name="procInsId" value="${procInsId}" class="input-medium" />
								<label>流程定义Key：</label><input type="text" id="procDefKey"
									name="procDefKey" value="${procDefKey}" class="input-medium" />
								&nbsp;<input id="btnSubmit" class="btn btn-primary pull-right"
									type="submit" value="查询" />
							</form>
							<sys:message content="${message}" />
							<table class="table table-striped table-bordered table-condensed ">
								<thead>
									<tr>
										<th >ID</th>
										<th>当前环节</th>
										<th>是否挂起</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${page.list}" var="procIns">
										<tr>
											<td>执&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行ID：${procIns.id} <br/>
												流程实例ID：${procIns.processInstanceId}<br/>
												流程定义ID：${procIns.processDefinitionId}</td>
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
				</div>
			</div>
	</div>
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
