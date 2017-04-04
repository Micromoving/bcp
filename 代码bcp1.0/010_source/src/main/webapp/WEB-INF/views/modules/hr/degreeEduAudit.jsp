<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
	$(document).ready(
			function() {
				$("#btnExport").click(
						function() {
							top.$.jBox.confirm("确认要导出吗？", "系统提示", function(v,
									h, f) {
								if (v == "ok") {
									$("#searchForm").attr("action",
											"${ctx}/hr/degreeEdu/export");
									$("#searchForm").submit();
								}
							}, {
								buttonsFocus : 1
							});
							top.$('.jbox-body .jbox-icon').css('top', '55px');
						});
			});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/degreeEdu/list");
		$("#searchForm").submit();
		return false;

		function audit(s) {

			if (s)
				$("#auditingStatus").val(s);

			$("#inputForm").submit();
			return false;
		}
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
							<div class="module-head">
								<h3>学历教育审核</h3>
							</div>
							<div class="module-body">

								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="degreeEdu"
									action="${ctx}/hr/degreeEdu/saveAudit" method="post">
									<form:hidden path="id" />
									<form:hidden path="act.taskId" />
									<form:hidden path="act.taskName" />
									<form:hidden path="act.taskDefKey" />
									<form:hidden path="act.procInsId" />
									<form:hidden path="act.procDefId" />
									<form:hidden id="flag" path="act.flag" />
									<sys:message content="${message}" />
									<!--基本信息-->
									<div class="stream-list">
										<div class="media stream">

											<div class="control-group">

												姓名： ${degreeEdu.user.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门：
												${degreeEdu.office.name } <br> <br>
												职称：${fns:getDictLabel(degreeEdu.protp.name,'tech_position'," ")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职级：${fns:getDictLabel(degreeEdu.protp.techPositionLevel,'tech_position_level',"")}
												<br> <br>
											</div>

										</div>
									</div>
									<div class="stream-list">
										<div class="media stream">
											<div class="control-group">
												攻读学历:${fns:getDictLabel(degreeEdu.diploma,'diploma',"")} <br>
												<br>
												攻读学位:${fns:getDictLabel(degreeEdu.degree,'degree',"")}&nbsp;&nbsp;&nbsp;&nbsp;攻读方式:
												${fns:getDictLabel(degreeEdu.eduMode,'edu_mode',"")}<br>
												<br>
												攻读类型:${fns:getDictLabel(degreeEdu.eduType,'edu_type',"")}&nbsp;&nbsp;&nbsp;&nbsp;是否脱产:${fns:getDictLabel(degreeEdu.isOffjob,'yes_no',"")}
												<br> <br> 协议签订情况:${degreeEdu.signAgreement }${fns:getDictLabel(degreeEdu.signAgreement,'yes_no',"")}&nbsp;&nbsp;&nbsp;&nbsp;攻读最短年限:${degreeEdu.ageLimit }
												<br> <br>
												攻读院校:${degreeEdu.school}&nbsp;&nbsp;&nbsp;&nbsp;攻读专业:${degreeEdu.major }&nbsp;&nbsp;&nbsp;&nbsp;专业攻读方向:${degreeEdu.researchArea }
												<br> <br>
												攻读开始时间:${fns:formatDate("yyyy年MM月",degreeEdu.startDate)}&nbsp;&nbsp;&nbsp;&nbsp;攻读完成时间:${fns:formatDate("yyyy年MM月",degreeEdu.endDate)}
												<br> <br>
												报考是否盖章:${fns:getDictLabel(degreeEdu.isConfirm,'yes_no',"")}&nbsp;&nbsp;&nbsp;&nbsp;学费:${degreeEdu.tuition }
												<br> <br> 借款金额:${degreeEdu.loan }&nbsp;&nbsp;&nbsp;&nbsp;借款时间:${degreeEdu.loanDate }
												<br> <br> 报销金额:${degreeEdu.wipeout }&nbsp;&nbsp;&nbsp;&nbsp;报销时间:${degreeEdu.wipeoutDate }
												<br> <br>
												启动读研(博)工资:${fns:getDictLabel(degreeEdu.hasSalary,'yes_no',"")}&nbsp;&nbsp;&nbsp;&nbsp;恢复工资时间:${fns:formatDate("yyyy年MM月",degreeEdu.recoverSalaryDate)}
												<br> <br>
												是否延期:${fns:getDictLabel(degreeEdu.isDelay,'yes_no',"")}&nbsp;&nbsp;&nbsp;&nbsp;取得证书时间:${fns:formatDate("yyyy年MM月",degreeEdu.getCertificateDate)}
												<br> <br> 毕业证编号:${degreeEdu.diplomaNum }&nbsp;&nbsp;&nbsp;&nbsp;学位证编号:${degreeEdu.degreeNum }
												<br> <br> 备注：${degreeEdu.comments }

											</div>
										</div>
									</div>
									<div class="stream-list">
										<div class="media stream">
											<div class="control-group">
												<div class="controls">
													<c:set var="status"
														value='<%=request.getParameter("status")%>'></c:set>
													<c:if test="${empty status}">
														<input id="btnSubmit" class="btn btn-success "
															type="submit" value="审核通过 "
															onclick="$('#flag').val('yes')" />&nbsp; <input
															id="btnSubmit" class="btn btn-danger" type="submit"
															value="审核不通过" onclick="$('#flag').val('no')" />&nbsp;
												</c:if>
												</div>
											</div>
										</div>
									</div>
									<!--/.media .stream-->
								</form:form>
							</div>
							<!--/.stream-list-->
						</div>
						<!--/.module-body-->
					</div>
					<!--/.module-->

				</div>
				<!--/.content-->
			</div>
			<!--/.span9-->
		</div>
	</div>
	<!--/.container-->
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>