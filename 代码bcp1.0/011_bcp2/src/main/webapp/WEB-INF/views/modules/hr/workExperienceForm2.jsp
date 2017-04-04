<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate();
	});
</script>
</head>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrap">
    <div class="container">
        <div class="row bg-grey">
            <!-- 路径 -->
            <div class="formbar">
                <b class="b-left-20"><a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a> &nbsp;>&nbsp;<a
						href="${ctx }/hr/workExperience/list">工作经历</a>
                    &nbsp;>&nbsp;<a href="${ctx }/hr/workExperience/form2">新增工作经历</a> </b>
            </div>
            <!-- 内容 -->
            <div class="formbar t-line">
                <div class="el-step is-horizontal b-left-20">
                    <div class="el-step__head is-process is-text underway" id="d1">
                        <div class="el-step__line is-horizontal">
                            <i class="el-step__line-inner"></i>
                        </div>
                        <span class="el-step__icon"><div>1</div></span></div>
                    <div class="el-step__main">
                        <div class="el-step__title is-process">请填写工号</div>
                        <div class="el-step__description is-process"></div>
                    </div>
                </div>
                <div class="el-step is-horizontal">
                    <div class="el-step__head is-process is-text" id="d2">
                        <div class="el-step__line is-horizontal">
                            <i class="el-step__line-inner"></i>
                        </div>
                        <span class="el-step__icon"><div>2</div></span></div>
                    <div class="el-step__main">
                        <div class="el-step__title is-process">请填写其他有关信息</div>
                        <div class="el-step__description is-process"></div>
                    </div>
                </div>
                <div class="el-step is-horizontal">
                    <div class="el-step__head is-process is-text" id="d3">
                        <span class="el-step__icon"><div>3</div></span></div>
                    <div class="el-step__main">
                        <div class="el-step__title is-process">确认填写信息是否无误</div>
                        <div class="el-step__description is-process"></div>
                    </div>
                </div>
                <div class="form">
                    <!--个人与家庭注册start-->
            <form:form class="form-horizontal row-fluid" id="inputForm"
							modelAttribute="workExperience" action="${ctx}/hr/workExperience/form3"
							method="post">
							<form:hidden path="user.loginName"/>
                            <div class="input_common">
                                <div class="info-prompt">
									${workExperience.user.name }，
									${fns:getDictLabel(workExperience.employee.gender,'gender'," ")}，
									${fns:getDictLabel(workExperience.employee.nation,'nation'," ")}，
									${fns:getDictLabel(workExperience.employee.politicalLandscape,'sy_political_landscape',"")}，，
									${fns:formatDate("yyyy年MM月",workExperience.employee.birthDate)}生，
									${workExperience.employee.nativePlace}人，
									${fns:formatDate("yyyy年MM月",workExperience.employee.inSchoolDate)}来我校参加工作。
									<br> 现任[信息学院院长] [清华大学] [美术学]专业，在职[研究生]学历，[法学博士]学位。
								</div>
                                <div class="mb15"><span class="title g5">任职单位名称</span>
                                    <form:input path="nameOfServiceUnit" class="h30 w300 cg" type="text"></form:input>
								</div>
                                <div class="mb15"><span class="title g5">任职开始年月</span>
                                    <form:input path="startDateString" class="h30 w300 cg" type="text" 
                                    onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
								</div>
                                <div class="mb15"><span class="title g5">任职结束年月</span>
                                    <form:input path="endDateString" class="h30 w300 cg" type="text" 
                                    onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
								</div>
                                <div class="mb15"><span class="title g5">单位性质类别</span>
                                    <form:select path="companyNature" class="h30 w300 cg">
                                        <form:options items="${fns:getDictList('sy_company_nature')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
                                    </form:select>
								</div>
                                <div class="mb15"><span class="title g5">任职岗位</span>
                                    <form:input path="post" class="h30 w300 cg" type="text"></form:input>
								</div>
                                <div class="mb15"><span class="title g5">工作描绘</span>
                                    <form:input path="jobDescription" class="h30 w300 cg" type="text"></form:input>
								</div>
                                <div class="mb15"><span class="title g5">工作类型</span>

                                    <form:select path="jobType" class="h30 w300 cg">
										<form:options items="${fns:getDictList('sy_job_type')}"
											itemLabel="label" itemValue="value" htmlEscape="false" />
                                    </form:select>

								</div>
                                <div class="mb15"><span class="title g5">归属部门</span>
                                    <form:input path="companyDepartment" class="h30 w300 cg" type="text"></form:input>
								</div>
                                <div class="mb15"><span class="title g5 veralg-top">备注</span>
                                    <form:textarea path="remarks" class="w300" rows="2"></form:textarea>
								</div>
				                <!-- 按钮 -->
					            <div class="formbar t-line">
                                <button class="btn-con btn-a mg-left-f2" type="submit">下一步</button>
                                <a class="btn-con" href="${ctx }/hr/workExperience/form2">上一步</a>
                                </div>
						    </div>
                            
                            
                    </form:form>

                </div>
                <div class="wxts">
                    <h4>温馨提示</h4>
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