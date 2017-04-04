<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
</head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate({

		});
	});
</script>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
			<div class="row bg-grey">
            <!-- 路径 -->
            <div class="formbar">
                <b class="b-left-20"><a 
                href="${ctx }/home">首页</a>&nbsp;>&nbsp;<a 
                href="${ctx }/sys/menu/bizmenu?id=100">人力资源管理</a>&nbsp;>&nbsp;<a 
                class="same">绩效考核</a>&nbsp;>&nbsp;<a 
                href="${ctx }/hr/annualAppraisal/List">年度考核</a>&nbsp;>&nbsp;<a 
                href="${ctx }/hr/annualAppraisal/Form1">新增年度考核</a> 
                </b>
            </div>
            <!-- 内容 -->
            <div class="formbar t-line">
                <div class="el-step is-horizontal b-left-20">
                    <div class="el-step__head is-process is-text is-success" id="d1">
                        <div class="el-step__line is-horizontal">
                            <i class="el-step__line-inner"></i>
                        </div>
                        <span class="el-step__icon"><div>1</div></span></div>
                    <div class="el-step__main">
                        <div class="el-step__title is-process">请填写工号
                        </div>
                        <div class="el-step__description is-process"></div>
                    </div>
                </div>
                <div class="el-step is-horizontal">
                    <div class="el-step__head is-process is-text underway" id="d2">
                        <div class="el-step__line is-horizontal">
                            <i class="el-step__line-inner"></i>
                        </div>
                        <span class="el-step__icon"><div>2</div></span></div>
                    <div class="el-step__main">
                        <div class="el-step__title is-process">请填写其他有关信息
                        </div>
                        <div class="el-step__description is-process"></div>
                    </div>
                </div>
                <div class="el-step is-horizontal">
                    <div class="el-step__head is-process is-text" id="d3">
                        <span class="el-step__icon"><div>3</div></span></div>
                    <div class="el-step__main">
                        <div class="el-step__title is-process">确认填写信息是否无误
                        </div>
                        <div class="el-step__description is-process"></div>
                    </div>
                </div>
                <sys:message content="${message}" />
					<form:form id="inputForm"
						action="${ctx }/hr/annualAppraisal/form3"
						modelAttribute="annualAppraisal" method="post"
						class="formbar t-line b-line">
						<form:hidden path="id" vluae="1" />
						<form:hidden path="user.loginName"/>
                    <!--个人与家庭注册start-->

                        <div class="input_common">
                            <div class="info-prompt">
                                ${annualAppraisal.user.name }，
                                ${fns:getDictLabel(annualAppraisal.employee.gender,'gender'," ")}，
                                ${fns:getDictLabel(annualAppraisal.employee.nation,'nation'," ")}，
                                ${fns:getDictLabel(annualAppraisal.employee.politicalLandscape,'politicalLandscape'," ")}，
                                ${fns:formatDate("yyyy年MM月",annualAppraisal.employee.birthDate)}生，
                                ${annualAppraisal.employee.nativePlace }，人，
                                ${fns:formatDate("yyyy年MM月",annualAppraisal.employee.inSchoolDate)}来我校参加工作。
                                <br>现任[信息学院院长] [清华大学] [美术学]专业，在职[研究生]学历，[法学博士]学位。
                            </div>
                            
                            <div class="mb15">
                            <span class="title g5">考核年度</span>
                                <form:select path="checkYear" class="h30 w300 cg" >
                                	<form:options
                                		items="${fns:getDictList('sy_certificate_types') }"
                                		itemLabel="label" itemValue="value" htmlEscape="false"/>
                                </form:select>
                            </div>
                            <div class="mb15">
                            <span class="title g5">考核结果</span>
                                <form:input path="checkResult" class="h30 w300 cg" type="text"></form:input>
                            </div>
                            <div class="mb15">
                            <span class="title g5">考核单位名称</span>
                                <form:input path="checkUnit" class="h30 w300 cg" type="text"></form:input>
                            </div>
                            <div class="mb15">
                            <span class="title g5">未考核原因</span>
                                <form:input path="notCheckReason" class="h30 w300 cg" type="text"></form:input>
                            </div>
						</div>

                            <!-- 按钮 -->
                            <div class="formbar t-line">
							<button class="btn-con btn-a mg-left-f2">下一步</button>
							<a class="btn-con" href="${ctx }/hr/annualAppraisal/form1">上一步</a>
						</div>
					</form:form>
     				</div>

			</div>
		</div>
		<div class="wxts">
			<h4>温馨提示</h4>
			<p>2.唐山、张家口、廊坊、秦皇岛、承德五个城市用户请选择冀北注册。</p>
		</div>
	</div>
	<div class="text-center"></div>
	<div class="text-center"></div>
	<div class="text-center"></div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>