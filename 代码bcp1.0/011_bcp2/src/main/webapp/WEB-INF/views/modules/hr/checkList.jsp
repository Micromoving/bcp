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
		$("#searchForm").attr("action", "${ctx}/hrcheck/list");
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
                <b><a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a> &nbsp;>&nbsp;<a
						class="same">师资培养</a>&nbsp;>&nbsp;<a href="${ctx }/hr/check/list">师德信息(考核信息)</a> </b>
            </div>
            <!-- 按钮 -->
            <div class="formbar t-line">
               <a href="${ctx }/hr/check/import" class="btn-con">导入</a> <a
						href="${ctx }/hr/check/export" class="btn-con">导出</a> <a
						href="${ctx }/hr/check/form" class="btn-con">增加</a>
            </div>
            <!-- 筛选条件 -->
           <form:form id="searchForm"
					action="${ctx }/hr/check/list"
					modelAttribute="check" method="post"
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
						
					<input name="employee.name" type="text" class="sfQuery"
						placeholder="姓名">
						
							<form:select path="checkConclusion" class="sfQuery">
						<form:option value="">师德考核结论</form:option>
						<form:options itemValue="value" itemLabel="label"
							items="${fns:getDictList('sy_check_conclusion')}" />
							</form:select>		
						
								
                <button class="btn-con mgtp-4 btn-bw-0">查询</button>
                <a href="#" class="btn-con mgtp-4">高级筛选</a>
				</form:form>
				<!-- 内容 -->
				            <div class="formbar">
                <table class="listtbl">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>部门</th>
                        <th>身份证件号</th>
                        <th>师德考核时间</th>
                        <th>师德考核结论</th>
                        <th>师德考核单位名称</th>
                        <th>操作</th>
                     </tr>
                    </thead>
                    <c:forEach items="${page.list}" var="data" varStatus="status">
                    <tbody>
	                    <tr>
	                        <td>${status.count }</td>
	                        <td>${data.employee.name }</td>
	                        <td>${data.employee.office.name }</td>
	                        <td>${data.employee.idNumber}</td>
	                        <td>${fns:formatDate("yyyy年MM月",data.checkDate) }</td>
	                        <td>${fns:getDictLabel(data.checkConclusion,'sy_check_conclusion'," ")}</td>
	                        <td>${data.checkUnit}</td>
	                        <td><a href="${ctx }/hr/check/form1?id=${data.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
								   href="${ctx }/hr/check/delete?id=${data.id}" onclick="return confirmx('确认要删除此记录吗？', this.href)">删除</a></td>
								</tr>
							

						</tbody></c:forEach>
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