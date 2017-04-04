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
		
		
		
		if (1 == $("#txtAssessmentTotal ").val()) {
			$(".div1").show();
		} else {
			$(".div1").hide();
		}

		$("#inputForm").validate();
	});

	$(function() {

		$("#txtAssessmentTotal").change(function() {
			if (1 == $("#txtAssessmentTotal ").val()) {
				$(".div1").show();
			} else {
				$(".div1").hide();
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

				<div class="span9" style="width:940px;">
					<div class="content">
						<div class="module">
							<div class="module-body">
								<c:if test="${not empty employ.id }">
									<div class="profile-head media">
										<div class="media-avatar pull-left">


											<c:if test="${not empty fns:getUser().photo }">
												<img src="${ctxUploads}/user/profile/${fns:getUser().photo}"
													class="nav-avatar" />
											</c:if>
											<c:if test="${ empty fns:getUser().photo }">
												<img src="${ctxUploads}/user/profile/user.png"
													class="nav-avatar" />
											</c:if>
										</div>
										<div class="media-body">

											<p class="profile-brief">
												申报岗位:${fns:getDictLabel(employ.declarePositionNumber,'declare_position_number','')}&nbsp;&nbsp;&nbsp;&nbsp;
												岗位类型:${fns:getDictLabel(employ.postType,'post_type','')}<br>
												<br> 审核状态:<font color="red">${fns:getDictLabel(employ.auditingStatus,'auditing_status','')}</font>
												<br>
												<c:if test="${not empty employ.auditComments }">
											审核意见：<font color="red">${employ.auditComments}</font>
												</c:if>
											</p>
											<div class="profile-details muted">
										
													<a href="${ctxFront}/employ/upload"
															class="btn btn-inverse">上传照片 </a>
														<c:if test="${not empty employ.id }">
															<a href="${ctxFront}/hr/employ/word?id=${employ.id} "
																class="btn btn-inverse">生成Word</a>
														</c:if>

													
												
											</div>
										</div>
									</div>
								</c:if>
								<br>
								<div class="module-head">
									<h3>填报个人资料</h3>
								</div>
								<br>
								<div class="module-body">
									<form:form class="form-horizontal row-fluid" id="inputForm"
										modelAttribute="employ" action="${ctx}/hr/employ/save"
										method="post">
										<sys:message content="${message}" />
										<form:hidden path="id" />
										<!--/岗位类型-->
										<div class="control-group">
											<label class="control-label" for="postType"><font
												color="red">*</font>岗位类型</label>
											<div class="controls">
												<form:select path="postType" 
													onchange="selectChange(this.value,'${ctxFront}/hr/employ/subList','declarePositionNumber')"
													class="span8 required"><form:option value="">请选择</form:option>
													<form:options
														items="${fns:getDictList('employ_post_type')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>
										<!--/申报岗位序号-->
										<div class="control-group" >
											<label class="control-label" for="declarePositionNumber"><font
												color="red">*</font>申报岗位</label>
											<div class="controls" >
												<form:select path="declarePositionNumber" 
													class="span8 required">
													<form:option value="">请选择</form:option>
													<form:options
														items="${fns:getDictList('declare_position_number')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>

										<!--/姓名-->
										<div class="control-group">
											<label class="control-label" for="name"><font
												color="red">*</font>姓名</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="name"></form:input>
											</div>
										</div>
										<!--/身份证号-->
										<div class="control-group">
											<label class="control-label" for="IDCardNumber"><font
												color="red">*</font>身份证号</label>
											<div class="controls">
												<form:input class="span8 required card" htmlEscape="false"
													path="IDCardNumber" readonly="true"></form:input>
											</div>
										</div>
										<!--/性别-->
										<div class="control-group">
											<label class="control-label"><font color="red">*</font>性别</label>
											<div class="controls">
												<form:select path="gender" class="span8 required">
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

												<form:input class="span8 required" htmlEscape="false"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
													path="birthDateString"></form:input>

											</div>
										</div>
										<!--/户籍地-->
										<div class="control-group">
											<label class="control-label" for="placeDomicile"><font
												color="red">*</font>户籍地</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="placeDomicile"></form:input>
											</div>
										</div>
										<!--/政治面貌-->
										<div class="control-group">
											<label class="control-label" for="politicsStatus"><font
												color="red">*</font>政治面貌</label>
											<div class="controls">
												<form:select class="span8 required" path="politicsStatus">
													<form:options items="${fns:getDictList('politics_status')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>
										<!--/民族-->
										<div class="control-group">
											<label class="control-label" for="nation"><font
												color="red">*</font>民族</label>
											<div class="controls">
												<form:select class="span8 required" path="nation">
													<form:options items="${fns:getDictList('nation')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>
										<!--/婚姻状况-->
										<div class="control-group">
											<label class="control-label"><font color="red">*</font>婚姻状况</label>
											<div class="controls">
												<form:select path="maritalStatus" class="span8 required">
													<form:options items="${fns:getDictList('marital_status')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>

										<!--/第一学历-->
										<div class="control-group">
											<label class="control-label" for="firstEduBackground"><font
												color="red">*</font>第一学历</label>
											<div class="controls">
												<form:select path="firstEduBackground"
													class="span8 required">
													<form:option value=""></form:option>
													<form:options
														items="${fns:getDictList('first_edu_background')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>
										<!--/第一学位-->
										<div class="control-group">
											<label class="control-label" for="firstDegree">第一学位</label>
											<div class="controls">
												<form:checkbox path="firstDegree" id="firstDegree" value="1" />
												学士
											</div>
										</div>
										<!--/第一学历毕业院校-->
										<div class="control-group">
											<label class="control-label" for="firstGraduateSchool"><font
												color="red">*</font>第一学历毕业院校</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="firstGraduateSchool"></form:input>
											</div>
										</div>
										<!--/第一学历所学专业-->
										<div class="control-group">
											<label class="control-label" for="firstMajor"><font
												color="red">*</font>第一学历所学专业</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="firstMajor"></form:input>
											</div>
										</div>
										<!-- 第一学历学习起止日期 -->
										<div class="control-group">
											<label class="control-label" for="firstStartDateString"><font
												color="red">*</font>第一学历起止日期</label>
											<div class="controls">
												<div class="col-sm-8">
													<form:input class="span4 " path="firstStartDateString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
													至
													<form:input class="span4 required"
														path="firstEndDateString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
												</div>
											</div>
										</div>
										<!-- 第一学位学习起止日期 -->
										<div class="control-group">
											<label class="control-label" for="firstDegreeStartDateString"><font
												color="red">*</font>第一学位学习起止日期</label>
											<div class="controls">
												<form:input class="span4 " path="firstDegreeStartDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
												至
												<form:input class="span4 required"
													path="firstDegreeEndDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>

										<!--/最高学历-->
										<div class="control-group">
											<label class="control-label" for="highestEduBackground"><font
												color="red">*</font>最高学历</label>
											<div class="controls">
												<form:select path="highestEduBackground"
													class="span8 required">
													<form:option value=""></form:option>
													<form:options
														items="${fns:getDictList('highest_edu_background')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>
										<!--/最高学位-->
										<div class="control-group">
											<label class="control-label" for="highestDegree">最高学位</label>
											<div class="controls">
												<form:select path="highestDegree" class="span8">
													<form:option value=""></form:option>
													<form:options items="${fns:getDictList('academic_degree')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>
											</div>
										</div>
										<!--/最高学历毕业院校-->
										<div class="control-group">
											<label class="control-label" for="highestGraduateSchool"><font
												color="red">*</font>最高学历毕业院校</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="highestGraduateSchool"></form:input>
											</div>
										</div>
										<!--/最高学历所学专业-->
										<div class="control-group">
											<label class="control-label" for="highestMajor"><font
												color="red">*</font>最高学历所学专业</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="highestMajor"></form:input>
											</div>
										</div>
										<!-- 最高学历学习起止日期 -->
										<div class="control-group">
											<label class="control-label" for="companyName"><font
												color="red">*</font>最高学历学习起止日期</label>
											<div class="controls">
												<div class="col-sm-8">
													<form:input class="span4 " path="highestStartDateString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
													至
													<form:input class="span4 required"
														path="highestEndDateString"
														onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
												</div>
											</div>
										</div>
										<!-- 最高学位学习起止日期 -->
										<div class="control-group">
											<label class="control-label"
												for="highestDegreeStartDateString"><font color="red">*</font>最高学位学习起止日期</label>
											<div class="controls">
												<form:input class="span4 "
													path="highestDegreeStartDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
												至
												<form:input class="span4 required"
													path="highestDegreeEndDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>

										<!--/是否有工作经历-->
										<div class="control-group">
											<label class="control-label"><font color="red">*</font>是否有工作经历</label>
											<div class="controls">
												<form:select id="txtAssessmentTotal" path="isWorkExperience"
													class="span8 required">
													<form:options items="${fns:getDictList('yes_no')}"
														itemLabel="label" itemValue="value" htmlEscape="false" />
												</form:select>

											</div>
										</div>

										<!--/现工作单位-->
										<div class="control-group div1" name="div1"
											style="display:none;">
											<label class="control-label" for="workingUnit"><font
												color="red">*</font>现工作单位</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="workingUnit"></form:input>
											</div>
										</div>

										<!--/现工作岗位-->
										<div class="control-group div1" name="div1"
											style="display:none;">
											<label class="control-label" for="workingPost"><font
												color="red">*</font>现工作岗位</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="workingPost"></form:input>
											</div>
										</div>
										<!-- 参加工作时间-->
										<div class="control-group div1" name="div1"
											style="display:none;">
											<label class="control-label" for="inWorkDateString"><font
												color="red">*</font>参加工作时间</label>
											<div class="controls">
												<form:input class="span8 required" path="inWorkDateString"
													onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
											</div>
										</div>
										<!--/执（职）业资格证-->
										<div class="control-group">
											<label class="control-label" for="qualifications"><font
												color="red">*</font>执（职）业资格证</label>
											<div class="controls">
												<form:input class="span8 required" htmlEscape="false"
													path="qualifications"></form:input>
											</div>
										</div>
										<!--技术职称-->
										<div class="control-group">
											<label class="control-label" for="techPosition"><font
												color="red">*</font>技术职称</label>
											<div class="controls">
												<form:input class="span8" htmlEscape="false"
													path="techPosition"></form:input>
											</div>
										</div>
										<!--/移动电话-->
										<div class="control-group">
											<label class="control-label" for="mobile"><font
												color="red">*</font>移动电话</label>
											<div class="controls">
												<form:input class="span8 required phone" htmlEscape="false"
													path="mobile"></form:input>
											</div>
										</div>
										<!--/通讯地址-->
										<div class="control-group">
											<label class="control-label" for="correspondenceAddress"><font
												color="red">*</font>通讯地址</label>
											<div class="controls">
												<form:input class="span8" htmlEscape="false"
													path="correspondenceAddress"></form:input>
											</div>
										</div>
										<!--/电子邮箱-->
										<div class="control-group">
											<label class="control-label" for="email"><font
												color="red">*</font>电子邮箱</label>
											<div class="controls">
												<form:input class="span8 required email" htmlEscape="false"
													path="email"></form:input>
											</div>
										</div>
										<!--/固定电话-->
										<div class="control-group">
											<label class="control-label" for="fixedPhone">固定电话</label>
											<div class="controls">
												<form:input class="span8 simplePhone" htmlEscape="false"
													path="fixedPhone"></form:input>
											</div>
										</div>
										<!--/个人简介-->
										<div class="control-group">
											<label class="control-label" for="personalProfile"><font
												color="red">*</font>个人简介</label>
											<div class="controls">
												<form:textarea cssClass="span8 required" rows="16"
													path="personalProfile" />
												<br>
												<lable style="width:230px;">个人简介主要介绍：①学习经历（从第一学历开始）②工作经历。
												</lable>
											</div>
										</div>
										<!--/家庭状况-->
										<div class="control-group">
											<label class="control-label" for="familyStstus"><font
												color="red">*</font>家庭成员状况</label>
											<div class="controls">
												<form:textarea cssClass="span8 required" path="familyStatus"
													rows="6" />
												<br>
												<lable style="width:230px;">家庭成员的简介，包括关系、姓名、年龄、职业以及工作单位等。</lable>
											</div>
										</div>
										<!--/符合应聘岗位要求的情况说明-->
										<div class="control-group">
											<label class="control-label" for="comments">备注</label>
											<div class="controls">
												<form:textarea cssClass="span8" path="comments" rows="6" />
												<br>
												<lable style="width:230px;">符合应聘岗位要求的情况说明。</lable>
											</div>
										</div>
										<c:if test="${!flag}">
											<c:if
												test="${empty employ.auditingStatus||'1' eq employ.auditingStatus}">

												<!--/提交按钮-->
												<div class="control-group">
													<div class="controls" id="submit">
														<input type="submit" class="btn btn-inverse" id="submit "
															value="提交">
													</div>
												</div>
											</c:if>
										</c:if>
									</form:form>
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
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->

	<jsp:include page="../../include/footer.jsp"></jsp:include>

</body>