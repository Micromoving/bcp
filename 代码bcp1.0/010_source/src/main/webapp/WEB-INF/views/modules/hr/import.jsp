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
								<h3>导入</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="employee"
									action="${ctx}/hr/employee/importFile" method="post">
									<shiro:hasPermission name="hr:employee:import">
										<!-- 是否导入个人资料 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="personalInformation"
													type="radio">
													个人资料&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label> <a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=personalInformation">下载导入个人资料模板</a>
											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:teacherQualification:import">
										<!-- 是否导入教师资格认定-->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="teacherQualification"
													type="radio"> 教师资格认定&nbsp;&nbsp;&nbsp;&nbsp;
												</label> <a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=teacherQualification">下载导入教师资格认定模板</a>


											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:proTechPosition:import">
										<!-- 是否导入职称履历 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="proTechPosition"
													type="radio"> 职称履历
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label> <a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=proTechPosition">下载导入职称履历模板</a>
											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:recruitment:import">
										<!-- 是否导入校内岗位履历 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="recruitment" type="radio">
													校内岗位履历 &nbsp;&nbsp;&nbsp;&nbsp;
												</label> <a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=recruitment">下载导入校内岗位履历模板</a>

											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:assessment:import">
										<!-- 是否导入历年考核 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="assessment" type="radio">
													历年考核
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label> <a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=assessment">下载导入历年考核模板</a>

											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:insuredSituation:import">
										<!-- 是否导入社会保障 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="insuredSituation"
													type="radio"> 社会保障
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label> <a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=insuredSituation">下载导入社会保障模板</a>
											</div>
										</div>
									</shiro:hasPermission>
									<!-- 是否导入参保信息 -->
									<div class="control-group">

										<div class="controls">
											<label class="radio inline"> <input
												name="selectedImportItem" value="insuranceRecord"
												type="radio"> 参保记录
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											</label> <a
												href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=insuranceRecord">下载导入参保记录模板</a>
										</div>
									</div>
									<shiro:hasPermission name="hr:personnelAgency:import">
										<!-- 是否导入人事档案 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="personnelAgency"
													type="radio"> 人事档案
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label><a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=personnelAgency">下载导入人事档案模板</a>

											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:edu:import">
										<!-- 是否导入教育经历 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="edu" type="radio">
													教育经历
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label><a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=edu">下载导入教育经历模板</a>


											</div>
										</div>
									</shiro:hasPermission>
									<shiro:hasPermission name="hr:work:import">
										<!-- 是否导入工作经历 -->
										<div class="control-group">

											<div class="controls">
												<label class="radio inline"> <input
													name="selectedImportItem" value="work" type="radio">
													工作经历
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												</label><a
													href="${ctx}/hr/employee/import/template?selectedImportTemplateItem=work">下载导入工作经历模板</a>

											</div>
										</div>
									</shiro:hasPermission>

									<br>
									<!--/导入按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn">导入</button>
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
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>