<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate();
	});
</script>
</head>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>

	<div class="wrap">
		<div class="container">
			<div class="formbar">
				<b><a href="home.html">首页</a>&nbsp;>&nbsp;<a href="bizMenu.html">人力资源管理</a>&nbsp;>&nbsp;<a
					href="#">教职员工</a>&nbsp;>&nbsp;<a href="employeeList.html">基本信息</a>&nbsp;>&nbsp;<a
					href="userForm.html">增加</a> </b>
			</div>
			<form:form class="form-horizontal row-fluid" id="inputForm"
				modelAttribute="user" action="${ctx}/sys/user/save" method="post">
				<div class="row">
					<!-- 内容 -->
					<div class="formbar t-line">
						<sys:message content="${message}" />
						<form:hidden path="id" />
						<form:hidden path="company.id" />

						<table class="formtbl">
							<tbody>
								<tr>
									<td class="td-color"><label class="control-label">编号：</label></td>
									<td><input id="oldLoginName" name="oldLoginName"
										type="hidden" value="${user.loginName}"> <form:input
											path="loginName" htmlEscape="false" maxlength="50"
											class="required userName span8" /> <span class="help-inline">即用户ID，供用户登录系统使用。建议教师采用工号，学生采用学号。</span>
									</td>
								</tr>
								<tr>
									<td class="td-color"><label class="control-label"
										for="name">姓名：</label></td>
									<td><form:input path="name" class="span8" /></td>
								</tr>
								<tr>
									<td class="td-color"><label class="control-label"
										for="email">邮箱</label></td>
									<td><form:input path="email" class="span8 required email"
											type="text"></form:input></td>
								</tr>
								<tr>
									<td class="td-color"><label class="control-label">密码:</label></td>
									<td><input id="newPassword" name="newPassword"
										type="password" value="" maxlength="50"
										class="${empty user.id?'required':''}  span8" /> <c:if
											test="${empty user.id}">
											<span class="help-inline"> </span>
										</c:if> <c:if test="${not empty user.id}">
											<span class="help-inline">若不修改密码，请留空。</span>
										</c:if></td>
								</tr>
								<tr>
									<td class="td-color"><label class="control-label">确认密码:</label></td>
									<td><input id="confirmNewPassword"
										name="confirmNewPassword" type="password" value=""
										maxlength="50" equalTo="#newPassword" class="span8" /> <c:if
											test="${empty user.id}">
											<span class="help-inline"> </span>
										</c:if></td>
								</tr>
								<tr>
									<td class="td-color"><label class="control-label">归属部门:</label></td>
									<td><sys:treeselect id="office" name="office.id"
											value="${user.office.id}" labelName="office.name"
											labelValue="${user.office.name}" title="部门"
											url="/sys/office/treeData?type=2" cssClass="input required "
											notAllowSelectParent="false" /></td>
								</tr>
								<tr>
									<td class="td-color"><label class="control-label">是否允许登录:</label></td>
									<td><form:select path="loginFlag" class="span8">
											<form:options items="${fns:getDictList('yes_no')}"
												itemLabel="label" itemValue="value" htmlEscape="false" />
										</form:select> <span class="help-inline"> “是”代表此账号允许登录，“否”则表示此账号不允许登录</span>
									</td>
								</tr>
								<tr>
									<td class="td-color"><label class="control-label">用户类型:</label></td>
									<td><form:select path="userType" class="span8">
											<form:options items="${fns:getDictList('sys_user_type')}"
												itemLabel="label" itemValue="value" htmlEscape="false" />
										</form:select></td>
								</tr>
								<tr>
									<td class="td-color"><label class="control-label">用户角色:</label></td>
									<td><form:checkboxes element="div"
											cssClass="checkbox inline" path="roleIdList"
											items="${allRoles}" itemLabel="name" itemValue="id"
											htmlEscape="false" /></td>
								</tr>
								<tr>


									<td class="td-color"><label class="control-label">备注:</label></td>
									<td><form:textarea path="remarks" htmlEscape="false"
											rows="3" maxlength="200" class="input-xlarge span8" /></td>
								</tr>
								<c:if test="${not empty user.id}">
									<tr>
										<td><div class="control-group">
												<label class="control-label">创建时间:</label> <label
													class="lbl"><fmt:formatDate
														value="${user.createDate}" type="both" dateStyle="full" /></label>
											</div>
										<td>
										<td><div class="control-group">
												<label class="control-label">最后登陆:</label> <label
													class="lbl">IP:
													${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate
														value="${user.loginDate}" type="both" dateStyle="full" />
												</label>
											</div></td>

									</tr>



								</c:if>


							</tbody>
						</table>
					</div>
					<div class="formbar t-line">
						<button class="btn-con btn-sub" type="submit">提交</button>
					</div>


				</div>
			</form:form>
			<div class="text-center"></div>
		</div>
		<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>