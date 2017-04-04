<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
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
								<h3>角色管理</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/sys/role/form"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--/表格栏-->
								
								<div class="stream-list">
									<div class="media stream">
										<sys:message content="${message}" />
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th>角色名称</th>
													<th>英文名称</th>
													<th>归属部门</th>
													<th>数据范围</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list}" var="role">
													<tr>
														<td><a href="${ctx}/sys/role/form?id=${role.id}">${role.name}</a></td>
														<td>${role.enname}</td>
														<td>${role.office.name}</td>
														<td>${fns:getDictLabel(role.dataScope, 'sys_data_scope', '无')}</td>

														<td><a href="${ctx}/sys/role/assign?id=${role.id}">分配</a>
															<c:if
																test="${(role.sysData eq fns:getDictValue('是', 'yes_no', '1') && fns:getUser().admin)||!(role.sysData eq fns:getDictValue('是', 'yes_no', '1'))}">
																<a href="${ctx}/sys/role/form?id=${role.id}">修改</a>
															</c:if> <a href="${ctx}/sys/role/delete?id=${role.id}"
															onclick="return confirmx('确认要删除该角色吗？', this.href)">删除</a></td>
													</tr>
												</c:forEach>
										</table>

									</div>
									<!--/.media .stream-->
								</div>
								<!--/.module-->
							</div>
						</div>
						<!--/.content-->
					</div>
					<!--/.span9-->
				</div>
				<!--row-->
			</div>
			<!--/.container-->
		</div>
		<!--/.wrapper-->
	</div>

	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>