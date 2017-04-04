<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script>
	$(function() {
		$('table td').css('border-color', '#f5f5f5');
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
						<form:form class="form-horizontal row-fluid" id="inputForm"
							modelAttribute="salaryItems" action="${ctx}/hr/salaryItems/save"
							method="post">
							<sys:message content="${message}" />
							<form:hidden path="id" />

							<div class="module">
								<div class="module-head">
									<h3>增加工资项</h3>
								</div>
								<!--/.module-head-->
								<div class="module-body">
									<form class="form-horizontal row-fluid"
										action="salaryItemsList.html">

										<div class="control-group">
											<label class="control-label" for="salaryMark">工资标识</label>
											<div class="controls">
												<form:input class="span8" htmlEscape="false"
													path="salaryMark"></form:input>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="salaryItemsName">工资项名称</label>
											<div class="controls">
												<form:input class="span8" htmlEscape="false"
													path="salaryItemsName"></form:input>
											</div>
										</div>
										<!-- 类型 -->
										<div class="control-group">
											<label class="control-label" for="salaryType">类型</label>
											<div class="controls">
												<form:select path="salaryType" class="span4">
													<form:options items="${fns:getDictList('salary_type')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>
										<!-- 是否系统数据 -->
										<div class="control-group">
											<label class="control-label" for="isSys">是否系统数据</label>
											<div class="controls">
												<form:select path="isSys" class="span4">
													<form:options items="${fns:getDictList('yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>
										<!--备注-->
										<div class="control-group">
											<label class="control-label" for="remarks">备注</label>
											<div class="controls">
												<form:textarea path="remarks" htmlEscape="false" rows="2"
													maxlength="200" class="input-xlarge span8" />
											</div>
										</div>

										<!--/提交按钮-->
										<div class="control-group">
											<div class="controls">
												<input type="submit" class="btn" id="submit " value="提交">
											</div>
										</div>

									</form>
									<!--/.form-horizontal row-fluid-->
								</div>
								<!--/.module-body-->
							</div>
							<!--/.module-->
						</form:form>
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
