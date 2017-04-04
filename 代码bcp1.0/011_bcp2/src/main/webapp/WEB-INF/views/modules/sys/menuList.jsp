<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#treeTable").treeTable({
			expandLevel : 3
		}).show();
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/employee/list");
		$("#searchForm").submit();
		return false;
	}
</script>

</head>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
			<div class="row">
				<!-- 路径 -->
				<div class="formbar">
					<b><a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=400">系统管理</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/list">菜单管理</a>&nbsp;&nbsp; </b>
				</div>
				<!-- 按钮 -->
				<div class="formbar t-line">
					<a href="${ctx}/sys/menu/form" class="btn-con">增加</a>
				</div>

				<!--/表格栏-->
				<div class="media stream">
					<!-- 内容 -->
					<div class="formbar">
						<sys:message content="${message}" />
						<form id="listForm" method="post">
							<table class="listtbl" id="treeTable">
								<thead>
									<tr>
										<th>名称</th>
										<th>链接</th>
										<th style="text-align:center;">排序</th>
										<th>可见</th>
										<th>权限标识</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list}" var="menu">
										<tr id="${menu.id}"
											pId="${menu.parent.id ne '1'?menu.parent.id:'0'}">
											<td nowrap><i
												class="icon-${not empty menu.icon?menu.icon:' hide'}"></i><a
												href="${ctx}/sys/menu/form?id=${menu.id}">${menu.name}</a></td>
											<td title="${menu.href}">${fns:abbr(menu.href,30)}</td>
											<td style="text-align:center;"><shiro:hasPermission
													name="sys:menu:update">
													<input type="hidden" name="ids" value="${menu.id}" />
													<input name="sorts" type="text" value="${menu.sort}"
														style="width:50px;margin:0;padding:0;text-align:center;">
												</shiro:hasPermission> <shiro:lacksPermission name="sys:menu:update">
							${menu.sort}
						</shiro:lacksPermission></td>
											<td>${menu.isShow eq '1'?'显示':'隐藏'}</td>
											<td title="${menu.permission}">${fns:abbr(menu.permission,30)}</td>
											<td nowrap><a href="${ctx}/sys/menu/form?id=${menu.id}">修改</a>
												<a href="${ctx}/sys/menu/form?parent.id=${menu.id}">添加下级菜单</a>
												<a href="${ctx}/sys/menu/delete?id=${menu.id}"
												onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)">删除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>
						<div class="stream-composer media">
							<div class="media-body">
								<div class="clearfix">
									<input id="btnSubmit" class="btn btn-primary pull-right"
										type="button" value="保存排序" onclick="updateSort();" />
								</div>
							</div>
						</div>
					</div>
					<!--/.media .stream-->
				</div>
			</div>
		</div>
		<!--/.module-body-->
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>