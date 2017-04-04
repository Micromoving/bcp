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
								<h3>增加校内职务</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<!--/职务起止日期-->
								<form:form class="form-horizontal row-fluid"
									modelAttribute="title" id="inputForm"
									action="${ctx}/hr/title/save" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<!-- 起止日期 -->
									<div class="control-group">
										<label class="control-label" for="companyName"><font
											color="red">*</font>起止日期</label>
										<div class="controls">
											<form:input class="span4 ainp" type="text" path="startDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											--
											<form:input class="span4 datainp" type="text" path="endDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
										</div>
									</div>

									<!--/职务名称-->
									<div class="control-group">
										<label class="control-label" for="jobName"><font
											color="red">*</font>职务名称</label>
										<div class="controls">
											<form:input path="jobName" class="span8 required" type="text" />
											<span class="help-inline"></span>
										</div>
									</div>

									<!-- 职务描述 -->
									<div class="control-group">
										<label class="control-label" for="remarks">职务描述</label>
										<div class="controls">
											<form:textarea class="span8" rows="5" path="remarks"></form:textarea>
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
			</div>
			<!--/.span9-->
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
