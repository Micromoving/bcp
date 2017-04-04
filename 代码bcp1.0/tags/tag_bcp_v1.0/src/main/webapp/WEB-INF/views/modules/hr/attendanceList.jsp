<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/hr/attendance/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
	function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm").attr("action","${ctx}/hr/attendance/list");
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
								<h3>历史请假记录</h3>
							</div>
							<div class="module-body">
							<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a id="btnExport" href="${ctx}/hr/attendance/export"
												class="btn btn-primary pull-right"> 导出 </a>
										</div>
									</div>
								<div class="stream-list">
								<sys:message content="${message}" />
									<!--/.media .stream-->
									<!--/表格栏-->
									<div class="media stream">
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th>申请人</th>
													<th>申请时间</th>
													<th>请假时间</th>
													<th>销假时间</th>
													<th>销假状态</th>
													<th>类型</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach var="attendanceData" items="${page.list}"
													varStatus="status">
													<c:if test="${attendanceData.leaveStatus eq '1' }">
														<tr>
															<td><a
																href="${ctx}/hr/leave/detail?id=${attendanceData.id}">${attendanceData.user.name}</a></td>
															<td>${fns:formatDate("yyyy-MM-dd",attendanceData.applyDate)}</td>
															<td>${fns:formatDate("yyyy-MM-dd",attendanceData.startDate)}至${fns:formatDate("yyyy-MM-dd",attendanceData.endDate)}</td>
															<td>${fns:formatDate("yyyy-MM-dd",attendanceData.reportDate)}</td>
															<td>${attendanceData.act.taskName}</td>
															<td>${fns:getDictLabel(attendanceData.leaveType,'oa_leave_type','')}</td>
															<td>
															<c:if
																	test="${empty attendanceData.reportPeople}">
																	<a
																		href="${ctx}/hr/attendance/reportSave?id=${attendanceData.id}">申请销假</a>
																</c:if>
																</td>
														</tr>
													</c:if>
												</c:forEach>
											</tbody>
										</table>
									</div>

								</div>
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

	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
























