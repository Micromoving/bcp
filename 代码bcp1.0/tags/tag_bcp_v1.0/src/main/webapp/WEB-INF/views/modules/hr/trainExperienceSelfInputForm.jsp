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
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>



				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>增加培训经历</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="trainExperience"
									action="${ctx}/hr/trainExperience/list" method="post">
									<sys:message content="${message}" />
									<form:hidden path="id" />
									<div class="control-group">
										<label class="control-label" for="training_type">培训类型</label>
										<div class="controls">
											<form:select path="trainingType" class="span8">
												<form:options items="${fns:getDictList('training_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="training_cycle">培训周期</label>
										<div class="controls">
											<form:select path="trainingCycle" class="span8">
												<form:options items="${fns:getDictList('training_cycle')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="is_home">国内外</label>
										<div class="controls">
											<form:select path="isHome" class="span8">
												<form:options items="${fns:getDictList('is_home')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="training_agency">培训机构</label>
										<div class="controls">
											<form:input path="trainingAgency" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="training_place">培训地点</label>
										<div class="controls">
											<form:input path="trainingPlace" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="training_course">培训课程</label>
										<div class="controls">
											<form:input path="trainingCourse" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="training_content">培训内容</label>
										<div class="controls">
											<form:input path="trainingContent" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="startDate">培训时间</label>
										<div class="controls">
											<form:input class="span4 " path="startDate"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月dd日'})"></form:input>
											至
											<form:input class="span4 " path="endDate"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月dd日'})"></form:input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="training_mode">培训方式</label>
										<div class="controls">
											<form:select path="trainingMode" class="span8">
												<form:options items="${fns:getDictList('training_mode')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="has_certificate">有无证书</label>
										<div class="controls">
											<form:select path="hasCertificate" class="span8">
												<form:options items="${fns:getDictList('has_certificate')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="certificate_id">证书编号</label>
										<div class="controls">
											<form:input path="certificateId" class="span8" />
										</div>
									</div>


									<div class="control-group">
										<label for="exampleInputFile" class="control-label">培训心得或总结</label>
										<div class="controls">
											<form:input type="file" path="trainingEtc" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="training_funds">培训费用</label>
										<div class="controls">
											<form:input path="trainingFunds" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="funds_origin">经费来源</label>
										<div class="controls">
											<form:select path="fundsOrigin" class="span8">
												<form:options items="${fns:getDictList('funds_origin')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="leave_date">离校时间</label>
										<div class="controls">
											<form:input path="leaveDate" class="span4" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="return_date">返校时间</label>
										<div class="controls">
											<form:input path="returnDate" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="loan">借款金额</label>
										<div class="controls">
											<form:input path="loan" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="loan_date">借款时间</label>
										<div class="controls">
											<form:input path="loanDate" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="wipeout">报销金额</label>
										<div class="controls">
											<form:input path="wipeout" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="wipeout_date">报销时间</label>
										<div class="controls">
											<form:input path="wipeoutDate" class="span8" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="wipeout_detail">报销明细</label>
										<div class="controls">
											<form:textarea class="span8" rows="5" path="wipeoutDetail"></form:textarea>
										</div>
									</div>
									<!--/提交按钮-->
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn">提交</button>
										</div>
									</div>

								</form:form>
								<!--/.form-horizontal row-fluid-->
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