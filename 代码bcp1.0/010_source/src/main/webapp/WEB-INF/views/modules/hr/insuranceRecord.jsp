<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>

<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/hr/insuranceRecord/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
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
								<h3>参保记录</h3>
							</div>
							<div class="module-body">
								<sys:message content="${message}" />

								<!--/按钮-->
								<div class="stream-composer media">
									<div class="media-body">

										<div class="clearfix">
											<input id="btnExport" class="btn btn-primary pull-right" type="button" value="导出 "/> 
											<a href="#" class="btn btn-primary pull-right"> 打印 </a>
										</div>
									</div>
								</div>
								<!--记录-->
								<div class="stream-list">
									<div class="media stream">
										<form:form id="searchForm"
											action="${ctx}/hr/insuranceRecord/list"
											modelAttribute="insuranceRecord"
											class="form-horizontal breadcrumb" method="post">

											<div class="control-group">姓名： ${user.name }
												&nbsp;&nbsp;&nbsp;&nbsp;部门：${fns:getOfficeRootName(employee.office.id)}
											</div>
										</form:form>
									</div>
									<!--/.media .stream-->

								</div>
								<!--/表格栏-->
								<div class="stream-list">
									<div class="media stream">
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p27">序号</th>
													<th class="p87">险种</th>
													<th>单位</th>
													<th>个人</th>
													<th class="p85">缴费时间</th>
													<th>备注</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${list}" var="data" varStatus="status">
													<tr>
														<td style="text-align: center;">${status.count }</td>
														<td>${data.insuranceRule.insuranceType}</td>
														<td>${data.unitPayment}</td>
														<td>${data.peoplePayment}</td>
														<td>${fns:formatDate("yyyy-MM",data.paymentDate)}</td>
														<td>${data.comments}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!--/.media .stream-->
								</div>
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
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>