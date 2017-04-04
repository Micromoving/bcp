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
									modelAttribute="degreeEdu"
									action="${ctx}/hr/degreeEdu/saveAudit" method="post">
									<form:hidden path="id" />
									<form:hidden path="act.taskId" />
									<form:hidden path="act.taskName" />
									<form:hidden path="act.taskDefKey" />
									<form:hidden path="act.procInsId" />
									<form:hidden path="act.procDefId" />
									<form:hidden id="flag" path="act.flag" />
									<sys:message content="${message}" />
									<!--/攻读开始时间-->
									<div class="control-group">
										<label class="control-label" for="start_date">攻读开始时间</label>
										<div class="controls">
											<form:input class="span8" path="startDate"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/攻读完成时间-->
									<div class="control-group">
										<label class="control-label" for="end_date">攻读完成时间</label>
										<div class="controls">
											<form:input class="span8" path="endDate"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/是否延期-->
									<div class="control-group">
										<label class="control-label">是否延期</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="isDelay">
												<form:options items="${fns:getDictList('yes_no')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/取得证书时间-->
									<div class="control-group">
										<label class="control-label" for="get_certificate_date">取得证书时间</label>
										<div class="controls">
											<form:input class="span8" path="getCertificateDate"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/毕业证编号-->
									<div class="control-group">
										<label class="control-label" for="diploma_num">毕业证编号</label>
										<div class="controls">
											<form:input path="diplomaNum" class="span8" type="text" />
										</div>
									</div>
									<!--/学位证编号-->
									<div class="control-group">
										<label class="control-label" for="degree_num">学位证编号</label>
										<div class="controls">
											<form:input path="degreeNum" class="span8" type="text" />
										</div>
									</div>
									<!--/备注-->
									<div class="control-group">
										<label class="control-label" for="comments">备注</label>
										<div class="controls">
											<form:textarea cssClass="span8" path="comments" rows="6" />
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