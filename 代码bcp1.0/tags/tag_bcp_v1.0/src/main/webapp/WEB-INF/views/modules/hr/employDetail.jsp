<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
	function audit(s) {

		if (s)
			$("#auditingStatus").val(s);

		$("#inputForm").submit();
		return false;
	}
</script>
<jsp:include page="../../include/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>


				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-body">
								<div class="profile-head media">
									<a href="#" class="media-avatar pull-left"> <c:if
											test="${not empty employ.user.photo }">
											<img src="${ctxUploads}/user/profile/${employ.user.photo}"
												class="nav-avatar" />
										</c:if> <c:if test="${ empty employ.user.photo }">
											<img src="${ctxUploads}/user/profile/user.png"
												class="nav-avatar" />
										</c:if>
									</a>
									<div class="media-body">

										<p class="profile-brief">
											姓名：${employ.name}<br />
											申报岗位:${fns:getDictLabel(employ.declarePositionNumber,'declare_position_number','')}
											<br />
											岗位类型:${fns:getDictLabel(employ.postType,'post_type','')} <br />
											申报时间:${fns:formatDate("yyyy-MM-dd HH:mm:ss",employ.applyDate)}
											<br /> 修改时间:${fns:formatDate("yyyy-MM-dd HH:mm:ss",employ.updateDate)}
										</p>
										<p class="profile-brief">
											<c:if
												test="${'2' eq employ.auditingStatus || '3' eq employ.auditingStatus }">
												<b> 审核状态:<font color="red">${fns:getDictLabel(employ.auditingStatus,'auditing_status','')}(${fns:formatDate("yyyy-MM-dd HH:mm:ss",employ.auditDate)})</font></b>
												<br />
											</c:if>
											<c:if test="${'1' eq employ.auditingStatus}">
												<b> 审核状态:<font color="red">${fns:getDictLabel(employ.auditingStatus,'auditing_status','')}</font></b>
												<br />
											</c:if>

											<c:if test="${not empty employ.auditComments }">
											审核意见：${employ.auditComments}</c:if>
										</p>
										<a href="${ctx}/hr/employ/word?id=${employ.id}"
											class="btn btn-primary pull-right">导出 Word </a><a href="#"
											onclick="javascript:window.print();"
											class="btn btn-primary pull-right">打印 </a>
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
												<td width="33%">姓名：${employ.name}</td>
												<td width="33%">性别：${fns:getDictLabel(employ.gender,'gender','')}
												</td>
												<td>出生年月：${fns:formatDate("yyyy年MM月",employ.birthDate)}</td>
											</tr>
											<tr>
												<td>民族：${fns:getDictLabel(employ.nation,'nation','')}</td>
												<td>籍贯：${employ.placeDomicile}</td>
												<td>婚姻状况：${fns:getDictLabel(employ.maritalStatus,'marital_status','')}</td>
											</tr>
											<tr>
												<td>身份证：${employ.IDCardNumber}</td>
												<td>政治面貌：${fns:getDictLabel(employ.politicsStatus,'politics_status','')}</td>
												<td>&nbsp;</td>
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
												<td>电子邮箱：${employ.email}</td>
											</tr>
											<tr>
												<td>手机：${employ.mobile}</td>
											</tr>
											<tr>
												<td>电话：${employ.fixedPhone}</td>
											</tr>
											<tr>
												<td>家庭住址（邮编）：${employ.correspondenceAddress}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="module">
						<div class="module-head">
							<h3>教育经历</h3>
						</div>
						<div class="module-body">
							<div class="media-body">
								<div class="stream-headline">

									<table class="table table-condensed">
										<tbody>
											<tr>
												<td>最高学历学位</td>
												<td>
													${fns:formatDate("yyyy年MM月",employ.highestStartDate)}
													~${fns:formatDate("yyyy年MM月",employ.highestEndDate)}
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													${employ.highestGraduateSchool}&nbsp;&nbsp;${employ.highestMajor}&nbsp;&nbsp;&nbsp;<br>学历：${fns:getDictLabel(employ.highestEduBackground,'highest_edu_background','')}
													&nbsp;&nbsp;学位：${fns:getDictLabel(employ.highestDegree,'academic_degree','')}
													&nbsp;&nbsp;
												</td>
											</tr>

											<tr>
												<td>第一学历学位</td>
												<td>${fns:formatDate("yyyy年MM月",employ.firstStartDate)}
													~${fns:formatDate("yyyy年MM月",employ.firstEndDate)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													${employ.firstGraduateSchool}&nbsp;&nbsp;${employ.firstMajor}&nbsp;&nbsp;&nbsp;<br>学历：${fns:getDictLabel(employ.firstEduBackground,'first_edu_background','')}
													&nbsp;&nbsp;学位：${fns:getDictLabel(employ.firstDegree,'academic_degree','')}
													&nbsp;&nbsp;
												</td>
											</tr>
										</tbody>
									</table>



								</div>
								<div class="stream-headline">
									<h5 class="stream-author"></h5>
								</div>
							</div>
						</div>
					</div>
					<c:if test="${employ.isWorkExperience eq '1'}">
						<div class="module">
							<div class="module-head">
								<h3>工作经历</h3>
							</div>
							<div class="module-body">
								<div class="media-body">
									<div class="stream-headline">
										<table class="table table-condensed">
											<tbody>
												<tr>
													<td>现工作单位：${employ.workingUnit}</td>
												</tr>
												<tr>
													<td>现工作岗位：${employ.workingPost}</td>
												</tr>
												<tr>
													<td>
														参加工作时间：${fns:formatDate("yyyy年MM月",employ.inWorkDate)}</td>
												</tr>
											</tbody>
										</table>

									</div>
								</div>
							</div>
						</div>
					</c:if>
					<div class="module">
						<div class="module-head">
							<h3>相关证书</h3>
						</div>
						<div class="module-body">
							<div class="media-body">
								<div class="stream-headline">
									<table class="table table-condensed">
										<tbody>
											<tr>
												<td>执（职）业资格证：${employ.qualifications}</td>
											</tr>
											<tr>
												<td>技术职称：${employ.techPosition}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="module">
						<div class="module-head">
							<h3>家庭状况</h3>
						</div>
						<div class="module-body">
							<div class="media-body">
								<div class="stream-headline">${employ.familyStatus}</div>
							</div>
						</div>
					</div>
					<div class="module">
						<div class="module-head">
							<h3>个人简介</h3>
						</div>
						<div class="module-body">
							<div class="media-body">
								<div class="stream-headline">${employ.personalProfile}</div>
							</div>
						</div>
					</div>
					<div class="module">
						<div class="module-head">
							<h3>情况说明</h3>
						</div>
						<div class="module-body">
							<div class="media-body">
								<div class="stream-headline">${employ.comments}</div>
							</div>
						</div>
					</div>

					<shiro:hasPermission name="hr:employ:audit">
						<c:if
							test="${'1' eq employ.auditingStatus || '3' eq employ.auditingStatus}">
							<div class="module noprint">
								<div class="module-head">
									<h3>审核</h3>
								</div>
								<div class="module-body">
									<div class="media-body">
										<div class="stream-headline">

											<form:form class="form-horizontal row-fluid" id="inputForm"
												modelAttribute="employ" action="${ctx}/hr/employ/audit"
												method="post">
												<sys:message content="${message}" />
												<form:hidden path="id" />
												<form:hidden path="auditingStatus" />
												<form:textarea cssClass="span12" path="auditComments"
													rows="6" />
												<br />
												<br />

												<a href="#" class="btn btn-success "
													onclick="javascript:audit(2);"> 审核通过 </a>
												<a href="#" class="btn btn-danger"
													onclick="javascript:audit(3);"> 审核不通过 </a>
											</form:form>

										</div>
									</div>
								</div>
							</div>
						</c:if>
					</shiro:hasPermission>

				</div>
			</div>
			<!--/.span9-->
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>