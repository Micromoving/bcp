<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#treeTable").treeTable({
					expandLevel : 3
				}).show();
				
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
								<h3>部门列表</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">


<form:form id="searchForm" modelAttribute="office" action="${ctx}/sys/office/export" method="post" ></form:form>
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">

										<div class="clearfix">
											<shiro:hasPermission name="sys:office:create">
												<a href="${ctx}/sys/office/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</shiro:hasPermission>
											<shiro:hasPermission name="sys:office:export">
												<a href="${ctx}/sys/office/export" id="btnExport"  onclick="return confirmx('确认要删除该用户吗？', this.href)"
													class="btn btn-primary pull-right"> 导出 </a>
											</shiro:hasPermission>
										</div>

									</div>
								</div>

								<div class="stream-list">
									<div class="media stream">
										<sys:message content="${message}" />
										<table id="treeTable"
											class="table table-striped table-bordered table-condensed hide">
											<tr>
												<th>部门名称</th>
												<th>部门编码</th>
												<th>负责人</th>
												<th>部门电话</th>
												<shiro:hasAnyPermissions
													name="sys:office:update,sys:office:delete">
													<th>操作</th>
												</shiro:hasAnyPermissions>
											</tr>
											<c:forEach var="officeData" items="${list}">
												<tr id="${officeData.id}" pId="${officeData.parent.id}">

													<td nowrap>${officeData.name}</td>
													<td>${officeData.code}</td>
													<td>${officeData.master}</td>
													<td>${officeData.phone}</td>
													<shiro:hasAnyPermissions
														name="sys:office:update,sys:office:delete">
														<td><shiro:hasPermission name="sys:office:update">
																<a href="${ctx}/sys/office/form?id=${officeData.id}">修改</a>
															</shiro:hasPermission>&nbsp; <shiro:hasPermission name="sys:office:delete">
																<a href="${ctx}/sys/office/delete?id=${officeData.id}"
																	onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
															</shiro:hasPermission>&nbsp; <shiro:hasPermission name="sys:office:update">
																<a
																	href="${ctx}/sys/office/form?parent.id=${officeData.id}">添加下级部门</a>
															</shiro:hasPermission></td>
													</shiro:hasAnyPermissions>
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>

							</div>
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