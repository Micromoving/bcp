<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
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


				<div class="span9" style="margin-left:175px">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>忘记密码</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
								 modelAttribute="user"
									action="${ctxFront}/register/first" method="post">
									<sys:message content="${message}" />
									<!-- 起止日期 -->
									<div class="control-group">
										<label class="control-label" for="loginName">用户名</label>
										<div class="controls">
											<form:input path="loginName" class="span8" />
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