<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<title>微动</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="http://www.micromoving.cn/" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<link rel="shortcut icon" href="${ctxStatic}/images/favicon.png"
	type="image/x-icon" />
<link rel="apple-touch-icon-precomposed"
	href="${ctxStatic}/images/favicon.png">
<meta name="Keywords" content="山西大学商务学院,信息学院,办公自动化,门户," />
<meta name="Description"
	content="BCP集成了校园内各类信息，整合了校内的各业务应用系统，让广大师生员工只要拥有一个账号，就能访问到权限范围内的所有信息资源，并让用户可以根据自己的喜好来个性定制信息服务和系统界面。" />
<script src="${ctxStatic}/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link type="text/css"
	href="${ctxStatic}/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet">

<script type="text/javascript"
	src="${ctxStatic}/scripts/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
	src="${ctxStatic}/scripts/jquery.form.js"></script>
<script src="${ctxStatic}/scripts/jquery-ui-1.10.1.custom.min.js"
	type="text/javascript"></script>

	<script src="${ctxStatic}/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>

 <link rel="stylesheet" href="${ctxStatic}/css/loginCss.css">
 
    <link rel="stylesheet" href="${ctxStatic}/fonts/iconfont.css">
<style>


.alert {
	padding:8px 35px 8px 14px;
	margin-bottom:0px;
	text-shadow:0 1px 0 rgba(255, 255, 255, 0.5);
	background-color:#fcf8e3;
	border:1px solid #fbeed5;
	-webkit-border-radius:4px;
	-moz-border-radius:4px;
	border-radius:4px;
}
.alert, .alert h4 {
	color:#c09853;
}
.alert h4 {
	margin:0;
}
.alert .close {
	position:relative;
	top:-2px;
	right:-21px;
	line-height:20px;
}
.alert-success {
	background-color:#dff0d8;
	border-color:#d6e9c6;
	color:#468847;
}
.alert-success h4 {
	color:#468847;
}
.alert-danger, .alert-error {
	background-color:#f2dede;
	border-color:#eed3d7;
	color:#b94a48;
}
.alert-danger h4, .alert-error h4 {
	color:#b94a48;
}
.alert-info {
	background-color:#d9edf7;
	border-color:#bce8f1;
	color:#3a87ad;
}
.alert-info h4 {
	color:#3a87ad;
}
.alert-block {
	padding-top:14px;
	padding-bottom:14px;
}
.alert-block>p, .alert-block>ul {
	margin-bottom:0;
}
.alert-block p+p {
	margin-top:5px;
}
</style>

</head>
<body>
<div class="wrapper">
    <div class="header clearfix">
        <a href="#" class="logo"><img src="${ctxStatic}/images/sylogo.gif" style="width: 200px;padding-top: 18px;"/></a>
        <span class="logo-tip">Hi,微动欢迎您!</span>
    </div>

    <div class="content clearfix">
        <!-- 登录注册版块 -->

        <div id="unauth_main" class="login-regist">
            <div class="login-box">
                <div class="title">帐号登录</div>
                <sys:message content="${message}" type="error" />
                <form  id="loginForm" action="${ctx}/login"class="formBox" method="post">

                    <div class="itembox user-name">

                        <div class="item">
                            <i class="ico iconfont icon-yonghu1"></i>
                            <label for="J_LoginUser" class="txt-tip"></label>
                            <input autocomplete="off" tabindex="1" style="ime-mode:disabled" id="J_LoginUser"
                                   placeholder="用户ID/邮箱/手机号" type="text" class="txt tabInput" value="" name="username">
                        </div>
                    </div>

                    <div class="itembox user-pwd">
                        <div class="item">
                            <i class="ico iconfont icon-mima1"></i>
                            <label for="J_LoginPsw" class="txt-tip"></label>
                            <input id="J_LoginPsw" name="password" tabindex="2" type="password" autocomplete="off" class="txt tabInput"
                                   placeholder="密码" value="">

                        </div>
                    </div>

<c:if test="${isValidateCodeLogin}">
                    <div class="itembox pass-verifycode" id="imgCodeBox">
                       <sys:validateCode name="validateCode"
									inputCssStyle="margin-bottom:0;" />
                    </div>
                    </c:if>

                    <div class="member-pass clearfix">
                        <a class="forget-pwd" href="#" target="_blank">忘记密码?</a>
                    </div>
                    <div id="loginBtnBox" class="login-btn"><span class="wait-bar" id="wait-bar"></span><input
                            id="J_LoginButton" type="submit" value="马上登录" tabindex="4" class="tabInput pass-btn"></div>
                </form>
            </div>
            <!-- //登录版块end -->
        </div>
    </div>
</div>
<div class="foot">
    <div class="wrapper">
        <script>var no_cbsi_site = '';    </script>
        <div class="footerw">
            &nbsp;&#169;2015-
            <script type="text/javascript">
                var yearStr;
                now = new Date();
                yearStr = now.getFullYear();
                document.write(yearStr);</script>
            山西大学商务学院微动 版权所有.
        </div>
    </div>
</div>

</body>
</html>