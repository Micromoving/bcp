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
								<h3>录入培训资料</h3>
							</div>
							<div class="module-body">

								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">

										<form:form class="form-horizontal row-fluid" id="inputForm"
											modelAttribute="trainExperience" method="post">
											<sys:message content="${message}" />
											<form:hidden path="id" />
											<!-- 归属部门 -->
											<div class="control-group">

												&nbsp;&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名 <input
													name="name" class="span2" type="text"> &nbsp;&nbsp;
												性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别 <select tabindex="1"
													class="span2" name="training_cycle">
													<option>请选择</option>
												</select><br> <br>&nbsp;&nbsp;&nbsp;职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称
												<select tabindex="1" class="span2" name="training_type">
													<option>请选择</option>
												</select> &nbsp;&nbsp;&nbsp;部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门
												<sys:treeselect id="office" name="office.id"
													value="${user.office.id}" labelName="office.name"
													labelValue="${user.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />
												&nbsp;&nbsp; <a href="#" class="btn btn-primary pull-right">
													查询 </a>

											</div>
										</form:form>
									</div>
									<!--/表格栏-->
									<div class="media stream">
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p27">序号</th>
													<th class="p45">姓名</th>
													<th class="p55">性别</th>
													<th class="p85">职称</th>
													<th class="p30">部门</th>
													<th class="p45">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="list" items="${list}" varStatus="status">
													<tr>
														<td style="text-align: center;">${status.count }</td>
														<td><a
															href="${ctx}/hr/trainExperience/form?id=${list.id}">${fns:getUser().name}
														</a></td>
														<td>${fns:getDictLabel(list.employee.gender,'gender','')}</td>
														<td></td>
														<td>${fns:getOfficeRootName(list.office.id)}</td>
														<td><a href="${ctx}/hr/trainExperience/selfInputForm">录入</a></td>
													</tr>

												</c:forEach>
											</tbody>
										</table>
										${page}
									</div>
									<!--/.media .stream-->



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
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>