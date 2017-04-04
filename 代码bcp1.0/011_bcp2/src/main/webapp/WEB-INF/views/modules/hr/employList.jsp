<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);

		$("#searchForm").submit();
		return false;
	}
	
	function exportExcel() {
		$("#searchForm").attr("action", "${ctx}/hr/employ/export");
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
								<h3>招聘管理</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a id="btnExport" href="#" onclick="exportExcel();"
													class="btn btn-primary pull-right"> 导出 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">

										<form:form id="searchForm" action="${ctx}/hr/employ/list"
											modelAttribute="employ" class="form-horizontal row-fluid"
											method="post">
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />

											<!-- 归属部门 -->
											<div class="control-group">
												姓名 <input name="name" style="width: 60px;" type="text">
												岗位类型
												<form:select path="postType" class="span2" onchange="selectChange(this.value,'${ctxFront}/hr/employ/subList','declarePositionNumber')">
													<form:option value=""></form:option>
													<form:options
														items="${fns:getDictList('employ_post_type')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>申报岗位
												<form:select path="declarePositionNumber" class="span2">
													<form:option value=""></form:option>
													<form:options
														items="${fns:getDictList('declare_position_number')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
												&nbsp;&nbsp;
												审核状态
												<form:select path="auditingStatus" class="span2">
													<form:option value=""></form:option>
													<form:options items="${fns:getDictList('auditing_status')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />

												</form:select>

												<a href="#"
													onclick="return page('','','${ctx}/hr/employ/list');"
													class="btn btn-primary pull-right"> 查询 </a>

											</div>
										</form:form>
									</div>
									<!--/.media .stream-->
									<!--/表格栏-->

									<div class="media stream">
										<sys:message content="${message}" />
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p27">序号</th>
													<th class="p55">姓名</th>
													<th class="p30">性别</th>
													<th>申报岗位</th>
													<th>岗位类型</th>
													<th>最高学历（位）</th>
													<th>申请时间</th>
													<th class="p75">操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${page.list}" var="data"
													varStatus="status">
													<tr>
														<td style="text-align: center;">${status.count }</td>
														<td><a href="${ctx}/hr/employ/detail?id=${data.id}">${data.name}
														</a></td>
														<td>${fns:getDictLabel(data.gender,'gender','')}</td>

														<td>${fns:getDictLabel(data.declarePositionNumber,'declare_position_number','')}</td>

														<td>${fns:getDictLabel(data.postType,'employ_post_type','')}</td>

														<td>${fns:getDictLabel(data.highestDegree,'academic_degree','')}(${fns:getDictLabel(data.highestEduBackground,'highest_edu_background','')})</td>
														<td>${fns:formatDate("yyyy-MM-dd HH:mm:ss",data.applyDate)}</td>
														<td><c:if test="${'1' eq data.auditingStatus}">
																<shiro:hasPermission name="hr:employ:audit">
																	<a href="${ctx}/hr/employ/detail?id=${data.id}">审核</a>
																</shiro:hasPermission>
															</c:if> <c:if test="${'1' ne data.auditingStatus}">
														${fns:getDictLabel(data.auditingStatus,'auditing_status','')}</c:if>
													</tr>

												</c:forEach>
											</tbody>
										</table>
										${page}
									</div>
									<!--/.media .stream-->

								</div>
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

	<div class="footer">
		<div class="container">
			<b class="copyright">山西大学商务学院信息学院微动--Micromoving &copy </b> All
			rights reserved.
		</div>
	</div>
</body>
</html>