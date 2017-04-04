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
			<div class="row bg-grey">
				<!-- 路径 -->
				<div class="formbar">
					<b> <a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a> &nbsp;>&nbsp;<a
						class="same">师资培养</a>&nbsp;>&nbsp;<a
						href="${ctx }/hr/teacherQualification/list">教师资格</a>&nbsp;>&nbsp;<a
						href="${ctx }/hr/teacherQualification/form1">新增教师资格</a>&nbsp;
					</b>
				</div>
				<!-- 内容 -->
				<div class="formbar t-line">
					<div class="el-step is-horizontal b-left-20">
						<div class="el-step__head is-process is-text is-success" id="d1">
							<div class="el-step__line is-horizontal">
								<i class="el-step__line-inner"></i>
							</div>
							<span class="el-step__icon"><div>1</div></span>
						</div>
						<div class="el-step__main">
							<div class="el-step__title is-process">请填写工号</div>
							<div class="el-step__description is-process"></div>
						</div>
					</div>
					<div class="el-step is-horizontal">
						<div class="el-step__head is-process is-text underway" id="d2">
							<div class="el-step__line is-horizontal">
								<i class="el-step__line-inner"></i>
							</div>
							<span class="el-step__icon"><div>2</div></span>
						</div>
						<div class="el-step__main">
							<div class="el-step__title is-process">请填写其他有关信息</div>
							<div class="el-step__description is-process"></div>
						</div>
					</div>
					<div class="el-step is-horizontal">
						<div class="el-step__head is-process is-text" id="d3">
							<span class="el-step__icon"><div>3</div></span>
						</div>
						<div class="el-step__main">
							<div class="el-step__title is-process">确认填写信息是否无误</div>
							<div class="el-step__description is-process"></div>
						</div>
					</div>

					<sys:message content="${message}" />
					<form:form id="inputForm"
						action="${ctx }/hr/teacherQualification/form3"
						modelAttribute="teacherQualification" method="post"
						class="formbar t-line b-line">
						<form:hidden path="user.loginName"/>
						<!--个人与家庭注册start-->
						<form:hidden path="id"/>
						<div class="input_common">
							<div class="info-prompt">
								${teacherQualification.user.name }，
								${fns:getDictLabel(teacherQualification.employee.gender,'gender'," ")}，
								${fns:getDictLabel(teacherQualification.employee.nation,'nation'," ")}，
								${fns:getDictLabel(teacherQualification.employee.politicalLandscape,'sy_political_landscape',"")}，，
								${fns:formatDate("yyyy年MM月",teacherQualification.employee.birthDate)}生，
								${teacherQualification.employee.nativePlace}人，
								${fns:formatDate("yyyy年MM月",teacherQualification.employee.inSchoolDate)}来我校参加工作。<br>现任[信息学院院长]
								[清华大学] [美术学]专业，在职[研究生]学历，[法学博士]学位。
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
						<div class="formbar t-line">
							<button class="btn-con btn-a mg-left-f2">下一步</button>
							<a class="btn-con"  onclick="window.history.back()">上一步</a>
						</div>
					</form:form>
				</div>

			</div>
		</div>
		<div class="wxts">
			<h4>温馨提示</h4>
			<p>2.唐山、张家口、廊坊、秦皇岛、承德五个城市用户请选择冀北注册。</p>
		</div>
	</div>
	<div class="text-center"></div>
	<div class="text-center"></div>
	<div class="text-center"></div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>