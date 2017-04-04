<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate({
			
		
		});
	});
</script>
</head>
<body>
	<!--顶部菜单-->
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>增加岗位设置</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="post" action="${ctx}/hr/post/save"
									method="post">
									<sys:message content="${message}" />
								
									<form:hidden path="id" />
									<!--/部门名称-->
									<div class="control-group">
										<label class="control-label" for="post_name"><font
												color="red">*</font>部门名称</label>
										<div class="controls">
											<sys:treeselect id="office" name="office.id"
												value="${post.office.id}" labelName="office.name"
												labelValue="${post.office.name}" title="部门"
												url="/sys/office/treeData?type=2"
												cssClass="input-small required" notAllowSelectParent="false" />
										</div>
									</div>

									<!--/岗位名称-->
									<div class="control-group">
										<label class="control-label" for="post_name"><font
												color="red">*</font>岗位名称</label>
										<div class="controls">
											<form:select id="txtAssessmentTotal" tabindex="1"
												class="span8" path="postName">
												<form:option value="" label="请选择" />
												<form:options items="${fns:getDictList('post_name')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>									
									<!--/职数-->
									<div class="control-group">
										<label class="control-label" for="postNumberSetting"><font
												color="red">*</font>职数</label>
										<div class="controls">
											<form:input path="postNumberSetting" class="span8 required digits"
												htmlEscape="false"></form:input>
										</div>
									</div>
									<!--/岗位等级-->
									<div class="control-group">
										<label class="control-label" for="postLevelList">岗位等级</label>
										<div class="controls">
											<form:checkboxes path="postLevelList"
												items="${fns:getDictList('post_level')}"
												itemLabel="label"  itemValue="value" htmlEscape="false" />
										</div>
									</div>
									<!--备注-->
									<div class="control-group">
										<label class="control-label" for="comments">备注</label>
										<div class="controls">
											<form:textarea path="comments" htmlEscape="false" rows="3"
												maxlength="200" class="input-xlarge span8" />
										</div>
									</div>

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