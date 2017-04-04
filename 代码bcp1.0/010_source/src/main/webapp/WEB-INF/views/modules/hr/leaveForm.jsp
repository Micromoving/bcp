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
								<h3>请假内容</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="attendance" action="${ctx}/hr/leave/save"
									method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<form:hidden path="act.taskId" />
									<form:hidden path="act.taskName" />
									<form:hidden path="act.taskDefKey" />
									<form:hidden path="act.procInsId" />
									<form:hidden path="act.procDefId" />
									<form:hidden id="flag" path="act.flag" />


									<div class="control-group">
										<label class="control-label" for="companyName">请假起止日期</label>
										<div class="controls">
											<form:input class="span4 required" path="startDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月dd日'})"></form:input>
											至
											<form:input class="span4 required" path="endDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月dd日'})"></form:input>
										</div>
									</div>


									<div class="control-group">
										<label class="control-label" for="training_type">请假类型</label>
										<div class="controls">
											<form:select path="leaveType" class="span8">
												<form:options items="${fns:getDictList('oa_leave_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>

									<div class="control-group">
										<label class="control-label" for="leaveReason">请假原因</label>
										<div class="controls">
											<form:textarea class="span8" rows="5" name="leaveReason"
												path="leaveReason"></form:textarea>
										</div>
									</div>

									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls" id="submit">
											<input type="submit" class="btn blue" id="submit"  onclick="$('#flag').val('yes')" value="提交" >
										</div>
									</div>

								</form:form>
							</div>
							<!--/.form-horizontal row-fluid-->
						</div>
					</div>
					<!--/.module-body-->
				</div>
				<!--/.module-->
			</div>
			<!--/.content-->
			<!--/.span9-->
		</div>
	</div>
	<!--/.container-->

	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>