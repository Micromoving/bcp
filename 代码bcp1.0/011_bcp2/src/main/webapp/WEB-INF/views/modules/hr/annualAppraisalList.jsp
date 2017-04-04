<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/annualAppraisal/list");
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
					<b><a href="${ctx}/home }">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a>&nbsp;>&nbsp;<a
						class="same">绩效考核</a>&nbsp;>&nbsp;<a
						href="${ctx }/hr/annualAppraisal/list">年度考核</a> </b>
				</div>
				<!-- 按钮 -->
				<div class="formbar t-line">
					<a href="${ctx}/hr/annualAppraisal/import " class="btn-con">导入</a>
					<a href="${ctx}/hr/annualAppraisal/export " class="btn-con">导出</a>
					<a href="${ctx}/hr/annualAppraisal/form1 " class="btn-con">增加</a>
				</div>
				<!-- 筛选条件 -->
				<form:form id="searchForm" action="${ctx }/hr/annualAppraisal/list"
					modelAttribute="annualAppraisal" method="post" class="formbar t-line b-line">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo} "/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize} "/>
					<sys:treeselect id="office" name="office.id" value="${office.id}"
						labelName="office.name" labelValue="${office.name}" title="部门"
						url="/sys/office/treeData?type=2" cssClass="sfQuery"
						cssStyle="float:left;" allowClear="true"
						notAllowSelectParent="false" cssPlaceholder="部门" />
					<input name="user.name" type="text" class="sfQuery" placeholder="姓名">
					<form:select path="annualAppraisalLevel" class="sfQuery">
						<form:option value="">荣誉级别</form:option>
						<form:options itemValue="value" itemLabel="label"
							items="${fns:getDictList('annualAppraisal_level')}" />
					</form:select>
					<button class="btn-con mgtp-4 btn-bw-0">查询</button>
				</form:form>
				<sys:message content="${message }"/>
				<!-- 内容 -->
				<div class="formbar">
				${page.message }
					<table class="listtbl">
						<thead>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>部门</th>
								<th>考核年度</th>
								<th>考核结果</th>
								<th>考核单位名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${page.list}" var="data" varStatus="status">
							<tr>
								<td>${status.count }</td>
								<td>${data.employee.name }</td>
								<td>${data.employee.office.name }</td>
								<td>${data.checkYear }</td>
								<td>${data.checkResult}</td>
								<td>${data.checkUnit }</td>
								<td><a href="${ctx }/hr/annualAppraisal/form2?id=${data.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="${ctx }/hr/annualAppraisal/delete?id=${data.id}"
										onclick="return confirmx('确认要删除此记录吗？', this.href)">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					${page }
				</div>
			</div>
			<div class="text-center"></div>
		</div>
	</div>
	<div class="text-center"></div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>