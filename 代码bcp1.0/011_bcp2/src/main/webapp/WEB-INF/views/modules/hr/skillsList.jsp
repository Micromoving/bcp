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
					<b><a href="${ ctx}/home">首页</a>&nbsp;&nbsp;
					<a href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a>&nbsp;&nbsp;<a
						class="same">教职员工</a>&nbsp;&nbsp;<a
						href="${ctx }/hr/employee/list">基本信息</a>&nbsp;&nbsp;<a
						href="${ctx }/hr/skills/list">技能及证书（其他技能）</a> </b>
				</div>
				<!-- 按钮 -->
				<div class="formbar t-line">
					<a href="${ctx }/hr/skills/import" class="btn-con">导入</a> <a href="${ctx }/hr/skills/export" class="btn-con">导出</a>
					<a href="${ctx }/hr/skills/form" class="btn-con">增加</a>
				</div>
				<!-- 筛选条件 -->
					<form:form id="searchForm" action="${ctx }/hr/skills/list"
					modelAttribute="skills" method="post" class="formbar t-line b-line">
					<input name="employee.office.id" class="sfQuery" type="text"
						value="" placeholder="部门" />
					<input name="employee.name" type="text" class="sfQuery"
						placeholder="姓名">
						
						
						<form:select path="masterDegree" class="sfQuery">
						<form:option value="">其他技能掌握程度</form:option>
						<form:options itemValue="value" itemLabel="label"
							items="${fns:getDictList('cn_master_degree')}" />
						
					</form:select>
					
						<form:button class="btn-con mgtp-4 btn-bw-0">查询</form:button>
						<form:button class="btn-con mgtp-4 btn-bw-0">高级筛选</form:button>
				</form:form>
				<!-- 内容 -->
				<div class="formbar">
					<table class="listtbl">
						<thead>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>部门</th>
								<th>其他技能名称</th>
								<th>其他技能掌握程度</th>

								<th>操作</th>
							</tr>
						</thead>
						<tbody>
					<c:forEach items="${page.list}" var="data" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${data.employee.name }</td>
									<td>${data.employee.office.name }</td>
									<td>${data.otherSkills }</td>
									<td>${fns:getDictLabel(data.masterDegree,'sy_master_degree'," ")}</td>
									<td><a href="${ctx }/hr/skills/detail" class="btn-con">编辑</a> </td>

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