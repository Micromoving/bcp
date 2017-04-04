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


				<div class="span9">
					<div class="content">
						<form:form class="form-horizontal row-fluid" id="inputForm"
							modelAttribute="insuranceRule"
							action="${ctx}/hr/insuranceRule/save" method="post">
							<sys:message content="${message}" />
							<form:hidden path="id" />

							<div class="module">
								<div class="module-head">
									<h3>新增险种规则</h3>
								</div>
								<!--/.module-head-->
								<div class="module-body">

									<div class="control-group">

										<label class="control-label" for="insuranceType"><font color="red">*</font>险种</label>
										<div class="controls">
											<form:input class="span8 required" htmlEscape="false"
												path="insuranceType"></form:input>
										</div>


									</div>

									<div class="control-group">
										<label class="control-label" for="unitProportion"><font color="red">*</font>单位比例</label>
										<div class="controls">
											<form:input class="span8 required number" path="unitProportion"></form:input>
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="peopleProportion"><font color="red">*</font>个人比例</label>
										<div class="controls">
											<form:input class="span8 required number" type="text" path="peopleProportion"></form:input>
										</div>
									</div>

									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<input type="submit" class="btn blue" id="submit " value="提交">
										</div>
									</div>

									<!--/.form-horizontal row-fluid-->
								</div>
								<!--/.module-body-->
							</div>
						</form:form>
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