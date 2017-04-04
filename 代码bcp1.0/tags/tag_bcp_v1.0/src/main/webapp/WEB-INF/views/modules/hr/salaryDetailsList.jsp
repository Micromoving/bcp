<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$('td,th,td div').css('text-align', 'center');
		$('td').css('padding', '0');
		$('.LHaBC').css('vertical-align', 'middle');
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/salaryDetails/list");
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


				<div class="span12">
					<div class="content">
						<div class="module">

							<div class="module-head">
								<h3>工资查询</h3>
							</div>
							<div class="module-body">
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="#" class="btn btn-primary pull-right"> 下载 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm"
											action="${ctx}/hr/salaryDetails/list"
											modelAttribute="salaryDetails"
											class="form-horizontal row-fluid" method="post">
											<sys:message content="${message}" />
											<form:hidden path="user.id" />
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />
											<div class="control-group">
												上报时间:&nbsp;&nbsp;
												<form:input path="startDateString" class="span2"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"></form:input>
												至
												<form:input path="endDateString" class="span2"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyyMM'})"></form:input>
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
													onclick="return page('','','${ctx}/hr/salaryDetails/list');">
													查询 </a>
											</div>
										</form:form>
									</div>
									<!--/表格栏-->
									<div class="media stream">

										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<th class="p30">序号</th>
												<th class="p100">基本信息</th>
												<th>工资发放项(单位：元)</th>
												<th>小计</th>
											</thead>
											<tbody>
												<c:forEach items="${salaryList}" var="salaryData"
													varStatus="status">
													<tr>
														<td class="LHaBC">${status.count}</td>
														<td class="LHaBC">${salaryData.yearMonth}<br>${salaryData.user.name}<br>${salaryData.user.office.name}
														</td>
														<td><c:forEach items="${taskList}" var="taskData">
																<div class="dv1">
																	<div class="dv2">${taskData.salaryItems.salaryItemsName}</div>
																	<div class="dv3">
																		${fns:formatDoubleNumber(fns:getFieldValue(salaryData,taskData.salaryItems.salaryMark),'#.00')}
																	</div>
																</div>
															</c:forEach></td>
														<td class="LHaBC">实发工资
															<div class="LHaBC singleTotal">${fns:formatDoubleNumber(salaryData.total,'###,###.00')}</div>
															应发工资
															<div>${fns:formatDoubleNumber(salaryData.initialWages,'###,###.00')}</div>
															扣除工资
															<div>${fns:formatDoubleNumber(salaryData.buckleWages,'###,###.00')}</div>
														</td>
													</tr>
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

										<!--/.media .stream-->
										${page}
									</div>

									<!--/.stream-list" -->
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
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>