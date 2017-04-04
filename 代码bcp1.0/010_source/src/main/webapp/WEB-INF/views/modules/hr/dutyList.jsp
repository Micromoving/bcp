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
				<jsp:include page="../../include/leftMenuSub.jsp"></jsp:include>
				<div class="span9">


					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>值班标准</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<c:if test="${duty.salaryPlan.planStatus ne '1' }">

												<a href="${ctx}/hr/duty/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</c:if>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">


										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p27">序号</th>
													<th class="p55">类型</th>
													<th class="p55">费用标准</th>
													<c:if test="${duty.salaryPlan.planStatus ne '1' }">
														<th class="p75">操作</th>
													</c:if>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="data" items="${list}" varStatus="status">
													<tr>
														<td>${status.count}</td>
														<td>${fns:getDictLabel(data.dutyType,'duty_type'," ")}</td>
														<td>${data.chargeStandard}</td>
														<c:if test="${duty.salaryPlan.planStatus ne '1' }">
															<td><a href="${ctx}/hr/duty/form?id=${data.id}">
																	编辑 </a> <a href="${ctx}/hr/duty/delete?id=${data.id}"
																onclick="return confirmx('确认要删除该记录吗？', this.href)">
																	删除 </a></td>
														</c:if>

													</tr>
												</c:forEach>

											</tbody>
										</table>

									</div>
									<!--/.media .stream-->

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
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>