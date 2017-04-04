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
								<h3>获奖信息</h3>
							</div>
							<div class="module-body"> 
							<sys:message content="${message}" />
								<!--/增加按钮-->

								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/hr/award/form"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--/实践经历列表-->
								<c:forEach items="${list}" var="data">
									<div class="stream-list">
										<div class="media stream">
											<div class="media-body">
												<div class="stream-headline">
													<h5 class="stream-author">${fns:formatDate("yyyy-MM-dd",data.gainDate)}&nbsp;&nbsp;${data.awardsName}</h5>
													  <div class="stream-text">${fns:getDictLabel(data.levelOne,'level_one','无')}&nbsp;&nbsp;${data.levelTwo} </div>
												</div>
												<!--/.stream-headline-->

												<div class="stream-options">
													<a href="${ctx}/hr/award/form?id=${data.id}"
														class="btn btn-small"> 编辑 </a> <a
														href="${ctx}/hr/award/delete?id=${data.id}"
														class="btn btn-small"> 删除 </a>
												</div>
											</div>
										</div>
									</div>
									<!--/.stream-list-->
								</c:forEach>
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