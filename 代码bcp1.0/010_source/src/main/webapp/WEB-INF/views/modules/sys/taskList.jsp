<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/sys/task/hislist");
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

						<div class="module message">
							<div class="module-head">
								<h3>任务管理</h3>
							</div>
							<div class="module-option clearfix">
								<form:form id="searchForm" action="${ctx}/sys/task/hislist"
									modelAttribute="reportedPerformance"
									class="form-horizontal row-fluid" method="post">
									<input id="pageNo" name="pageNo" type="hidden"
										value="${page.pageNo}" />
									<input id="pageSize" name="pageSize" type="hidden"
										value="${page.pageSize}" />
								</form:form>
							</div>

							<div class="module-body table">
								<table class="table table-message">
									<tbody>
										<tr class="heading">
											<td class="cell-icon"></td>
											<td class="cell-title">任务类型</td>
											<td class="cell-title">申请人</td>
											<td class="cell-status hidden-phone hidden-tablet">申请时间</td>
											<td class="cell-time align-right">操作</td>
										</tr>
										<c:forEach items="${list}" var="data">
											<tr class="task">
												<td class="cell-icon"><i class="icon-checker high"></i>
												</td>
												<td class="cell-title"><div>${data.title}</div></td>
												<td class="cell-title">${data.name}</td>
												<td class="cell-time align-right">${fns:formatDate("yyyy-MM-dd",data.applyDate)}</td>
												<td class="cell-status hidden-phone hidden-tablet"><c:set
														var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_LEAVE[0]%>">
													</c:set> <c:if test="${data.procDef.key eq procKey}">
														<a href="${ctx}/hr/attendance/audit?id=${data.recordId}">
															<b class="due">审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_SALARY[0]%>">

													</c:set> <c:if test="${data.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/salaryInstance/initialResults?salaryInstance.id=${data.recordId}">
															<b class="due">审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_DUTY[0]%>">

													</c:set> <c:if test="${data.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/dutyDetail/form?reportRecord.id=${data.recordId}">
															<b class="due">审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_PERFORMANCE_2[0]%>"></c:set>
													<c:if test="${data.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/reportPerformance/form?reportRecord.id=${data.recordId}&postType=2">
															<b class="due">审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_PERFORMANCE[0]%>">
													</c:set> <c:if test="${data.procDef.key eq procKey}">
														<c:if test="${data.flag eq 5}">
															<a
																href="${ctx}/hr/reportPerformance/form?reportRecord.id=${data.recordId}&postType=4">
																<b class="due">审批</b>
															</a>
														</c:if>
														<c:if test="${data.flag eq 1}">
															<a
																href="${ctx}/hr/reportPerformance/form?reportRecord.id=${data.recordId}&postType=2">
																<b class="due">审批</b>
															</a>
														</c:if>
														<c:if test="${data.flag eq 6}">
															<a
																href="${ctx}/hr/reportPerformance/form?reportRecord.id=${data.recordId}&postType=3&reportRecord.dataClassification=6">
																<b class="due">审批</b>
															</a>
														</c:if>

													</c:if> <c:if
														test="${data.procDef.key eq procKey && data.flag eq 4}">
														<a
															href="${ctx}/hr/empPerformanceLevel/form?reportRecord.id=${data.recordId}&postType=4">
															<b class="due">审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_WORKLOAD_1[0]%>">
													</c:set> <c:if test="${data.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/reportedWorkloade/form?reportRecord.id=${data.recordId}">
															<b class="due">审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_WORKLOAD_2[0]%>">
													</c:set> <c:if test="${data.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/reportedWorkloade/form?reportRecord.id=${data.recordId}">
															<b class="due">审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_DEGREEEDU[0]%>">

													</c:set> <c:if test="${data.procDef.key eq procKey}">
														<a href="${ctx}/hr/degreeEdu/form?id=${data.recordId}">
															<b class="due">审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_TRAIN_EXPERIENCE[0]%>">

													</c:set> <c:if test="${data.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/trainExperience/selfAudit?id=${data.recordId}">
															<b class="due">审批</b>
														</a>
													</c:if></td>
											</tr>
										</c:forEach>
										<c:forEach items="${page.list}" var="data2">
											<tr class="task">
												<td class="cell-icon"><i class="icon-checker high"></i></td>
												<td class="cell-title"><div>${data2.procDef.name }</div></td>
												<td class="cell-title">${data2.name}</td>
												<td class="cell-time align-right">${fns:formatDate("yyyy-MM-dd",data2.applyDate)}</td>
												<td class="cell-status hidden-phone hidden-tablet"><c:set
														var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_LEAVE[0]%>">
													</c:set> <c:if test="${data2.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/attendance/audit?id=${data2.recordId}&status=${data2.status}">
															<b class="due">已审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_SALARY[0]%>">

													</c:set> <c:if test="${data2.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/salaryInstance/initialResults?salaryInstance.id=${data2.recordId}&status=${data2.status}">
															<b class="due">已审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_DUTY[0]%>">

													</c:set> <c:if test="${data2.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/dutyDetail/form?reportRecord.id=${data2.recordId}&status=${data2.status}">
															<b class="due">已审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_PERFORMANCE[0]%>">
													</c:set> <c:if test="${data2.procDef.key eq procKey}">
														<c:if test="${data2.flag eq 5}">
															<a
																href="${ctx}/hr/reportPerformance/form?reportRecord.id=${data2.recordId}&postType=4&status=${data2.status}">
																<b class="due">已审批</b>
															</a>
														</c:if>
														<c:if test="${data2.flag eq 1}">
															<a
																href="${ctx}/hr/reportPerformance/form?reportRecord.id=${data2.recordId}&postType=2&status=${data2.status}">
																<b class="due">已审批</b>
															</a>
														</c:if>
														<c:if test="${data2.flag eq 6}">
															<a
																href="${ctx}/hr/reportPerformance/form?reportRecord.id=${data2.recordId}&postType=3&reportRecord.dataClassification=6&status=${data2.status}">
																<b class="due">已审批</b>
															</a>
														</c:if>

													</c:if> <c:if
														test="${data2.procDef.key eq procKey && data2.flag eq 4}">
														<a
															href="${ctx}/hr/empPerformanceLevel/form?reportRecord.id=${data2.recordId}&postType=4&status=${data2.status}">
															<b class="due">已审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_WORKLOAD_1[0]%>">
													</c:set> <c:if test="${data2.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/reportedWorkloade/form?reportRecord.id=${data2.recordId}&status=${data2.status}">
															<b class="due">已审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_WORKLOAD_2[0]%>">
													</c:set> <c:if test="${data2.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/reportedWorkloade/form?reportRecord.id=${data2.recordId}&status=${data2.status}">
															<b class="due">已审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_DEGREEEDU[0]%>">

													</c:set> <c:if test="${data2.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/degreeEdu/form?id=${data2.recordId}&status=${data2.status}">
															<b class="due">已审批</b>
														</a>
													</c:if> <c:set var="procKey"
														value="<%=cn.micromoving.bcp.modules.act.utils.ActUtils.PD_HR_TRAIN_EXPERIENCE[0]%>">

													</c:set> <c:if test="${data2.procDef.key eq procKey}">
														<a
															href="${ctx}/hr/trainExperience/selfAudit?id=${data2.recordId}&status=${data2.status}">
															<b class="due">已审批</b>
														</a>
													</c:if></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							${page}
						</div>

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
	<script type="text/javascript">
		$(document).ready(function() {
			$('.table-message tbody tr').click(function() {
				$(this).toggleClass('resolved');
			});
		});
	</script>
</body>