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

		});
	});
	function get(){
		var sel = document.getElementById("techPositionType").value;
		var sels = document.getElementById("techPositionLevel").value;
		return sel+","+sels;
	}
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
								<h3>新增职称</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="proTechPosition"
									action="${ctx}/hr/proTechPosition/save" method="post">
									<sys:message content="${message}" />

									<form:hidden path="id" />
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${proTechPosition.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${proTechPosition.user.loginName }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(proTechPosition.employee.office.id)}"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">职称类型</label>
										<div class="controls">
											<form:select path="techPositionType" id="techPositionType"
											onchange="selectChange(get(),'${ctx}/hr/proTechPosition/subList','name')"
											 class="span8">
											 <form:option value="0">请选择</form:option>
												<form:options
													items="${fns:getDictList('tech_position_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>

									<!-- 职称级别 -->
									<div class="control-group">
										<label class="control-label" for="tech_position_level">职称级别</label>
										<div class="controls">
											<form:select path="techPositionLevel" id="techPositionLevel"
											onchange="selectChange(get(),'${ctx}/hr/proTechPosition/subList','name')"
											 class="span8">
											  	<form:option value="0">请选择</form:option>
												<form:options
													items="${fns:getDictList('tech_position_level')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--专业技术职务-->
									<div class="control-group">
										<label class="control-label" for="name"><font
											color="red">*</font>专业技术职务</label>
										<div class="controls">
											<form:select path="name" class="span8 required ">
											<form:option value="">请选择</form:option>
												<form:options items="${fns:getDictList('tech_position')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/获取职称时间-->
									<div class="control-group">
										<label class="control-label" for="gainDateString"><font
											color="red">*</font>获取职称时间</label>
										<div class="controls ">
											<div class="col-sm-8">
												<form:input class="span8 required" path="gainDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>

										</div>
									</div>
									<!--/聘任时间-->
									<div class="control-group">
										<label class="control-label" for="appointDateString"><font
											color="red">*</font>聘任时间</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span8 required" path="appointDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>

										</div>
									</div>
									<shiro:hasAnyPermissions
										name="hr:proTechPosition:create,hr:proTechPosition:update">
										<!--/提交按钮-->
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">提交</button>
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
			<!--/.container-->
		</div>
		<!--/.wrapper-->
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>