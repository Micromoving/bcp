<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
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
								<h3>查看培训</h3>
							</div>
							<div class="module-body">

								<!--基本信息-->
								<div class="stream-list">
									<div class="media stream">
										<form class="form-horizontal breadcrumb" method="post">
											<div class="control-group">
												姓名：${list.user.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门:${fns:getOfficeRootName(list.office.id)}<br> <br>
												职称：${fns:getDictLabel(list.protp.name,"tech_position",'')}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职级：${fns:getDictLabel(list.protp.techPositionLevel,"tech_position_level",'')}
												<br> <br>
											</div>
										</form>
									</div>
								</div>
								<div class="stream-list">
									<div class="media stream">
										<div class="control-group">
											培训类型：${fns:getDictLabel(list.trainingType,'training_type','')}<br>
											<br>
											培训周期：${fns:getDictLabel(list.trainingCycle,'training_cycle','')}<br>
											<br> 国内外：${fns:getDictLabel(list.isHome,'is_home','')} <br>
											<br> 培训机构：${list.trainingAgency} <br> <br>
											培训地点：${list.trainingPlace} <br> <br>
											培训课程：${list.trainingCourse} <br> <br>
											培训内容：${list.trainingContent} <br> <br> 起始时间：${fns:formatDate("yyyy-MM-dd",list.startDate)}<c:if test="${not empty list.startDate}">-</c:if>${fns:formatDate("yyyy-MM-dd",list.endDate)}<br>
											<br>
											培训方式：${fns:getDictLabel(list.trainingMode,'training_mode','')}
											<br> <br>
											有无证书：${fns:getDictLabel(list.hasCertificate,'has_certificate','')}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;证书编号：${list.certificateId}
											<br> <br> 培训心得或总结：${list.trainingEtc} <br> <br>
											培训费用：${list.trainingFunds}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经费来源：${fns:getDictLabel(list.fundsOrigin,'funds_origin','')}<br>
											<br>
											离校时间：${fns:formatDate("yyyy-MM-dd",list.leaveDate)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返校时间：${fns:formatDate("yyyy-MM-dd",list.returnDate)}<br>
											<br>
											借款金额：${list.loan}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;借款时间：${fns:formatDate("yyyy-MM-dd",list.loanDate)}
											<br> <br>
											报销金额：${list.wipeout}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报销时间：${fns:formatDate("yyyy-MM-dd",list.wipeoutDate)}
											<br> <br> 报销明细：${list.wipeoutDetail}<br> <br>
											校内培训成绩：${list.trainingType} <br> <br>
											网络培训成绩：${list.trainingType}<br> <br>
											听评课成绩：${list.trainingType}
										</div>
									</div>

								</div>

								<!--/.media .stream-->
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