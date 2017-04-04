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

		$("#inputForm1").validate({

		});

		$("#inputForm2").validate({

		});
		$("#inputForm3").validate({

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
							<div class="module-body">
								<div class="profile-head media">
									<a href="#" class="media-avatar pull-left"> <c:if
											test="${not empty user.photo}">
											<img src="${ctxUploads}/user/profile/${user.photo}"
												class="nav-avatar" />
										</c:if> <c:if test="${empty user.photo}">
											<img src="${ctxUploads}/user/profile/user.png"
												class="nav-avatar" />
										</c:if>

									</a>
									<div class="media-body">


										<p class="profile-brief">
											${user.name}
											<c:if test="${not empty employee.gender }">
												，
											${fns:getDictLabel(employee.gender,'gender'," ")}，
											${fns:getDictLabel(employee.nation,'nation'," ")}，
											${fns:getDictLabel(employee.politicsStatus,'politics_status',"")}，
											${fns:formatDate("yyyy年MM月",employee.birthDate)}生，
											${employee.nativePlace}人，
											${fns:formatDate("yyyy年MM月",employee.inSchoolDate)}来我校参加工作。
										</c:if>
										<hr>
										${employee.personalProfile}
										</p>
										<div class="profile-details muted">
											<a href="${ctx}/hr/employee/detail?id=${employee.id}" class="btn btn-primary">导出 </a> <a
												href="${ctx}/sys/user/upload?id=${employee.id}" class="btn btn-primary">更改头像
											</a>

										</div>
									</div>
								</div>
								<ul class="profile-tab nav nav-tabs">
									<li class="active"><a href="#activity" data-toggle="tab">基本资料</a></li>
									<li><a href="#friends" data-toggle="tab">联系方式</a></li>
								</ul>
								<div class="profile-tab-content tab-content">
									<div class="tab-pane fade active in" id="activity">
										<form:form class="form-horizontal row-fluid"
											action="${ctx}/hr/employee/save1" method="post"
											modelAttribute="employee" id="inputForm1">
											<sys:message content="${message}" />
											<form:hidden path="id" />
											<!--/工号-->
											<div class="control-group">
												<label class="control-label" for="userNo"><font
													color="red">*</font>工号</label>
												<div class="controls">
													<form:input path="userNo" class="span8 required"
														type="text" readonly="true"></form:input>
												</div>
											</div>
											<!--/姓名-->
											<div class="control-group">
												<label class="control-label" for="name"><font
													color="red">*</font>姓名</label>
												<div class="controls">
													<form:input path="user.name" class="span8 required"
														type="text" readonly="true"></form:input>
												</div>
											</div>
											<!--/性别-->
											<div class="control-group">
												<label class="control-label" for="gender"><font
													color="red">*</font>性别</label>
												<div class="controls">
													<form:select path="gender" class="span8 required">
														<option></option>
														<form:options items="${fns:getDictList('gender')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!-- 出生日期 -->
											<div class="control-group">
												<label class="control-label" for="birthDateString"><font
													color="red">*</font>出生日期</label>
												<div class="controls">
													<form:input class="span8 required" path="birthDateString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
												</div>
											</div>
											<!--/民族-->
											<div class="control-group">
												<label class="control-label" for="nation"><font
													color="red">*</font>民族</label>
												<div class="controls">
													<form:select path="nation" class="span8 required">
														<form:options items="${fns:getDictList('nation')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/籍贯-->
											<div class="control-group">
												<label class="control-label" for="nativePlace"><font
													color="red">*</font>籍贯</label>
												<div class="controls">
													<form:input path="nativePlace" class="span8 required"
														type="text"></form:input>
												</div>
											</div>
											<!--/出生地-->
											<div class="control-group">
												<label class="control-label" for="bornPlace"><font
													color="red">*</font>出生地</label>
												<div class="controls">
													<form:input path="bornPlace" class="span8 required"
														type="text"></form:input>
												</div>
											</div>
											<!--/证件类型-->
											<div class="control-group">
												<label class="control-label" for="papersType"><font
													color="red">*</font>证件类型</label>
												<div class="controls">
													<form:select path="papersType" class="span8 required">
														<form:options items="${fns:getDictList('papers_type')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/证件编号-->
											<div class="control-group">
												<label class="control-label" for="papersNumber"><font
													color="red">*</font>证件号码</label>
												<div class="controls">
													<form:input path="papersNumber" class="span8 required"
														type="text"></form:input>
												</div>
											</div>
											<!--/户籍所在地-->
											<div class="control-group">
												<label class="control-label" for="placeDomicile">户籍所在地</label>
												<div class="controls">
													<form:input path="placeDomicile" class="span8" type="text"></form:input>
												</div>
											</div>
											<!--/婚姻状况-->
											<div class="control-group">
												<label class="control-label" for="maritalStatus"><font
													color="red">*</font>婚姻状况</label>
												<div class="controls">
													<form:select path="maritalStatus" class="span8 required">
														<form:options items="${fns:getDictList('marital_status')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/政治面貌-->
											<div class="control-group">
												<label class="control-label" for="politicsStatus"><font
													color="red">*</font>政治面貌</label>
												<div class="controls">
													<form:select class="span8" path="politicsStatus">
														<form:options
															items="${fns:getDictList('politics_status')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/加入时间-->
											<div class="control-group">
												<label class="control-label" for="addTimeString">加入时间</label>
												<div class="controls">
													<form:input class="span8" path="addTimeString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
												</div>
											</div>
											<!--/参加工作时间 -->
											<div class="control-group">
												<label class="control-label" for="inWorkDateString"><font
													color="red">*</font>参加工作时间</label>
												<div class="controls">

													<form:input class="span8 required" path="inWorkDateString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
												</div>
											</div>
											<!--/到校时间 -->
											<div class="control-group">
												<label class="control-label" for="inSchoolDateString"><font
													color="red">*</font>到校时间</label>
												<div class="controls">

													<form:input class="span8 required"
														path="inSchoolDateString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
												</div>
											</div>
											<!--/院龄差值-->
											<div class="control-group">
												<label class="control-label" for="papersNumber">院龄差（年）</label>
												<div class="controls">
													<form:input class="span8" path="differenceValue"></form:input>
												</div>
											</div>
											<!--/正式聘任时间  -->
											<div class="control-group">
												<label class="control-label" for="engagingDateString">正式聘任时间
												</label>
												<div class="controls">
													<form:input class="span8" path="engagingDateString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
												</div>
											</div>
											<!--/就业类型-->
											<div class="control-group">
												<label class="control-label" for="jobType"><font
													color="red">*</font>就业类型</label>
												<div class="controls">
													<form:select path="jobType" class="span8 required">
														<form:options items="${fns:getDictList('job_type')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/到校就业类型-->
											<div class="control-group">
												<label class="control-label" for="schoolJobType"><font
													color="red">*</font>到校就业类型</label>
												<div class="controls">
													<form:select path="schoolJobType" class="span8 required">
														<form:options
															items="${fns:getDictList('school_job_type')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/本人身份-->
											<div class="control-group">
												<label class="control-label" for="selfIdentity"><font
													color="red">*</font>本人身份</label>
												<div class="controls">
													<form:select path="selfIdentity" class="span8 required">
														<form:options items="${fns:getDictList('self_identity')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/人员类型-->
											<div class="control-group">
												<label class="control-label" for="staffType"><font
													color="red">*</font>人员类型</label>
												<div class="controls">
													<form:select path="staffType" class="span8 required">
														<form:options items="${fns:getDictList('staff_type')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/编制情况-->
											<div class="control-group">
												<label class="control-label" for="establishmentSituation"><font
													color="red">*</font>编制情况</label>
												<div class="controls">
													<form:select path="establishmentSituation"
														class="span8 required">
														<form:options
															items="${fns:getDictList('establishment_situation')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="isSpecial"><font
													color="red">*</font>是否享受国务院特殊津贴</label>
												<div class="controls">
													<form:select path="isSpecial"
														class="span8 required">
														<form:options
															items="${fns:getDictList('is_special')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/人事代理人员-->
											<div class="control-group">
												<label class="control-label" for="staffingAgent"><font
													color="red">*</font>人事代理人员</label>
												<div class="controls">
													<form:select path="staffingAgent" class="span8 required">
														<form:options items="${fns:getDictList('staffing_agent')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/学缘结构-->
											<div class="control-group">
												<label class="control-label" for="learningEdgeRafters"><font
													color="red">*</font>学缘结构</label>
												<div class="controls">
													<form:select path="learningEdgeRafters"
														class="span8 required">
														<form:options
															items="${fns:getDictList('learning_edge_rafters')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/在职状态-->
											<div class="control-group">
												<label class="control-label" for="incumbencyStatus"><font
													color="red">*</font>在职状态</label>
												<div class="controls">
													<form:select path="incumbencyStatus" class="span8 required">
														<form:options
															items="${fns:getDictList('incumbency_status')}"
															itemLabel="label" itemValue="value" htmlEscape="false" />
													</form:select>
												</div>
											</div>
											<!--/提交按钮-->
											<div class="control-group">
												<div class="controls">
													<input type="submit" class="btn" value="提交"></input>
												</div>
											</div>
										</form:form>
									</div>


									<!--/.form-horizontal row-fluid-->
									<div class="tab-pane fade" id="friends">
										<form:form class="form-horizontal row-fluid"
											action="${ctx}/hr/employee/save2" method="post"
											modelAttribute="employee" id="inputForm2">
											<form:hidden path="id" />
											<!--/邮箱-->
											<div class="control-group">
												<label class="control-label" for="email">邮箱</label>
												<div class="controls">
													<form:input path="user.email" class="span8"></form:input>
												</div>
											</div>
											<!--/手机-->
											<div class="control-group">
												<label class="control-label" for="mobile"><font
													color="red">*</font>手机</label>
												<div class="controls">
													<form:input path="mobile" class="span8 required phone2"
														type="text"></form:input>
												</div>
											</div>
											<!--/办公电话-->
											<div class="control-group">
												<label class="control-label" for="phone">办公电话</label>
												<div class="controls">
													<form:input path="phone" class="span8 simplePhone"
														type="text"></form:input>
												</div>
											</div>

											<!--/QQ-->
											<div class="control-group">
												<label class="control-label" for="qq">QQ</label>
												<div class="controls">
													<form:input path="qq" class="span8" type="text"></form:input>
												</div>
											</div>

											<!--/家庭住址-->
											<div class="control-group">
												<label class="control-label" for="familyAddress">家庭住址</label>
												<div class="controls">
													<form:input path="familyAddress" class="span8" type="text"></form:input>
												</div>
											</div>

											<!--/邮编-->
											<div class="control-group">
												<label class="control-label" for="postcode">邮编</label>
												<div class="controls">
													<form:input path="postcode" class="span8 zipCode"
														maxlength="6" type="text"></form:input>
												</div>
											</div>
											<!--/个人主页-->
											<div class="control-group">
												<label class="control-label" for="personalHomepage">个人主页</label>
												<div class="controls">
													<form:input path="personalHomepage" class="span8"
														type="text"></form:input>
												</div>
											</div>
											<!--/个人简介-->
											<div class="control-group">
												<label class="control-label" for="personalProfile">个人简介</label>
												<div class="controls">
													<form:textarea class="span8" name="personalProfile"
														rows="5" htmlEscape="false" path="personalProfile"></form:textarea>
												</div>
											</div>
											<!--/提交按钮-->
											<div class="control-group">
												<div class="controls">
													<input type="submit" class="btn" value="提交"></input>
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</div>

						</div>

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
	<!--/.span9-->
	</div>
	<!--/.container-->
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
