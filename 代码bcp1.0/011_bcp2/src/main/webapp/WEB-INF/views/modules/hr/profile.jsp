<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate();
		$("#inputForm1").validate();
	});
</script>
</head>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
			
				<div class="row" style="background:#f6f6f6;">
					<!-- 路径 -->
					<div class="formbar">
						<b class="b-left-20"><a href="home.html">首页</a>&nbsp;>&nbsp;<a
							href="bizManagement.html">人力资源管理</a>&nbsp;>&nbsp;<a href="#">教职员工</a>&nbsp;>&nbsp;<a
							href="employeeList.html">基本信息</a>&nbsp;>&nbsp;<a
							href="userForm.html">增加</a> </b>
					</div>
					<!-- 内容 -->
					<div class="formbar t-line">


						<div class="form">
							<br>
							<br>
							<!--头像-->
							<div class="profile-head media">
								<a href="#" class="media-avatar pull-left"> <img
									src="images/photo.jpg" height="100" width="100">
								</a>
								<div class="media-body">
									<p class="profile-brief">
										${user.name}
										<c:if test="${not empty employee.gender }">
											${fns:getDictLabel(employee.name,'name'," ")}，
											${fns:getDictLabel(employee.gender,'gender'," ")}，
											${fns:getDictLabel(employee.nation,'nation'," ")}，
											${fns:getDictLabel(employee.politicalLandscape,'political_landscape',"")}，
											${fns:formatDate("yyyy年MM月",employee.birthDate)}生，
											${employee.nativePlace}人，
											${fns:formatDate("yyyy年MM月",employee.inSchoolDate)}来我校参加工作。
										</c:if>
									<hr>
									${employee.personalProfile}
									</p>
									<div class="profile-details muted">
										<a href="${ctx}/hr/employee/detail?id=${employee.id}"
											class="btn-con">导出 </a> <a
											href="${ctx}/sys/user/upload?id=${employee.id}"
											class="btn-con">更改头像 </a>
									</div>
								</div>
							</div>
							<br>


							<!--个人与家庭注册start-->

						
							<ul class="tabs" style="height: 2000px;">
								  <div class="line-head"></div>
								<li><input type="radio" name="tabs" id="tab1" checked /> <label
									for="tab1" id="tab1">基本信息</label>
									<div id="tab-content1" class="tab-content">
										<form:form class="form-horizontal row-fluid"
											action="${ctx}/hr/employee/save" method="post"
											modelAttribute="employee" id="inputForm">
											<sys:message content="${message}" />
											<form:hidden path="id" />
											<!--/姓名-->
											<div class="mb15">
												<span class="title g5""><font color="red">*</font>姓名</span>
												<form:input path="user.name" class="h30 w300 cg" type="text"
													readonly="true"></form:input>
											</div>
											<!-- 曾用名 -->
											<div class="mb15">
												<span class="title g5">曾用名</span>
												<form:input path="nameUsedBefore" class="h30 w300 cg"
													type="text" readonly="true"></form:input>
											</div>
											<!-- 性别 -->
											<div class="mb15">
												<span class="title g5">性别</span>
												<form:select path="gender" class="h30 w300 cg">
													<form:options items="${fns:getDictList('cn_gender')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 国籍/地区 -->
											<div class="mb15">
												<span class="title g5">国籍/地区</span>
												<form:select path="nationalityRegion" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_country')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 身份证件类型 -->
											<div class="mb15">
												<span class="title g5">身份证件类型</span>
												<form:select path="identityDocumentType" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_identity_document_type')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 身份证件号 -->
											<div class="mb15">
												<span class="title g5">身份证件号</span>
												<form:input path="idNumber" class="h30 w300 cg" type="text"></form:input>
											</div>
											<!-- 出生日期 -->
											<div class="mb15">
												<span class="title g5">出生日期</span>
												<form:input path="birthDateString" class="h30 w300 cg"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
											</div>
											<!-- 籍贯 -->
											<div class="mb15">
												<span class="title g5">籍贯</span>
												<form:input path="nativePlace" class="h30 w300 cg"
													type="text"></form:input>
											</div>
											<!-- 出生地 -->
											<div class="mb15">
												<span class="title g5">出生地(行政区划代码)</span>
												<form:input path="birthPlace" class="h30 w300 cg"
													type="text"></form:input>
											</div>
											<!-- 民族-->
											<div class="mb15">
												<span class="title g5">民族</span>
												<form:select path="nation" class="h30 w300 cg">
													<form:options items="${fns:getDictList('cn_nation')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 政治面貌 -->
											<div class="mb15">
												<span class="title g5">政治面貌</span>
												<form:select path="politicalLandscape" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_political_landscape')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 婚姻状况 -->
											<div class="mb15">
												<span class="title g5">婚姻状况</span>
												<form:select path="maritalStatus" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_marital_status')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 健康状况 -->
											<div class="mb15">
												<span class="title g5">健康状况</span>
												<form:select path="health" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_health')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 参加工作年月 -->
											<div class="mb15">
												<span class="title g5">参加工作年月</span>
												<form:input path="inWorkDateString" class="h30 w300 cg"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
											</div>
											<!-- 进本校年月 -->
											<div class="mb15">
												<span class="title g5">进本校年月</span>
												<form:input path="inSchoolDateString" class="h30 w300 cg"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
											</div>
											<!-- 教职工来源 -->
											<div class="mb15">
												<span class="title g5">教职工来源</span>
												<form:select path="staffSource" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_staff_source')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 教职工类别 -->
											<div class="mb15">
												<span class="title g5">教职工类别</span>
												<form:select path="staffType" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_staff_type')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 编制情况 -->
											<div class="mb15">
												<span class="title g5">编制情况</span>
												<form:select path="preparationSituation" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_preparation_situation')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 是否在编 -->
											<div class="mb15">
												<span class="title g5">是否在编</span>
												<form:select path="whetherInSeries" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 用人形式 -->
											<div class="mb15">
												<span class="title g5">用人形式</span>
												<form:select path="theHumanForm" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_the_human_form')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 人员状态 -->
											<div class="mb15">
												<span class="title g5">人员状态</span>
												<form:select path="personnelStatus" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_personnel_status')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 签订合同情况 -->
											<div class="mb15">
												<span class="title g5">签订合同情况</span>
												<form:select path="signTheContract" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_sign_the_contract')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 学缘结构 -->
											<div class="mb15">
												<span class="title g5">学缘结构</span>
												<form:select path="learningEdgeStructure"
													class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_learning_edge_structure')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 信息技术应用能力 -->
											<div class="mb15">
												<span class="title g5">信息技术应用能力</span>
												<form:select path="informationTechnology"
													class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_information_technology')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 是否双师型教师 -->
											<div class="mb15">
												<span class="title g5">是否双师型教师</span>
												<form:select path="whetherDoubleTeacher" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 是否具备职业技能等级证书  -->
											<div class="mb15">
												<span class="title g5">是否具备职业技能等级证书</span>
												<form:select path="certificateOfVocational"
													class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 是否是特级教师  -->
											<div class="mb15">
												<span class="title g5">是否是特级教师</span>
												<form:select path="specialTeacher" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 企业工作(实践)时长  -->
											<div class="mb15">
												<span class="title g5">企业工作(实践)时长</span>
												<form:select path="enterpriseWork" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_enterprise_work')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 正式聘任时间  -->
											<div class="mb15">
												<span class="title g5">正式聘任时间</span>
												<form:input path="engagingDateString" class="h30 w300 cg"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
											</div>
											<!-- 就业类型  -->
											<div class="mb15">
												<span class="title g5">就业类型</span>
												<form:select path="employmentType" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_employment_type')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 到校就业类型  -->
											<div class="mb15">
												<span class="title g5">到校就业类型</span>
												<form:select path="schoolEmployment" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_school_employment')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 本人身份  -->
											<div class="mb15">
												<span class="title g5">本人身份</span>
												<form:select path="myIdentity" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_my_identity')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 人事代理人员  -->
											<div class="mb15">
												<span class="title g5">人事代理人员</span>
												<form:select path="personnelAgency" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 在职状态  -->
											<div class="mb15">
												<span class="title g5">在职状态</span>
												<form:select path="incumbencyStatus" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_incumbency_status')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 独子到期时间  -->
											<div class="mb15">
												<span class="title g5">独子到期时间</span>
												<form:input path="onlyChildEndDateString"
													class="h30 w300 cg"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
											</div>
											<!-- 户籍所在地  -->
											<div class="mb15">
												<span class="title g5">户籍所在地</span>
												<form:input path="placeDomicile" class="h30 w300 cg"
													type="text"></form:input>
											</div>
											<!-- 加入时间  -->
											<div class="mb15">
												<span class="title g5">加入时间</span>
												<form:input path="addDateString" class="h30 w300 cg"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
											</div>
											<!-- 部门  -->
											<div class="mb15">
												<span class="title g5">部门</span>
												<form:input path="office.name" class="h30 w300 cg"
													type="text" readonly="true"></form:input>
											</div>
											<!-- 人员类型  -->
											<div class="mb15">
												<span class="title g5">人员类型</span>
												<form:select path="PersonnelType" class="h30 w300 cg">
													<form:options
														items="${fns:getDictList('sy_personnel_type')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 是否享受国务院特殊津贴 -->
											<div class="mb15">
												<span class="title g5">是否享受国务院特殊津贴</span>
												<form:select path="isSpecial" class="h30 w300 cg">
													<form:options items="${fns:getDictList('sy_yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
											<!-- 按钮 -->
											<div class="formbar t-line">
												<button class="btn-con btn-a mg-left-f1" type="submit">提交</button>
												<a class="btn-con" onclick="window.history.back()">返回</a>
											</div>
										</form:form>
									</div></li>


								<li><input type="radio" name="tabs" id="tab2" /> <label
									for="tab2" id="tab2">联系方式</label>
									<div id="tab-content2" class="tab-content">
										<form:form class="form-horizontal row-fluid"
											action="${ctx}/hr/employee/save" method="post"
											modelAttribute="employee" id="inputForm1">
											<sys:message content="${message}"  />
											<form:hidden path="id"></form:hidden>
											<!-- 手机  -->
											<div class="mb15">
												<span class="title g5">手机</span>
												<form:input path="mobilePhone" class="h30 w300 cg"
													type="text"></form:input>
											</div>
											<!-- 办公电话  -->
											<div class="mb15">
												<span class="title g5">办公电话</span>
												<form:input path="officeTelephone" class="h30 w300 cg"
													type="text"></form:input>
											</div>
											<!-- QQ  -->
											<div class="mb15">
												<span class="title g5">QQ</span>
												<form:input path="QQ" class="h30 w300 cg" type="text"></form:input>
											</div>
											<!-- 家庭住址  -->
											<div class="mb15">
												<span class="title g5">家庭住址</span>
												<form:input path="homeAddress" class="h30 w300 cg"
													type="text"></form:input>
											</div>
											<!-- 邮编  -->
											<div class="mb15">
												<span class="title g5">邮编</span>
												<form:input path="zipCode" class="h30 w300 cg" type="text"></form:input>
											</div>
											<!-- 按钮 -->
											<div class="formbar t-line">
												<button class="btn-con btn-a mg-left-f1">提交</button>
												<a class="btn-con" onclick="window.history.back()">返回</a>
											</div>
										</form:form>
									</div></li>
							</ul>
						</div>
						<div class="wxts">
							<h4>温馨提示</h4>
							<p>1.95598智能互动网站（www.95598.cn）是国家电网公司统一对外服务网站，提供电力信息浏览、网上业务受理、网上
								缴费、信息自助查询等网络服务功能，为电力客户提供信息咨询、沟通交流和互动服务平台，宣传电动汽车、绿色能源、智能用电等新型业务。95598智能互动
								网站服务范围已覆盖国家电网27个省（市）电力公司。</p>
							<p>2.唐山、张家口、廊坊、秦皇岛、承德五个城市用户请选择冀北注册。</p>
						</div>
					</div>
				</div>
				<div class="text-center"></div>
		</div>
	</div>

	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>