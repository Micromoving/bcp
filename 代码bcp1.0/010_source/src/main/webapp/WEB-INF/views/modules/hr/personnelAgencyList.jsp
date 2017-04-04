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
								<h3>查看人事档案材料</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />

								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>

								<c:forEach var="data" items="${list}">
									<div class="stream-list">
										<div class="media stream">
											<div class="media-body">
												<div class="control-group">
													<c:if test="${data.archivesIsComplete eq '0' }">
														<h5>人事档案缺失材料:${data.missingMaterial}</h5>
													</c:if>
													
													 内容清单: ${data.archivesList} <br> <br>
													备注:${data.comments}
													<div class="stream-options">
														&nbsp;&nbsp;&nbsp;&nbsp;<br>
														<shiro:hasPermission name="hr:personnelAgency:update">
															<a href="${ctx}/hr/personnelAgency/form?id=${data.id}"
																class="btn btn-small">编辑</a>
														</shiro:hasPermission>
													</div>
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