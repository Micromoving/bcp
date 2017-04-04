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
								<h3>请假管理</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/hr/leave/form?procDefId=${process.id}"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>

								<!--/搜索栏-->
								<div class="stream-list">


									<!--/.media .stream-->
									<!--/表格栏-->
									<div class="media stream">
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p27">序号</th>
													<th>申请人</th>
													<th>申请时间</th>
													<th>请假时间</th>
													<th>类型</th>
													<th>请假状态</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach var="leaveData" items="${page.list}"
													varStatus="status">
													<tr>
														<td style="text-align: center;">${status.count}</td>
														<td>${fns:getUser().name}</td>
														<td>${fns:formatDate("yyyy-MM-dd",leaveData.applyDate)}</td>
														<td>${fns:formatDate("yyyy-MM-dd",leaveData.startDate)}至${fns:formatDate("yyyy-MM-dd",leaveData.endDate)}</td>
														<td>${fns:getDictLabel(leaveData.leaveType,'oa_leave_type','')}</td>
														<td><c:if test="${not empty leaveData.act.taskName}">${leaveData.act.taskName}</c:if>
															<c:if test="${empty leaveData.act.taskName}">请假成功</c:if>
														</td>
														<td><c:if
																test="${leaveData.act.taskDefKey eq 'leaveaudit1'|| leaveData.act.taskDefKey eq ''||leaveData.act.taskDefKey eq 'usertask1'}">
																<c:if test="${empty leaveData.reportPeople}">
																	<a href="${ctx}/hr/leave/form?id=${leaveData.id}">修改</a>
																	<a href="${ctx}/hr/leave/delete?id=${leaveData.id}">撤销</a>
																</c:if>
															</c:if> <c:if
																test="${leaveData.act.taskDefKey ne 'leaveaudit1' &&leaveData.act.taskDefKey ne 'usertask1'}">
																<a href="${ctx}/hr/leave/detail?id=${leaveData.id}">请假详情</a>
															</c:if> <c:if test="${not empty leaveData.reportPeople}">
																<a href="${ctx}/hr/leave/detail?id=${leaveData.id}">销假详情</a>
															</c:if>
													</tr>
												</c:forEach>

											</tbody>
										</table>

									</div>
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
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>