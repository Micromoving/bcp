<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
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
								<h3>特殊数据处理</h3>
							</div>
							<div class="module-body">

								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">

										<form:form class="form-horizontal row-fluid" id="inputForm"
											modelAttribute="specialDataProcessing"
											action="${ctx}/hr/specialDataProcessing/form" method="post">
											<form:hidden path="salaryInstanceId"></form:hidden>

											<!-- 数据分类 -->
											<div class="control-group">
												数据分类
												<form:select path="dataClassification" class="span2">
													<form:option value="1">请假人员</form:option>
													<form:option value="2">学历教育</form:option>
													<form:option value="3">培训</form:option>
													<form:option value="4">年薪制人员</form:option>
													<form:option value="5">辅导员带班数</form:option>
													<form:option value="6">非在编工资项</form:option>
												</form:select>
												<!--/提交按钮-->
												<div class="control-group">
													<div class="controls" id="submit">
														<input type="submit" class="btn btn-primary pull-right"
															id="submit " value="查询">
													</div>
												</div>

											</div>
										</form:form>
									</div>
									<!--/.media .stream-->
									<!--/表格栏-->

									<!--/.module-body-->
								</div>
								<!--/.module-->

							</div>
							<!--/.content-->
						</div>
						<!--/.span9-->
					</div>
				</div>
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>