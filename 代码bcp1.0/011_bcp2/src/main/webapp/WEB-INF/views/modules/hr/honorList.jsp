<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
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
						class="same">师资培养</a>&nbsp;>&nbsp;<a href="${ctx }/hr/honor/list">师德信息（荣誉信息）</a>&nbsp;
					</b>
				</div>
				<!-- 按钮 -->
				<div class="formbar t-line">
					<a href="${ctx }/hr/honor/import" class="btn-con">导入</a> <a
						href="${ctx }/hr/honor/export" class="btn-con">导出</a> <a
						href="${ctx }/hr/honor/form" class="btn-con">增加</a>
				</div>
				<!-- 筛选条件 -->
				<form:form id="searchForm" action="${ctx }/hr/honor/list"
					modelAttribute="honor" method="post" class="formbar t-line b-line">
					<input name="employee.office.id" class="sfQuery" type="text"
						value="" placeholder="部门" />
					<input name="employee.name" type="text" class="sfQuery"
						placeholder="姓名">

					<form:select path="honorLevel" class="sfQuery">
						<form:option value="">荣誉级别</form:option>
						<form:options itemValue="value" itemLabel="label"
							items="${fns:getDictList('sy_honor_level')}" />

					</form:select>
					<form:button class="btn-con mgtp-4 btn-bw-0">查询</form:button>
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
								<th>荣誉级别</th>
								<th>荣誉称号</th>
								<th>授予荣誉名称</th>
								<th>操作</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="data" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${data.employee.name }</td>
									<td>${data.employee.office.name }</td>
									<td>${fns:getDictLabel(data.honorLevel,'sy_honor_level'," ")}</td>
									<td>${fns:getDictLabel(data.receiveHonoraryTitle,'sy_receive_honorary_title'," ")}</td>
									<td>${data.honoraryAwardunit }</td>
									<td><a href="${ctx }/hr/honor/form1?id=${data.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${ctx }/hr/honor/delete?id=${data.id}" onclick="return confirmx('确认要删除此记录吗？', this.href)">删除</a></td>
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

	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>