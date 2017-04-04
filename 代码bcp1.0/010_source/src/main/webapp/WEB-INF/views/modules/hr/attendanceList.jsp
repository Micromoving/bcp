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
								<h3>考勤管理</h3>
							</div>
							<div class="module-body">
								<!--/按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/hr/attendance/middle"
												class="btn btn-primary pull-right"> 增加 </a> <a href="#"
												class="btn btn-primary pull-right"> 导出 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm" action="${ctx}/hr/attendance/list"
											modelAttribute="attendance" class="form-horizontal row-fluid"
											method="post">

											<div class="control-group">
											部门
												<sys:treeselect id="office" name="user.office.id"
													value="${user.office.id}" labelName="user.office.name"
													labelValue="${user.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />
												&nbsp;&nbsp; 姓名
												<form:input name="user.name" class="span2" path="user.name"
													type="text"></form:input>
												&nbsp;&nbsp;<br /> <br />请假时间
												<form:input class="span2" path="startDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
												至
												<form:input class="span2 " path="endDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>

												<a href="#" class="btn btn-primary pull-right"
													onclick="return page('','','${ctx}/hr/attendance/list');">
													查询 </a>
											</div>
										</form:form>
									</div>

									<div class="media stream">
										<!--/表格栏-->
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p27">序号</th>
													<th class="p55">姓名</th>
													<th class="p75">部门</th>
													<th class="p85">申请时间</th>
													<th class="p180">请假时间</th>
													<th class="p65">类型</th>
													<th class="p75">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="attendanceData" items="${page.list}"
													varStatus="status">
													<tr>
													<!-- <a href="${ctx}/hr/leave/detail?id=${attendanceData.id}">${attendanceData.user.name}</a> -->
														<td style="text-align: center;">${status.count }</td>
														<td>${attendanceData.user.name}</td>
														<td>${fns:getOfficeRootName(attendanceData.office.id)}</td>
														<td>${fns:formatDate("yyyy-MM-dd",attendanceData.applyDate)}</td>
														<td>${fns:formatDate("yyyy-MM-dd",attendanceData.startDate)}至${fns:formatDate("yyyy-MM-dd",attendanceData.endDate)}</td>
														<td>${fns:getDictLabel(attendanceData.leaveType,'oa_leave_type','')}</td>
														<td><a
															href="${ctx}/hr/attendance/form?id=${attendanceData.id}">修改</a>

															<a
															href="${ctx}/hr/attendance/delete?id=${attendanceData.id}"
															onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>

									</div>
									<!--/.media .stream-->

								</div>
								${page}
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