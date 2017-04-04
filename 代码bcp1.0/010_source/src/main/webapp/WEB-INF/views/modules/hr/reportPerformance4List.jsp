<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function submitBatch(state) {
		$("#dataState").val(state);
		$("#batchForm").submit();
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

	function performanceOnchange(data) {
		for (var i = 0; i < ${list.size()}; i++) {
			if (data == "1") {
				$("#performanceSalary" + i).val($("#latestSal" + i).text());
			} else {
				$("#performanceSalary" + i).val("");
			}
		}
	}
</script>
</head>
<body>
	<div id="importBox" class="hide" noresize="noresize">
		<form:form id="auditForm" action="#" method="post" class="form-search"
			style="padding-left:20px;padding-top:20px;"
			modelAttribute="reportPerformance">
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
								<h3>上报绩效</h3>
							</div>
							<div class="module-body">
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<c:set var="status"
												value='<%=request.getParameter("status")%>'></c:set>
											<c:if test="${empty status}">
												<shiro:hasPermission name="hr:reportPerformance:import">
													<c:if
														test="${ empty reportPerformance.reportRecord.dataState||'1' eq reportPerformance.reportRecord.dataState}">
														<a href="${ctx}/hr/reportPerformance/upload"
															class="btn btn-primary pull-right">导入 </a>
													</c:if>
												</shiro:hasPermission>
												<shiro:hasPermission name="hr:reportPerformance:audit">
													<a id="auditButton" class="btn btn-primary pull-right">
														审核 </a>
												</shiro:hasPermission>
											</c:if>
										</div>
									</div>
								</div>


								<!--/表格栏-->
								<div class="stream-list">

									<!--/.media .stream-->
									<!--/表格栏-->
									<div class="media stream">
										<form:form id="batchForm"
											action="${ctx}/hr/reportPerformance/batchSave"
											modelAttribute="reportPerformance"
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
											<div class="control-group" style="margin-bottom:6px;">

												<font color="red">*</font>上报记录年月
												<form:input class="span2 required"
													path="reportRecord.yearMonth"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"></form:input>
												&nbsp;&nbsp;数据来源
												<form:select onchange="performanceOnchange($(this).val())"
													path="dataFrom" class="span3">
													<form:option label="无" value="0" />
													<form:option label="上月所发绩效" value="1" />
												</form:select>
											</div>
											<sys:message content="${message}" />
											<div class="media stream" style="margin-top:0px;padding:0px">
												<table
													class="table table-striped table-bordered table-condensed">
													<thead>
														<tr>
															<th class="p40">序号</th>
															<th class="p70">职员编号</th>
															<th class="p55">姓名</th>
															<th class="p90">院系</th>
															<th class="p120">应发绩效(单位：元)</th>
															<th class="p120">上月绩效(单位：元)</th>
															<th class="p120">上报绩效(单位：元)</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="data" items="${list}" varStatus="status">
															<tr>
																<td>${status.count }</td>
																<td>${data.user.loginName}</td>
																<td>${data.user.name}</td>
																<td>${data.user.office.name}</td>
																<td>一档：${data.stdSal1} <c:if
																		test="${not empty data.stdSal2}">
																		<br>二档：${data.stdSal2}</c:if> <c:if
																		test="${not empty data.stdSal3}">
																		<br>三档：${data.stdSal3}
																</c:if>
																</td>
																<td id="latestSal${status.index}">${data.latestSal}<c:if
																		test="${not empty data.latestSal}"></c:if>
																</td>
																<td><input type="text"
																	id="performanceSalary${status.index}"
																	name="performanceSalaryList[${status.index}]"
																	class="span8" value="${data.performanceSalary}">
																	<input type="hidden"
																	name="employeeIdList[${status.index }]"
																	value="${data.user.id}"> <input type="hidden"
																	name="dataIdList[${status.index}]" value="${data.id}"></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<c:if
													test="${ empty reportPerformance.reportRecord.dataState||'1' eq reportPerformance.reportRecord.dataState}">
													<div class="controls">
														<button class="btn btn-primary" onclick="submitBatch('1')">暂存</button>
														<button class="btn btn-primary" onclick="submitBatch('2')">提交</button>

													</div>
												</c:if>
												<!--/.media .stream-->
											</div>
										</form:form>
									</div>
								</div>
							</div>
							<!--/.module-->
						</div>
					</div>
					<!--/.content-->
				</div>
				<!--/.span9-->
			</div>
			<!--/.container-->
		</div>
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>

