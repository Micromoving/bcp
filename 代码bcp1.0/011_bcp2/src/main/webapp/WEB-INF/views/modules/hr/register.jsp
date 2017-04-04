<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>

</head>
<body>

	<jsp:include page="../../include/top.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(
				function() {

					$("#inputForm").validate(
							{
								rules : {
									password:{
										required:true,
										minlength:6
									}
								},
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
	<div class="wrapper">
		<div class="container">
			
			<div class="row">

				<div class="span9" style="margin-left:175px">
					<div class="content">
						<div class="module ">
							<div class="module-head">
								<h3>注册</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-vertical row-fluid" modelAttribute="user"
									action="${ctxFront}/register/save" id="inputForm" method="post">
									<div class="offset_login">
				<sys:message content="${message}" />
			</div>
									<!-- 真实姓名 -->
									<div class="control-group">
										<label class="control-label row-fluid" for="name"><font
												color="red">*</font>真实姓名</label>
										<div class="controls row-fluid">
											<input class="span8 required" type="text" name="name"
												placeholder="真实姓名" id="name">
												
												
										</div>
									</div>
									<div class="control-group">
										<label class="control-label row-fluid" for="loginName"><font
												color="red">*</font>用户名（身份证作为用户名）</label>
										<div class="controls">
											<input class="span8 required card" type="text"
												name="loginName" placeholder="用户名" id="loginName" />
											
										</div>
									</div>
									<!-- 密码 -->
									<div class="control-group">
										<label class="control-label" for="password"><font
												color="red">*</font>密码（密码长度不少于6位）</label>
										<div class="controls">
											<input name="password" placeholder="密码" id="password"
												class="span8 required minlength" type="password" />
											
										</div>
									</div>
									<!-- 确认密码 -->
									<div class="control-group">
										<label class="control-label" for="confirmNewPassword"><font
												color="red">*</font>确认密码</label>
										<div class="controls">
											<input id="confirmNewPassword" name="confirmNewPassword"
												class="span8 required" type="password" equalTo="#password"
												value="" placeholder=" 确认密码" />
										</div>
									</div>
									<!--/第一个验证问题-->
									<div class="control-group">
										<label class="control-label" for="firstVerificationQuestion"><font
												color="red">*</font>验证问题</label>
										<div class="controls">
											<form:select class="span8 required"
												path="safeQuestion.firstVerificationQuestion">
												<form:option value="">请选择</form:option>
												<form:options items="${fns:getDictList('question')}"
													itemLabel="label" itemValue="value" htmlEscape="false"></form:options>
											</form:select>
										</div>
									</div>
									<!-- 第一个验证问题答案-->
									<div class="control-group">
										<label class="control-label" for="firstAnswer"><font
												color="red">*</font>答案</label>
										<div class="controls">
											<form:input path="safeQuestion.firstAnswer" class="span8 required"
												type="text" />
										</div>
									</div>
									
									<!--/注册按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">注册</button>
										</div>
									</div>
									<!--/.form-horizontal row-fluid-->
								</form:form>
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
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>