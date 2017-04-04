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
                <b class="b-left-20"><a href="${ ctx}/home">首页
                </a>&nbsp;>&nbsp;<a href="${ctx }/sys/menu/bizMenu?id=100">
                人力资源管理</a>&nbsp;>&nbsp;<a class="same">师资培养
                </a>&nbsp;>&nbsp;<a href="${ctx }/hr/cert/list">证书信息
                </a>&nbsp;>&nbsp;<a href="${ctx }/hr/cert/form3">
                增加证书信息</a> </b>
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
                    <div class="el-step__head is-process is-text is-success" id="d2">
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
                    <div class="el-step__head is-process is-text underway" id="d3">
                        <span class="el-step__icon"><div>3</div></span></div>
                    <div class="el-step__main">
                        <div class="el-step__title is-process">确认填写信息是否无误
                        </div>
                        <div class="el-step__description is-process"></div>
                    </div>
                </div>
               
                <div class="form">
                    <!--个人与家庭注册start-->
                    <form:form class="form-horizontal row-fluid" id="inputForm"
							modelAttribute="certificateInformation" action="${ctx}/hr/cert/save"
							method="post">

							<sys:message content="${message}" />
							<form:hidden path="id"/>
                    <div class="input_common">
                        <table class="dttable step3-tbl wtd-step3">
                            <tbody>
                            <tr>
                                <td class="step3-td"><strong>工号</strong></td>
                                <td>${certificateInformation.user.loginName}</td>
                            </tr>
                            <tr>
                                <td class="step3-td"><strong>证书类型</strong></td>
                                <td>${fns:getDictLabel(certificateInformation.certificateType,'sy_certificate_type'," ")}</td>
                            </tr>
                            <tr>
                                <td class="step3-td"><strong>语言证书名称</strong></td>
                                <td>${fns:getDictLabel(certificateInformation.languageCertificate,'sy_language_certificate'," ")}</td>
                            </tr>
                            <tr>
                                <td class="step3-td"><strong>证书名称</strong></td>
                                <td>${certificateInformation.certificate}</td>
                            </tr>
                            <tr>
                                <td class="step3-td"><strong>发证年月</strong></td>
                                <td>${fns:formatDate("yyyy年MM月",certificateInformation.dateOfIssuance) }</td>
                            </tr>
                            <tr>
                                <td class="step3-td"><strong>发证单位</strong></td>
                                <td>${certificateInformation.issuingUnit }</td>
                            </tr>
                            <tr>
                                <td class="step3-td"><strong>证书编号</strong></td>
                                <td>${certificateInformation.certificateNumber }</td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                        <!-- 按钮 -->
                        <div class="formbar t-line">
                            <button class="btn-con btn-a mg-left-f1" type="submit">完成</button>
                            <a class="btn-con" href="${ctx }/hr/cert/form2">返回</a>
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
<div class="text-center"></div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>