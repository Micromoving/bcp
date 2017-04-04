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


				<div class="span9" style="margin-left:175px">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>忘记密码</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
							<sys:message content="${message}" />
								<form:form class="form-horizontal row-fluid" id="inputForm"
								 modelAttribute="user"
									action="${ctxFront}/register/second" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<!--/第一个验证问题-->
									<div class="control-group">
										<label class="control-label"
											for="safeQuestion.firstVerificationQuestion">验证问题</label>
										<div class="controls">
									${fns:getDictLabel(user.safeQuestion.firstVerificationQuestion,'question','') }
										</div>
									</div>
									<!-- 第一个验证问题答案-->
									<div class="control-group">
										<label class="control-label" for="firstAnswer">答案</label>
										<div class="controls">
											<form:input path="safeQuestion.firstAnswer" class="span8 required" />
										</div>
									</div>
									
								
									<!--/按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">下一步</button>
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