<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function exportExcel() {
		$("#inputForm").attr("action", "${ctx}/hr/reportedWorkloade/import");
		$("#inputForm").submit();
		return false;
	}
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
								<h3>上报记录</h3>
							</div>

							<div class="module-body">

								<form:form id="inputForm" class="form-horizontal row-fluid"
									modelAttribute="reportedWorkloade"
									enctype="multipart/form-data"
									action="${ctx}/hr/reportedWorkloade/import" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<form:hidden path="dataState" />
									<form:hidden path="reportRecord.id" />
									<!--/增加按钮-->
									<div class="stream-composer media">
										<div class="media-body">
											<div class="clearfix">
												<a id="btnExport"
													href="${ctx}/hr/reportedWorkloade/export?reportRecord.id=${reportedWorkloade.reportRecord.id}"
													class="btn btn-primary pull-right"> 下载模板 </a>
											</div>
										</div>
									</div>


									<!--基本信息-->
									<div class="stream-list">
										<br>
										

										<div class="control-group">
											<label class="control-label" for="basicinput">上传文件</label>
											<div class="controls">
												<input id="uploadFile" name="file" type="file" class="span8 required">
											</div>
										</div>

										<div class="control-group">
											<div class="controls">
												<input type="submit" class="btn blue" id="submit "
													value="提交">
											</div>
										</div>
										<br>
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
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>