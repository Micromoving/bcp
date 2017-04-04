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
				<jsp:include page="../../include/leftMenuSub.jsp"></jsp:include>
				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>增加津贴标准</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid"
									action="${ctx}/hr/subsidiaryStandard/save" id="inputForm"
									modelAttribute="subsidiaryStandard" method="post">
									<form:hidden path="id" />
									<sys:message content="${message}" />



									<table class="table table-condensed">
										<tbody>
											<tr>
												<td width="50%">
													<!--/津贴类型-->

													<div class="control-group">
														<label class="control-label"> 津贴类型</label>
														<div class="controls">
															<form:select path="allowanceType" class="span8">
																<form:options
																	items="${fns:getDictList('allowance_type')}"
																	itemLabel="label" itemValue="value" htmlEscape="false" />
															</form:select>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td width="50%">
													<!--/津贴主体-->

													<div class="control-group">
														<label class="control-label" for="allowance_main_body">津贴主体</label>
														<div class="controls">
															<form:select path="allowanceMainBody" class="span8">
																<form:options
																	items="${fns:getDictList('allowance_main_body')}"
																	itemLabel="label" itemValue="value" htmlEscape="false" />
															</form:select>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td width="50%">
													<!--/费用标准-->

													<div class="control-group">
														<label class="control-label" for="charge_standard">费用标准</label>
														<div class="controls">
															<form:input path="chargeStandard" class="span8 required"
																htmlEscape="false" />

														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td width="50%">
													<!--/津贴单位-->

													<div class="control-group">
														<label class="control-label" for="allowance_unit">津贴单位</label>
														<div class="controls">
															<form:select path="allowanceUnit" class="span8">
																<form:options
																	items="${fns:getDictList('allowance_unit')}"
																	itemLabel="label" itemValue="value" htmlEscape="false" />
															</form:select>

														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td width="50%">
													<!--/备注-->

													<div class="control-group">
														<label class="control-label" for="comments">备注</label>
														<div class="controls">
															<form:textarea class="span8" name="comments" rows="2"
																path="comments"></form:textarea>
														</div>
													</div>
												</td>
											</tr>
										</tbody>
									</table>

									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<input type="submit" class="btn blue" id="submit" value="提交">
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
			<!--/.row-->
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>