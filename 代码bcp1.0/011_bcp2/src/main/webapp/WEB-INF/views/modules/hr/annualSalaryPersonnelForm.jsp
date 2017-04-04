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
								<h3>增加年薪制人员</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="annualSalaryPersonnel"
									action="${ctx}/hr/annualSalaryPersonnel/save" method="post">
									<form:hidden path="id"/>
									<form:hidden path="salEmpView.id" />
									<sys:message content="${message}" />

									<!--/工号-->
									<div class="control-group">
										<label class="control-label" for="salEmpView.loginName">工号</label>
										<div class="controls">
											<form:input path="salEmpView.loginName" class="span7" type="text"
												readonly="true" />

										</div>

									</div>
									<!--/姓名-->
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<form:input class="span7" type="text" path="salEmpView.name"
												readonly="true"/>

										</div>
									</div>


									<!--/身份证号-->
									<div class="control-group">
										<label class="control-label" for="salEmpView.papersNumber">身份证号</label>
										<div class="controls">
											<form:input path="salEmpView.papersNumber" class="span7"
												type="text" readonly="true" />

										</div>
									</div>
									<!--/年薪-->
									<div class="control-group">
										<label class="control-label" for="yearlySalary">年薪</label>
										<div class="controls">
											<form:input path="yearlySalary" class="span7" type="text" />
											&nbsp;&nbsp;&nbsp;元/年

										</div>
									</div>
									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" id="submit" class="btn">提交</button>
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