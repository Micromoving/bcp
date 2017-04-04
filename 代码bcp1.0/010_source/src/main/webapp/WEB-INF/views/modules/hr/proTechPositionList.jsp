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
								<h3>职称详情</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="hr:proTechPosition:create">
												<a href="${ctx}/hr/proTechPosition/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</shiro:hasPermission>
										</div>

									</div>
								</div>

								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>

								<!--/职称列表-->
								<%-- <c:forEach items="${list}" var="data"> --%>
								<div class="stream-list">
									<div class="media stream">
										<div class="media-body">



											<table
												class="table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th class="p27">序号</th>
														<th>职称名称</th>
														<th>专业技术职务</th>
														<th class="p100">获取时间</th>
														<th class="p100">生效时间</th>

														<shiro:hasAnyPermissions
															name="hr:proTechPosition:save,hr:proTechPosition:delete">
															<th class="p75">操作</th>
														</shiro:hasAnyPermissions>


													</tr>
												</thead>
												<c:forEach var="data" items="${list}"
													varStatus="status">
													<tbody>
														<tr>
															<td>${status.count}</td>
															<td>${fns:getDictLabel(data.techPositionType,'tech_position_type','')}</td>
															<td>${fns:getDictLabel(data.name,'tech_position','')}</td>
															<td>${fns:formatDate("yyyy-MM",data.gainDate)}</td>
															<td>${fns:formatDate("yyyy-MM",data.appointDate)}</td>
															<shiro:hasAnyPermissions
																name="hr:proTechPosition:update,hr:proTechPosition:delete">
																<td><shiro:hasPermission
																		name="hr:proTechPosition:update">
																		<a
																			href="${ctx}/hr/proTechPosition/form?id=${data.id}">编辑</a>
																	</shiro:hasPermission> <shiro:hasPermission name="hr:proTechPosition:delete">
																		<a
																			href="${ctx}/hr/proTechPosition/delete?id=${data.id}"
																			onclick="return confirmx('确认要删除该记录吗？', this.href)">删除</a>
																	</shiro:hasPermission></td>
															</shiro:hasAnyPermissions>


														</tr>
													</tbody>
												</c:forEach>
											</table>
											${page}




											<%-- <div class="stream-headline">
													<h5 class="stream-author">
														${fns:formatDate("yyyy-MM",data.gainDate)}&nbsp;&nbsp;获取&nbsp;&nbsp;${fns:getDictLabel(data.name,'name','')}职称&nbsp;&nbsp;&nbsp;&nbsp;${fns:formatDate("yyyy-MM",data.appointDate)}&nbsp;&nbsp;正式聘任
													</h5>

												</div>
												<!--/.stream-headline-->
												<div class="stream-options">
													<shiro:hasPermission name="hr:proTechPosition:delete">
														<a href="${ctx}/hr/proTechPosition/delete?id=${data.id}"
															class="btn btn-small"> 删除 </a>
													</shiro:hasPermission>
												</div> --%>





										</div>
									</div>
									<!--/.media .stream-->
								</div>
								<%-- </c:forEach> --%>
								<!--/.stream-list-->
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