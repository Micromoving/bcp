<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function batchSubmit() {
		$("#batchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenuSub.jsp"></jsp:include>
				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>系列工资标准</h3>
							</div>
							<div class="module-body">
								<form:form id="batchForm" class="form-horizontal row-fluid"
									modelAttribute="salaryStandard"
									action="${ctx }/hr/salaryStandard/batchSave" method="post">
									<sys:message content="${message}" />
									<form:hidden path="postType" />
									<div class="profile-tab-content tab-content">
										<c:if test="${salaryStandard.salaryPlan.planStatus ne '1'}">
											<a href="#" class="btn btn-primary pull-right"
												onclick="return confirmx('确认要保存修改结果吗？',batchSubmit)"> 保存
											</a>
										</c:if>
										<br> <br>
										<table
											class="table table-striped table-bordered table-condensed">
											<tbody>
												<tr>
													<td colspan="6" style="text-align:center"><div>
															<b>新聘人员见习、初期、试用期工资标准</b>
														</div></td>
												</tr>
												<tr>
													<td style="text-align:center" width="120px">
														<!--岗位类别--> 岗位类别
													</td>
													<td style="text-align:center"><div
															class="control-group">工资项目</div></td>
													<td style="text-align:center"><div
															class="control-group">硕士研究生</div></td>
													<td style="text-align:center"><div
															class="control-group">其他</div></td>
													<td style="text-align:center"><div
															class="control-group">本科</div></td>
													<td style="text-align:center"><div
															class="control-group">专科</div></td>
												</tr>
												<tr>
													<td rowspan="3"><div
															style="line-height:110px; text-align:center">教师岗位</div></td>
													<td width="10%">

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">职级工资</div>
													</td>

													<c:forEach varStatus="status" var="data" items="${list}">


														<c:if test="${data.postType  eq '1' }">
															<td style="text-align:center"><input
																name="professionalLevelSalaryList[${status.index }]"
																style="width:40px"
																value="${data. professionalLevelSalary.intValue()}"
																type="text"><input type="hidden"
																name="idList[${status.index }]" value="${data.id}" /></td>
														</c:if>

													</c:forEach>

												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">岗位工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${list}">
														<c:if test="${data.postType  eq '1' }">
															<td style="text-align:center"><input
																name="postSalaryList[${status.index }]"
																style="width:40px"
																value="${data. postSalary.intValue()}" type="text"></td>
														</c:if>
													</c:forEach>
												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">绩效工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${perList}">
														<c:if test="${data.salarystandard.postType  eq '1' }">
															<td style="text-align:center"><input
																value="${data. performanceSalary.intValue()}"
																name="performanceLevel.performanceSalaryList[${status.index }]"
																style="width:40px" type="text"> <input
																type="hidden"
																name="performanceLevel.perIdList[${status.index }]"
																value="${data.id}" /></td>
														</c:if>
													</c:forEach>

												</tr>
												<tr>
													<td rowspan="3"><div
															style="line-height:110px; text-align:center">教辅岗位</div></td>
													<td width="10%">

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">职级工资</div>
													</td>

													<c:forEach varStatus="status" var="data" items="${list}">


														<c:if test="${data.postType  eq '3' }">
															<td style="text-align:center"><input
																name="professionalLevelSalaryList[${status.index }]"
																style="width:40px"
																value="${data. professionalLevelSalary.intValue()}"
																type="text"><input type="hidden"
																name="idList[${status.index }]" value="${data.id}" /></td>
														</c:if>

													</c:forEach>

												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">岗位工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${list}">
														<c:if test="${data.postType  eq '3' }">
															<td style="text-align:center"><input
																name="postSalaryList[${status.index }]"
																style="width:40px"
																value="${data. postSalary.intValue()}" type="text"></td>
														</c:if>
													</c:forEach>
												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">绩效工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${perList}">
														<c:if test="${data.salarystandard.postType  eq '3' }">
															<td style="text-align:center"><input
																value="${data. performanceSalary.intValue()}"
																name="performanceLevel.performanceSalaryList[${status.index }]"
																style="width:40px" type="text"> <input
																type="hidden"
																name="performanceLevel.perIdList[${status.index }]"
																value="${data.id}" /></td>
														</c:if>
													</c:forEach>

												</tr>
												<tr>
													<td rowspan="3"><div
															style="line-height:110px; text-align:center">职员岗位</div></td>
													<td width="10%">

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">职级工资</div>
													</td>

													<c:forEach varStatus="status" var="data" items="${list}">


														<c:if test="${data.postType  eq '2' }">
															<td style="text-align:center"><input
																name="professionalLevelSalaryList[${status.index }]"
																style="width:40px"
																value="${data. professionalLevelSalary.intValue()}"
																type="text"><input type="hidden"
																name="idList[${status.index }]" value="${data.id}" /></td>
														</c:if>

													</c:forEach>

												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">岗位工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${list}">
														<c:if test="${data.postType  eq '2' }">
															<td style="text-align:center"><input
																name="postSalaryList[${status.index }]"
																style="width:40px"
																value="${data. postSalary.intValue()}" type="text"></td>
														</c:if>
													</c:forEach>
												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">绩效工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${perList}">
														<c:if test="${data.salarystandard.postType  eq '2' }">
															<td style="text-align:center"><input
																value="${data. performanceSalary.intValue()}"
																name="performanceLevel.performanceSalaryList[${status.index }]"
																style="width:40px" type="text"> <input
																type="hidden"
																name="performanceLevel.perIdList[${status.index }]"
																value="${data.id}" /></td>
														</c:if>
													</c:forEach>

												</tr>
												<tr>
													<td rowspan="3"><div
															style="line-height:110px; text-align:center">专职辅导员岗位</div></td>
													<td width="10%">

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">职级工资</div>
													</td>

													<c:forEach varStatus="status" var="data" items="${list}">


														<c:if test="${data.postType  eq '4' }">
															<td style="text-align:center"><input
																name="professionalLevelSalaryList[${status.index }]"
																style="width:40px"
																value="${data. professionalLevelSalary.intValue()}"
																type="text"><input type="hidden"
																name="idList[${status.index }]" value="${data.id}" /></td>
														</c:if>

													</c:forEach>

												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">岗位工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${list}">
														<c:if test="${data.postType  eq '4' }">
															<td style="text-align:center"><input
																name="postSalaryList[${status.index }]"
																style="width:40px"
																value="${data. postSalary.intValue()}" type="text"></td>
														</c:if>
													</c:forEach>
												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">绩效工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${perList}">
														<c:if test="${data.salarystandard.postType  eq '4' }">
															<td style="text-align:center"><input
																value="${data. performanceSalary.intValue()}"
																name="performanceLevel.performanceSalaryList[${status.index }]"
																style="width:40px" type="text"> <input
																type="hidden"
																name="performanceLevel.perIdList[${status.index }]"
																value="${data.id}" /></td>
														</c:if>
													</c:forEach>

												</tr>
												<tr>
													<td rowspan="3"><div
															style="line-height:110px; text-align:center">工勤岗位</div></td>
													<td width="10%">

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">职级工资</div>
													</td>

													<c:forEach varStatus="status" var="data" items="${list}">


														<c:if test="${data.postType  eq '5' }">
															<td style="text-align:center"><input
																name="professionalLevelSalaryList[${status.index }]"
																style="width:40px"
																value="${data. professionalLevelSalary.intValue()}"
																type="text"><input type="hidden"
																name="idList[${status.index }]" value="${data.id}" /></td>
														</c:if>

													</c:forEach>

												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">岗位工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${list}">
														<c:if test="${data.postType  eq '5' }">
															<td style="text-align:center"><input
																name="postSalaryList[${status.index }]"
																style="width:40px"
																value="${data. postSalary.intValue()}" type="text"></td>
														</c:if>
													</c:forEach>
												</tr>
												<tr>
													<td>

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">绩效工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${perList}">
														<c:if test="${data.salarystandard.postType  eq '5' }">
															<td style="text-align:center"><input
																value="${data. performanceSalary.intValue()}"
																name="performanceLevel.performanceSalaryList[${status.index }]"
																style="width:40px" type="text"> <input
																type="hidden"
																name="performanceLevel.perIdList[${status.index }]"
																value="${data.id}" /></td>
														</c:if>
													</c:forEach>

												</tr>


												<tr>
													<td colspan="7">备注：教师岗位见习期如超出标准工作量按20元/个计算。其他是指获第二学历学位的大学生本科毕业生、研究生班毕业和未获得硕士学位的研究生。</td>
												</tr>
											</tbody>
										</table>
										<br>
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