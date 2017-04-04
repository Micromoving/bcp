<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$('td').css('text-align', 'center').css('padding', '2px');
		$('td input').css('padding', '2px');
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/classTeacherFee/list");
		$("#searchForm").submit();
		return false;
	}
	function submitBatch(state) {
		$("#postType").val(state);
		$("#batchForm").submit();
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
								<h3>增加班主任费</h3>
							</div>
							<div class="module-body">
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/hr/classTeacherFee/upload"
												class="btn btn-primary pull-right"> 导入 </a>
										</div>
									</div>
								</div>
								<!--/搜索栏-->
								<div class="stream-list">
									<div class="media stream">

										<form class="form-horizontal row-fluid" method="post">


											<!-- 归属部门 -->
											<div class="control-group">


												&nbsp;&nbsp; 姓名 <input name="name" class="span2" type="text">
												&nbsp;&nbsp;部门
												<sys:treeselect id="office" name="office.id"
													value="${user.office.id}" labelName="office.name"
													labelValue="${user.office.name}" title="部门"
													url="/sys/office/treeData?type=2" cssClass="input-small"
													allowClear="true" notAllowSelectParent="false" />
												<a href="#" class="btn btn-primary pull-right"> 查询 </a>
                                                 
											</div>
										</form>
									</div>
									<!--/表格栏-->
									<div class="media stream">
										<div class="alert alert-success">
											<button type="button" class="close" data-dismiss="alert">×</button>
											<strong>Well done!</strong> Now you are listening me :)
										</div>
										<table
											class="table table-striped table-bordered table-condensed">
											<thead>
												<tr>
													<th class="p60">序号</th>
													<th class="p60">工号</th>
													<th class="p60">姓名</th>
													<th class="p60">部门</th>
													<th class="p60">岗位类型</th>
													<th class="p60">带班数</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="data" items="${list}" varStatus="status">
													<tr>
														<td>${status.count}</td>
														<td>${data.salEmpView.loginName}</td>
														<td>${data.salEmpView.name}</td>
														<td>${data.salEmpView.office}</td>
														<td>${data.salEmpView.postType}</td>
														<td><input value="${data.classNumber}" class="span2" type="text" >&nbsp;&nbsp;个</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<br>
										<!--/提交按钮-->
										<div class="control-group" style="float: right">
											<div class="controls">
												<button class="btn btn-primary">确定</button>
											</div>
										</div>
										<!--/.media .stream-->

									</div>


								</div>

							</div>
							<!--/.module-body-->
						</div>
					</div>
					<!--/.module-->
				</div>
				<!--/.content-->

				<!--/.span9-->
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>