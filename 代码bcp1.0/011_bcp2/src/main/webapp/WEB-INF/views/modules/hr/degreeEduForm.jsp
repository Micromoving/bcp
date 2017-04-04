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

				<!--/.span3-->

				<div class="span9">
					<div class="content">

						<div class="module">
							<div class="module-head">
								<h3>增加学历教育</h3>
							</div>
							<!--/.module-head-->
							<div class="module-body">
								<form:form class="form-horizontal row-fluid" id="inputForm"
									modelAttribute="degreeEdu" action="${ctx}/hr/degreeEdu/save"
									method="post">
									<form:hidden path="id" />
									<sys:message content="${message}" />
									<!--/攻读学历-->
									<div class="control-group">
										<label class="control-label">攻读学历</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="diploma">
												<form:options items="${fns:getDictList('diploma')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/攻读学位-->
									<div class="control-group">
										<label class="control-label">攻读学位</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="degree">
												<form:options items="${fns:getDictList('degree')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/攻读方式-->
									<div class="control-group">
										<label class="control-label">攻读方式</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="eduMode">
												<form:options items="${fns:getDictList('edu_mode')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/攻读类型-->
									<div class="control-group">
										<label class="control-label">攻读类型</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="eduType">
												<form:options items="${fns:getDictList('edu_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/是否脱产-->
									<div class="control-group">
										<label class="control-label">是否脱产</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="isOffjob">
												<form:options items="${fns:getDictList('yes_no')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/协议签订情况-->
									<div class="control-group">
										<label class="control-label">协议签订情况</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="signAgreement">
												<form:options items="${fns:getDictList('sign_agreement')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/攻读最短年限-->
									<div class="control-group">
										<label class="control-label" for="age_limit">攻读最短年限</label>
										<div class="controls">
											<form:input path="ageLimit" class="span8" type="text" />
										</div>
									</div>
									<!--攻读院校-->
									<div class="control-group">
										<label class="control-label" for="school">攻读院校</label>
										<div class="controls">
											<form:input path="school" class="span8" type="text" />
										</div>
									</div>
									<!--/攻读专业-->
									<div class="control-group">
										<label class="control-label" for="major">攻读专业</label>
										<div class="controls">
											<form:input path="major" class="span8" type="text" />
										</div>
									</div>
									<!--/攻读方向-->
									<div class="control-group">
										<label class="control-label" for="research_area">攻读方向</label>
										<div class="controls">
											<form:input path="researchArea" class="span8" type="text" />
										</div>
									</div>
									<!--/攻读开始时间-->
									<div class="control-group">
										<label class="control-label" for="startDate">攻读开始时间</label>
										<div class="controls">
											<form:input class="span8" path="startDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/攻读完成时间-->
									<div class="control-group">
										<label class="control-label" for="endDate">攻读完成时间</label>
										<div class="controls">
											<form:input class="span8" path="endDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/报考是否盖章-->
									<div class="control-group">
										<label class="control-label">报考是否盖章</label>
										<div class="controls">
												<form:select tabindex="1" class="span8" path="isConfirm">
													<form:options items="${fns:getDictList('yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
										</div>
									</div>
									<!--/学费-->
									<div class="control-group">
										<label class="control-label" for="tuition">学费</label>
										<div class="controls">
											<form:input path="tuition" class="span8" type="text" />
										</div>
									</div>
									<!--/借款金额-->
									<div class="control-group">
										<label class="control-label" for="loan">借款金额</label>
										<div class="controls">
											<form:input path="loan" class="span8" type="text" />
										</div>
									</div>
									<!--/借款时间-->
									<div class="control-group">
										<label class="control-label" for="loanDate">借款时间</label>
										<div class="controls">
											<form:input class="span8" path="loanDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/报销金额-->
									<div class="control-group">
										<label class="control-label" for="wipeout">报销金额</label>
										<div class="controls">
											<form:input path="wipeout" class="span8" type="text" />
										</div>
									</div>
									<!--报销时间-->
									<div class="control-group">
										<label class="control-label" for="wipeoutDate">报销时间</label>
										<div class="controls">
											<form:input class="span8" path="wipeoutDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/启动读研(博)工资-->
									<div class="control-group">
										<label class="control-label">启动读研(博)工资</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="hasSalary">
												<form:options items="${fns:getDictList('yes_no')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
									</div>
									<!--/恢复工资时间-->
									<div class="control-group">
										<label class="control-label" for="recoverSalaryDate">恢复工资时间</label>
										<div class="controls">
											<form:input class="span8" path="recoverSalaryDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/是否延期-->
									<div class="control-group">
										<label class="control-label">是否延期</label>
										<div class="controls">
											<form:select tabindex="1" class="span8" path="isDelay">
													<form:options items="${fns:getDictList('yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
										</div>
									</div>
									<!--/取得证书时间-->
									<div class="control-group">
										<label class="control-label" for="getCertificateDate">取得证书时间</label>
										<div class="controls">
											<form:input class="span8" path="getCertificateDateString"
												onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
										</div>
									</div>
									<!--/毕业证编号-->
									<div class="control-group">
										<label class="control-label" for="diploma_num">毕业证编号</label>
										<div class="controls">
											<form:input path="diplomaNum" class="span8" type="text" />
										</div>
									</div>
									<!--/学位证编号-->
									<div class="control-group">
										<label class="control-label" for="degree_num">学位证编号</label>
										<div class="controls">
											<form:input path="degreeNum" class="span8" type="text" />
										</div>
									</div>
									<!--/备注-->
									<div class="control-group">
										<label class="control-label" for="comments">备注</label>
										<div class="controls">
											<form:textarea cssClass="span8" path="comments" rows="6" />
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