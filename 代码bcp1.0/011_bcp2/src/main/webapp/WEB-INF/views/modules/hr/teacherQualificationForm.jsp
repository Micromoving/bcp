<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
</head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate({

		});
	});
</script>
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
						href="${ctx }/hr/teacherQualification/list">教师资格</a>&nbsp;>&nbsp;<a
						href="${ctx }/hr/teacherQualification/form">新增教师资格</a>&nbsp;
					</b>
					<sys:message content="${message}" />
					<form:form id="inputForm"
						action="${ctx }/hr/teacherQualification/save"
						modelAttribute="teacherQualification" method="post"
						class="formbar t-line b-line">
						<form:hidden path="id" vluae="1" />
						
						<div class="input_common">
							<div class="mb15">
								<span class="title g5"><em class="gred">*</em>工号</span>
								<form:input path="user.loginName" readonly="readonly"
									value="${teacherQualification.user.loginName}"
									class="h30 w300 cg" type="text"></form:input>
							</div>
							<div class="mb15">
								<span class="title g5">教师资格证种类</span>
								<form:select path="certificateTypes" class="h30 w300 cg">
									<form:options
										items="${fns:getDictList('sy_certificate_types')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>
							</div>
							<div class="mb15">
								<span class="title g5">教师资格证号码</span>
								<form:input path="certificationNumber" class="h30 w300 cg"
									type="text"></form:input>
							</div>
							<div class="mb15">
								<span class="title g5">任教学科</span>
								<form:input path="teachingSubjects" class="h30 w300 cg"
									type="text"></form:input>
							</div>
							<div class="mb15">
								<span class="title g5">证书颁发日期</span>
								<form:input path="certificateIssueDateString"
									class="h30 w300 cg" type="text"
									onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
							</div>
							<div class="mb15">
								<span class="title g5">颁发机构</span>
								<form:input path="issuingAgency" class="h30 w300 cg" type="text"></form:input>
							</div>
							<div class="mb15">
								<span class="title g5">申请单位</span>
								<form:input path="applicationUnit" class="h30 w300 cg"
									type="text"></form:input>
							</div>
							<div class="mb15">
								<span class="title g5">审核时间</span>
								<form:input path="auditDateString" class="h30 w300 cg"
									type="text"
									onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
							</div>
							<div class="mb15">
								<span class="title g5 veralg-top" style="margin-bottom: 80px;">备注</span>
								<textarea class="w300" rows="2"></textarea>
							</div>
						</div>
						<!-- 按钮 -->
						<div class="formbar t-line form-bar-left">
							<button class="btn-con btn-sub btn-big-ft btn-left-70"
								type="submit">提&nbsp;&nbsp;交</button>
							<button class="btn-con btn-sub btn-big-ft btn-left-co"
								type="submit">返&nbsp;&nbsp;回</button>
						</div>
					</form:form>

				</div>
			</div>
			<div class="text-center"></div>
		</div>
	</div>
	<div class="text-center"></div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>