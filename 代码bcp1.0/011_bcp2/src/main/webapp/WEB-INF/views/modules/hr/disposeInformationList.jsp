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
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a> &nbsp;>&nbsp;<a
						class="same">师资培养</a>&nbsp;>&nbsp;<a href="${ctx }/hr/disposeInformation/list">师德信息(处分信息)</a> </b>
            </div>
            <!-- 按钮 -->
            <div class="formbar t-line">
               <a href="${ctx }/hr/disposeInformation/import" class="btn-con">导入</a> <a
						href="${ctx }/hr/disposeInformation/export" class="btn-con">导出</a> <a
						href="${ctx }/hr/disposeInformation/form" class="btn-con">增加</a>
            </div>
            <!-- 筛选条件 -->
           	<form:form id="searchForm" action="${ctx }/hr/disposeInformation/list"
					modelAttribute="disposeInformation" method="post" class="formbar t-line b-line">
					<input name="employee.office.id" class="sfQuery" type="text"
						value="" placeholder="部门" />
					<input name="employee.name" type="text" class="sfQuery"
						placeholder="姓名">

					<form:select path="disciplinaryCategories" class="sfQuery">
						<form:option value="">处分级别</form:option>
						<form:options itemValue="value" itemLabel="label"
							items="${fns:getDictList('sy_disciplinary_categories')}" />
					</form:select>
					<form:button class="btn-con mgtp-4 btn-bw-0">查询</form:button>
				</form:form>
				<!-- 内容 -->
				            <div class="formbar">
                <table class="listtbl">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>部门</th>
                        <th>处分类别</th>
                        <th>原因</th>
                        <th>发生日期</th>
                        <th>单位名称</th>
                        <th>处分日期</th>
                        <th>撤销日期</th>
                        <th>撤销原因</th>                    
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="data" varStatus="status">
	                    <tr>
	                        <td>${status.count }</td>
	                        <td>${data.employee.name }</td>
	                        <td>${data.employee.office.name }</td>
	                        <td>${fns:getDictLabel(data.,'sy_dispose_level'," ")}</td>
	                        <td>${data.disciplinaryReasons}</td>
	                        <td>${fns:formatDate("yyyy年MM月",data.disposeStartDate)}</td>
	                        <td>${data.disposeUnit}</td>
	                        <td>${fns:formatDate("yyyy年MM月",data.disposeDate)}</td>
	                        <td>${fns:formatDate("yyyy年MM月",data.disposeEndDate)}</td>
	                        <td>${data.disposeEndReason}</td>
	                     
	     
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