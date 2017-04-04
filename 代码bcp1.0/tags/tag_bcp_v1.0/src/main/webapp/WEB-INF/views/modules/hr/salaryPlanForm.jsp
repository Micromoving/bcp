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
			<div class="row">
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>增加方案</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form action="${ctx}/hr/salaryPlan/save"
									modelAttribute="salaryPlan" class="form-horizontal row-fluid"
									method="post">
									<form:hidden path="id"/>

									<!--/职务起止日期-->

									<!-- 方案名称 -->
									<div class="control-group">
										<label class="control-label" for="planName">方案名称</label>
										<div class="controls">
											<form:input path="planName" class="span8" />

										</div>
									</div>

									<!--/方案说明-->
									<div class="control-group">
										<label class="control-label" for="planExplain">方案说明</label>
										<div class="controls">
											<form:textarea path="planExplain" class="span8" rows="5" />
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
				<!--/.span9-->
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>