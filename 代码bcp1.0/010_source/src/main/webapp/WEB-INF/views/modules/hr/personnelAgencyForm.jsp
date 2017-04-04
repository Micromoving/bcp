<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate();
	});
});
</script>
<script type="text/javascript">
	$(document).ready(function() {

		if (1 == $("#txtAssessmentTotal ").val()) {
			$(".div1").hide();
		} else {
			$(".div1").show();
		}

		$("#inputForm").validate();
	});

	$(function() {

		$("#txtAssessmentTotal").change(function() {
			if (1 == $("#txtAssessmentTotal ").val()) {
				$(".div1").hide();
			} else {
				$(".div1").show();
			}

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
								<h3>新增人事档案材料</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="personnelAgency"
									action="${ctx}/hr/personnelAgency/save" method="post">
									<div>
										<sys:message content="${message}" />
										<form:hidden path="id" />
										<div class="control-group">
											<label class="control-label">姓名</label>
											<div class="controls">
												<input class="span8" path="userName" readonly="readonly"
													value="${personnelAgency.user.name }"> </input>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">工号</label>
											<div class="controls">
												<input class="span8" path="loginName" readonly="readonly"
													value="${personnelAgency.user.loginName }"> </input>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">部门</label>
											<div class="controls">
												<input class="span8" path="office" readonly="readonly"
													value="${fns:getOfficeRootName(personnelAgency.user.office.id)}">
												</input>
											</div>
										</div>
										<!--人事档案材料是否齐全-->
										<div class="control-group">
											<label class="control-label">人事档案材料是否齐全:</label>
											<div class="controls">
												<form:select id="txtAssessmentTotal"
													path="archivesIsComplete" class="span8 required">
													<form:options items="${fns:getDictList('yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>

											</div>
										</div>

										<!--人事档案缺失材料-->
										<div class="control-group div1" name="div1">
											<label class="control-label" for="missingMaterial"> <font
												color="red">*</font>人事档案缺失材料
											</label>
											<div class="controls">

												<form:textarea path="missingMaterial"
													class="span8  required"></form:textarea>
											</div>
										</div>
										<!--人事档案内容清单-->
										<div class="control-group">
											<label class="control-label" for="archives_list">人事档案内容清单</label>
											<div class="controls">


												<form:textarea rows="10" path="archivesList" class="span8"></form:textarea>
											</div>
										</div>
										<!--备注-->
										<div class="control-group">
											<label class="control-label" for="comments">备&nbsp;注</label>
											<div class="controls">
												<form:textarea path="comments" class="span8"></form:textarea>
											</div>
										</div>
										<shiro:hasAnyPermissions
											name="hr:personnelAgency:create,hr:personnelAgency:update">
											<!--/提交按钮-->
											<div class="control-group">
												<div class="controls">
													<button type="submit" class="btn">提交</button>
												</div>
											</div>
										</shiro:hasAnyPermissions>
										<!--/.form-horizontal row-fluid-->
									</div>
									<!--/.module-body-->

								</form:form>
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