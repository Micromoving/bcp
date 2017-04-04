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
										<a href="#" class="btn btn-primary pull-right"
											onclick="return confirmx('确认要保存修改结果吗？',batchSubmit)"> 保存 </a><br>
										<br>
										<table
											class="table table-striped table-bordered table-condensed">
											<tbody>
												<tr>
													<td colspan="11" style="text-align:center"><div>
															<b>(教师)系列工资标准</b>
														</div></td>
												</tr>
												<tr>
													<td width="8%" colspan="2">
														<!--/项目-->

														<div class="control-group" name="duties_level"
															style="line-height:30px; text-align:center">项目</div>
													</td>
													<td colspan="2" style="text-align:center"><div
															class="control-group" style="line-height:30px;">初级</div></td>
													<td colspan="2" style="text-align:center"><div
															class="control-group" style="line-height:30px;">中级</div></td>
													<td colspan="2" style="text-align:center"><div
															class="control-group" style="line-height:30px;">副高</div></td>
													<td colspan="3" style="text-align:center"><div
															class="control-group" style="line-height:30px;">正高</div></td>
												</tr>
												<tr>
													<td width="8%" colspan="2">
														<!--/级别-->

														<div class="control-group" name="professional_level"
															style="line-height:30px; text-align:center">级别</div>
													</td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">九级</div></td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">八级</div></td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">七级</div></td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">六级</div></td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">五级</div></td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">四级</div></td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">三级</div></td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">二级</div></td>
													<td style="text-align:center"><div
															class="control-group" style="line-height:30px;">一级</div></td>
												</tr>
												<tr>
													<td width="10%" colspan="2">
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
													<td width="10%" colspan="2">
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
													<td rowspan="3"><div
															style="display:block; width:12px;line-height:25px;height:100px">绩效工资</div></td>
													<td width="10%">
														<!--/一档-->

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">一档</div>
													</td>
													<c:forEach varStatus="status" var="data" items="${perList}">

														<c:if test="${status.index eq '9' }">
												</tr>
												<tr>
													<td width="10%">
														<!--/二档-->

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">二档</div>
													</td>
													</c:if>
													<c:if test="${status.index eq '18' }">
												</tr>
												<tr>
													<td width="10%">
														<!--/三档-->

														<div class="control-group" name="performance_level"
															style="line-height:30px; text-align:center">三档</div>
													</td>

													</c:if>

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
													<td width="10%" colspan="2">
														<!--/每学期末完成标准课时量后发放的岗位津贴(每学期末发放，一年发放两次)-->

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
													<td colspan="11">备注：岗位津贴，每学期末完成标准课时量后发放的岗位津贴（每学期末发放，一年发放两次）</td>
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