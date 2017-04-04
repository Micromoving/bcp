<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function() {
		$('td,th,td div').css('text-align', 'center');
		$('td').css('padding', '0');
		$('td input').css('padding', '0');
		$('.LHaBC').css('vertical-align', 'middle');
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm")
				.attr(
						"action",
						"${ctx}/hr/salaryInstance/initialResults?SalaryInstance.id=${salaryInstance.id}");
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
				"${ctx}/hr/salaryInstance/saveAudit?flag=" + flag);
		$("#auditForm").submit();
		return false;
	}
	$(document)
			.ready(
					function() {
						$("#btnExport")
								.click(
										function() {
											top.$.jBox
													.confirm(
															"确认要导出工资信息吗？",
															"系统提示",
															function(v, h, f) {
																if (v == "ok") {
																	$(
																			"#searchForm")
																			.attr(
																					"action",
																					"${ctx}/hr/salaryInstance/export?salaryInstance.id=${salaryInstance.id}");
																	$(
																			"#searchForm")
																			.submit();
																}
															},
															{
																buttonsFocus : 1
															});
											top.$('.jbox-body .jbox-icon').css(
													'top', '55px');
										});
						$("#btnImport")
								.click(
										function() {
											$
													.jBox(
															$("#importExcelBox")
																	.html(),
															{
																title : "导入数据",
																buttons : {
																	"关闭" : true
																},
																bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"	
																
															});
										});

					});
