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
				<jsp:include page="../../include/leftMenuSub.jsp"></jsp:include>
				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>增加值班标准</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="duty" action="${ctx}/hr/duty/save"
									method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<table class="table table-condensed">
										<tbody>
											<tr>
												<td width="50%">
													<!--/类型-->
													<div class="control-group">
														<label class="control-label" for="duty_type">类型</label>
														<div class="controls">
															<form:select path="dutyType" class="span8">
																<form:options items="${fns:getDictList('duty_type')}"
																	itemLabel="label" itemValue="value" htmlEscape="false" />
															</form:select>
														</div>
													</div>
												</td>
											</tr>
											<tr>
												<td width="50%">
													<!--/ 费用标准-->
													<div class="control-group">
														<label class="control-label" for="charge_standard">
															费用标准</label>
														<div class="controls">
															<form:input class="span8 required" htmlEscape="false"
																path="chargeStandard"></form:input>

														</div>
													</div>
												</td>
											</tr>


										</tbody>
									</table>

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