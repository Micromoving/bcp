<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/annualSalaryPersonnel/detail");
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
								<h3>增加年薪制人员</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">

								<!--/搜索栏-->
								<div class="stream-list">

									<div class="media stream">
									<form:form id="searchForm"
											action="${ctx}/hr/annualSalaryPersonnel/detail"
											modelAttribute="annualSalaryPersonnel"
											class="form-horizontal row-fluid" method="post">

										<!-- 归属部门 -->
											<div class="control-group">
												&nbsp;&nbsp;&nbsp;部门
												<sys:treeselect id="office" name="salEmpView.office.id"
													value="${salEmpView.office.id}" labelName="salEmpView.office.name"
													labelValue="${salEmpView.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />

												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓名
												<form:input name="salEmpView.name" class="span2" path="salEmpView.name"
													type="text"></form:input>
													
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 工号
												<form:input name="salEmpView.loginName" class="span2" path="salEmpView.loginName"
													type="text"></form:input>
                                                <input type="submit" class="btn btn-primary pull-right"
													id="submit " value="查询">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</div>
										</form:form>
									</div>

									<!--/.media .stream-->
									<!--/表格栏-->
									<div class="media stream">
									
											<div class="media stream">
											
												<sys:message content="${message}" />


												<table
													class="table table-striped table-bordered table-condensed">
													<thead>
														<tr>
															<th >序号</th>
															<th>工号</th>
															<th >姓名</th>
															<th>证件号码</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach var="data" items="${list}" varStatus="status">
														<tr>
															<td>${status.count}</td>
															<td> ${data.salEmpView.loginName} </td>
															<td>${data.salEmpView.name}</td>
															<td>${data.salEmpView.papersNumber }</td>
															<td><a href="${ctx}/hr/annualSalaryPersonnel/form?salEmpView.id=${data.salEmpView.id}">增加年薪制</a></td>
														</tr>
														</c:forEach>
													</tbody>
												</table>

											</div>
											<!--/.media .stream-->
									
									</div>
									<!--/.module-body-->
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