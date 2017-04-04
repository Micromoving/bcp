<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate();
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
								<h3>教师基本资料</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<div id="" class=""></div>
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="user" action="${ctx}/sys/user/save"
									method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<form:hidden path="company.id" />
									<!--/工号-->
									<div class="control-group">
										<label class="control-label">编号：</label>

										<div class="controls">
											<input id="oldLoginName" name="oldLoginName" type="hidden"
												value="${user.loginName}">
											<form:input path="loginName" htmlEscape="false"
												maxlength="50" class="required userName span8" />
											<span class="help-inline">即用户ID，供用户登录系统使用。建议教师采用工号，学生采用学号。</span>
										</div>
									</div>
									<!--/姓名-->

									<div class="control-group">
										<label class="control-label" for="name">姓名：</label>
										<div class="controls">
											<form:input path="name" class="span8" />
										</div>
									</div>
									<!--/邮箱-->
									<div class="control-group">
										<label class="control-label" for="email">邮箱</label>
										<div class="controls">
											<form:input path="email" class="span8 required email"
												type="text"></form:input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">密码:</label>
										<div class="controls">
											<input id="newPassword" name="newPassword" type="password"
												value="" maxlength="50"
												class="${empty user.id?'required':''}  span8" />
											<c:if test="${empty user.id}">
												<span class="help-inline"> </span>
											</c:if>
											<c:if test="${not empty user.id}">
												<span class="help-inline">若不修改密码，请留空。</span>
											</c:if>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">确认密码:</label>
										<div class="controls">
											<input id="confirmNewPassword" name="confirmNewPassword"
												type="password" value="" maxlength="50"
												equalTo="#newPassword" class="span8" />
											<c:if test="${empty user.id}">
												<span class="help-inline"> </span>
											</c:if>
										</div>
									</div>

									<div class="control-group">
										<label class="control-label">归属部门:</label>
										<div class="controls ">
											<sys:treeselect id="office" name="office.id"
												value="${user.office.id}" labelName="office.name"
												labelValue="${user.office.name}" title="部门"
												url="/sys/office/treeData?type=2" cssClass="input required "
												notAllowSelectParent="false" />

										</div>
									</div>
									<div class="control-group">
										<label class="control-label">是否允许登录:</label>
										<div class="controls">
											<form:select path="loginFlag" class="span8">
												<form:options items="${fns:getDictList('yes_no')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
											<span class="help-inline"> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">用户类型:</label>
										<div class="controls">
											<form:select path="userType" class="span8">
												<form:options items="${fns:getDictList('sys_user_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">用户角色:</label>
										<div class="controls">
											<form:checkboxes element="div" cssClass="checkbox inline"
												path="roleIdList" items="${allRoles}" itemLabel="name"
												itemValue="id" htmlEscape="false" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">备注:</label>
										<div class="controls">
											<form:textarea path="remarks" htmlEscape="false" rows="3"
												maxlength="200" class="input-xlarge span8" />
										</div>
									</div>
									<c:if test="${not empty user.id}">
										<div class="control-group">
											<label class="control-label">创建时间:</label>
											<div class="controls">
												<label class="lbl"><fmt:formatDate
														value="${user.createDate}" type="both" dateStyle="full" /></label>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">最后登陆:</label>
											<div class="controls">
												<label class="lbl">IP:
													${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate
														value="${user.loginDate}" type="both" dateStyle="full" />
												</label>
											</div>
										</div>
									</c:if>
									<shiro:hasAnyPermissions
										name="hr:employee:create,hr:employee:update">
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
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>