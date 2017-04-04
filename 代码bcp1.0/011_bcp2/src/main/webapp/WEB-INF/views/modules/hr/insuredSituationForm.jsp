<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>

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

				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>新增社保</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="insuredSituation"
									action="${ctx}/hr/insuredSituation/save" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${insuredSituation.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${insuredSituation.user.loginName }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(insuredSituation.user.office.id)}">
											</input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="socialInsuranceId"><font
													color="red">*</font>险种编号</label>
										<div class="controls">
											<form:input htmlEscape="false" path="socialInsuranceId"
												class="span8 required" />
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="insuranceType"><font
													color="red">*</font>险种</label>
										<div class="controls">
											<form:select path="insuranceRule.id" class="span8 required">
												<form:options items="${insuredSituation.insList}"
													itemLabel="insuranceType" itemValue="id" htmlEscape="false" />
											</form:select>
										</div>
									</div>



									<div class="control-group">
										<label class="control-label" for="insuredDate"><font
													color="red">*</font>参保时间</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span8 ainp required" type="text"
													path="insuredDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>
									</div>




									<div class="control-group">
										<label class="control-label" for="schoolInsuredDate"><font
													color="red">*</font>学院参保时间</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span8 ainp required" type="text"
													path="schoolInsuredDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>
									</div>






									<div class="control-group">
										<label class="control-label" for="insuredYear"><font
													color="red">*</font>参保年限（年）</label>
										<div class="controls">
											<form:input htmlEscape="false" path="insuredYear"
												class="span8 required" maxlength="2"/>
											
										</div>
									</div>
									<shiro:hasAnyPermissions
										name="hr:insuredSituation:create,hr:insuredSituation:update">
										<!--/提交按钮-->
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">提交</button>
											</div>
										</div>
									</shiro:hasAnyPermissions>
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