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
 <b class="b-left-20"><a href="${ ctx}/home.html">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/bizManagement.html">人力资源管理</a> &nbsp;>&nbsp;<a
						href="${ctx }/hrLanguageAbilityList.html">语音能力</a>
                    &nbsp;>&nbsp;<a href="${ctx }/hrLanguageAbilityForm3.html">新增语音能力</a> </b>
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
                    <form id="personForm" method="post" action="hrLanguageAbilityForm3.html">

                        <div class="input_common">
                            <div class="info-prompt">
                                [习近平]，[男]，[汉族]，[党员]，[1953年6月]生，[陕西富平]人，[1969年1月]来我校参加工作。<br>现任[信息学院院长] [清华大学] [美术学]专业，在职[研究生]学历，[法学博士]学位。
                            </div>

                                <div class="mb15"><span class="title g5"><em class="gred">*</em>语种</span>
                                    <select name="languages" class="h30 w300 cg">
                                        <option>待定</option>
                                        <option>待定</option>
                                    </select>
                                </div>
                                <div class="mb15"><span class="title g5"><em class="gred">*</em>掌握程度</span>
                                    <select name="Master_degree" class="h30 w300 cg">
                                        <option>待定</option>
                                        <option>待定</option>
                                    </select>
                                </div>
                            <!-- 按钮 -->
                            <div class="formbar t-line">
                                <button class="btn-con btn-a mg-left-f1">下一步</button>
                                <a class="btn-con" href="hrLanguageAbilityForm1.html">上一步</a>
                            </div>
                        </div>
                    </form>
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