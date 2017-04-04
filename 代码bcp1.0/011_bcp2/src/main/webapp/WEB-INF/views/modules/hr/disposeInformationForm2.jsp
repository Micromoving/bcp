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
                  <b><a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=100">人力资源管理</a> &nbsp;>&nbsp;<a
						class="same">师资培养</a>&nbsp;>&nbsp;<a href="${ctx }/hr/disposeInformation/list">师德信息(处分信息)</a>&nbsp;>&nbsp;<a 
						href="${ctx }/hr/disposeInformation/form2">增加处分信息</a> </b>
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
                        <div class="el-step__title is-process">请填写工号</div>
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
							modelAttribute="disposeInformation" action="${ctx}/hr/disposeInformation/form2"
							method="post">
                    
                        <div class="input_common">
                            <div class="info-prompt">
                               ${disposeInformation.user.name}，
                               ${fns:getDictLabel(disposeInformation.employee.gender,'gender'," ")}，
                               ${fns:getDictLabel(disposeInformation.employee.nation,'nation'," ")}，
                               ${fns:getDictLabel(disposeInformation.employee.politicalLandscape,'sy_political_landscape',"")}，
                              ${fns:formatDate("yyyy年MM月",disposeInformation.employee.birthDate)}生，
                              ${disposeInformation.employee.nativePlace}人，
                              ${fns:formatDate("yyyy年MM月",disposeInformation.employee.inSchoolDate)}来我校参加工作。
                              <br>现任[信息学院院长] [清华大学] [美术学]专业，在职[研究生]学历，[法学博士]学位。
                            </div>
                            
                            <div class="mb15"><span class="title g5">处分类别</span>
                                <form:input path="disciplinaryCategories" class="h30 w300 cg" type="text"></form:input>
                            </div>
                            <div class="mb15"><span class="title g5">处分原因</span>
                                <form:input path="disciplinaryReasons" class="h30 w300 cg" type="text"></form:input>
                            </div>
                            <div class="mb15"><span class="title g5">处分发生日期</span>
                                <form:input path="disposeStartDateString" class="h30 w300 cg" type="text"
                                onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
                            </div>
                            <div class="mb15"><span class="title g5">处分记录描述</span>
                                <form:input path="disposeRecordDescription" class="h30 w300 cg" type="text"></form:input>
                            </div>
                            <div class="mb15"><span class="title g5"><em class="gred"></em>处分单位名称</span>
                                <form:input path="disposeUnit" class="h30 w300 cg" type="text"></form:input>
                            </div>
                            <div class="mb15"><span class="title g5"><em class="gred"></em>处分日期</span>
                                <form:input path="disposeDateString" class="h30 w300 cg" type="text"
                                onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
                            </div>
                            <div class="mb15"><span class="title g5"><em class="gred"></em>处分撤销日期</span>
                                <form:input path="disposeEndDateString" class="h30 w300 cg" type="text"
                                onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></form:input>
                            </div>
                            <div class="mb15"><span class="title g5 veralg-top"><em class="gred"></em>处分撤销原因</span>
                                <form:input path="disposeEndReason" class="h30 w300 cg" type="text"></form:input>
                            </div>
                            <!-- 按钮 -->
                            <div class="formbar t-line">
                                <button class="btn-con btn-a mg-left-f2" type="submit">下一步</button>
                                <a class="btn-con" href="${ctx }/hr/disposeInformation/form">上一步</a>
                            </div>
                        </div>
                    </form:form>
                </div>
                
                </div>
            </div> 
        </div>
        <div class="text-center"></div>
    </div>
</div>
<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>