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
				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>增加考核内容</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="assessment" action="${ctx}/hr/assessment/save"
									method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${assessment.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${assessment.user.loginName }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(assessment.user.office.id)}">
										</div>
									</div>
									<!--/考核年度-->
									<div class="control-group">
										<label class="control-label" for="assessment_year">考核年度</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span8" path="assessmentYear"
													disabled="disabled" value="" onfocus="this.blur()"></form:input>
											</div>
										</div>
									</div>
									<!--/考核等级-->
									<div class="control-group">
										<label class="control-label" for="assessment_level">考核等级</label>
										<div class="controls">
											<form:select path="assessmentLevel" class="span8">
												<option></option>
												<form:options items="${fns:getDictList('assessment_level')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/未考核原因-->
									<div class="control-group">
										<label class="control-label" for="noCheckrise">未考核原因</label>
										<div class="controls">
											<form:textarea cssClass="span8" htmlEscape="false"
												path="noCheckrise"></form:textarea>
										</div>
									</div>
									<!--备注-->
									<div class="control-group">
										<label class="control-label" for="comments">备注</label>
										<div class="controls">
											<form:textarea cssClass="span8" path="comments" />
										</div>
									</div>
									<div class="control-group">
										<shiro:hasAnyPermissions
											name="hr:assessment:create,hr:assessment:update">
											<!--/提交按钮-->

											<div class="controls">
												<button type="submit" class="btn">提交</button>
											</div>

										</shiro:hasAnyPermissions>
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