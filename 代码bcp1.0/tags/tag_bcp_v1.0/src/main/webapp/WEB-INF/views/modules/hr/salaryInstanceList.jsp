<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/salaryInstance/list");
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
								<h3>工资管理</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a
												href="${ctx}/hr/salaryInstance/form?procDefId=${process.id}"
												class="btn btn-primary pull-right"> 启动工资 </a>
										</div>
										<br>
										<div class="stream-list">
											<div class="media stream">
												<form:form id="searchForm"
													action="${ctx}/hr/salaryInstance/list"
													modelAttribute="salaryInstance"
													class="form-horizontal row-fluid" method="post">
													<input id="pageNo" name="pageNo" type="hidden"
														value="${page.pageNo}" />
													<input id="pageSize" name="pageSize" type="hidden"
														value="${page.pageSize}" />

													<!-- 筛选条件 -->
													<div class="control-group">
														上报时间&nbsp;&nbsp;
														<form:input path="yearMonth" class="span2"
															onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>

														<a href="#" class="btn btn-primary pull-right"
															onclick="return page('','','${ctx}/hr/salaryInstance/list');">
															查询 </a>
													</div>
												</form:form>
											</div>
										</div>
									</div>
								</div>
								<!--/实践经历列表-->
								<div class="stream-list">
									<div class="media stream">
										<div class="media-body">
											<c:forEach items="${page.list}" var="data">
												<div class="stream-headline">

													<h5 class="stream-author">${data.yearMonth}</h5>
													<div class="stream-text">
														启动人：${data.createBy.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														启动时间：${fns:formatDate("yyyy-MM-dd",data.createDate)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														状态：
														<c:if test="${not empty data.dataState}">${data.act.taskName}</c:if>
														<c:if test="${empty data.dataState}">待上报</c:if>
														<c:if test="${empty data.act.taskName}">已完成</c:if>
														<br> 工资信息： [
														<c:forEach items="${data.salaryInstanceTaskList}"
															var="datatask">
															<c:if test="${datatask.salaryItems.salaryType eq '1'}">
																${datatask.salaryItems.salaryItemsName},
															</c:if>
														</c:forEach>
														] <br> 福利信息： [
														<c:forEach items="${data.salaryInstanceTaskList}"
															var="datatask">
															<c:if test="${datatask.salaryItems.salaryType eq '2'}">
																${datatask.salaryItems.salaryItemsName},
															</c:if>
														</c:forEach>
														]<br> 社保信息： [
														<c:forEach items="${data.salaryInstanceTaskList}"
															var="datatask">
															<c:if test="${datatask.salaryItems.salaryType eq '3'}">
																${datatask.salaryItems.salaryItemsName},
															</c:if>
														</c:forEach>
														]
													</div>
												</div>
												<!--/.stream-headline-->

												<div class="stream-options">
													<c:if test="${empty data.dataState}">
														<a href="${ctx}/hr/salaryInstance/form?id=${data.id}"
															class="btn btn-small"> 更改 </a>
														<a
															href="${ctx}/hr/salaryInstance/commitActiviti?id=${data.id}"
															class="btn btn-small"> 上报工资 </a>
													</c:if>
													<a
														href="${ctx}/hr/reportRecord/list?salaryInstance.id=${data.id}"
														class="btn btn-small"> 上报明细 </a><a
														href="${ctx}/hr/salaryInstance/initialResults?salaryInstance.id=${data.id}"
														target="_blank" class="btn btn-small"> 工资详情 </a>
												</div>
											</c:forEach>

										</div>
										${page}
									</div>
								</div>
								<!--/.stream-list-->
							</div>
							<!--/.module-body-->
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

	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>