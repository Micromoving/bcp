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
								<h3>增加工作经历</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">

								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="work" action="${ctx}/hr/work/save"
									method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
										<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${work.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${work.user.loginName }"> </input>
										</div>
									</div>
									
									<!-- 起止日期 -->
									<div class="control-group">
										<label class="control-label" for="companyName"><font
											color="red">*</font>起止日期</label>
										<div class="controls">

											<form:input class="span4 required" path="startDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											--
											<form:input class="span4 " path="endDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											<br>　　　　　　　　　　　　　　　　注：空缺时表示至今
										</div>
									</div>
									<!-- 单位名称 -->
									<div class="control-group">
										<label class="control-label" for="companyName"><font
											color="red">*</font>单位名称</label>
										<div class="controls">
											<form:input class="span8 required" htmlEscape="false"
												path="companyName"></form:input>
											<span class="help-inline"></span>
										</div>
									</div>
									<!-- 单位性质 -->
									<div class="control-group">
										<label class="control-label" for="dataClassification">单位性质</label>
										<div class="controls">
											<form:select path="companyNature" class="span8">
												<form:options items="${fns:getDictList('company_nature')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!-- 归属部门 -->
									<div class="control-group">
										<label class="control-label" for="companyDepartment">归属部门</label>
										<div class="controls">
											<form:input class="span8" htmlEscape="false"
												path="companyDepartment"></form:input>
										</div>
									</div>
									<!-- 职位名称 -->
									<div class="control-group">
										<label class="control-label" for="position">职位名称</label>
										<div class="controls">
											<form:input class="span8" htmlEscape="false" path="position"></form:input>

										</div>
									</div>
									<!-- 工作类型 -->
									<div class="control-group">
										<label class="control-label" for="workSort">工作类型</label>
										<div class="controls">
											<form:select path="workSort" class="span8">
												<form:options items="${fns:getDictList('work_sort')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!-- 工作描述 -->
									<div class="control-group">
										<label class="control-label" for="remarks">工作描述</label>
										<div class="controls">
											<form:textarea cssClass="span8" path="remarks" />
										</div>
									</div>
									<shiro:hasAnyPermissions name="hr:work:create,hr:work:update">
										<!--/提交按钮-->
										<div class="control-group">
											<div class="controls" id="submit">
												<input type="submit" class="btn blue" id="submit "
													value="提交">
											</div>
										</div>
									</shiro:hasAnyPermissions>
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