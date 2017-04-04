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
			errorPlacement : function(error, element) {
				error.appendTo(element.next());
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
								<h3>增加培训经历</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="train" action="${ctx}/hr/train/save"
									method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
										<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${train.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${train.user.loginName }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(train.user.office.id)}">
											</input>
										</div>
									</div>
									<!-- 起止日期 -->
									<div class="control-group">
										<label class="control-label" for="startEndDate"><font
											color="red">*</font>起止日期</label>
										<div class="controls">
											<form:input class=" span4 ainp" type="text"
												path="startDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											--
											<form:input class="span4 datainp" type="text"
												path="endDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
										</div>
									</div>
									<!--/培训机构-->
									<div class="control-group">
										<label class="control-label" for="trainingAgency"><font
											color="red">*</font>培训机构</label>
										<div class="controls">
											<form:input path="trainingAgency" class="span8 required" />
											<span class="help-inline"></span>
										</div>
									</div>
									<!--/培训地点-->
									<div class="control-group">
										<label class="control-label" for="place"><font
											color="red">*</font>培训地点</label>
										<div class="controls">
											<form:input path="place" class="span8 required" />
											<span class="help-inline"></span>
										</div>
									</div>
									<!--/培训课程-->
									<div class="control-group">
										<label class="control-label" for="course"><font
											color="red">*</font>培训课程</label>
										<div class="controls">
											<form:input path="course" class="span8 required" />
											<span class="help-inline"></span>
										</div>
									</div>
									<!--/培训证书-->
									<div class="control-group">
										<label class="control-label" for="certificate"><font
											color="red">*</font>培训证书</label>
										<div class="controls">
											<form:input path="certificate" class="span8 required" />
											<span class="help-inline"></span>
										</div>
									</div>
									<!--详细描述-->
									<div class="control-group">
										<label class="control-label" for="detailDescription">详细描述</label>
										<div class="controls">
											<form:textarea path="detailDescription" class="span8"
												rows="5" />
										</div>
									</div>
									<shiro:hasAnyPermissions name="hr:train:create,hr:train:update">
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