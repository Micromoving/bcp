<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate({
			errorPlacement : function(error, element) {
				error.appendTo(element.next());
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>

				<!--/.span3-->


				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>工资管理</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="salaryInstance"
									action="${ctx}/hr/salaryInstance/save" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<form:hidden path="act.taskId" />
									<form:hidden path="act.taskName" />
									<form:hidden path="act.taskDefKey" />
									<form:hidden path="act.procInsId" />
									<form:hidden path="act.procDefId" />
									<form:hidden id="flag" path="act.flag" />
									<ul class="profile-tab nav nav-tabs">
										<div class="control-group">
											<label class="control-label">工资标题</label>
											<div class="controls">
												<form:input class="span4 required" htmlEscape="false"
													path="salaryExplain"></form:input>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">日期</label>
											<div class="controls">
												<form:input path="yearMonth" class="span4 required"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">参考工作量</label>
											<div class="controls">
												<form:input path="referenceWorkLoade" class="span4 required"></form:input>
											</div>
										</div>
										<li class="active"><a href="#activity" data-toggle="tab">工资项</a></li>
										<li><a href="#friends" data-toggle="tab">福利项</a></li>
										<li><a href="#insurance" data-toggle="tab">社保项</a></li>
									</ul>
									
									<div class="profile-tab-content tab-content">
										<div class="tab-pane fade active in" id="activity">
											<table
												class=" table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th>工资项</th>
														<th style="text-align:center">系数</th>
														<th style="text-align:center">工资项说明</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach varStatus="status"
														items="${salaryInstance.salaryInstanceTaskList}"
														var="data">
														<c:if test="${data.salaryItems.salaryType eq '1'}">

															<tr>
																<c:if test="${empty salaryInstance.id }">
																	<td><input value="${data.salaryItems.id}"
																		name="selectedItem[${ status.index}]"
																		class="checkbox inline" type="checkbox"
																		${data.salaryItems.useable eq '1'?"checked=\"checked\"":""}>&nbsp;&nbsp;${data.salaryItems.salaryItemsName}
																	</td>
																</c:if>
																<c:if test="${not empty salaryInstance.id }">
																	<td><input value="${data.salaryItems.id}"
																		name="selectedItem[${ status.index}]"
																		class="checkbox inline" type="checkbox"
																		${data.salaryItems.useable eq '1'?"checked=\"checked\"":""}>&nbsp;&nbsp;${data.salaryItems.salaryItemsName}
																	</td>
																</c:if>
																<td style="text-align:center"><input
																	value="${data.coefficient}" class="span8"
																	name="selectedCoefficient[${ status.index}]"></input></td>
																<td style="text-align:center">${data.salaryItems.remarks}</td>
															</tr>

														</c:if>
													</c:forEach>
												</tbody>
											</table>
											<br>

										</div>
										<div class="tab-pane fade" id="friends">
											<table
												class="table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th>福利项</th>
														<th style="text-align:center">福利项说明</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${salaryInstance.salaryInstanceTaskList}"
														var="data" varStatus="status">
														<c:if test="${data.salaryItems.salaryType eq '2'}">
															<tr>
																<c:if test="${empty salaryInstance.id }">
																	<td><input value="${data.salaryItems.id}"
																		name="selectedItem[${ status.index}]"
																		class="checkbox inline" type="checkbox">&nbsp;&nbsp;${data.salaryItems.salaryItemsName}</td>
																	<td>${data.salaryItems.remarks}</td>
																</c:if>
																<c:if test="${not empty salaryInstance.id }">
																	<td><input value="${data.salaryItems.id}"
																		name="selectedItem[${ status.index}]"
																		${data.salaryItems.useable eq '1'?"checked=\"checked\"":""}
																		class="checkbox inline" type="checkbox">&nbsp;&nbsp;${data.salaryItems.salaryItemsName}</td>
																	<td>${data.salaryItems.remarks}</td>
																</c:if>
															</tr>

														</c:if>
													</c:forEach>
												</tbody>
											</table>
											<br>

										</div>
										<div class="tab-pane fade" id="insurance">
											<table
												class=" table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th>社保项</th>
														<th style="text-align:center">社保项说明</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach varStatus="status"
														items="${salaryInstance.salaryInstanceTaskList}"
														var="data">
														<c:if test="${data.salaryItems.salaryType eq '3'}">

															<tr>
																<c:if test="${empty salaryInstance.id }">
																	<td><input value="${data.salaryItems.id}"
																		name="selectedItem[${ status.index}]"
																		class="checkbox inline" type="checkbox"
																		${data.salaryItems.useable eq '1'?"checked=\"checked\"":""}>&nbsp;&nbsp;${data.salaryItems.salaryItemsName}
																	</td>
																</c:if>
																<c:if test="${not empty salaryInstance.id }">
																	<td><input value="${data.salaryItems.id}"
																		name="selectedItem[${ status.index}]"
																		class="checkbox inline" type="checkbox"
																		${data.salaryItems.useable eq '1'?"checked=\"checked\"":""}>&nbsp;&nbsp;${data.salaryItems.salaryItemsName}
																	</td>
																</c:if>
																<td style="text-align:center">${data.salaryItems.remarks}</td>
															</tr>

														</c:if>
													</c:forEach>
												</tbody>
											</table>
											<br>

										</div>
									</div>
									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls" id="submit">
											<input type="submit" class="btn blue" id="submit"
												onclick="$('#flag').val('yes')" value="提交">
										</div>
									</div>
								</form:form>
								<!--/.form-horizontal row-fluid-->
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
