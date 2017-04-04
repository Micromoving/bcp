<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#btnExport").click(
						function() {
							top.$.jBox.confirm("确认要导出吗？", "系统提示", function(v,
									h, f) {
								if (v == "ok") {
									$("#searchForm").attr("action",
											"${ctx}/hr/degreeEdu/export");
									$("#searchForm").submit();
								}
							}, {
								buttonsFocus : 1
							});
							top.$('.jbox-body .jbox-icon').css('top', '55px');
						});
			});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/degreeEdu/list");
		$("#searchForm").submit();
		return false;
	}
</script>
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
								<h3>培训</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/hr/trainExperience/form?procDefId=${process.id}"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm"
											action="${ctx}/hr/trainExperience/list"
											modelAttribute="trainExperience"
											class="form-horizontal row-fluid" method="post">
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />
											<!-- 归属部门 -->
											<div class="control-group">
												部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门
												<sys:treeselect id="office" name="office.id"
													value="${user.office.id}" labelName="office.name"
													labelValue="${user.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />
												&nbsp;&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名 <input
													name="name" class="span2" type="text"> &nbsp;&nbsp;<br>
												<br> 培训类型 
													<form:select tabindex="1" class="span2" path="trainingType">
														<form:option value="" label="请选择" />
														<form:options items="${fns:getDictList('training_type')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />

													</form:select>
													
												&nbsp;&nbsp;培训周期
														<form:select tabindex="1" class="span2" path="trainingCycle">
														<form:option value="" label="请选择" />
														<form:options items="${fns:getDictList('training_cycle')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />

													</form:select>
												&nbsp;培训时间<form:input class="span2" path="startDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>至<form:input class="span2 " path="endDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
													<a href="#" class="btn btn-primary pull-right"
														onclick="return page('','','${ctx}/hr/trainExperience/list');">
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
													<th class="p55">部门</th>
													<th class="p85">类型</th>
													<th class="p30">周期</th>
													<th class="p100">机构</th>
													<th class="p100">培训时间</th>
													<th class="p45">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="list" items="${list}"
													varStatus="status">
													<tr>
														<td style="text-align: center;">${status.count }</td>
														<td><a
															href="${ctx}/hr/trainExperience/detail?id=${list.id}">${list.getUser().name}
														</a></td>
														<td>${fns:getOfficeRootName(list.office.id)}</td>
														<td>${fns:getDictLabel(list.trainingType,'training_type','')}</td>
														<td>${fns:getDictLabel(list.trainingCycle,'training_cycle','')}</td>
														<td>${list.trainingAgency}</td>
														<td>${fns:formatDate("yyyy-MM-dd",list.startDate)}<c:if test="${not empty list.startDate}">-</c:if>${fns:formatDate("yyyy-MM-dd",list.endDate)}</td>
														<td><a
															href="${ctx}/hr/trainExperience/form?id=${list.id}">修改</a>
															<a href="${ctx}/hr/trainExperience/delete?id=${list.id}"
																	onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a></td>
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