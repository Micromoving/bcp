<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
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
								<h3>导出</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="employee"
									action="${ctx}/hr/employee/export/file" method="post">
									<sys:message content="${message}" />
									<shiro:hasPermission name="hr:employee:export">
										<!-- 是否导出个人资料 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[0]" value="personalInformation">
													个人资料&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label>

											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:teacherQualification:export">
										<!-- 是否导出教师资格认定-->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[1]" value="teacherQualification">
													教师资格认定&nbsp;&nbsp;&nbsp;&nbsp;
												</label>
											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:proTechPosition:export">
										<!-- 是否导出职称履历 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[2]" value="proTechPosition">
													职称履历
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label>

											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:recruitment:export">
										<!-- 是否导出校内岗位履历 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[3]" value="recruitment"> 校内岗位履历
													&nbsp;&nbsp;&nbsp;&nbsp;
												</label>

											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:assessment:export">
										<!-- 是否导出历年考核 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[4]" value="assessment"> 历年考核
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label>

											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:insuredSituation:export">
										<!-- 是否导出社会保障 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[5]" value="insuredSituation">
													社会保障
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label>

											</div>
										</div>
									</shiro:hasPermission>
									<!-- 是否导出参保记录 -->
									<div class="control-group">

										<div class="controls">
											<label class="radio inline"> <input type="checkbox"
												name="selectedItem[6]" value="insuranceRecord"> 参保记录
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</label>

										</div>
									</div>
									<shiro:hasPermission name="hr:personnelAgency:export">
										<!-- 是否导出人事档案 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[7]" value="personnelAgency">
													人事档案
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label>

											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:edu:export">
										<!-- 是否导出教育经历 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[8]" value="edu"> 教育经历
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label>
											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:work:export">
										<!-- 是否导出工作经历 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input type="checkbox"
													name="selectedItem[9]" value="work"> 工作经历
												</label>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

											</div>
										</div>


									</shiro:hasPermission>

									<!--/导出按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn">导出</button>
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
			<!--/.container-->
		</div>
		<!--/.wrapper-->
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>