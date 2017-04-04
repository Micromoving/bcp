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
					<b><a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a>&nbsp;>&nbsp;<a
						class="same">岗位聘任</a>&nbsp;>&nbsp;<a
						href="${ctx }/hr/postAppointment/list">结果管理</a> </b>
				</div>

				<!-- 按钮 -->
				<div class="formbar t-line">
					<a href="${ctx }/hr/postAppointment/import" class="btn-con">导入</a>
					<a href="${ctx }/hr/postAppointment/export" class="btn-con">导出</a>
				</div>

				<!-- 筛选条件 -->
				<form:form id="searchForm" action="${ctx }/hr/postAppointment/list"
					modelAttribute="postAppointment" method="post"
					class="formbar t-line b-line">
					<input name="employee.office.id" class="sfQuery" type="text"
						value="" placeholder="部门" />
					<input name="employee.name" type="text" class="sfQuery"
						placeholder="姓名">

					<select id="politicalLandscape" name="politicalLandscape"
						class="sfQuery">
						<option value="selected">政治面貌</option>
						<c:forEach items="${fns:getDictList('sy_political_landscape')}"
							var="dict">
							<option value="${dict.value}">${dict.label}</option>
						</c:forEach>
					</select>
					<form:button class="btn-con mgtp-4 btn-bw-0">查询</form:button>
					<form:button class="btn-con mgtp-4 btn-bw-0">高级筛选</form:button>
				</form:form>

				<!-- 内容 -->
				<div class="formbar">
					<sys:message content="${message}" />
					${page.message }
					<table class="listtbl tb-wd-6">
						<thead>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>部门</th>
								<th>岗位类别</th>
								<th>岗位等级</th>
								<th>校级职务</th>
								<th>任职开始年月</th>
								<th>职务</th>
								<th>职级等级</th>
								<th>职务等级</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="data" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a href="${ctx }/hr/employee/form?id=${data.employee.id}" target="_blank">${data.employee.name }</a></td>
									<td>${data.employee.office.name }</td>
									<td>${data.postType }</td>
									<td>${data.postLevel }</td>
									<td>${data.theDuty }</td>
									<td>${fns:formatDate("yyyy-MM",data.startDate)}</td>
									<td>${data.duty }</td>
									<td>${data.professionalLevel }</td>
									<td>${data.dutiesLevel }</td>
									<td><a
										href="${ctx }/hr/postAppointment/delete?id=${data.id}"
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
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>