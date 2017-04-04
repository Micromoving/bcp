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
						class="same">师资培养</a>&nbsp;>&nbsp;<a href="${ctx }/hr/cert/list">技能及证书（证书信息）</a>&nbsp;
               </b>
            </div>
            <!-- 按钮 -->
            <div class="formbar t-line">
                <a href="${ctx }/hr/cert/import" class="btn-con">导入</a>
                <a href="${ctx }/hr/cert/export" class="btn-con">导出</a>
                <a href="${ctx }/hr/cert/form1" class="btn-con">增加</a>
            </div>
            <!-- 筛选条件 -->
           <form:form id="searchForm" action="${ctx }/hr/cert/list"
					modelAttribute="certificateInformation" method="post" class="formbar t-line b-line">
					<input name="employee.office.id" class="sfQuery" type="text"
						value="" placeholder="部门" />
					<input name="employee.name" type="text" class="sfQuery"
						placeholder="姓名">

					<form:select path="certificateType" class="sfQuery">
						<form:option value="">证书类型</form:option>
						<form:options itemValue="value" itemLabel="label"
							items="${fns:getDictList('certificate_type')}" />
					</form:select>
					<form:select path="languageCertificate" class="sfQuery">
						<form:option value="">语言证书名称</form:option>
						<form:options itemValue="value" itemLabel="label"
							items="${fns:getDictList('language_certificate')}" />
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
                        <th>证书类型</th>
                        <th>语言证书名称</th>
                        <th>证书名称</th>
                        <th>发证年月</th>
                        <th>发证单位</th>
                        <th>证书编号</th>
                        
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${page.list}" var="data" varStatus="status">
                        <tr>
                          <td>${status.count}</td>
                          <td>${data.employee.name }</td>
                          <td>${data.employee.office.name}</td>
                          <td>${fns:getDictLabel(data.certificateType,'sy_certificate_type'," ")}</td>
                          <td>${fns:getDictLabel(data.languageCertificate,'sy_language_certificate'," ")}</td>
                          <td>${data.certificate}</td>
                          <td>${fns:formatDate("yyyy年MM月",data.dateOfIssuance) }</td>
                          <td>${data.issuingUnit }</td>
                          <td>${data.certificateNumber }</td>
                          <td><a
										href="${ctx }/hr/cert/form2?id=${data.id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="${ctx }/hr/cert/delete?id=${data.id}" 
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