<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate({
			errorPlacement : function(error, element) {
				error.appendTo(element.next());
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">



				<div class="span9" style="margin-left:175px">
					<div class="content">
						<div class="module">
							<div class="module-body">
								<div class="profile-head media">
									<a href="/bcp/a/sys/user/upload" class="media-avatar pull-left">
										<c:if test="${not empty fns:getUser().photo }">
											<img src="${ctxUploads}/user/profile/${fns:getUser().photo}"
												class="nav-avatar" />
										</c:if> <c:if test="${ empty fns:getUser().photo }">
											<img src="${ctxUploads}/user/profile/user.png"
												class="nav-avatar" />
										</c:if>

									</a>
									<div class="media-body">
										<h4>${fns:getUser().name}</h4>
										<p class="profile-brief">${fns:getDictLabel(employee.gender,'gender'," ")}，
											${fns:getDictLabel(employee.nation,'nation'," ")} ，
											${employee.nativePlace}</p>
										<div class="profile-details muted">
											<a href="#" class="btn btn-primary">打印 </a>&nbsp;&nbsp;&nbsp;&nbsp;<a
												href="${ctx}/hr/employee/download?id=${employee.id}" class="btn btn-primary">导出 </a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="module">
							<div class="module-head">
								<h3>基本资料</h3>
							</div>
							<div class="module-body">
								<div class="media-body">
									<div class="stream-headline">
										<table class="table table-condensed">
											<tbody>
												<tr>
													<td width="33%">姓名：${fns:getUser().name}</td>
													<td width="33%">性别：${fns:getDictLabel(employee.gender,'gender'," ")}</td>
													<td>出生年月：${fns:formatDate("yyyy年MM月",employee.birthDate)}</td>
												</tr>
												<tr>
													<td>民族：${fns:getDictLabel(employee.nation,'nation'," ")}</td>
													<td>籍贯：${employee.nativePlace}</td>
													<td>生源地：${employee.bornPlace}</td>
												</tr>
												<tr>
													<td>证件类型：${employee.bornPlace}</td>
													<td>证件编号：${employee.papersNumber}</td>
													<td>婚姻状况：${fns:getDictLabel(employee.maritalStatus,'marital_status'," ")}</td>
												</tr>
												<tr>
													<td>工作时间：${fns:formatDate("yyyy年MM月",employee.inWorkDate)}</td>
													<td>政治面貌：${fns:getDictLabel(employee.politicsStatus,'politics_status'," ")}</td>
													<td>加入时间：${fns:formatDate("yyyy年MM月",employee.addTime)}</td>
												</tr>
												<tr>
													<td>正式聘任时间：${fns:formatDate("yyyy年MM月",employee.engagingDate)}</td>
													<td>院龄（满）：${employee.engagingDateString}</td>
													<td>院龄（年度）：${employee.schoolAge}</td>
												</tr>
												<tr>
													<td colspan="2" span="3" >来校时间：${fns:formatDate("yyyy年MM月",employee.inSchoolDate)}</td>
													<td>就业类型：${fns:getDictLabel(employee.jobType,'job_type'," ")}</td>
												</tr>
												<tr>
													<td>本人身份：${fns:getDictLabel(employee.selfIdentity,'self_identity',"")}</td>
													<td>人员类型：${fns:getDictLabel(employee.staffType,'staff_type',"")}</td>
													<td>编制情况：${fns:getDictLabel(employee.establishmentSituation,'establishment_situation',"")}</td>
												</tr>
												<tr>
													<td>人事代理人员：${fns:getDictLabel(employee.staffingAgent,'staffing_agent',"")}</td>
													<td colspan="2">户籍所在地：${employee.placeDomicile}</td>
												</tr>

											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="module">
							<div class="module-head">
								<h3>联系方式</h3>
							</div>
							<div class="module-body">
								<div class="media-body">
									<div class="stream-headline">
										<table class="table table-condensed">
											<tbody>
												<tr>
													<td>电子邮箱：${user.email}</td>
													<td>手机：${employee.mobile}</td>
													<td>电话：${employee.phone}</td>
												</tr>
												<tr>
													<td colspan="3">QQ号：${employee.qq}</td>
												</tr>
												<tr>
													<td colspan="3">邮件地址（邮编）：${employee.postcode}</td>
												</tr>
												<tr>
													<td colspan="3">家庭住址：${employee.familyAddress}</td>
												</tr>
												<tr>
													<td colspan="3">个人主页：${employee.personalHomepage}</td>
												</tr>
												<tr>
													<td colspan="3">个人简介：${employee.personalProfile}</td>
												</tr>
												<tr>
													<td colspan="3">学缘结构：${fns:getDictLabel(employee.learningEdgeRafters,'learning_edge_rafters',"")}</td>
												</tr>
												<tr>
													<td>在职状态：${fns:getDictLabel(employee.incumbencyStatus,'incumbency_status',"")}</td>
													<td>社保编号：${insuredSituation.socialInsuranceId}</td>
													<td>代理编号：${employee.agentId}</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="module">
							<div class="module-head">
								<h3>职称履历</h3>
							</div>
							<div class="module-body">
								<div class="media-body">
								<c:forEach items="${proTechPositionList}" var="data">
									<div class="stream-headline">
										<table class="table table-condensed">
											<tbody>
												<tr>
													<td>职称名称：${fns:getDictLabel(data.techPositionType,'tech_position_type','')}</td>
													<td>职称级别：${fns:getDictLabel(data.techPositionLevel,'tech_position_level','')}</td>
												</tr>
												<tr>
													<td colspan="3">专业技术职务：${fns:getDictLabel(data.name,'name','')} </td>
												</tr>
												<tr>
													<td colspan="3">获取职称时间：${fns:formatDate("yyyy-MM",data.gainDate)} </td>
												</tr>
												<tr>
													<td colspan="3">聘任时间:${fns:formatDate("yyyy-MM",data.appointDate)}</td>
												</tr>
											</tbody>
										</table>
									</div>
									</c:forEach>
								</div>
							</div>
						</div>
						
						<div class="module">
							<div class="module-head">
								<h3>校内岗位履历</h3>
							</div>
							<div class="module-body">
								<div class="media-body">
								<c:forEach items="${recruitmentList}" var="data">
									<div class="stream-headline">
										<table class="table table-condensed">
											<tbody>
												<tr>
													<td colspan="3"> 聘任起止时间：${fns:formatDate("yyyy-MM-dd",data.startDate)}至${fns:formatDate("yyyy-MM-dd",data.endDate)}</td>
												</tr>
												<tr>
													<td> 职务：${fns:getDictLabel(data.duties,'duties','')} </td>
													<td> 职务等级 ：${fns:getDictLabel(data.dutiesLevel,'duties_level','')}</td>
												</tr>
												<tr>
													<td> 岗位类型：${fns:getDictLabel(data.postType,'post_type','')} </td>
													<td> 岗位等级 ： ${fns:getDictLabel(data.postLevel,'post_level','')} </td>
												</tr>
												<tr>
													<td> 中层干部试用期截止时间：${fns:formatDate("yyyy-MM-dd",data.tryOutDate)} </td>
													<td> 职级等级：  ${fns:getDictLabel(data.professionalLevel,'professional_level','')} </td>
												</tr>
												<tr>
													<td> 聘任情况： ${fns:getDictLabel(data.engageSituation,'engage_situation','')}</td>
													<td> 是否签订聘用合同：${fns:getDictLabel(data.isSignContract,'is_sign_contract','')}</td>
												</tr>
											</tbody>
										</table>
									</div>
									</c:forEach>
								</div>
							</div>
						</div>
						
						<div class="module">
							<div class="module-head">
								<h3>历年考核</h3>
							</div>
							<div class="module-body">
								<div class="media-body">
								<c:forEach items="${assessmentList}" var="data">
									<div class="stream-headline">
										<table class="table table-condensed">
											<tbody>
												<tr>
													<td>考核年度：${data.assessmentYear}</td>
												</tr>
												<tr>
													<td>考核等级：${fns:getDictLabel(data.assessmentLevel,'assessment_level','')}</td>
												</tr>
											</tbody>
										</table>
									</div>
									</c:forEach>
								</div>
							</div>
						</div>
						
						<c:if test="${not empty eduList }">
							<div class="module">
								<div class="module-head">
									<h3>教育经历</h3>
								</div>
								<div class="module-body">
									<div class="media-body">
										<c:forEach items="${eduList}" var="data">
											<div class="stream-headline">
												<h5 class="stream-author">
													${fns:formatDate("yyyy-MM",data.startDate)}至${fns:formatDate("yyyy-MM",data.endDate)}&nbsp;&nbsp;
													${edu.schoolName}&nbsp;&nbsp;
													${data.specialty}&nbsp;${fns:getDictLabel(data.eduBackground,'edu_background','')}&nbsp;[学历]&nbsp;
													<c:if test="${0 eq edu.isFulltime}">
														非全日制
												</c:if>
													&nbsp;&nbsp;
													<c:if test="${1 eq edu.isOverseas}">
														海外学习
												</c:if>
												</h5>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty workList }">
							<div class="module">
								<div class="module-head">
									<h3>工作经历</h3>
								</div>
								<div class="module-body">
									<div class="media-body">
										<c:forEach items="${workList}" var="data">
											<div class="stream-headline">
												<h5 class="stream-author">
													<strong>${fns:formatDate("yyyy-MM",data.startDate)}至${fns:formatDate("yyyy-MM",data.endDate)}&nbsp;
														&nbsp;${data.companyName}
														&nbsp;（${fns:getDictLabel(data.companyNature,'company_nature','无')}）</strong>
													<br> 职位名称：${data.companyDepartment}&nbsp;&nbsp;
													${data.position}&nbsp; <br> 工作描述：${data.remarks} <br>
													工作类型：(${fns:getDictLabel(data.workSort,'work_sort','无')})
												</h5>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<!--/.container-->
		</div>
		<!--/.wrapper-->
	</div>

	<!--/.container-->

	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
