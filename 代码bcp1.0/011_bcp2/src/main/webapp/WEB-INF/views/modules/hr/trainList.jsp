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
								<h3>培训经历</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="hr:train:create">
												<a href="${ctx}/hr/train/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</shiro:hasPermission>
										</div>
									</div>
								</div>
								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>
								<c:forEach items="${list}" var="data">
									<!--/培训经历列表-->
									<div class="stream-list">
										<div class="media stream">
											<div class="media-body">
												<div class="stream-headline">
													<h5 class="stream-author">
														${fns:formatDate("yyyy-MM",data.startDate)}至${fns:formatDate("yyyy-MM",data.endDate)}&nbsp;&nbsp;地点:${data.place}&nbsp;&nbsp;培训机构:${data.trainingAgency}
													</h5>
													<div class="stream-text">
														课程:&nbsp;${data.course}&nbsp;&nbsp;证书:&nbsp;${data.certificate}
														<br> 详细描述:&nbsp;${data.detailDescription }
													</div>
												</div>
												<!--/.stream-headline-->
												<div class="stream-options">
													<shiro:hasAnyPermissions
														name="hr:train:update,hr:train:delete">
														<shiro:hasPermission name="hr:train:update">
															<a href="${ctx}/hr/train/form?id=${data.id}"
																class="btn btn-small"> 编辑 </a>
														</shiro:hasPermission>
														<shiro:hasPermission name="hr:train:delete">
															<a href="${ctx}/hr/train/delete?id=${data.id}"
																class="btn btn-small"> 删除 </a>
														</shiro:hasPermission>
													</shiro:hasAnyPermissions>
												</div>
											</div>
											<!--/.media .stream-->

										</div>
									</div>
								</c:forEach>
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