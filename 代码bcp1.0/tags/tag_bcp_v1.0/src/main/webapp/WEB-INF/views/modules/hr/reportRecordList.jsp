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
			.attr(
				"action",
				"${ctx}/hr/reportRecord/list?salaryInstance.id=${reportRecored.salaryInstance.id}");
		$("#searchForm").submit();
		return false;
	}
	function change() {
		var select = document.getElementById('salaryInstanceId');
		document.getElementById("salId").value = select.value;
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
						<div class="module">
							<div class="module-head">
								<h3>上报记录</h3>
							</div>
							<div class="module-body">
								<div class="clearfix">

									<c:if test="${reportRecored.dataClassification   eq '1' }">
										<a
											href="${ctx}/hr/reportPerformance/list?postType=2&reportRecord.dataClassification=1&procDefId=${process.id}"
											class="btn btn-primary pull-right">上报</a>
									</c:if>
									<c:if test="${reportRecored.dataClassification  eq '3'}">
										<a href="${ctx}/hr/dutyDetail/list?procDefId=${process.id}&reportRecord.dataClassification=3"
											class="btn btn-primary pull-right">上报</a>
									</c:if>
									<c:if test="${reportRecored.dataClassification  eq '2' }">
										<a
											href="${ctx}/hr/reportedWorkloade/list?procDefId=${process.id}"
											class="btn btn-primary pull-right">上报</a>
									</c:if>
									<c:if test="${reportRecored.dataClassification  eq '4' }">
										<a
											href="${ctx}/hr/empPerformanceLevel/list?procDefId=${process.id}&reportRecord.dataClassification=4"
											class="btn btn-primary pull-right">上报</a>
									</c:if>

									<c:if test="${reportRecored.dataClassification   eq '5' }">
										<a
											href="${ctx}/hr/reportPerformance/list?procDefId=${process.id}&postType=4&reportRecord.dataClassification=5"
											class="btn btn-primary pull-right">上报</a>
									</c:if>

								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm"
											action="${ctx}/hr/reportRecord/list?salaryInstance.id=${reportRecored.salaryInstance.id}"
											modelAttribute="reportRecord"
											class="form-horizontal row-fluid" method="post">
											<sys:message content="${message}" />
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />
											<!-- 归属部门 -->
											<div class="control-group">
												部门
												<sys:treeselect id="office" name="office.id"
													value="${office.id}" labelName="office.name"
													labelValue="${office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />
												<c:if test="${empty reportRecord.dataClassification}">
												数据分类
												<form:select path="dataClassification" class="span2">
														<form:option value="请选择"></form:option>
														<form:options
															items="${fns:getDictList('data_classification')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</c:if>
												<a href="#" class="btn btn-primary pull-right"
													onclick="return page('','','${ctx}/hr/reportRecord/list?salaryInstance.id=${reportRecored.salaryInstance.id}');">
													查询 </a>
											</div>
										</form:form>
									</div>
									<!--/表格栏-->
									<form:form id="searchForm" action="${ctx}/hr/reportRecord/save"
										modelAttribute="reportRecord"
										class="form-horizontal row-fluid" method="post">
										<form:hidden path="salaryInstance.id" />
										<div class="media stream">
											<sys:message content="${message}" />
											<form:hidden path="dataState"/>
											<form:hidden path="dataClassification"/>
											<form:hidden path="postType"/>
											<table
												class="table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th class="p30">序号</th>
														<th>时间</th>
														<th class="p65">数据分类</th>
														<th>部门</th>
														<th>上传者</th>
														<th>所属工资实例</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="data" items="${page.list}"
														varStatus="status">
														<tr>
															<td style="text-align: center;">${status.count }</td>
															<td>${data.yearMonth}</td>
															<td>${fns:getDictLabel(data.dataClassification,'data_classification'," ")}</td>
															<td>${data.office.name }</td>
															<td>${data.user.name }</td>
															<td>
															<c:if test="${empty data.salaryInstance.id}">
																	<input type="hidden"
																		name="recordIdList[${status.index }]"
																		value="${data.id}">
																	<select name="salaryIdList[${status.index }]"
																		class="span12">

																		<c:forEach var="optData" items="${salaryList}">

																			<option value="${optData.id }">${optData.salaryExplain }</option>
																		</c:forEach>

																	</select>
																</c:if> <c:if test="${not empty data.salaryInstance.id}">
																${data.salaryInstance.salaryExplain}
															</c:if></td>
															<td>
															<c:if
																	test="${'1' eq data.dataState &&  empty data.act.procInsId}">草稿状态</c:if> <c:if
																	test="${'2' eq data.dataState || not empty data.act.procInsId}">${data.act.taskName}</c:if>
																<c:if test="${'3' eq data.dataState }">已完成</c:if> &nbsp;<c:if
																	test="${data.dataClassification   eq '1' }">
																	<a
																		href="${ctx}/hr/reportPerformance/form?procDefId=${process.id}&reportRecord.id=${data.id}&postType=2">详情</a>
																</c:if> <c:if test="${data.dataClassification   eq  '2' }">
																	<a
																		href="${ctx}/hr/reportedWorkloade/form?reportRecord.id=${data.id}">详情</a>
																		<a href="${ctx}/hr/reportedWorkloade/downloade?reportRecord.id=${data.id}">下载</a>
																</c:if> <c:if test="${data.dataClassification   eq '3' }">
																	<a
																		href="${ctx}/hr/dutyDetail/form?reportRecord.id=${data.id}">详情</a>
																</c:if> <c:if test="${data.dataClassification   eq '4' }">
																	<a
																		href="${ctx}/hr/empPerformanceLevel/form?reportRecord.id=${data.id}">详情</a>
																</c:if> <c:if test="${data.dataClassification   eq '5' }">
																	<a
																		href="${ctx}/hr/reportPerformance/form?procDefId=${process.id}&reportRecord.id=${data.id}&postType=4">详情</a>
																</c:if>

															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<!--/提交按钮-->
										<div class="control-group">
											<div class="controls" id="submit">
												<input type="submit" class="btn btn-primary" id="submit "
													value="提交">
											</div>
										</div>
										<!--/.media .stream-->
									</form:form>
								</div>
								${page}
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

	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>