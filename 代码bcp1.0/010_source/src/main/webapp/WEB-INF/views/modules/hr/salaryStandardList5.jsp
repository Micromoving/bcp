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
													<td colspan="10" style="text-align:center"><div>
															<b>(工勤岗位)系列工资标准</b>
														</div></td>
												</tr>
												<tr>
													<td width="8%">
														<!--/职级-->

														<div class="control-group" name="professional_level"
															style="text-align:center">职级</div>
													</td>
													<td style="text-align:center"><div
															class="control-group">五级</div></td>
													<td style="text-align:center"><div
															class="control-group">四级</div></td>
													<td style="text-align:center"><div
															class="control-group">三级</div></td>
													<td style="text-align:center"><div
															class="control-group">二级</div></td>
													<td style="text-align:center"><div
															class="control-group">一级</div></td>
												</tr>
												<tr>
													<td width="10%">
														<!--/职级工资-->

														<div class="control-group"
															name="professional_level_salary"
															style="line-height:30px; text-align:center">职级工资</div>
													</td>

													<c:forEach varStatus="status" var="data" items="${list}">

														<td style="text-align:center"><input
															name="professionalLevelSalaryList[${status.index }]"
															style="width:40px"
															value="${data. professionalLevelSalary.intValue()}"
															type="text"><input type="hidden"
															name="idList[${status.index }]" value="${data.id}" /></td>
													</c:forEach>

												</tr>
												<tr>
													<td width="10%">
														<!--/岗位工资-->

														<div class="control-group" name="post_salary"
															style="line-height:30px; text-align:center">岗位工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${list}">
														<td style="text-align:center"><input
															name="postSalaryList[${status.index }]"
															style="width:40px" value="${data. postSalary.intValue()}"
															type="text"></td>
													</c:forEach>
												</tr>
												<tr>
													<td width="10%">
														<!--/职级工资-->

														<div class="control-group" name="performance_salary"
															style="line-height:30px; text-align:center">绩效工资</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${perList}">


														<td style="text-align:center"><input
															value="${data. performanceSalary.intValue()}"
															name="performanceLevel.performanceSalaryList[${status.index }]"
															style="width:40px" type="text"> <input
															type="hidden"
															name="performanceLevel.perIdList[${status.index }]"
															value="${data.id}" /></td>


													</c:forEach>
												</tr>
												<tr>
													<td width="10%">
														<!--/年终完成工作任务后发放的岗位津贴(年终一次性发放)-->

														<div class="control-group" name="post_subside"
															style="line-height:30px; text-align:center">岗位津贴</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${list}">
														<td style="text-align:center"><input
															name="postSubsideList[${status.index }]"
															style="width:40px"
															value="${data. postSubside.intValue()}" type="text"></td>
													</c:forEach>
												</tr>
												<tr>
													<td colspan="10">备注：岗位津贴是年终完成工作任务后发放的岗位津贴(年终一次性发放)</td>
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