</script>
</head>
<body>
	<div id="importBox" class="hide" noresize="noresize">
		<form:form id="auditForm" action="#" method="post" class="form-search"
			style="padding-left:20px;padding-top:20px;"
			modelAttribute="salaryInstance">
			<form:hidden path="id" />
			<form:hidden path="act.taskId" />
			<form:hidden path="act.taskName" />
			<form:hidden path="act.taskDefKey" />
			<form:hidden path="act.procInsId" />
			<form:hidden path="act.procDefId" />
			<form:hidden id="flag" path="act.flag" />
			审核意见：
			<form:textarea path="act.comment" class="" rows="3"
				cssStyle="width:300px" />
			<br>
			<div style="width:185px;height:35px;margin:auto;margin-top:15px;">
				<input id="btnSubmit" class="btn btn-success " type="button"
					value="审核通过 " onclick="auditSubmit('yes')" /> &nbsp;&nbsp;&nbsp;
				<c:if
					test="${salaryInstance.act.taskDefKey ne 'salarymanager5' &&  salaryInstance.act.taskDefKey ne 'salarymanager1'}">
					<input id="btnSubmit" class="btn btn-danger" type="button"
						value="审核不通过" onclick="auditSubmit('no')" />
				</c:if>
				&nbsp;
			</div>
		</form:form>

	</div>
	<div id="importExcelBox" class="hide">
		<form id="importForm"
			action="${ctx}/hr/salaryInstance/import?salaryInstance.id=${salaryInstance.id}"
			method="post" enctype="multipart/form-data" class="form-search"
			style="padding-left:20px;text-align:center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width:330px" /><br /> <br /> <input id="btnImportSubmit"
				class="btn btn-primary" type="submit" value="   导    入   " /> <a
				href="${ctx}/hr/salaryInstance/import/template?salaryInstance.id=${salaryInstance.id}">下载模板</a>
		</form>
	</div>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>初始结果</h3>
							</div>
							<div class="module-body">
								<div class="media-body">
									<div class="stream-composer media">
										<div class="media-body">
											<div class="clearfix">
												<a id="btnExport" href="#"
													class="btn btn-primary pull-right"> 导出 </a>
												<c:if test="${empty salaryInstance.dataState}">
													<a href="#" id="btnImport"
														class="btn btn-primary pull-right"> 导入 </a>
												</c:if>
												<a id="auditButton" class="btn btn-primary pull-right">
													审核 </a>
											</div>
										</div>
									</div>
									<div class="stream-list">
										<div class="media stream">
											<form:form id="searchForm"
												action="${ctx}/hr/salaryInstance/initialResults?SalaryInstance.id=${salaryInstance.id}"
												modelAttribute="salaryDetails"
												class="form-horizontal row-fluid" method="post">
												<sys:message content="${message}"/>
												<input id="pageNo" name="pageNo" type="hidden"
													value="${page.pageNo}" />
												<input id="pageSize" name="pageSize" type="hidden"
													value="${page.pageSize}" />
												<!-- 归属部门 -->
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
													&nbsp;&nbsp;<a href="#" class="btn btn-primary pull-right"
														onclick="return page('','','${ctx}/hr/salaryInstance/initialResults?SalaryInstance.id=${salaryInstance.id}');">
														查询 </a>
												</div>
											</form:form>
										</div>

										<!--/.media .stream-->
									</div>
								</div>
							</div>
							<div class="module">
								<div class="module-head">
									<h3>${salaryInstance.yearMonth}</h3>
								</div>
								<div class="module-body">
									<div class="media-body">
										<div class="stream-headline">
											<table
												class="table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th class="p30">序号</th>
														<th class="p100">基本信息</th>
														<th>工资发放项(单位：元)</th>
														<th>小计</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${salaryList}" var="salaryData"
														varStatus="status">
														<input type="hidden"
															name="salaryDetailsList[${status.index}].id"
															value="${fns:getFieldValue(salaryData,'id')}">
														<form:form id="ajaxForm${status.index }"
															action="${ctx}/hr/salaryDetails/save"
															modelAttribute="salaryDetails"
															class="form-horizontal row-fluid" method="post">
															<input type="hidden" name="id" id="id"
																value="${fns:getFieldValue(salaryData,'id')}">

															<tr>
																<td class="LHaBC">${status.count}</td>
																<td class="LHaBC">${salaryData.user.name}<br>${salaryData.user.office.name}
																</td>
																<td><c:forEach items="${taskList}" var="taskData">
																		<div class="dv1">
																			<div class="dv2">${taskData.salaryItems.salaryItemsName}</div>
																			<div class="dv3">

																				<input type="text"
																					onkeyup='this.value = this.value.replace(/[^-?\d.]/g, "");'
																					onchange="ajaxSubmit('${ctx}/hr/salaryDetails/change',  'ajaxForm${status.index }','total${status.index}' )"
																					name="${taskData.salaryItems.salaryMark }"
																					value="${fns:formatDoubleNumber(fns:getFieldValue(salaryData,taskData.salaryItems.salaryMark),'#.00')}"
																					class="p55 input">

																			</div>
																		</div>
																	</c:forEach></td>
																<td>
																	<div id="total${status.index}">
																		实发工资
																		<div class="LHaBC singleTotal">${fns:formatDoubleNumber(salaryData.total,'###,###.00')}</div>
																		应发工资
																		<div>${fns:formatDoubleNumber(salaryData.initialWages,'###,###.00')}</div>
																		扣除工资
																		<div>${fns:formatDoubleNumber(salaryData.buckleWages,'###,###.00')}</div>
																	</div> <c:if test="${empty salaryInstance.dataState}">
																		<button type="button"
																			onclick='$.ajax({
																		  type: "POST",
																		  url: "${ctx}/hr/salaryDetails/save",
																		  data: $("#ajaxForm${status.index }").serialize(),
																	        success: function(data) {
																	         showTip("保存成功") ;
																	        }
																		});'
																			class="btn">保存</button>
																	</c:if>
																</td>
															</tr>
														</form:form>
													</c:forEach>
													<tr>
														<td colspan="4"><p
																style="text-align: left;padding: 5px;">
																合计：共${salaryList.size()}人&nbsp;
																<c:forEach items="${taskList}" var="taskData">
																	<c:if
																		test="${fns:getFieldValue(sumData,taskData.salaryItems.salaryMark)>0.1 }">
																
															&nbsp;${taskData.salaryItems.salaryItemsName}:
																	

																			${fns:formatDoubleNumber(fns:getFieldValue(sumData,taskData.salaryItems.salaryMark),'###,###.00')}

																</c:if>
																</c:forEach>
															</p></td>
													</tr>
												</tbody>
											</table>

											${page}
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--/.span9-->
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>