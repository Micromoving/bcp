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
								<h3>教育经历</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<shiro:hasPermission name="hr:edu:create">
												<a href="${ctx}/hr/edu/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</shiro:hasPermission>
										</div>
									</div>
								</div>
								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>
								<c:forEach items="${list}" var="data">
									<div class="stream-list">
										<div class="media stream">
											<div class="media-body">
												<div class="stream-headline">
													
													<table class="table table-condensed">
													<thead>
													<tr>
														<td>起止日期</td>
														<td>${fns:formatDate("yyyy年MM月",data.startDate)}—${fns:formatDate("yyyy年MM月",data.endDate)}</td>
														<td>毕业院校</td>
														<td>${data.graduateSchool}</td>
														
														
												       </tr>
														
													<tr>
													<td> 专业</td>
													<td>${data.specialty}</td>
														<td>研究方向</td>
														<td>${data.researchArea}</td>
													
													</tr>
													
														<tr>
														<td>学历</td>
														<td>${fns:getDictLabel(data.eduBackground,'edu_background','无')}</td>
														<td>学历取得时间</td>
														<td>${data.eduBackgroundDateString}</td>
														</tr>
														<tr>
														<td>学位</td>
														<td>${fns:getDictLabel(data.degree,'academic_degree','无')}</td>
														<td>学位取得时间</td>
														<td>${data.getDegreeDateString}</td>
														</tr>
														
														<tr>
														
														<td>就读类型</td>
														<td>${fns:getDictLabel(data.studyType,'study_type','无')}</td>
														<td>是否海外学习</td>
														<td><c:if test="${1 eq data.isOverseas }">海外学习</c:if>
														<c:if test="${2 eq data.isOverseas }">
														无海外学习</c:if></td>
														</tr>
														<tr>
														<td>专业描述</td>
														<td colspan="3">${data.remarks}</td>
														
														</tr>
														</thead>
														</table>
													</div>
													<!--/.stream-headline-->
													<div class="stream-options">
													<shiro:hasAnyPermissions
															name="hr:edu:update,hr:edu:delete">
														<shiro:hasPermission name="hr:edu:update">
																<a href="${ctx}/hr/edu/form?id=${data.id}"
															class="btn btn-small"> 编辑 </a>
															</shiro:hasPermission>
																<shiro:hasPermission name="hr:edu:delete">
															<a href="${ctx}/hr/edu/delete?id=${data.id}" class="btn btn-small"
															onclick="return confirmx('确认要删除此记录吗？', this.href)">删除</a>
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
			</div>
			<!--/.span9-->
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>