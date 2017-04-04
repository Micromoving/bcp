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
								<h3>工作经历</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="hr:work:create">
												<a href="${ctx}/hr/work/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</shiro:hasPermission>
										</div>
									</div>
								</div>
								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>
								<c:forEach items="${list}" var="data">
									<!--/工作经历列表-->
									<div class="stream-list">
										<div class="media stream">
											<div class="media-body">
												<div class="stream-headline">
													<h5 class="stream-author">
																	<c:if test="${empty data.endDate}">
														${fns:formatDate("yyyy年MM月",data.startDate)}至今&nbsp;&nbsp;
													</c:if>
													<c:if test="${not empty data.endDate}">
														${fns:formatDate("yyyy年MM月",data.startDate)}—${fns:formatDate("yyyy年MM月",data.endDate)}&nbsp;&nbsp;
													</c:if>
														${data.companyName}&nbsp;
														${fns:getDictLabel(data.companyNature,'company_nature','无')}
													</h5>
													<div class="stream-text">
														职位名称：${data.companyDepartment}
														${data.position}&nbsp;&nbsp;&nbsp;
														<c:if test="${data.workSort eq '2'}">
														(${fns:getDictLabel(data.workSort,'work_sort','无')})
</c:if>														
														<br>
														工作描述：${data.remarks}
													</div>
												</div>
												<!--/.stream-headline-->
												<div class="stream-options">
													<shiro:hasAnyPermissions
														name="hr:work:update,hr:work:delete">
														<shiro:hasPermission name="hr:work:update">
															<a href="${ctx}/hr/work/form?id=${data.id}"
																class="btn btn-small"> 编辑 </a>
														</shiro:hasPermission>
														<shiro:hasPermission name="hr:work:delete">
															<a href="${ctx}/hr/work/delete?id=${data.id}"
																class="btn btn-small"> 删除 </a>
														</shiro:hasPermission>
													</shiro:hasAnyPermissions>
												</div>
											</div>
										</div>
										<!--/.media .stream-->
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