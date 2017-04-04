<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/workExperience/list");
		$("#searchForm").submit();
		return false;
	};
</script>
</head>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<!-- 路径 -->
				<div class="formbar">
					<b><a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a> &nbsp;>&nbsp;<a
						href="${ctx }/hr/workExperience/list">工作经历</a>&nbsp; </b>
				</div>
				<!-- 按钮 -->
				<div class="formbar t-line">
					<a href="${ctx }/hr/workExperience/import" class="btn-con">导入</a> <a
						href="${ctx }/hr/workExperience/export" class="btn-con">导出</a> <a
						href="${ctx }/hr/workExperience/form1" class="btn-con">增加</a>
				</div>
				<!-- 筛选条件 -->
				<form:form id="searchForm" action="${ctx }/hr/workExperience/list"
					modelAttribute="workExperience" method="post"
					class="formbar t-line b-line">
					<input id="pageNo" name="pageNo" type="hidden"
						value="${page.pageNo}" />
					<input id="pageSize" name="pageSize" type="hidden"
						value="${page.pageSize}" />
					<sys:treeselect id="office" name="office.id" value="${office.id}"
						labelName="office.name" labelValue="${office.name}" title="部门"
						url="/sys/office/treeData?type=2" cssClass="sfQuery"
						cssStyle="float:left;" allowClear="true"
						notAllowSelectParent="false" cssPlaceholder="部门" />
					<input name="user.name" type="text" class="sfQuery"
						placeholder="姓名">

					<form:select tabindex="1" path="companyNature" class="sfQuery">
						<form:option value="">单位性质</form:option>
						<form:options items="${fns:getDictList('sy_company_nature')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
					<form:select tabindex="1" path="jobType" class="sfQuery">
						<form:option value="">工作类型</form:option>
						<form:options items="${fns:getDictList('sy_job_type')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>

					<form:button class="btn-con mgtp-4 btn-bw-0">查询</form:button>
				</form:form>

				<div class="formbar">
					${page.message }
					<table class="listtbl">
						<thead>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>部门</th>
								<th>任职单位名称</th>
								<th>任职开始年月</th>
								<th>结束年月</th>
								<th>单位性质类别</th>
								<th>任职岗位</th>
								<th>工作类型</th>
								<th>归属部门</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="data" varStatus="status">
								<tr>
									<td>${status.count+(page.pageNo-1)*page.pageSize }</td>
									<td>${data.employee.name }</td>
									<td>${data.office.name}</td>
									<td>${data.nameOfServiceUnit}</td>
									<td>${fns:formatDate("yyyy年MM月",data.startDate) }</td>
									<td>${fns:formatDate("yyyy年MM月",data.endDate) }</td>
									<td>${fns:getDictLabel(data.companyNature,'sy_company_nature'," ")}</td>
									<td>${data.post}</td>
									<td>${fns:getDictLabel(data.jobType,'sy_job_type'," ")}</td>
									<td>${data.companyDepartment}</td>
									<td><a href="${ctx }/hr/workExperience/form1?id=${data.id}"> 编辑 </a>
										&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="${ctx }/hr/workExperience/delete?id=${data.id}"> 删除 </a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					${page}

					<div class="text-center"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>