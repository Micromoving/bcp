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
		$("#searchForm").attr("action", "${ctx}/hr/notSeriesSalaryItems/list");
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
						<div class="module">
							<div class="module-head">
								<h3>非在编工资项</h3>
							</div>
							<div class="module-body">
								<!--/.media .stream-->


								<div class="media stream">
									<div class="media-body">
									<sys:message content="${message}"></sys:message>
										<form:form id="searchForm"
											action="${ctx}/hr/notSeriesSalaryItems/list"
											modelAttribute="notSeriesSalaryItems"
											class="form-horizontal row-fluid" method="post">

											<!-- 筛选条件 -->
											<div class="control-group">
												&nbsp;&nbsp;&nbsp;姓名
												<form:input class="span3" htmlEscape="false" path="sal.name" />
												&nbsp;&nbsp;&nbsp; 部门
												<sys:treeselect id="office" name="sal.office.id"
													value="${sal.office.id}" labelName="sal.office.name"
													labelValue="${sal.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input"
													allowClear="true" notAllowSelectParent="false" />
												<br> <br> &nbsp;&nbsp;&nbsp;工号
												<form:input class="span3" htmlEscape="false"
													path="sal.LoginName" />
												&nbsp;&nbsp;&nbsp;身份证
												<form:input class="span3" htmlEscape="false"
													path="sal.papersNumber" />
												<input type="submit" class="btn btn-primary pull-right"
													id="submit " value="查询">
											</div>
										</form:form>
										<form:form id="searchForm"
											action="${ctx}/hr/notSeriesSalaryItems/save"
											modelAttribute="notSeriesSalaryItems"
											class="form-horizontal row-fluid" method="post">
											
											<c:if test="${not empty list}">
												<div class="media stream">
													<table
														class="table table-striped table-bordered table-condensed">
														<thead>
															<tr>
																<th class="p27">序号</th>
																<th>工号</th>
																<th class="p55">姓名</th>
																<th>部门</th>
																<th>工资项</th>
															</tr>
														</thead>
														<c:forEach items="${list}" var="data" varStatus="status">
															<tbody>
																<tr>
																	<td>${status.count }</td>
																	<td>${data.sal.loginName }<input type="hidden"
																		name="employeeList[${status.index }]"
																		value="${data.sal.id}"></td>
																	<td>${data.sal.name }</td>
																	<td>${data.sal.office }</td>
																	<c:forEach varStatus="itemStatus"
																		items="${data.salaryItemsList}" var="itemData">
																		<td><input value="${data.sal.id}${itemData.id}"
																			id="selectedItems" 
																			name="selectedItems[${itemStatus.index}]" 
																			class="checkbox inline" type="checkbox"
																			${itemData.useable eq '1'?"checked=\"checked\"":""}>&nbsp;&nbsp;${itemData.salaryItemsName}
																			</td>
																	</c:forEach>
																</tr>
															</tbody>
														</c:forEach>
													</table>
													<br>
													<!--/提交按钮-->
													<div class="control-group">
														<div class="controls" id="submit">
															<input type="submit" class="btn btn-primary" id="submit "
																value="提交">
														</div>
													</div>
													<!--/.media .stream-->
												</div>
											</c:if>
										</form:form>
									</div>
								</div>
							</div>
							<!--/.media .stream-->
						</div>
					</div>
					<!--/.stream-list-->
				</div>
				<!--/.module-body-->
			</div>
			<!--/.module-->

		</div>
		<!--/.content-->
	</div>
	<!--/.span9-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>