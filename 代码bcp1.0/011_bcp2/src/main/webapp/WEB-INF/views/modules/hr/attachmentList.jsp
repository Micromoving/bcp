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
								<h3>附件</h3>
							</div>
							
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
									<sys:message content="${message}" />
										<div class="clearfix">
											<a href="${ctx}/hr/attachment/form"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>
								<c:forEach items="${list}" var="data">
									<!--/.media .stream-->
									<div class="media stream">
										<div class="media-body">
											<div class="stream-headline">
												<div class="preview2 preview1">

													<img class="preview"
														src="${ctxUploads}/user/attach/${data.attachmentSource}">
													<h5 class="preview3">附件名称:${data.attachmentName}</h5>
													<div class="preview4">附件描述： ${data.comments}</div>
												</div>
												<!--/.stream-headline-->
											</div>
											<div class="preview5">
												<a href="${ctx}/hr/attachment/downloads?id=${data.id}"
													class="btn btn-small"> 下载 </a> <a
													href="${ctx}/hr/attachment/form?id=${data.id}"
													class="btn btn-small"> 编辑 </a> <a
													href="${ctx}/hr/attachment/delete?id=${data.id}"
													class="btn btn-small"> 删除 </a>
											</div>
										</div>
									</div>
								</c:forEach>
								<!--/.media .stream-->


							</div>
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
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
