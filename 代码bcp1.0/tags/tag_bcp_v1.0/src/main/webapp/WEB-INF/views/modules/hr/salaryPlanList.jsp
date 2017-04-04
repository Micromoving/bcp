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
								<h3>方案管理</h3>
							</div>
							<div class="module-body">
								<form:form action="${ctx}/hr/salaryPlan/enable"
									modelAttribute="salaryPlan" class="form-horizontal row-fluid"
									method="post">
									<!--/增加按钮-->
									<div class="stream-composer media">
										<div class="media-body">
											<div class="clearfix">
												<button type="submit" class="btn btn-primary pull-right">启用</button>
												<a href="${ctx }/hr/salaryPlan/form"
													class="btn btn-primary pull-right"> 增加 </a>
											</div>
										</div>
									</div>
									<!--/搜索栏-->
									<div class="stream-list">
										<!--/表格栏-->
										<div class="media stream">
											<table
												class="table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th >序号</th>
														<th >状态</th>
														<th>方案名称</th>
														<th>创建者</th>
														<th>创建时间</th>
														<th>方案说明</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="data" items="${list }" varStatus="status">
														<tr>
															<td>${status.count}</td>
															<td><input type="radio" name="planStatus" ${data.planStatus eq "1"?"checked=\"checked\"":""}
																value="${data.id }">
																</td>
															<td>${data.planName }</td>
															<td>${data.createBy.name }</td>
															<td>${data.createDateString }</td>
															<td>${data.planExplain }</td>
															<td>	<c:if test="${data.planStatus ne '1'}"><a href="${ctx}/hr/salaryPlan/form?id=${data.id}"
														> 编辑 </a>	<a href="${ctx}/hr/salaryPlan/delete?id=${data.id}" onclick="return confirmx('确认要删除该记录吗？', this.href)"
															> 删除 </a></c:if><a  href="${ctx}/hr/salaryStandard/list?salaryPlan.id=${data.id}&postType=1&menuIds=510,5101,5101A">详情</a></td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>


									</div>
									<!--/.media .stream-->
								</form:form>
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