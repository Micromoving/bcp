<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
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
					<b><a href="${ctx}/home">首页</a>&nbsp;&gt;&nbsp;<a
						href="${ctx}/sys/menu/bizMenu?id=400">系统管理</a>&nbsp;&gt;&nbsp;<a href="${ctx }/sys/role">角色管理</a></b>
				</div>
				<div class="formbar t-line">
					<a href="${ctx}/sys/role/form" class="btn-con">增加</a>
				</div>
				<sys:message content="${message}" />
				<!--/表格栏-->

				<div class="formbar">
					<table class="listtbl">
						<thead>
							<tr>
								<th>序号</th>
								<th>角色名称</th>
								<th>英文名称</th>
								<th>归属部门</th>
								<th>数据范围</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach varStatus="status" items="${list}" var="role">
								<tr>
									<td>${status.count }</td>
									<td><a href="${ctx}/sys/role/form?id=${role.id}">${role.name}</a></td>
									<td>${role.enname}</td>
									<td>${role.office.name}</td>
									<td>${fns:getDictLabel(role.dataScope, 'sys_data_scope', '无')}</td>

									<td><a href="${ctx}/sys/role/assign?id=${role.id}">分配</a>
										<c:if
											test="${(role.sysData eq fns:getDictValue('是', 'sy_yes_no', '1') && fns:getUser().admin)||!(role.sysData eq fns:getDictValue('是', 'sy_yes_no', '1'))}">
											<a href="${ctx}/sys/role/form?id=${role.id}">修改</a>
										</c:if> <a href="${ctx}/sys/role/delete?id=${role.id}"
										onclick="return confirmx('确认要删除该角色吗？', this.href)">删除</a></td>
								</tr>
							</c:forEach>
							</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>