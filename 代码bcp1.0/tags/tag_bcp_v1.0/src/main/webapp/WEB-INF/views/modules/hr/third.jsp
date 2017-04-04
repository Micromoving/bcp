<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(
			function() {

				$("#inputForm")
						.validate(
								{
									rules : {},
									messages : {
										confirmNewPassword : {
											equalTo : "输入与上面相同的密码"
										}
									},
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
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


				<div class="span9" style="margin-left:175px">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>重置密码</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
								 modelAttribute="user"
									action="${ctxFront}/register/third" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<div class="control-group">
										<label class="control-label">新密码</label>
										<div class="controls">
											<form:input class="span8 required" type="password" path="newPassword" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">重复新密码</label>
										<div class="controls">
											<input class="span8 required" type="password" name="confirmNewPassword" equalTo="#newPassword"
												value="" placeholder=" 确认密码"/>
										</div>
									</div>
									<!--/按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">完成</button>
										</div>
									</div>
								</form:form>
							</div>
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