<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>


<script type="text/javascript">
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
								<h3>教师信息管理</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="hr:employee:create">
												<a href="${ctx}/sys/user/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</shiro:hasPermission>
											<a id="btnExport" href="${ctx}/hr/employee/export"
												class="btn btn-primary pull-right"> 导出 </a> <a
												href="${ctx}/hr/employee/import" id="btnImport"
												class="btn btn-primary pull-right"> 导入 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm" action="${ctx}/hr/employee/list"
											modelAttribute="salEmpView" class="form-horizontal row-fluid"
											method="post">
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />
											<!-- 归属部门 -->
											<div class="control-group">
												部门
												<sys:treeselect id="office" name="office.id"
													value="${salEmpView.office.id}" labelName="office.name"
													labelValue="${salEmpView.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />
												&nbsp;&nbsp; 姓名 <input name="name" value="${salEmpView.name }"
													class="span2" type="text"> &nbsp;&nbsp;政治面貌
												<form:select tabindex="1" class="span2"
													path="politicsStatus">
													<form:option value="" label="请选择" />
													<form:options items="${fns:getDictList('politics_status')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select><br><br>
												
												性别
												<form:select tabindex="1" class="span2"
													path="gender">
													<form:option value="" label="请选择" />
													<form:options items="${fns:getDictList('gender')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
												&nbsp;&nbsp;&nbsp;职称级别
												<form:select tabindex="1" class="span2"
													path="maxTechPositionLevel">
													<form:option value="" label="请选择" />
													<form:options items="${fns:getDictList('tech_position_level')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
												<a href="#" class="btn btn-primary pull-right"
													onclick="return page('','','${ctx}/hr/employee/list');">
													查询 </a> <a href="${ctx}/hr/employee/highList" class="btn btn-primary pull-right">
													高级查询 </a>
											</div>
										</form:form>
									</div>
									<!--/.media .stream-->
									<!--/表格栏-->
									<div class="media stream">
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th>序号</th>
													<th>姓名</th>
													<th>部门</th>
													<th>证件号码</th>
													<th>到校时间</th>
													<th>手机</th>
													<shiro:hasAnyPermissions 
														name="hr:employee:update,hr:employee:delete" >
														<th class="p75">操作</th>
													</shiro:hasAnyPermissions>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="employeeData" items="${page.list}"
													varStatus="status">
													<tr>
														<td style="text-align: center;">${(page.pageSize*(page.pageNo-1))+status.count }</td>
														<td><a
															href="${ctx}/hr/employee/form?id=${employeeData.id}">${employeeData.user.name}
														</a></td>
														<td>${fns:getOfficeRootName(employeeData.office.id)}</td>
														<td>${employeeData.papersNumber}</td>
														<td>${employeeData.inSchoolDateString}</td>
														<td>${employeeData.mobile }</td>

														<shiro:hasAnyPermissions
															name="hr:employee:update,hr:employee:delete">
															<td><shiro:hasPermission name="hr:employee:update">
																	<a href="${ctx}/sys/user/form?id=${employeeData.id}">修改</a>
																</shiro:hasPermission> <shiro:hasPermission name="hr:employee:delete">
																	<a href="${ctx}/sys/user/delete?id=${employeeData.id}"
																		onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
																</shiro:hasPermission></td>
														</shiro:hasAnyPermissions>
													</tr>

												</c:forEach>
											</tbody>
										</table>
										${page}
									</div>
									<!--/.media .stream-->

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
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>