<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>修改密码</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#oldPassword").focus();
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
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.appendTo ( element.next() );
										}
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


					<div class="module">
						<ul class="nav">
							<div class="module-head">
								<h3>修改密码</h3>
							</div>
						</ul>
						<br />
						<div class="module-body">
							<form:form id="inputForm" modelAttribute="user"
								action="${ctx}/sys/user/modifyPwd" method="post"
								class="form-horizontal row-fluid">
								<form:hidden path="id" />
								<sys:message content="${message}" />
								<div class="control-group">
									<label class="control-label"><font color="red" >*</font>旧密码:</label>
									<div class="controls">
										<input id="oldPassword" name="oldPassword" type="password"
											path="oldPassword" maxlength="50" minlength="3" class="span8 required" /> <span
											class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><font color="red" >*</font>新密码:</label>
									<div class="controls">
										<input id="newPassword" name="newPassword" type="password"
											value="" maxlength="50" minlength="3" class="span8 required" /> <span
											class="help-inline"></span>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><font color="red" >*</font>确认新密码:</label>
									<div class="controls">
										<input id="confirmNewPassword" name="confirmNewPassword"
											type="password" value="" maxlength="50" minlength="3"
											class="span8 required" equalTo="#newPassword" /> <span
											class="help-inline"> </span>
									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<input type="submit" class="btn" value="提交">
									</div>
								</div>
							</form:form>
						</div>
					</div>
					<!--/.span9-->
				</div>
			</div>
			<!--/.container-->
		</div>
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>