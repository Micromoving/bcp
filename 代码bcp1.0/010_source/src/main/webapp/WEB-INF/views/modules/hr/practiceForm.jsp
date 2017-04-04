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
			errorPlacement: function(error, element) {  
				error.appendTo ( element.next() );
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
							<form:form class="form-horizontal row-fluid" id="inputForm"
								modelAttribute="practice" action="${ctx}/hr/practice/save"
								method="post">
								<form:hidden path="id" />
								<sys:message content="${message}" />
								<div class="module-head">
									<h3>增加实践经历</h3>
								</div>
								<!--/.module-head-->
								<div class="module-body">
									<!-- 起止日期 -->
									<div class="control-group">
										<label class="control-label" for="startEndDate"><font
											color="red">*</font>起止日期</label>
										<div class="controls">
										<form:input class=" span4 ainp" style="text" path="startDateString"
											onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
										--
										<form:input class="span4  datainp" style="text" path="endDateString"
											onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
										</div>
									</div>
								
								<!--/实践名称-->
								<div class="control-group">
									<label class="control-label" for="practiceName"><font
										color="red">*</font>实践名称</label>
									<div class="controls">
										<form:input path="practiceName" class="span8 required"
											type="text" /><span
											class="help-inline"></span>
									</div>
								</div>
								<!--实践描述-->
								<div class="control-group">
									<label class="control-label" for="remarks">实践描述</label>
									<div class="controls">
										<form:textarea path="remarks" class="span8" rows="5"></form:textarea>
									</div>
								</div>
							</div>
								<!--/提交按钮-->
								<div class="control-group">
									<div class="controls">
										<button type="submit" class="btn">提交</button>
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
	<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>