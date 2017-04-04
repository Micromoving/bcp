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

				<!--/.span3-->

				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>增加学历教育</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="degreeEdu" action="${ctx}/hr/degreeEdu/saveAudit"
									method="post">
									<form:hidden path="id" />
									<form:hidden path="act.taskId" />
									<form:hidden path="act.taskName" />
									<form:hidden path="act.taskDefKey" />
									<form:hidden path="act.procInsId" />
									<form:hidden path="act.procDefId" />
									<form:hidden id="flag" path="act.flag" />
									<sys:message content="${message}" />

									<!--攻读院校-->
									<div class="control-group">
										<label class="control-label" for="school">攻读院校</label>
										<div class="controls">
											<form:input path="school" class="span8" type="text" />
										</div>
									</div>
									
									<!--/攻读专业-->
									<div class="control-group">
										<label class="control-label" for="major">攻读专业</label>
										<div class="controls">
											<form:input path="major" class="span8" type="text" />
										</div>
									</div>
									
									<!--/攻读方向-->
									<div class="control-group">
										<label class="control-label" for="research_area">攻读方向</label>
										<div class="controls">
											<form:input path="researchArea" class="span8" type="text" />
										</div>
									</div>

									<!--/学费-->
									<div class="control-group">
										<label class="control-label" for="tuition">学费</label>
										<div class="controls">
											<form:input path="tuition" class="span8" type="text" />
										</div>
									</div>

									<!--/启动读研(博)工资-->
									<div class="control-group">
										<label class="control-label">启动读研(博)工资</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="hasSalary">
												<form:options items="${fns:getDictList('yes_no')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									
									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<input id="btnSubmit" class="btn btn-success " type="submit"
												value="审核通过 " onclick="$('#flag').val('yes')" />&nbsp; <input
												id="btnSubmit" class="btn btn-danger" type="submit"
												value="审核不通过" onclick="$('#flag').val('no')" />&nbsp;
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