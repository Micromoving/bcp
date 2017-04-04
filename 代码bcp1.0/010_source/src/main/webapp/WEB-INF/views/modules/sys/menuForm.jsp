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
								<h3>菜单修改</h3>
							</div>
							<div class="module-body">
								<!--/.media .stream-->
								<form:form class="form-horizontal row-fluid" name="menu"
									id="inputForm" modelAttribute="menu"
									action="${ctx}/sys/menu/save" method="post">
									<form:hidden path="id" />
									<sys:message content="${message}" />
									<input type="hidden" name="parentIds" value="0,1,">
									<!--/上级菜单-->
									<div class="control-group">
										<label class="control-label" for="parent.id">上级菜单：</label>
										<div class="controls">
											<input class="span8 required" type="text" name="parent.id"
												value="${menu.parent.id}">
										</div>
									</div>
									<!--/名称-->
									<div class="control-group">
										<label class="control-label" for="name">名称：</label>
										<div class="controls">
											<form:input name="name" class="span8 required" type="text"
												htmlEscape="false" path="name"></form:input>

										</div>
									</div>
									<!--/链接-->
									<div class="control-group">
										<label class="control-label" for="href">链接:</label>
										<div class="controls">
											<form:input name="href" class="span8" type="text"
												htmlEscape="false" path="href"></form:input>
											<span>点击菜单跳转的页面</span>
										</div>
									</div>
									<!--/目标-->
									<div class="control-group">
										<label class="control-label" for="target">目标:</label>
										<div class="controls">
											<form:input name="target" class="span8" type="text"
												htmlEscape="false" path="target"></form:input>
											<span class="help-inline">链接地址打开的目标窗口，默认：mainFrame</span>
										</div>
									</div>
									<!--图标-->
									<div class="control-group">
										<label for="icon" class="control-label">图标:</label>
										<div class="controls">
											<sys:iconselect id="icon" name="icon" value="${menu.icon}" />
										</div>
									</div>
									<!--/排序-->
									<div class="control-group">
										<label class="control-label" for="sort">排序:</label>
										<div class="controls">
											<form:input name="sort" class="span8 required digits"
												type="text" htmlEscape="false" path="sort"></form:input>
											<span class="help-inline">排列顺序，升序</span>
										</div>
									</div>
									<!--/可见-->
									<div class="control-group">
										<label class="control-label" for="isShow">可见:</label>
										<div class="controls ">
											<form:select path="isShow">
												<form:options items="${fns:getDictList('show_hide')}"
													itemLabel="label" itemValue="value" htmlEscape="false"
													class="required" />
											</form:select>
											<span class="help-inline">该菜单或操作是否显示到系统菜单中</span>

										</div>
									</div>
									<!--/权限标识-->
									<div class="control-group">
										<label class="control-label" for="permission">权限标识:</label>
										<div class="controls">
											<form:input name="permission" class="span8" type="text"
												htmlEscape="false" path="permission"></form:input>
											<span class="help-inline">控制器中定义的权限标识，如：@RequiresPermissions("权限标识")</span>
										</div>
									</div>
									<!--备注-->
									<div class="control-group">
										<label class="control-label" for="remarks">备注:</label>
										<div class="controls">
											<form:textarea name="remarks" class="span8" rows="5"
												path="remarks"></form:textarea>
										</div>
									</div>
									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<input type="submit" class="btn" id="submit" value="提交">
											<input type="submit" id="button" class="btn" value="返回">
										</div>
									</div>
									<!--/.media .stream-->
								</form:form>

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