<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate({
			errorPlacement : function(error, element) {
				error.appendTo(element.next());
			}
		});
	});
</script>
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
								<h3>培训审核</h3>
							</div>
							<div class="module-body">
								<!--/按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="#" class="btn btn-primary pull-right"> 暂存 </a> <a
												href="#" class="btn btn-primary pull-right"> 提交 </a>
										</div>
									</div>
								</div>
								<div class="stream-list">
									<!--/表格栏-->
									<form:form class="form-horizontal row-fluid" id="inputForm"
										modelAttribute="trainExperience"
										action="${ctx}/hr/trainExperience/saveAudit" method="post">
										<form:hidden path="id" />
										<form:hidden path="act.taskId" />
										<form:hidden path="act.taskName" />
										<form:hidden path="act.taskDefKey" />
										<form:hidden path="act.procInsId" />
										<form:hidden path="act.procDefId" />
										<form:hidden id="flag" path="act.flag" />
										<sys:message content="${message}" />
										<div class="media stream">
											<table
												class="table table-striped table-bordered table-condensed">
												<thead>
													<tr>
														<th class="p10">序号</th>
														<th class="p50">姓名</th>
														<th class="p10">性别</th>
														<th class="p35">职称</th>
														<th class="p50">类型</th>
														<th class="p50">机构</th>
														<th class="p100">操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="data" items="${list}" varStatus="status">
														<tr>
															<td>${status.count}</td>
															<td><a
																href="${ctx}/hr/trainExperience/form?id=${data.id}">${data.user.name}
															</a></td>
															<td>${fns:getDictLabel(data.employee.gender,'gender',"")}</td>
															<td>${fns:getDictLabel(data.protp.name,'tech_position',"")}</td>
															<td>${fns:getDictLabel(data.trainingType,'training_type',"")}</td>
															<td>${data.trainingAgency}</td>
															<td><c:set var="status"
																	value='<%=request.getParameter("status")%>'></c:set> <c:if
																	test="${empty status}">
																	<input id="btnSubmit" class="btn btn-success "
																		type="submit" value="审核通过 "
																		onclick="$('#flag').val('yes')" />
																</c:if></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>