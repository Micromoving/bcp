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
		$("#searchForm").attr("action", "${ctx}/hr/attendance/middle");
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
								<h3>职员信息管理</h3>
							</div>
							<div class="module-body">
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm" action="${ctx}/hr/attendance/middle"
											modelAttribute="attendance" class="form-horizontal row-fluid"
											method="post">
											<input id="pageNo" name="pageNo" type="hidden"
												value="${employeepage.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${employeepage.pageSize}" />
											<!-- 归属部门 -->
											<div class="control-group">
												部门
												<sys:treeselect id="office" name="office.id"
													value="${office.id}" labelName="user.office.name"
													labelValue="${office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />
												&nbsp;&nbsp; 姓名
												<form:input name="user.name" class="span2" path="user.name"
													type="text"></form:input>
												&nbsp;&nbsp;&nbsp;<a href="#"
													class="btn btn-primary pull-right"
													onclick="return page('','','${ctx}/hr/attendance/middle');">
													查询 </a>
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
													<th class="p27">序号</th>
													<th class="p55">姓名</th>
													<th>部门</th>
													<th>证件号码</th>
													<th class="p85">手机</th>
													<th class="p75">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="employeeData" items="${employeepage.list}"
													varStatus="status">
													<tr>
														<td style="text-align: center;">${status.count }</td>
														<td><a
															href="${ctx}/hr/employee/form?id=${employeeData.id}">${employeeData.user.name}
														</a></td>
														<td>${fns:getOfficeRootName(employeeData.office.id)}</td>
														<td>${employeeData.papersNumber}</td>

														<td>${employeeData.mobile }</td>
														<td><a
															href="${ctx}/hr/attendance/form?user.id=${employeeData.id}">请假</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										${employeepage}

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