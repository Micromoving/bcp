<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="../../include/top.jsp"></jsp:include>

	<div class="wrapper">
		<div class="container">
			<div class="offset_login">
				<sys:message content="${message}" type="error" />
			</div>
			<div class="row">

				<div class="module module-login span4 offset4">

					<form id="loginForm" action="${ctx}/login" method="post">
						<div class="module-head">
							<h3>登录</h3>
						</div>
						<div class="module-body">
							<!--/出错消息-->
							<br />
							<div class="control-group">
								<div class="controls row-fluid">
									<input class="span12" type="text"
										onblur="if(this.value==''){this.value='用户名';}" name="username"
										onfocus="this.value='';" value="用户名">
								</div>
							</div>
							<div class="control-group">
								<div class="controls row-fluid">
									<input class="span12" type="password" name="password"
										value="密码" onfocus="this.value='';"
										onblur="if(this.value==''){this.value='';}">
								</div>
							</div>
							<c:if test="${isValidateCodeLogin}">
								<label class="input-label mid" for="validateCode">验证码</label>
								<sys:validateCode name="validateCode"
									inputCssStyle="margin-bottom:0;" />
							</c:if>
							<a href="${ctxFront}/register/forget"> 忘记密码</a>
						</div>
						<div class="module-foot">
							<div class="control-group">
								<div class="stream-composer media">
									<div class="media-body clearfix">
										
										
										<button type="submit" class="btn btn-primary pull-right">登录</button>
										<label class="checkbox"><input type="checkbox">
											记住密码 </label>

									</div>
								</div>
							</div>
						</div>



					</form>
				</div>
			</div>
		</div>
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>

</body>