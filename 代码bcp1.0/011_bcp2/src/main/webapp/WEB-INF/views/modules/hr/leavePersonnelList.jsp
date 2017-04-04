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
								<h3>请假人员</h3>
							</div>
							<div class="module-body">
								<!--/搜索栏-->
								<div class="stream-list">

									<div class="media stream">
										<form:form id="searchForm"
											action="${ctx}/hr/leavePersonnel/save"
											modelAttribute="leavePersonnel"
											class="form-horizontal row-fluid" method="post">
											<form:hidden path="salaryInstanceId"></form:hidden>
											<c:if test="${not empty list}">
												<!--/表格栏-->
												<table
													class="table table-striped table-bordered table-condensed">
													<thead>
														<tr>
															<th class="p25">序号</th>
															<th class="p55">姓名</th>
															<th class="p65">部门</th>
															<th class="p65">岗位</th>
															<th class="p50">请假类型</th>
															<th class="p130">起止日期</th>
															<th class="p75">销假时间</th>
															<th class="p45">扣考勤费</th>
														</tr>
													</thead>
													<c:forEach items="${list}" var="data" varStatus="status">
														<tbody>
															<tr>
																<td>${status.count }</td>
																<td>${data.user.name }<input type="hidden"
																	name="employeeList[${status.index }]"
																	value="${data.user.id}"></td>
																<td>${data.office}</td>
																<td>${fns:getDictLabel(data.postType,'post_type','')}</td>
																<td>${fns:getDictLabel(data.leaveType,'oa_leave_type','')}</td>
																<td>${data.leaveDate}</td>
																<td>${fns:formatDate("yyyy-MM-dd",data.reportDate)}</td>
																<td><input type="text" class="p45"
																	name="buckleAbsenteeismList[${status.index }]"
																	value="${data.buckleAbsenteeism}" /></td>
															</tr>
														</tbody>
													</c:forEach>
												</table>

												<!--/提交按钮-->
												<div class="control-group">
													<div class="controls" id="submit">
														<input type="submit" class="btn btn-primary pull-right"
															id="submit " value="提交">
													</div>
												</div>
											</c:if>
										</form:form>
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
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>