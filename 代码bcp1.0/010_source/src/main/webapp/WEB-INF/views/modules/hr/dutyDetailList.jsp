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
		$("#searchForm")
				.attr("action",
						"${ctx}/hr/dutyDetail/list?reportRecord.id=${dutyDetail.reportRecord.id}");
		$("#searchForm").submit();
		return false;
	}

	$(document).ready(function() {
		$("#auditButton").click(function() {
			$.jBox($("#importBox").html(), {
				title : "审核",
				width : 440,
				height : 220
			});
			$(".jbox-button-panel").hide();
			$(".jbox-title-panel").css("pointer-events", "none");
		});
	});

	function auditSubmit(flag) {
		$("#auditForm").attr("action",
				"${ctx}/hr/dutyDetail/saveAudit?flag=" + flag);
		$("#auditForm").submit();
		return false;
	}

	function submitBatch(state) {
		$("#dataState").val(state);
		$("#batchForm").submit();
	}
</script>
</head>
<body>
	<div id="importBox" class="hide" noresize="noresize">
		<form:form id="auditForm" action="#" method="post" class="form-search"
			style="padding-left:20px;padding-top:20px;"
			modelAttribute="dutyDetail">
			<form:hidden path="reportRecord.id" />
			<form:hidden path="reportRecord.act.taskId" />
			<form:hidden path="reportRecord.act.taskName" />
			<form:hidden path="reportRecord.act.taskDefKey" />
			<form:hidden path="reportRecord.act.procInsId" />
			<form:hidden path="reportRecord.act.procDefId" />
			<form:hidden path="flag" />
			
			审核意见：
			<form:textarea path="reportRecord.act.comment" rows="3"
				cssStyle="width:300px" />
			<br>
			<div style="width:185px;height:35px;margin:auto;margin-top:15px;">
				<input id="btnSubmit" class="btn btn-success " type="button"
					value="审核通过 " onclick="auditSubmit('yes')" /> &nbsp;&nbsp;&nbsp; <input
					id="btnSubmit" class="btn btn-danger" type="button" value="审核不通过"
					onclick="auditSubmit('no')" /> &nbsp;
			</div>
		</form:form>
	</div>

	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>上报值班</h3>
							</div>
							<div class="module-body">
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<c:set var="status"
												value='<%=request.getParameter("status")%>'></c:set>
											<c:if test="${empty status}">
												<shiro:hasPermission name="hr:dutyDetail:import">
													<c:if
														test="${ empty dutyDetail.reportRecord.dataState||'1' eq dutyDetail.reportRecord.dataState}">
														<a href="${ctx }/hr/dutyDetail/upload"
															class="btn btn-primary pull-right">导入 </a>
													</c:if>
												</shiro:hasPermission>
												<shiro:hasPermission name="hr:dutyDetail:audit">
													<a id="auditButton" class="btn btn-primary pull-right">
														审核 </a>
												</shiro:hasPermission>
											</c:if>

										</div>
									</div>
								</div>
								<!--/表格栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm"
											action="${ctx}/hr/dutyDetail/list?reportRecord.id=${dutyDetail.reportRecord.id}"
											modelAttribute="dutyDetail" class="form-horizontal row-fluid"
											method="post">
											<sys:message content="${message}" />

											<!-- 筛选条件 -->
											<div class="control-group">
												部门：
												<sys:treeselect id="office" name="user.office.id"
													value="${user.office.id}" labelName="user.office.name"
													labelValue="${user.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />
												&nbsp;&nbsp; 姓名
												<form:input name="user.name" class="span2" path="user.name"
													type="text"></form:input>
												&nbsp;&nbsp; <a href="#" class="btn btn-primary pull-right"
													onclick="return page('','','${ctx}/hr/dutyDetail/list?reportRecord.id=${dutyDetail.reportRecord.id}');">
													查询 </a>

											</div>

										</form:form>
									</div>
									<div class="media stream">
										<form:form id="batchForm"
											action="${ctx}/hr/dutyDetail/batchSave"
											modelAttribute="dutyDetail" class="form-horizontal row-fluid"
											method="post">
											<form:hidden path="dataState" />
											<form:hidden path="reportRecord.dataClassification" />
											<form:hidden path="reportRecord.act.taskId" />
											<form:hidden path="reportRecord.act.taskName" />
											<form:hidden path="reportRecord.act.taskDefKey" />
											<form:hidden path="reportRecord.act.procInsId" />
											<form:hidden path="reportRecord.act.procDefId" />
											<form:hidden id="flag" path="reportRecord.act.flag" />
											<form:hidden path="reportRecord.id" />
											<div class="control-group" style="margin-bottom:6px;">
												上报记录年月
												<form:input class="span2" path="reportRecord.yearMonth"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"></form:input>
											</div>
											<div style="float:right;">单位：天</div>

											<table
												class="table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th class="p30">序号</th>
														<th class="p60">职员编号</th>
														<th class="p60">姓名</th>
														<th class="p100">院系</th>
														<th>中午</th>
														<th>晚上</th>
														<th>周六日</th>
														<th>节假日</th>
													</tr>
												</thead>

												<tbody>

													<c:forEach var="data" items="${list}" varStatus="status">
														<tr>
															<td style="text-align: center;">${status.count}</td>
															<td>${data.user.loginName}</td>
															<td>${data.user.name }</td>
															<td>${data.user.office.name}</td>
															<td><input type="text" class="span10"
																name="noonDutyDaysList[${status.index}]"
																value="${data.noonDutyDays}"> <input
																type="hidden" name="employeeIdList[${status.index}]"
																value="${data.user.id}"> <input type="hidden"
																name="dataIdList[${status.index}]" value="${data.id}">
															</td>
															<td><input type="text" class="span10"
																name="nightDutyDaysList[${status.index}]"
																value="${data.nightDutyDays}"></td>
															<td><input type="text" class="span10"
																name="weekwedsDutyDaysList[${status.index}]"
																value="${data.weekendsDutyDays}"></td>
															<td><input type="text" class="span10"
																name="holidayDatyDuysList[${status.index}]"
																value="${data.holidayDutyDays}"></td>

														</tr>
													</c:forEach>

												</tbody>
											</table>

											<div class="controls">
												<div class="controls">
													<c:if
														test="${ empty dutyDetail.reportRecord.dataState||'1' eq dutyDetail.reportRecord.dataState}">
														<div class="controls">
															<button class="btn btn-primary"
																onclick="submitBatch('1')">暂存</button>
															<button class="btn btn-primary"
																onclick="submitBatch('2')">提交</button>
														</div>
													</c:if>

												</div>
											</div>
											<!--/.media .stream-->
										</form:form>
									</div>
								</div>

								<!--/.module-body-->
							</div>
							<!--/.module-->

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

	<div class="footer">
		<div class="container">
			<b class="copyright">山西大学商务学院信息学院微动--Micromoving &copy </b> All
			rights reserved.
		</div>
	</div>
</body>
</html>
