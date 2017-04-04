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
							<div class="module-body">
								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>

								<br>
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="employee" action="${ctx}/hr/employee/saveOced"
									method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<!--独子到期时间-->
									<div class="control-group">
										<label class="control-label" for="onlyChildEndDateString">独子到期时间</label>
										<div class="controls">

											<form:input path="onlyChildEndDateString" class="span8"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
												<br>
												持有《独生子女父母光荣证》，发放到子女16周岁止。<br>没有申请《独生子女父母光荣证》，此项不用填写。
												
										</div>
										
									</div>
									         
										

									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls" id="submit">
											<input type="submit" class="btn blue" id="submit " value="提交">
										</div>
									</div>
									<!--/.form-horizontal row-fluid-->
								</form:form>
								
								<!--/.form-horizontal row-fluid-->
							</div>
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
	<!--/.span9-->
	</div>
	<!--/.container-->
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>