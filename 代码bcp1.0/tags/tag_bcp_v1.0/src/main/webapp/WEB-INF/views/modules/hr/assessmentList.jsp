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
								<h3>历年考核</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->

								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="hr:assessment:create">
												<a href="${ctx}/hr/assessment/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</shiro:hasPermission>
										</div>
									</div>
								</div>
								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>

								<!--/列表-->
								<c:forEach items="${list}" var="data">
									<div class="stream-list">
										<div class="media stream">
											<div class="media-body">
												<div class="stream-headline">
													<h5 class="stream-author">
														考核年度：${data.assessmentYear}年&nbsp;&nbsp;
														<c:if test="${empty data.noCheckrise}">
														考核等级：${fns:getDictLabel(data.assessmentLevel,'assessment_level','无')}               
														</c:if>
														<c:if test="${empty data.assessmentLevel}">
														未考核原因：${data.noCheckrise}
														</c:if>
													</h5>
													<div class="stream-text">备注：${data.comments}</div>
												</div>
												<!--/.stream-headline-->

												<div class="stream-options">
													<c:set var="lastYear"
														value="<%=java.util.GregorianCalendar.getInstance().get(
							java.util.Calendar.YEAR) - 1%>" />

													<c:if test="${data.assessmentYear eq lastYear}">
														<shiro:hasAnyPermissions
															name="hr:assessment:update,hr:assessment:delete">
															<shiro:hasPermission name="hr:assessment:update">
																<a href="${ctx}/hr/assessment/form?id=${data.id}"
																	class="btn btn-small"> 编辑 </a>
															</shiro:hasPermission>
															<shiro:hasPermission name="hr:assessment:delete">
																<a href="${ctx}/hr/assessment/delete?id=${data.id}"
																	class="btn btn-small"> 删除 </a>
															</shiro:hasPermission>
														</shiro:hasAnyPermissions>
													</c:if>
												</div>
											</div>
										</div>
										<!--/.media .stream-->
									</div>
								</c:forEach>


							</div>
						</div>
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

	<!--/.container-->

	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>