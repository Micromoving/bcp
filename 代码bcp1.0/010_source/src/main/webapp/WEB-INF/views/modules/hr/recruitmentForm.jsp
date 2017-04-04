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
								<h3>编辑岗位聘任管理</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="recruitment"
									action="${ctx}/hr/recruitment/save" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${recruitment.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${recruitment.user.loginName }"> </input>
										</div>
									</div>
									<!--/部门-->
									<div class="control-group">
										<label class="control-label" for="office">部门</label>
										<div class="controls">
											<sys:treeselect id="office" name="office.id"
												value="${fns:getOfficeRootName(recruitment.user.office.id)}"
												labelName="office.name" dataMsgRequired="recruitment"
												labelValue="${fns:getOfficeRootName(recruitment.user.office.id)}"
												title="部门" url="/sys/office/treeData?type=2"
												cssClass="input-xxxlarge" allowClear="true"
												allowInput="true" notAllowSelectParent="false" />
										</div>
									</div>
									<!--
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(recruitment.user.office.id)}">
											</input>
										</div>
									</div>
									-->
									<!-- 聘任起止日期 -->
									<div class="control-group">
										<label class="control-label" for="startDateString"><font
											color="red">*</font>聘任起止日期</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span4 required" path="startDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
												至
												<form:input class="span4 required" path="endDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
											</div>
										</div>
									</div>
									<!--/职务-->
									<div class="control-group">
										<label class="control-label" for="duties">职务</label>
										<div class="controls">
											<form:select path="duties" id="duties" class="span8">
												<form:option value="">请选择</form:option>
											</form:select>
										</div>
									</div>
									<!--/岗位类型-->
									<div class="control-group">
										<label class="control-label" for="postType"><font
											color="red">*</font>岗位类型</label>
										<div class="controls">
											<form:select path="postType" class="span8 required">
												<form:options items="${fns:getDictList('post_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/岗位等级-->
									<div class="control-group">
										<label class="control-label" for="postLevel">岗位等级</label>
										<div class="controls">
											<form:select path="postLevel" class="span8">
												<option></option>
												<form:options items="${fns:getDictList('post_level')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/职级等级-->
									<div class="control-group">
										<label class="control-label" for="professionalLevel">职级等级</label>
										<div class="controls">
											<form:select path="professionalLevel" class="span8">
												<option></option>
												<form:options
													items="${fns:getDictList('professional_level')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/职务等级-->
									<div class="control-group">
										<label class="control-label" for="dutiesLevel">职务等级</label>
										<div class="controls">
											<form:select path="dutiesLevel" class="span8">
												<option></option>
												<form:options items="${fns:getDictList('duties_level')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/新选拔中层干部试用期期限-->
									<div class="control-group">
										<label class="control-label" for="tryOutDateString">中层干部试用期截止时间</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span8" path="tryOutDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
											</div>
										</div>
									</div>
									<!--/聘任情况-->
									<div class="control-group">
										<label class="control-label" for="engageSituation">聘任情况</label>
										<div class="controls">
											<form:select path="engageSituation" class="span8">
												<option></option>
												<form:options items="${fns:getDictList('engage_situation')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>

									<!-- 是否签订聘用合同 -->
									<div class="control-group">
										<label class="control-label" for="isSignContract"><font
											color="red">*</font>是否签订聘用合同</label>
										<div class="controls">
											<form:select path="isSignContract" class="span8 required">
												<form:options items="${fns:getDictList('is_sign_contract')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls" id="submit">
											<input type="submit" class="btn blue" id="submit " value="提交">
										</div>
									</div>

								</form:form>
							</div>
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
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>