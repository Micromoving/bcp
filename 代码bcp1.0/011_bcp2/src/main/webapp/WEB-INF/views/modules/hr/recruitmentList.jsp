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

				<!--/.span3-->
				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>岗位聘任管理</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="hr:recruitment:create">
												<a href="${ctx}/hr/recruitment/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</shiro:hasPermission>
										</div>
									</div>
								</div>
								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>

								<!--/列表-->
								<c:forEach items="${list}" var="data">

									<div class="media stream">
										<div class="media-body">
											<div class="stream-headline">

												<table class="table table-condensed">
													<thead>
														<tr>
															<td width="40%">聘任起止时间</td>
															<td colspan="3">${fns:formatDate("yyyy年MM月dd日",data.startDate)}－${fns:formatDate("yyyy年MM月dd日",data.endDate)}</td>
														</tr>
														<c:if test="${not empty data.duties}">
															<tr>
																<td>应聘职务</td>
																<td colspan="3">${fns:getDictLabel(data.duties,'post_name','')}</td>
															</tr>
														</c:if>
														<tr>
															<td>应聘部门</td>
															<td>${fns:getOfficeRootName(data.user.office.id)}</td>
														</tr>
														<c:if test="${not empty data.engageSituation}">
														<tr>
															<td>聘任情况</td>
															<td>${fns:getDictLabel(data.engageSituation,'engage_situation','无')}</td>
														</tr>
														</c:if>
														<tr>
															<td>岗位类型</td>
															<td>${fns:getDictLabel(data.postType,'post_type','无')}</td>
														</tr>
														<c:if test="${not empty data.postLevel}">
														<tr>
															<td>岗位等级</td>
															<td>${fns:getDictLabel(data.postLevel,'post_level','无')}</td>
														</tr>
														</c:if>
														<c:if test="${not empty data.professionalLevel}">
														<tr>
															<td>职级等级</td>
															<td>${fns:getDictLabel(data.professionalLevel,'professional_level','无')}</td>
														</tr>
														</c:if>
														<c:if test="${not empty data.dutiesLevel}">
														<tr>
															<td>职务等级</td>
															<td>${fns:getDictLabel(data.dutiesLevel,'duties_level','无')}</td>
														</tr>
														</c:if>
														<c:if test="${not empty data.tryOutDate}">
														<tr>
															<td class="p180">新选拔中层干部试用期截止时间</td>
															<td class="p180">${fns:formatDate("yyyy年MM月dd日",data.tryOutDate)}</td>
														</tr>
														</c:if>
														<tr>
															<td>是否签订聘用合同</td>
															<td>${fns:getDictLabel(data.isSignContract,'is_sign_contract','无')}</td>
														</tr>
													</thead>
												</table>
											</div>
											<!--/.stream-headline-->

											<div class="stream-options">
												<shiro:hasPermission name="hr:recruitment:update">
													<a href="${ctx}/hr/recruitment/form?id=${data.id}"
														class="btn btn-small"> 编辑 </a>
												</shiro:hasPermission>
											</div>
										</div>
									</div>

								</c:forEach>
							</div>
						</div>
						<!--/.media .stream-->
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
	<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>