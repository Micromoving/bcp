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
		$('#x').css('height', '14px');
		$('#x').css('width', '90px');
		$('#y').css('height', '14px');
		$('#y').css('width', '90px');
		$('#z').css('height', '14px');
		$('#z').css('width', '90px');
		$('#m').css('height', '14px');
		$('#m').css('width', '90px');
		$('#a').css('margin-top', '5px');
		$('#a').css('margin-left', '150px');
		$('#a').css('margin-bottom', '5px');
		$('#b').css('margin-top', '5px');
		$('#b').css('margin-left', '25px');
		$('#b').css('margin-bottom', '5px');
	});
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
		$("#batchForm").validate({});
	});
	function submitBatch(state) {
		$("#dataState").val(state);
		$("#batchForm").submit();
	}

	function auditSubmit(flag) {
		$("#auditForm").attr("action",
				"${ctx}/hr/reportedWorkloade/saveAudit?flag=" + flag);
		$("#auditForm").submit();
		return false;
	}
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/reportedWorkloade/list");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<div id="importBox" class="hide" noresize="noresize">
		<form:form id="auditForm" action="#" method="post" class="form-search"
			style="padding-left:20px;padding-top:20px;"
			modelAttribute="reportedWorkloade">
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
				<div class="span12">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>上报工作量</h3>
							</div>
							<div class="module-body">
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="hr:reportedWorkloade:import">
												<a
													href="${ctx }/hr/reportedWorkloade/upload?reportRecord.id=${reportedWorkloade.reportRecord.id}"
													class="btn btn-primary pull-right">导入 </a>
											</shiro:hasPermission>
											<shiro:hasPermission name="hr:reportedWorkloade:audit">
												<a id="auditButton" class="btn btn-primary pull-right">
													审核 </a>
											</shiro:hasPermission>
										</div>
									</div>
								</div>


								<!--/表格栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm"
											action="${ctx}/hr/reportedWorkloade/list?reportRecord.id=${reportedWorkloade.reportRecord.id}"
											modelAttribute="reportedWorkloade"
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
												<shiro:hasPermission name="hr:reportedWorkloade:search">
													<a href="#" class="btn btn-primary pull-right"
														onclick="return page('','','${ctx}/hr/reportedWorkloade/list?reportRecord.id=${reportedWorkloade.reportRecord.id}');">
														查询 </a>
												</shiro:hasPermission>

											</div>
										</form:form>
									</div>

									<div class="media stream">
										<form:form id="batchForm"
											action="${ctx}/hr/reportedWorkloade/batchSave"
											modelAttribute="reportedWorkloade"
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
											<form:hidden path="flag"></form:hidden>
											<div class="control-group" style="margin-bottom:6px;">
												上报记录年月
												<form:input class="span2 " path="reportRecord.yearMonth"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"></form:input>
											</div>
											<div class="media stream" style="margin-top:0px;padding:0px">

												<table
													class="table table-striped table-bordered table-condensed">
													<thead>
														<tr>
															<th class="p25">序号</th>
															<th class="p70">职员编号</th>
															<th class="p40">姓名</th>
															<th class="p135">院系</th>
															<th class="p10" >教师</th>
															<th class="p10">毕业<br>论文
															</th>
															<th class="p10">导师制</th>
															<th class="p10">公选</th>
															<th class="p10">学年<br>论文
															</th>
															<th class="p10">阅卷</th>
															<th class="p10">出题</th>
															<th class="p10">监考</th>
															<th class="p10">小班<br>讨论
															</th>
															<th class="p10">各类<br>竞赛
															</th>
															<th class="p10">其他</th>
															<th class="p10">合计</th>

														</tr>
													</thead>
													<tbody>
														<c:forEach varStatus="status" var="data" items="${list}">
															<tr>
																<td>${status.count}</td>
																<td>${data.user.loginName}</td>
																<td>${data.user.name }</td>
																<td>${data.user.office.name}</td>
																<td><input type="text" class="p25" name="teachingList[${status.index }]"
																	value="${data.teaching}"> </td>
																<td><input type="text" class="p25" name="dissertationList[${status.index }]"
																	value="${data.dissertation}"> </td>
																<td><input type="text" class="p25" name="tutorialSystemList[${status.index }]"
																	value="${data.tutorialSystem}"> </td>
																<td><input type="text" class="p25" name="optionalList[${status.index }]"
																	value="${data.optional}"> </td>
																<td><input type="text" class="p25" name="termPaperList[${status.index }]"
																	value="${data.termPaper}"> </td>
																<td><input type="text" class="p25" name="markingList[${status.index }]"
																	value="${data.marking}"> </td>
																<td><input type="text" class="p25" name="theTopicList[${status.index }]"
																	value="${data.theTopic}"> </td>
																<td><input type="text" class="p25" name="invigilatorList[${status.index }]"
																	value="${data.invigilator}"> </td>
																<td><input type="text" class="p25" name="smallClassDiscussionList[${status.index }]"
																	value="${data.smallClassDiscussion}"> </td>
																<td><input type="text" class="p25" name="allKindsOfCompetitionList[${status.index }]"
																	value="${data.allKindsOfCompetition}"> </td>
																<td><input type="text" class="p25" name="otherList[${status.index }]"
																	value="${data.other}"> </td>
																<td><input type="text" class="p25"
																	name="workloadList[${status.index }]"
																	value="${data.workload}"> <input type="hidden"
																	name="employeeIdList[${status.index }]"
																	value="${data.user.id}"> <input type="hidden"
																	name="dataIdList[${status.index }]" value="${data.id}"></td>
															</tr>
														</c:forEach>

													</tbody>
												</table>

												<div class="controls">

													<c:if
														test="${ empty reportedWorkloade.reportRecord.dataState||'1' eq reportedWorkloade.reportRecord.dataState||'4' eq reportedWorkloade.reportRecord.dataState}">
														<button class="btn btn-primary" onclick="submitBatch('1')">暂存</button>
														<button class="btn btn-primary" onclick="submitBatch('2')">提交</button>
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

			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>