<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);

		$("#searchForm").submit();
		return false;
	}
</script>
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
								<h3>请假审批</h3>
							</div>
							<div class="module-body">
								<div class="media-body">
									<div class="stream-headline">
										<form:form class="form-horizontal row-fluid" id="inputForm"
											modelAttribute="attendance"
											action="${ctx}/hr/attendance/saveAudit" method="post">
											<table class="table table-condensed">
												<sys:message content="${message}" />
												<form:hidden path="id" />
												<form:hidden path="act.taskId" />
												<form:hidden path="act.taskName" />
												<form:hidden path="act.taskDefKey" />
												<form:hidden path="act.procInsId" />
												<form:hidden path="act.procDefId" />
												<form:hidden id="flag" path="act.flag" />
												<tbody>
													<tr>
														<td>申请人：${attendance.user.name}</td>
													</tr>
													<tr>
														<td>申请时间：${fns:formatDate("yyyy-MM-dd",attendance.applyDate)}</td>
													</tr>
													<tr>
														<td>请假时间：${fns:formatDate("yyyy-MM-dd",attendance.startDate)}至${fns:formatDate("yyyy-MM-dd",attendance.endDate)}</td>
													</tr>
													<tr>
														<td>类型：${fns:getDictLabel(attendance.leaveType,'oa_leave_type','')}</td>
													</tr>
													<tr>
														<c:if test="${attendance.act.taskDefKey ne 'leaveaudit5'}">
															<td>审核意见：<br> <form:textarea path="act.comment"
																	class="" rows="5" cssStyle="width:500px" /></td>
														</c:if>
													</tr>
													<tr>
													<td><c:if test="${attendance.act.taskDefKey eq 'leaveaudit5'}">
															实际请假工作日：<form:input class="span4 required"
																htmlEscape="false" path="leaveDays" />
														</c:if></td>
													</tr>
												</tbody>
											</table>
											

											<c:if test="${attendance.act.taskDefKey ne 'endevent1'}">
												<input id="btnSubmit" class="btn btn-success " type="submit"
													value="审核通过 " onclick="$('#flag').val('yes')" />&nbsp;
													</c:if>

											<c:if test="${attendance.act.taskDefKey eq 'leaveaudit1'}">
												<input id="btnSubmit" class="btn btn-danger" type="submit"
													value="审核不通过" onclick="$('#flag').val('no')" />&nbsp;
													</c:if>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--/.span9-->
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
