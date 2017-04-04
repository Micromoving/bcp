<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
function page(n,s){
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").attr("action","${ctx}/sys/dict/list");
	$("#searchForm").submit();
	return false;
}
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
								<h3>数据字典管理</h3>
							</div>
							<div class="module-body">
							<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/sys/dict/form"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">

										<form:form id="searchForm" modelAttribute="dict"
											action="${ctx}/sys/dict/" method="post"
											class=" form-search">
											<div class="control-group">
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />
											<label>类型：</label>
											<form:select id="type" path="type" class="input-medium">
												<form:option value="" label="" />
												<form:options items="${typeList}" htmlEscape="false" />
											</form:select>
												&nbsp;&nbsp;<label>描述 ：</label>
											<form:input path="description" htmlEscape="false"
												maxlength="50" class="input-medium" />
												&nbsp;<a href="#" class="btn btn-primary pull-right"
												onclick="return page();"> 查询 </a>
												</div>
										</form:form>
										<sys:message content="${message}" />
										<table id="contentTable"
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th>键值</th>
													<th>标签</th>
													<th>类型</th>
													<th>描述</th>
													<th>排序</th>
													<shiro:hasPermission name="sys:dict:edit">
														<th>操作</th>
													</shiro:hasPermission>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${page.list}" var="dict">
													<tr>
														<td>${dict.value}</td>
														<td><a href="${ctx}/sys/dict/form?id=${dict.id}">${dict.label}</a></td>
														<td><a href="javascript:"
															onclick="$('#type').val('${dict.type}');$('#searchForm').submit();return false;">${dict.type}</a></td>
														<td>${dict.description}</td>
														<td>${dict.sort}</td>

														<td><a href="${ctx}/sys/dict/form?id=${dict.id}">修改</a>
															<a
															href="${ctx}/sys/dict/delete?id=${dict.id}&type=${dict.type}"
															onclick="return confirmx('确认要删除该字典吗？', this.href)">删除</a>
															<a
															href="<c:url value='${fns:getAdminPath()}/sys/dict/form?type=${dict.type}&sort=${dict.sort+10}'><c:param name='description' value='${dict.description}'/></c:url>">添加键值</a>
														</td>

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
		<!--/.wrapper-->
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>