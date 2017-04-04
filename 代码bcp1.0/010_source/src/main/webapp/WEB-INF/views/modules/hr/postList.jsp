<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>岗位设置</title>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/post/list");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<!--顶部菜单-->
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>岗位设置</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/hr/post/form"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm" action="${ctx}/hr/post/list"
											modelAttribute="post" class="form-horizontal row-fluid"
											method="post">
											<input id="pageNo" name="pageNo" type="hidden"
												value="${page.pageNo}" />
											<input id="pageSize" name="pageSize" type="hidden"
												value="${page.pageSize}" />
											<!-- 归属部门 -->
											<div class="control-group">
											
											
											
											
												部门
												<sys:treeselect id="office" name="office.id"
													value="${post.office.id}" labelName="office.name"
													labelValue="${post.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													 notAllowSelectParent="false" />
											
												<a href="#" class="btn btn-primary pull-right"
													onclick="return page('','','${ctx}/hr/post/list');"> 查询
												</a>
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
													<th class="p45">部门</th>
													<th class="p45">岗位名称</th>
													<th class="p45">岗位等级</th>
													<th class="p27">职数</th>
													<th class="p27">备注</th>
													<th class="p75">操作</th>
												</tr>
											</thead>
											<tbody>
											
												<c:forEach var="postData" items="${page.list}"
													varStatus="status">
													<c:if test="${postData.postLevel}"></c:if>
													<tr>
														<td style="text-align: center;">${status.count }</td>
														<td>${postData.office.name}</td>
														<td>${fns:getDictLabel(postData.postName,'post_name','')}</td>
														<td>${fns:getDictLabels(postData.postLevel,'post_level','')}</td>
														<td>${postData.postNumberSetting}</td>
														<td>${postData.comments }</td>
														<td><a href="${ctx}/hr/post/form?id=${postData.id}">修改</a>
															<a href="${ctx}/hr/post/delete?id=${postData.id}"
															onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
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
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>