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
								<h3>教师资格认定</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="teacherQualification"
									action="${ctx}/hr/teacherQualification/save" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<!--/-->
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input class="span8" path="userName" readonly="readonly"
												value="${teacherQualification.user.name }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">工号</label>
										<div class="controls">
											<input class="span8" path="loginName" readonly="readonly"
												value="${teacherQualification.user.loginName }"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">部门</label>
										<div class="controls">
											<input class="span8" path="office" readonly="readonly"
												value="${fns:getOfficeRootName(teacherQualification.employee.office.id)}"> </input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">资格类型</label>
										<div class="controls">
											<form:select path="qualificationsType" class="span8">
												<form:options
													items="${fns:getDictList('qualifications_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>


									<!---->
									<div class="control-group">
										<label class="control-label"><font
											color="red">*</font>证书编号</label>
										<div class="controls">
											<form:input path="certificateId" class="span8 required" type="text" />
										</div>
									</div>
									<!---->
									<div class="control-group">
										<label class="control-label"><font
											color="red">*</font>认定机构</label>
										<div class="controls">
											<form:input path="certifyingBody" class="span8 required" type="text" />

										</div>
									</div>
									<!---->
									<div class="control-group">
										<label class="control-label"><font
											color="red">*</font>申请单位</label>
										<div class="controls">
											<form:input path="applicationUnit" class="span8 required" type="text" />


										</div>
									</div>
									<!--/-->
									<div class="control-group">
										<label class="control-label"><font
											color="red">*</font>取得时间</label>
										<div class="controls">
											<form:input class="span8 ainp required" type="text"
												path="gainDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
										</div>
									</div>
									<!--
                                 <div class="control-group">
                                    <label class="control-label">审核状态：</label>
                                    <div class="controls">
                                           <form:select path="auditingStatus" class="span8">
												<form:options items="${fns:getDictList('auditing_status')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
                                        </form:select>
                                    </div>
                                </div>/-->



									<!---->
									<div class="control-group">
										<label class="control-label">审核时间</label>
										<div class="controls" style="padding:6px;">
											${fns:formatDate("yyyy-MM-dd HH:mm:ss",teacherQualification.auditingDate)}
										</div>
									</div>
									<!--注释
                                 <div class="control-group">
                                    <label class="control-label">备注:</label>
                                    <div class="controls">
                                        <textarea name="comments" class="span8" rows="5"></textarea>
                                    </div>
                                </div>注释-->


									<!--/提交按钮-->
									<c:if test="${teacherQualification.auditingDate==null}">
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">提交</button>
											</div>
										</div>
									</c:if>
									<c:if test="${teacherQualification.auditingDate!=null}">
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">审核</button>
											</div>
										</div>
									</c:if>

								</form:form>
							</div>
						</div>
						<!--/.module-->
					</div>
					<!--/.content-->
				</div>
				<!--9_over-->

				<!--/.span9-->
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>