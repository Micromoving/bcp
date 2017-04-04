<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		top.$.jBox.tip.mess = null;
	});
	function page(n, s) {
		location = '${ctx}/act/process/?pageNo=' + n + '&pageSize=' + s;
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
					<b><a href="${ctx}/home">首页</a>&nbsp;&gt;&nbsp;<a
						href="${ctx}/sys/menu/bizMenu?id=400">系统管理</a>&nbsp;&gt;&nbsp;<a href="">流程管理</a></b>
				</div>
				<div class="formbar t-line">
					<a href="${ctx}/act/process/" class="btn-con"> 流程管理 </a> <a
						href="${ctx}/act/process/deploy/" class="btn-con"> 部署流程 </a> <a
						href="${ctx}/act/process/running/" class="btn-con"> 运行中的流程 </a>
				</div>
				<form id="searchForm" action="${ctx}/act/process/" method="post"
					class="formbar t-line b-line">
					<select id="category" name="category" class="sfQuery">
						<option value="">全部分类</option>
						<c:forEach items="${fns:getDictList('act_category')}" var="dict">
							<option value="${dict.value}"
								${dict.value==category?'selected':''}>${dict.label}</option>
						</c:forEach>
					</select> &nbsp; <input id="btnSubmit" class="btn-con mgtp-4 btn-bw-0"
						type="submit" value="查询" />
				</form>
				<div class="formbar">
					${page.message }
					<table class="listtbl">
						<thead>
							<tr>
								<th>流程分类</th>

								<th>流程标识</th>
								<th>名称</th>
								<th>版本</th>
								<th>流程资源</th>
								<th>部署时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="object">
								<c:set var="process" value="${object[0]}" />
								<c:set var="deployment" value="${object[1]}" />
								<tr>
									<td><a
										href="javascript:updateCategory('${process.id}', '${process.category}')"
										title="设置分类">${fns:getDictLabel(process.category,'act_category','无分类')}</a></td>

									<td>${process.key}</td>
									<td>${process.name}</td>
									<td><b title='流程版本号'>V: ${process.version}</b></td>
									<td><a target="_blank"
										href="${ctx}/act/process/resource/read?procDefId=${process.id}&resType=xml">XML</a>&nbsp;<a
										target="_blank"
										href="${ctx}/act/process/resource/read?procDefId=${process.id}&resType=image">图片</a></td>
									<td><fmt:formatDate value="${deployment.deploymentTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td><c:if test="${process.suspended}">
											<a
												href="${ctx}/act/process/update/active?procDefId=${process.id}"
												onclick="return confirmx('确认要激活吗？', this.href)">激活</a>
										</c:if> <c:if test="${!process.suspended}">
											<a
												href="${ctx}/act/process/update/suspend?procDefId=${process.id}"
												onclick="return confirmx('确认挂起除吗？', this.href)">挂起</a>
										</c:if> <a
										href='${ctx}/act/process/delete?deploymentId=${process.deploymentId}'
										onclick="return confirmx('确认要删除该流程吗？', this.href)">删除</a> <a
										href='${ctx}/act/process/convert/toModel?procDefId=${process.id}'
										onclick="return confirmx('确认要转换为模型吗？', this.href)">转换为模型</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					${page}
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
