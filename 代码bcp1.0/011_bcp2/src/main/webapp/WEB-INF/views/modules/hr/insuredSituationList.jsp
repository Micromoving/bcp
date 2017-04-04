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
								<h3>参保管理</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="#" onclick='$("#inputForm").submit();'
												class="btn btn-primary pull-right">保存</a> <a
												href="${ctx}/hr/insuranceRecord"
												class="btn btn-primary pull-right">参保记录</a>
											<shiro:hasPermission name="hr:insuredSituation:create">
												<a href="${ctx}/hr/insuredSituation/form"
													class="btn btn-primary pull-right">新增社保</a>
											</shiro:hasPermission>
										</div>
									</div>
								</div>
								<!--基本信息-->
								<jsp:include page="../../modules/hr/profileBrief.jsp"></jsp:include>
								<!--/表格栏-->
								<div class="stream-list">
									<div class="media stream">
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p75">险种名字</th>
													<th class="p65">险种编号</th>
													<th class="p85">参保时间</th>
													<th class="p85">学院参保时间</th>
													<th class="p65">年限</th>
													<th class="p85">缴费基数</th>
												</tr>
											</thead>
											<tbody>
												<form:form class="form-horizontal row-fluid" id="inputForm"
													modelAttribute="insuredSituation"
													action="${ctx}/hr/insuredSituation/saveBatch" method="post">
													<c:forEach items="${list}" var="data" varStatus="i">
														<tr>
															<td>${data.insuranceRule.insuranceType}</td>
															<td>${data.socialInsuranceId}</td>
															<td>${fns:formatDate("yyyy-MM-dd",data.insuredDate)}</td>
															<td>${fns:formatDate("yyyy-MM-dd",data.schoolInsuredDate)}</td>
															<td>${data.insuredYear}</td>
															<td><input type="text" class="theFormInput p65 p200"
																name="baseArray[${i.index }]" value="${data.base}">
																<input type="hidden"
																name="insuranceIdArray[${i.index }]" value="${data.id}">
															</td>
														</tr>
													</c:forEach>
												</form:form>
											</tbody>
										</table>

									</div>
									<!--/.media .stream-->
								</div>
							</div>
							<!--/.module-->
						</div>
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