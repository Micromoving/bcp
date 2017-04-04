<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
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
								<h3>增加获奖信息</h3>
							</div>
							<!--/.module-head-->
							  <div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="award" action="${ctx}/hr/award/save"
									method="post">
									<form:hidden path="id" />
									<sys:message content="${message}" />
									<!-- 获奖日期 -->
									<div class="control-group">
										<label class="control-label" for="gainDate"><font
											color="red">*</font>获奖日期</label>
										<div class="controls">
											<form:input class="span4" path="gainDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>

									<!--/奖项名称-->
									<div class="control-group">
										<label class="control-label" for="awardsName"><font
											color="red">*</font>奖项名称</label>
										<div class="controls">
											<form:input path="awardsName" class="required  span4  "
												type="text"></form:input>
											<span class="help-inline"></span>
										</div>
									</div>

									<!--/1-->
									<div class="control-group">
										<label class="control-label" for="levelOne">奖项级别一</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="levelOne">
												<form:options items="${fns:getDictList('level_one')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/2-->
									<div class="control-group">
										<label class="control-label" for="levelTwo">奖项级别二</label>
										<div class="controls">
											<form:input path="levelTwo" class="span8" type="text" />
										</div>
									</div>
									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls" id="submit">
											<input type="submit" class="btn blue" id="submit " value="提交">
										</div>
									</div>

								</form:form>
							</div>
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