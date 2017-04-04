<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate();
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
								<h3>添加数据字典</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="dict" action="${ctx}/sys/dict/save"
									method="post">
									<form:hidden path="id" />
									<sys:message content="${message}" />
									<!--/键值-->
									<div class="control-group">
										<label class="control-label" for="value">键值：</label>
										<div class="controls">
											<form:input class="span8 required" htmlEscape="false" path="value" />
										</div>
									</div>

									<!--/标签-->
									<div class="control-group">
										<label class="control-label" for="label">标签：</label>
										<div class="controls">
											<form:input class="span8 required" name="label" htmlEscape="false" path="label" />
										</div>
									</div>

									<!--/类型-->
									<div class="control-group">
										<label class="control-label" for="type">类型：</label>
										<div class="controls">
											<form:input class="span8 required" htmlEscape="false" path="type" />
										</div>
									</div>

									<!--/描述-->
									<div class="control-group">
										<label class="control-label" for="description">描述：</label>
										<div class="controls">
											<form:input class="span8 required" htmlEscape="false"
												path="description" />
										</div>
									</div>

									<!--/排序-->
									<div class="control-group">
										<label class="control-label" for="sort">排序：</label>
										<div class="controls">
											<form:input class="span8 required digits" htmlEscape="false" path="sort" />
										</div>
									</div>

									<!--/备注-->
									<div class="control-group">
										<label class="control-label" for="remarks">备注：</label>
										<div class="controls">
											<form:textarea path="remarks" htmlEscape="false" rows="3"
												maxlength="200" class="input-xlarge" />
										</div>
									</div>

									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<input type="submit" class="btn" value="提交">
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
					<!--/.span9-->
				</div>
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>