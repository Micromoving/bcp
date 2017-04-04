<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#treeTable").treeTable({
			expandLevel : 3
		}).show();
	});
	function updateSort() {
		loading('正在提交，请稍等...');
		$("#listForm").attr("action", "${ctx}/sys/menu/updateSort");
		$("#listForm").submit();
	}
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
								<h3>菜单管理</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/sys/menu/form"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<!--/.media .stream-->

									<!--/表格栏-->
									<div class="media stream">
										<sys:message content="${message}" />
										<form id="listForm" method="post">
											<table id="treeTable"
												class="table table-striped table-bordered table-condensed hide">
												<thead>
													<tr>
														<th>名称</th>
														<th>链接</th>
														<th style="text-align:center;">排序</th>
														<th>可见</th>
														<th>权限标识</th>
														<shiro:hasAnyPermissions
															name="sys:menu:update,sys:menu:delete">
															<th>操作</th>
														</shiro:hasAnyPermissions>
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
															<shiro:hasAnyPermissions
																name="sys:menu:update,sys:menu:delete">
																<td nowrap><shiro:hasPermission
																		name="sys:menu:update">
																		<a href="${ctx}/sys/menu/form?id=${menu.id}">修改</a>
																		<a href="${ctx}/sys/menu/form?parent.id=${menu.id}">添加下级菜单</a>
																	</shiro:hasPermission> <shiro:hasPermission name="sys:menu:delete">
																		<a href="${ctx}/sys/menu/delete?id=${menu.id}"
																			onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)">删除</a>
																	</shiro:hasPermission></td>
															</shiro:hasAnyPermissions>
														</tr>
													</c:forEach>
												</tbody>
											</table>

										</form>

									</div>
									<!--/.media .stream-->
								</div>
							<shiro:hasAnyPermissions name="sys:menu:update,sys:menu:delete">
										<div class="stream-composer media">
										<div class="media-body">
											<div class="clearfix">
												<input id="btnSubmit" class="btn btn-primary pull-right" type="button"
													value="保存排序" onclick="updateSort();" />
											</div>
										</div></div>
									</shiro:hasAnyPermissions>
							</div>
							<!--/.module-body-->
						</div>
						<!--/.module-->

					</div>
					<!--/.content-->
				</div>
				<!--/.span9-->
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>