<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
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
								<h3>津贴标准</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/hr/subsidiaryStandard/form"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>

								<div class="stream-list">


									<!--/表格栏-->
									<div class="media stream">
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p27">序号</th>
													<th class="p85">津贴类型</th>
													<th align="center">津贴主体</th>
													<th align="center">费用标准</th>
													<th class="p55" align="center">津贴单位</th>
													<th class="p180" align="center">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="data" items="${list}" varStatus="status">
													<tr>
														<td>${status.count }</td>
														<td>${fns:getDictLabel(data.allowanceType,'allowance_type','')}</td>
														<td>${fns:getDictLabel(data.allowanceMainBody,'allowance_main_body','')}</td>
														<td>${data.chargeStandard.intValue()}</td>
														<td>${fns:getDictLabel(data.allowanceUnit,'allowance_unit','')}</td>
														<td><a
															href="${ctx}/hr/subsidiaryStandard/form?id=${data.id}">修改</a>
															<a
															href="${ctx}/hr/subsidiaryStandard/delete?id=${data.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!--/.media .stream-->

								</div>
								<!--/stream-list-->
							</div>
							<!--/.module-body-->

						</div>
						<!--/.module-->
					</div>
					<!--/.content-->
				</div>
				<!--/.span9-->
			</div>
			<!--/.row-->
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>