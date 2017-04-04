<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/employee/list");
		$("#searchForm").submit();
		return false;
	};
</script>
</head>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>

	<div class="wrap">
		<div class="container">
			<div class="row">
				<!-- 路径 -->
				<div class="formbar">
					<b><a href="home.html">首页</a>&nbsp;>&nbsp;<a
						href="bizMenu.html">人力资源管理</a>&nbsp;>&nbsp;<a href="#">教职员工</a>&nbsp;>&nbsp;<a
						href="employeeList.html">基本信息</a> </b>
				</div>
				<!-- 按钮 -->
				<div class="formbar t-line">
					<a href="#" class="btn-con">导出</a> <a href="#" class="btn-con">导出</a>
					<a href="${ctx}/sys/user/form" class="btn-con">增加</a>
				</div>
				<!-- 筛选条件 -->
				<form:form id="searchForm" action="${ctx}/hr/employee/list"
					modelAttribute="employee" class="form-horizontal row-fluid"
					method="post">
					<input id="pageNo" name="pageNo" type="hidden"
						value="${page.pageNo}" />
					<input id="pageSize" name="pageSize" type="hidden"
						value="${page.pageSize}" />
					<div class="formbar t-line b-line">
						<sys:treeselect id="office" name="office.id"
							value="${employee.office.id}" labelName="office.name"
							labelValue="${employee.office.name}" title="部门"
							url="/sys/office/treeData?type=2" cssClass="sfQuery"
							cssStyle="float:left;" allowClear="true"
							notAllowSelectParent="false" cssPlaceholder="部门" />
						&nbsp;&nbsp; <input placeholder="姓名" value="${employee.name }" name="name" class="sfQuery"
							type="text"> &nbsp;&nbsp;
						<form:select tabindex="1" class="sfQuery" style="height: 35px;"
							path="politicalLandscape">
							<form:option value="" label="政治面貌" />
							<form:options items="${fns:getDictList('sy_political_landscape')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />

						</form:select>
						<form:button class="btn-con mgtp-4 btn-bw-0">查询</form:button>
					</div>
				</form:form>
				<!-- 内容 -->
				<div class="formbar">
				${page.message }
					<table class="listtbl">
						<thead>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>部门</th>
								<th>证件号码</th>
								<th>到校时间</th>
								<th>手机</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list }" var="data" varStatus="status">

								<tr>
									<td>${status.count+(page.pageNo-1)*page.pageSize }</td>
									<td><a href="${ctx}/hr/employee/form?id=${data.id} ">${data.user.name }</a></td>
									<td>${data.office.name }</td>
									<td>${data.idNumber }</td>
									<td>${fns:formatDate("yyyy年MM月",data.inSchoolDate)}</td>
									<td>${data.mobilePhone}</td>
									<td><a href="#">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					${page }


				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>