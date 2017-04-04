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
		$("#searchForm").attr("action", "${ctx}/hr/teacherQualification/list");
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
					<b> <a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a> &nbsp;>&nbsp;<a
						class="same">师资培养</a>&nbsp;>&nbsp;<a
						href="${ctx }/hr/teacherQualification/list">教师资格</a>&nbsp;
					</b>
				</div>
				<!-- 按钮 -->
				<div class="formbar t-line">
					<a href="${ctx }/hr/teacherQualification/import" class="btn-con">导入</a>
					<a href="${ctx }/hr/teacherQualification/export" class="btn-con">导出</a>
					<a href="${ctx }/hr/teacherQualification/form1" class="btn-con">增加</a>
				</div>
				<!-- 筛选条件 -->
				<form:form id="searchForm"
					action="${ctx }/hr/teacherQualification/list"
					modelAttribute="teacherQualification" method="post"
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

					<form:select path="certificateTypes" class="sfQuery">
						<form:option value="">教师资格种类</form:option>
						<form:options itemValue="value" itemLabel="label"
							items="${fns:getDictList('sy_certificate_types')}" />
					</form:select>
					<form:button class="btn-con mgtp-4 btn-bw-0">查询</form:button>
				</form:form>
				<sys:message content="${message}" />
				<!-- 内容 -->
				<div class="formbar">
					${page.message }
					<table class="listtbl">
						<thead>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>部门</th>
								<th>教师资格种类</th>
								<th>号码</th>
								<th>任教学科</th>
								<th>证书颁发日期</th>
								<th>颁发机构</th>
								<th>申请单位</th>
								<th>审核时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="data" varStatus="status">
								<tr>
									<td>${status.count+(page.pageNo-1)*page.pageSize }</td>
									<td>${data.user.name}</td>
									<td>${data.office.name}</td>
									<td>${fns:getDictLabel(data.certificateTypes,'sy_certificate_types'," ")}</td>
									<td>${data.certificationNumber}</td>
									<td>${data.teachingSubjects}</td>
									<td>${data.certificateIssueDateString}</td>
									<td>${data.issuingAgency}</td>
									<td>${data.applicationUnit}</td>
									<td>${data.auditDateString}</td>
									<td><a
										href="${ctx }/hr/teacherQualification/form2?id=${data.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="${ctx }/hr/teacherQualification/delete?id=${data.id}" 
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