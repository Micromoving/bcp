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
								<h3>学历教育</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="degreeEduForm.html"
												class="btn btn-primary pull-right"> 导出 </a> <a
												href="${ctx}/hr/degreeEdu/form"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm" action="${ctx}/hr/degreeEdu/list"
											modelAttribute="degreeEdu" class="form-horizontal row-fluid"
											method="post">
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />
											<!-- 归属部门 -->
											<div class="control-group">

												<div class="control-group">
													部门
													<sys:treeselect id="office" name="office.id"
														value="${user.office.id}" labelName="office.name"
														labelValue="${user.office.name}" title="部门"
														url="/sys/office/treeData?type=2" cssClass="input-small"
														allowClear="true" notAllowSelectParent="false" />
													&nbsp;&nbsp; 姓名 <input name="name" class="span2"
														type="text"> &nbsp;&nbsp; 攻读类型
													<form:select tabindex="1" class="span2" path="eduType">
														<form:option value="" label="请选择" />
														<form:options items="${fns:getDictList('edu_type')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />

													</form:select>

													<a href="#" class="btn btn-primary pull-right"
														onclick="return page('','','${ctx}/hr/degreeEdu/list');">
														查询 </a>

												</div>
											</div>
										</form:form>
									</div>
									<form:form id="inputForm" action="${ctx}/hr/degreeEdu/commit"
										modelAttribute="degreeEdu" class="form-horizontal "
										method="post">
										<sys:message content="${message}" />
										<!--/.media .stream-->
										<div class="media stream">
											<!--/.media .stream-->
											<!--/表格栏-->
											<div class="media stream">
												<table
													class="table table-striped table-bordered table-condensed">
													<thead>
														<tr>
															<th class="p27">序号</th>
															<th class="p55">姓名</th>
															<th>部门</th>
															<th>攻读学历</th>
															<th>攻读学位</th>
															<th class="p85">攻读方式</th>
															<th class="p85">是否脱产</th>
															<th class="p55">操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="data" items="${page.list}"
															varStatus="status">
															<tr>
																<td style="text-align: center;">${status.count}</td>
																<td>${data.user.name}</td>
																<td>${data.office.name }</td>
																<td>${fns:getDictLabel(data.diploma,'diploma','')}</td>
																<td>${fns:getDictLabel(data.degree,'degree','')}</td>
																<td>${fns:getDictLabel(data.eduMode,'edu_mode','')}</td>
																<td>${fns:getDictLabel(data.isOffjob,'yes_no','')}</td>

																<td>
																	<!--  <input type="hidden"
																	name="degreeIdList[${status.index }]"
																	value="${data.id}"> <c:if
																		test="${empty data.processInstanceId}">
																		<select name="flagList[${status.index}]" class="span1">
																			<option value="0">否</option>
																			<option value="1">是</option>
																		</select>
																	</c:if> <c:if test="${not empty data.processInstanceId}">是</c:if>
																</td>
																--> <a href="${ctx}/hr/degreeEdu/form?id=${data.id}">修改</a>

																	<a href="${ctx}/hr/degreeEdu/delete?id=${data.id}"
																	onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<!--<div class="control-group">
											
											<c:if test="${empty data.processInstanceId}">
												<div class="controls" id="submit">
													<input type="submit" class="btn btn-primary" id="submit "
														value="提交">
												</div>
											</c:if>
											
										</div>-->
										<!--/提交按钮-->
									</form:form>

								</div>
								${page}
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
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>