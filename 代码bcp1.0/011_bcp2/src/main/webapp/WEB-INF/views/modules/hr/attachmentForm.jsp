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
				<div class="span9">
					<div class="content">

						<div class="module">

							<div class="module-head">
								<h3>增加附件</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									enctype="multipart/form-data" modelAttribute="attachment"
									action="${ctx}/hr/attachment/save" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
										<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${attachment.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${attachment.user.loginName }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(attachment.user.office.id)}">
											</input>
										</div>
									</div>
									<!--/附件名称-->
									<div class="control-group">
										<label class="control-label" for="attachmentName"><font
											color="red">*</font>附件名称</label>
										<div class="controls">
											<form:input class="form-control required" style="width:50%"
												htmlEscape="false" path="attachmentName"></form:input>
											<span class="help-inline"></span>
										</div>
									</div>
									<!--附件描述-->
									<div class="control-group">
										<label class="control-label" for="comments">附件描述</label>
										<div class="controls">
											<form:textarea path="comments" class="span8" rows="5" />
										</div>
									</div>
									<!--添加文件-->
									<div class="control-group">
										<label for="exampleInputFile" class="control-label"><font
											color="red">*</font>文件</label>
										<div class="controls">
											<input type="file" name="file" class="span8">
										</div>
									</div>

									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn">提交</button>
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