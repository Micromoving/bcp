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
								<h3>高级查询</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="salEmpView" action="${ctx}/hr/employee/list"
									method="post">
									<div class="control-group">
										<label class="control-label" for="training_type">部门</label>
										<div class="controls">
											<sys:treeselect id="office" name="office.id"
												value="${salEmpView.office.id}" labelName="office.name"
												labelValue="${salEmpView.office.name}" title="部门"
												url="/sys/office/treeData?type=2" 
												allowClear="true" notAllowSelectParent="false" cssStyle="width:138.93617%"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="name">姓名</label>
										<div class="controls">
											<form:input class="span6 required" htmlEscape="false"
												path="name"></form:input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="politicsStatus">政治面貌</label>
										<div class="controls">
											<form:select tabindex="1" class="span6" path="politicsStatus">
												<form:option value="" label="请选择" />
												<form:options items="${fns:getDictList('politics_status')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="gender">性别</label>
										<div class="controls">
											<form:select tabindex="1" class="span6" path="gender">
												<form:option value="" label="请选择" />
												<form:options items="${fns:getDictList('gender')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="maxTechPositionLevel">职称级别</label>
										<div class="controls">
											<form:select tabindex="1" class="span6"
													path="maxTechPositionLevel">
													<form:option value="" label="请选择" />
													<form:options items="${fns:getDictList('tech_position_level')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="maxEduBackground">学历</label>
										<div class="controls">
											<form:select path="maxEduBackground" class="span6">
											<form:option value="" label="请选择" />
												<form:options items="${fns:getDictList('edu_background')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="maxDegree">学位</label>
										<div class="controls">
											<form:select path="maxDegree" class="span6">
											    <form:option value="" label="请选择" />
												<form:options items="${fns:getDictList('academic_degree')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>


									<div class="control-group">
										<label class="control-label" for="staffType">人员类型</label>
										<div class="controls">
											<form:select path="staffType" class="span6">
											   <form:option value="" label="请选择" />
												<form:options items="${fns:getDictList('staff_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="establishmentSituation">编制类型</label>
										<div class="controls">
											<form:select path="establishmentSituation" class="span6">
											    <form:option value="" label="请选择" />
												<form:options items="${fns:getDictList('establishment_situation')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>


									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn">查询</button>
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