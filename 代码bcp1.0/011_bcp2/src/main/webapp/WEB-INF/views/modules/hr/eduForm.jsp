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
								<h3>增加教育经历</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="edu" action="${ctx}/hr/edu/save" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
										<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${edu.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${edu.user.loginName }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(edu.user.office.id)}">
											</input>
										</div>
									</div>
									<!-- 起止日期 -->
									<div class="control-group">
										<label class="control-label" for="companyName"><font
											color="red">*</font>起止日期</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span4 required" path="startDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
												至
												<form:input class="span4 required" path="endDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>
									</div>
									<!-- 学历 -->
									<div class="control-group">
										<label class="control-label" for="eduBackground">学历</label>
										<div class="controls">
											<form:select path="eduBackground" class="span8">
											<option> </option>
												<form:options items="${fns:getDictList('edu_background')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!-- 学历取得时间 -->
									<div class="control-group">
										<label class="control-label" for="eduBackgroundDate">学历取得时间</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span8" path="eduBackgroundDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>
									</div>
									<!-- 学位 -->
									<div class="control-group">
										<label class="control-label" for="degree">学位</label>
										<div class="controls">
											<form:select path="degree" class="span8">
											    <option> </option>
												<form:options items="${fns:getDictList('academic_degree')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!-- 学位取得时间 -->
									<div class="control-group">
										<label class="control-label" for="getDegreeDate">学位取得时间</label>
										<div class="controls">
											<div class="col-sm-8">
												<form:input class="span8" path="getDegreeDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>
									</div>
									<!-- 毕业院校-->
									<div class="control-group">
										<label class="control-label" for="graduateSchool"><font
											color="red">*</font>毕业院校</label>
										<div class="controls">
											<form:input class="span8 required" htmlEscape="false"
												path="graduateSchool"></form:input>
										</div>
									</div>
									<!-- 专业 -->
									<div class="control-group">
										<label class="control-label" for="specialty">专业</label>
										<div class="controls">
											<form:input class="span8" htmlEscape="false" path="specialty"></form:input>
										</div>
									</div>
									<!-- 研究方向 -->
									<div class="control-group">
										<label class="control-label" for="researchArea">研究方向</label>
										<div class="controls">
											<form:input class="span8" htmlEscape="false"
												path="researchArea"></form:input>
										</div>
									</div>

									<!-- 就读类型 -->
									<div class="control-group">
										<label class="control-label" for="studyType">就读类型</label>
										<div class="controls">
											<form:select path="studyType" class="span8">
											<option> </option>
												<form:options items="${fns:getDictList('study_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!-- 海外学习经历 -->
									<div class="control-group">
										<label class="control-label" for="isOverseas">是否海外</label>
										<div class="controls">
											<form:select path="isOverseas" class="span8">
											<option> </option>
												<form:options items="${fns:getDictList('is_overseas')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!-- 专业描述 -->
									<div class="control-group">
										<label class="control-label" for="remarks">专业描述</label>
										<div class="controls">
											<form:textarea cssClass="span8" path="remarks" />
										</div>
									</div>
									<shiro:hasAnyPermissions name="hr:edu:create,hr:edu:update">
										<!--/提交按钮-->
										<div class="control-group">
											<div class="controls" id="submit">
												<input type="submit" class="btn blue" id="submit "
													value="提交">
											</div>
										</div>
									</shiro:hasAnyPermissions>
								</form:form>
							</div>


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
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>