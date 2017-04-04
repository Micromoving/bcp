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


				<!-- 内容 -->
				<div class="span9">
					<div class="content">

						<div class="module">
							<form:form class="form-horizontal row-fluid" id="inputForm"
								modelAttribute="certificate" action="${ctx}/hr/certificate/save"
								method="post">
								
								<form:hidden path="id" />
								<div class="module-head">
									<h3>增加证书信息</h3>
								</div>
								<!--/.module-head-->
								<div class="module-body">
									<sys:message content="${message}" />
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${certificate.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${certificate.user.loginName }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(certificate.user.office.id)}">
											</input>
										</div>
									</div>
									<!-- 获奖日期 -->
									<div class="control-group">
										<label class="control-label" for="gainDate"><font
											color="red">*</font>取得日期</label>
										<div class="controls">
											<form:input path="gainDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"
												class="span8 required" />

										</div>
									</div>
									<!--/证书名称-->
									<div class="control-group">
										<label class="control-label" for="certificateName"><font
											color="red">*</font>证书名称</label>
										<div class="controls">
											<form:input path="certificateName" class="span8 required" />
											<span class="help-inline"></span>
										</div>
									</div>
									<!--/证书成绩-->
									<div class="control-group">
										<label class="control-label" for="grade"><font
											color="red">*</font>证书成绩</label>
										<div class="controls">
											<form:input path="grade" class="span8 required" />
											<span class="help-inline"></span>
										</div>
									</div>

								</div>

								<!--/提交按钮-->
								<div class="control-group">
									<div class="controls">
										<button type="submit" class="btn">提交</button>
									</div>
								</div>

							</form:form>
						</div>
					</div>
					<!--/.module-->
				</div>
				<!--/.content-->
			</div>
			<!--9_over-->
		</div>
	</div>
	<!--/.container-->

	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>