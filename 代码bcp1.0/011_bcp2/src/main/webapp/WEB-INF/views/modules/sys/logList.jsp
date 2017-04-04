<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/sys/log/list");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>

	<jsp:include page="../../include/top.jsp"></jsp:include>


	<div class="wrap">
		<div class="container">
			<div class="row">

				<!-- 路径 -->
				<div class="formbar">
					<b><a href="${ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx}/sys/menu/bizMenu?id=400">系统管理</a>&nbsp;>&nbsp;<a
						href="${ctx}/sys/log">操作日志</a> </b>
				</div>
				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">

											<div class="media stream">

												<form:form id="searchForm" action="${ctx}/sys/log/"
													method="post" class="breadcrumb form-search">
													<input id="pageNo" name="pageNo" type="hidden"
														value="${page.pageNo}" />
													<input id="pageSize" name="pageSize" type="hidden"
														value="${page.pageSize}" />

													<div>
														<label>操作菜单：</label>
														<input id="title" name="title"
															type="text" maxlength="50" class="btn btn-primary pull-right"
															value="${log.title}" /> <label>用户ID：</label><input
															id="createBy.id" name="createBy.id" type="text"
															maxlength="50" class="btn btn-primary pull-right"
															value="${log.createBy.id}" /> <label>URI：</label><input
															id="requestUri" name="requestUri" type="text"
															maxlength="50" class="btn btn-primary pull-right"
															value="${log.requestUri}" />
													</div>
													<div style="margin-top:8px;">
														<label>日期范围：&nbsp;</label><input id="beginDate"
															name="beginDate" type="text" readonly="readonly"
															maxlength="20" class="btn btn-primary pull-right"
															value="<fmt:formatDate value="${log.beginDate}" pattern="yyyy-MM-dd"/>"
															onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
														<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input
															id="endDate" name="endDate" type="text"
															readonly="readonly" maxlength="20" class="btn btn-primary pull-right"
															value="<fmt:formatDate value="${log.endDate}" pattern="yyyy-MM-dd"/>"
															onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />&nbsp;&nbsp;
														&nbsp;<label class="checkbox inline" for="exception"><input
															id="exception" name="exception" type="checkbox"
															${log.exception eq '1'?' checked':''} value="1" />&nbsp;只查询异常信息</label>
														&nbsp;&nbsp;&nbsp;<input id="btnSubmit"
															class="btn btn-primary" type="submit" value="查询" />&nbsp;&nbsp;
													</div>
												</form:form>
												<div class="formbar">
													<form id="listForm" method="post">
														<table class="listtbl" id="treeTable">
															<sys:message content="${message}" />
															<thead>
																<tr>
																	<th>操作菜单</th>
																	<th>操作用户</th>
																	<th>所在部门</th>
																	<th>URI</th>
																	<th>提交方式</th>
																	<th>操作者IP</th>
																	<th>操作时间</th>
															</thead>
															<tbody>
																<%
																	request.setAttribute("strEnter", "\n");
																	request.setAttribute("strTab", "\t");
																%>
																<c:forEach items="${page.list}" var="log">
																	<tr>
																		<td>${log.title}</td>
																		<td>${log.createBy.name}</td>
																		<td>${log.createBy.office.name}</td>
																		<td><strong>${log.requestUri}</strong></td>
																		<td>${log.method}</td>
																		<td>${log.remoteAddr}</td>
																		<td><fmt:formatDate value="${log.createDate}"
																				type="both" /></td>
																	</tr>
																	<c:if test="${not empty log.exception}">
																		<tr>
																			<td colspan="8"
																				style="word-wrap:break-word;word-break:break-all;">
																				<%-- 					用户代理: ${log.userAgent}<br/> --%> <%-- 					提交参数: ${fns:escapeHtml(log.params)} <br/> --%>
																				异常信息: <br />
																				${fn:replace(fn:replace(fns:escapeHtml(log.exception), strEnter, '<br/>'), strTab, '&nbsp; &nbsp; ')}
																			</td>
																		</tr>
																	</c:if>
																</c:forEach>
															</tbody>
														</table>
													</form>
													${page}
												</div>
											</div>
											<!--/.media .stream-->
										</div>
									</div>
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
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>