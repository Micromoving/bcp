<%@page import="cn.micromoving.bcp.modules.hr.service.AttendanceService"%>
<%@page import="cn.micromoving.bcp.modules.hr.entity.Attendance"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	
</script>
<style type="text/css">
</style>
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
								<h3>请假详情</h3>
							</div>
							<div class="module-body">
								<c:set var="isPass" value="true"></c:set>
								<div class="stream-list">
									<c:if test="${photonumber eq '3' }">
										<div class="media stream">
											<div class="circular" style="background:#3C3C3C;">1</div>
											<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit1' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">2</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">3</div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit1' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">2</div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">3</div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit1' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">2</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">3</div>
											</c:if>
											<br> <br> <br> <br> <br>
											<div class="text" style="text-align:center">
												<c:forEach items="${route}" var="routes">${routes}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
											</div>
										</div>
									</c:if>
									<c:if test="${photonumber eq '5' }">
										<div class="media stream">
											<div class="circular" style="background:#3C3C3C;">1</div>
											<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit1' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">2</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit1' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">2</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit1' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">2</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit2' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">3</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit2' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">3</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit2' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">3</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit5' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">4</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">5</div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit5' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">4</div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">5</div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit5' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">4</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">5</div>
											</c:if>

											<br> <br> <br> <br> <br>
											<c:forEach items="${route}" var="routes">${routes}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>

										</div>
									</c:if>
									<c:if test="${photonumber eq '6' }">
										<div class="media stream">
											<div class="circular" style="background:#3C3C3C;">1</div>
											<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit1' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">2</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit1' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">2</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit1' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">2</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit2' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">3</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit2' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">3</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit2' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">3</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit3' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">4</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit3' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">4</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit3' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">4</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit5' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">5</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">6</div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit5' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">5</div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">6</div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit5' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">5</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">6</div>
											</c:if>
											<br> <br> <br> <br> <br>
											<div class="text" style="text-align:center">
												<c:forEach items="${route}" var="routes">${routes}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>
											</div>
										</div>
									</c:if>
									<c:if test="${photonumber eq '7' }">
										<div class="media stream">
											<div class="circular" style="background:#3C3C3C;">1</div>
											<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>


											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit1' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">2</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit1' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">2</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit1' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">2</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit2' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">3</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit2' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">3</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit2' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">3</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit3' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">4</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit3' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">4</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit3' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">4</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>


											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit4' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">5</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit4' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">5</div>
												<div class="Arrow2 MARGIN-2"></div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit4' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">5</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
											</c:if>

											<c:if
												test="${attendance.act.taskDefKey eq 'leaveaudit5' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="circular MARGIN-2" style="background:#FF0000;">6</div>
												<div class="Arrow2 MARGIN-2" style="background:#FF0000;"></div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">7</div>
												<c:set var="isPass" value="false"></c:set>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit5' && isPass eq 'false'}">
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">6</div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="Arrow2 MARGIN-2"></div>
												<div class="circular MARGIN-2">7</div>
											</c:if>
											<c:if
												test="${attendance.act.taskDefKey ne 'leaveaudit5' && isPass eq 'true'}">
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">6</div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="Arrow2 MARGIN-2" style="background:#3C3C3C;"></div>
												<div class="circular MARGIN-2" style="background:#3C3C3C;">7</div>
											</c:if>
											<br> <br> <br> <br> <br>
											<div class="text" >
												<b><c:forEach items="${route}" var="routes">${routes}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach></b>
											</div>
										</div>
									</c:if>


								</div>

								<!--基本信息-->
								<div class="stream-list">
									<div class="media stream">
										<form:form class="form-horizontal " method="post">
											<div class="control-group">
												姓名： ${fns:getUser().name}<br>
												部门：${fns:getUser().office}
											</div>
										</form:form>
									</div>
								</div>
								<div class="stream-list">
									<div class="media stream">
										<div class="control-group">
											申请时间:${fns:formatDate("yyyy-MM-dd",attendance.applyDate)} <br>
											请假时间:${fns:formatDate("yyyy-MM-dd",attendance.startDate)}至${fns:formatDate("yyyy-MM-dd",attendance.endDate)}<br>
											请假类型：${fns:getDictLabel(attendance.leaveType,'oa_leave_type','')}
											<br> 请假原因： ${attendance.leaveReason}
										</div>
									</div>
								</div>
								<div class="stream-list">
									<div class="media stream">

										<c:if
											test="${not empty attendances4.auditRecords.auditOpinion}">
										院系处室领导审批意见：${attendances4.auditRecords.auditOpinion}<br>
										</c:if>
										<c:if
											test="${not empty attendances3.auditRecords.auditOpinion}">
										分管领导审批意见：${attendances3.auditRecords.auditOpinion}<br>
										</c:if>
										<c:if
											test="${not empty attendances2.auditRecords.auditOpinion  }">
										人事主管审批意见：${attendances2.auditRecords.auditOpinion}<br>
										</c:if>
										<c:if
											test="${not empty attendances1.auditRecords.auditOpinion}">
										院长审批意见：${attendances1.auditRecords.auditOpinion}
										</c:if>

									</div>
								</div>
								<!--/.media .stream-->

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
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>