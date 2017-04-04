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
								<h3>导入资料</h3>
							</div>
							<div class="module-body">




								<!--基本信息-->
								<div class="stream-list">
									<form:form class="form-horizontal row-fluid" id="inputForm"
										modelAttribute="employee" enctype="multipart/form-data"
										action="${ctx}/hr/employee/import/file" method="post">

										<form:hidden path="selectedImportItem" />
										<div class="control-group">
											<label class="control-label" for="basicinput">上传文件</label>
											<div class="controls">
												<input id="uploadFile" name="file" type="file"/>
												<br/>
											</div>
										</div>

										<!--/导入按钮-->
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">导入</button>
											</div>
										</div>
									</form:form>
									<!--/.stream-list-->
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
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
