<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$('th').css('text-align', 'center');
		$('td').css('text-align', 'center');
	});
	function submitBatch(state) {
		$("#dataState").val(state);
		$("#batchForm").validate({
			errorPlacement : function(error, element) {
				error.appendTo(element.next());
			}
		}).submit();
	}
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/empPerformanceLevel/list");
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
				"${ctx}/hr/reportPerformance/saveAudit?flag=" + flag);
		$("#auditForm").submit();
		return false;
	}
	/*统计所选档数,在加载页面和选中单选框时都触发该函数*/
	$(function() {
		total();
		$(":radio").click(function() {
			total();
		});
		return false;
	});
	function total() {
		var count1 = 0;
		var count2 = 0;
		var count3 = 0;
		$(":radio:checked").each(function() {
			if ($(this).val() == '1') {
				count1++;
			} else if ($(this).val() == '2') {
				count2++;
			} else if ($(this).val() == '3') {
				count3++;
			}
		});
		$("#count1").text(count1);
		$("#count2").text(count2);
		$("#count3").text(count3);
	}
</script>
</head>
<body>
	<div id="importBox" class="hide" noresize="noresize">
		<form:form id="auditForm" action="#" method="post" class="form-search"
			style="padding-left:20px;padding-top:20px;"
			modelAttribute="empPerformanceLevel">
			<form:hidden path="reportRecord.id" />
			<form:hidden path="reportRecord.act.taskId" />
			<form:hidden path="reportRecord.act.taskName" />
			<form:hidden path="reportRecord.act.taskDefKey" />
			<form:hidden path="reportRecord.act.procInsId" />
			<form:hidden path="reportRecord.act.procDefId" />
			<form:hidden path="reportRecord.act.flag" />
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
								<h3>上报绩效档</h3>
							</div>
							<div class="module-body">
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<c:set var="status"
												value='<%=request.getParameter("status")%>'></c:set>
											<c:if test="${empty status}">
												<shiro:hasPermission name="hr:empPerformanceLevel:import">
													<c:if
														test="${ empty empPerformanceLevel.reportRecord.dataState||'1' eq empPerformanceLevel.reportRecord.dataState}">
														<a
															href="${ctx }/hr/empPerformanceLevel/upload?reportRecord.id=${empPerformanceLevel.reportRecord.id}"
															class="btn btn-primary pull-right">导入 </a>
													</c:if>
												</shiro:hasPermission>
												<shiro:hasAnyPermissions name="hr:reportPerformance:audit">

													<a id="auditButton" class="btn btn-primary pull-right">
														审核 </a>
												</shiro:hasAnyPermissions>
											</c:if>
										</div>
									</div>
								</div>


								<!--/表格栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm"
											action="${ctx}/hr/empPerformanceLevel/list?reportRecord.id=${empPerformanceLevel.reportRecord.id}"
											modelAttribute="empPerformanceLevel"
											class="form-horizontal row-fluid" method="post">
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
												&nbsp;&nbsp;
												<shiro:hasPermission name="hr:empPerformanceLevel:search">
													<a href="#" class="btn btn-primary pull-right"
														onclick="return page('','','${ctx}/hr/empPerformanceLevel/list?reportRecord.id=${empPerformanceLevel.reportRecord.id}');">
														查询 </a>
												</shiro:hasPermission>
											</div>
										</form:form>
									</div>
									<div class="media stream">
										<form:form id="batchForm"
											action="${ctx}/hr/empPerformanceLevel/batchSave"
											modelAttribute="empPerformanceLevel"
											class="form-horizontal row-fluid" method="post">
											<form:hidden path="dataState" />
											<form:hidden path="reportRecord.dataClassification" />
											<form:hidden path="reportRecord.act.taskId" />
											<form:hidden path="reportRecord.act.taskName" />
											<form:hidden path="reportRecord.act.taskDefKey" />
											<form:hidden path="reportRecord.act.procInsId" />
											<form:hidden path="reportRecord.act.procDefId" />
											<form:hidden id="flag" path="reportRecord.act.flag" />
											<form:hidden path="reportRecord.id" />

											<div class="media stream" style="margin-top:0px;padding:0px">
												<div class="control-group" style="margin-bottom:6px;">

													<font color="red">*</font>上报记录年月
													<form:input class="span2 required"
														path="reportRecord.yearMonth"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"></form:input>
													&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>生效日期
													<form:input class="span2 required" path="startDate"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"></form:input>
													至
													<form:input class="span2 required" path="endDate"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"></form:input>
												</div>
												<table
													class="table table-striped table-bordered table-condensed">
													<thead>
														<tr>
															<th class="p55">序号</th>
															<th class="p85">职员编号</th>
															<th class="p40">姓名</th>
															<th class="p90">院系</th>
															<th class="p55">职级</th>
															<th class="p90">绩效档</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach varStatus="status" var="data" items="${list}">
															<tr>
																<td>${status.count}</td>
																<td>${data.user.loginName}</td>
																<td>${data.user.name }</td>
																<td>${data.user.office.name}</td>
																<td>${fns:getDictLabel(data.professionalLevel,'professional_level','')}</td>
																<td><input type="hidden"
																	name="dataIdList[${status.index}]" value="${data.id}">
																	<input type="hidden"
																	name="employeeIdList[${status.index}]"
																	value="${data.user.id}">
																	<div
																		${(not empty empPerformanceLevel.reportRecord.act.procInsId && empPerformanceLevel.reportRecord.dataState ne '1')?" style=\"display:none\"":""}>

																		<input type="radio"
																			${data.performanceLevel eq '1'?" checked=\"checked\"":"" }
																			value="1"
																			name="performanceLevelList[${status.index}]">一档
																		<input type="radio" value="2"
																			${data.performanceLevel eq '2'?" checked=\"checked\"":"" }
																			name="performanceLevelList[${status.index}]">二档
																		<input type="radio" value="3"
																			${data.performanceLevel eq '3'?" checked=\"checked\"":"" }
																			name="performanceLevelList[${status.index}]">三档
																	</div> <c:if
																		test="${not empty empPerformanceLevel.reportRecord.act.procInsId && empPerformanceLevel.reportRecord.dataState ne '1' }">
																		${fns:getDictLabel(data.performanceLevel,"performance_level","")}
																	</c:if></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												一档：<span id="count1">0</span>&nbsp;&nbsp;二档：<span
													id="count2">0</span>&nbsp;&nbsp;三挡：<span id="count3">0</span>
												<div class="controls">
													<c:if
														test="${empty empPerformanceLevel.reportRecord.act.procInsId && empty empPerformanceLevel.reportRecord.dataState}">
														<button class="btn btn-primary" onclick="submitBatch('1')">暂存</button>
														<button class="btn btn-primary" onclick="submitBatch('2')">提交</button>
													</c:if>
													<c:if
														test="${empPerformanceLevel.reportRecord.dataState eq '1'}">
														<button class="btn btn-primary" onclick="submitBatch('1')">暂存</button>
														<button class="btn btn-primary" onclick="submitBatch('2')">提交</button>
													</c:if>
												</div>
											</div>
										</form:form>
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
			</div>
			<!--/.container-->
		</div>
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>