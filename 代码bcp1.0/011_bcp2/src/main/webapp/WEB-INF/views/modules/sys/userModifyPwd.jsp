<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#oldPassword").focus();
				$("#inputForm")
						.validate(
								{
									rules : {},
									messages : {
										confirmNewPassword : {
											equalTo : "输入与上面相同的密码"
										}
									},
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.appendTo(element.next());
										}
									}
								});
			});
</script>
</head>
<body>
<body class="tag-index" data-mod="tag">
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrap">
		<div class="container">
			<div>
				<div class="row" style="background:#f6f6f6;">
					<!-- 路径 -->
					<div class="formbar">
						<b class="b-left-20"><a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
							href="${ ctx}/sys/user/modifyPwd" class="same">修改密码</a> </b>
					</div>
					<!-- 内容 -->

					<div class="formbar t-line">

						<!--个人与家庭注册start-->
						<form:form id="searchForm" action="${ctx }/sys/user/modifyPwd"
							modelAttribute="user" method="post" class="formbar t-line b-line">
							<form:hidden path="id" />
							<sys:message content="${message}" />
							<!-- <input name="pwd" id="wuserspwd" type="hidden">
                            <input name="repwd" id="wusersrepwd" type="hidden">
                            <input name="userType" id="userType" value="01" type="hidden"> -->
							<div class="input_common">
								<div class="mb15">
									<span class="title g5"><em class="gred">*</em>旧密码</span> <input
										id="oldPassword" name="oldPassword" type="password"
										path="oldPassword" maxlength="50" minlength="3"
										class="h30 w300 cg">
								</div>
								<div class="mb15">
									<span class="title g5"><em class="gred">*</em>新密码</span> <input
										id="newPassword" name="newPassword" type="password" value=""
										maxlength="50" minlength="3" class="h30 w300 cg">
								</div>
								<div class="mb15" style=" margin-bottom: 80px;">
									<span class="title g5"><em class="gred">*</em>确认新密码</span> <input
										id="confirmNewPassword" name="confirmNewPassword"
										type="password" value="" maxlength="50" minlength="3"
										class="h30 w300 cg" equalTo="#newPassword">
								</div>
							</div>
							<!-- 按钮 -->
							<div class="formbar t-line">
								<button class="btn-con btn-a mg-left-f1">提交</button>
								<a class="btn-con" href="${ ctx}/home">返回</a>
							</div>
						</form:form>
					</div>
					<div class="wxts">
						<h4>温馨提示</h4>
						<p>1.95598智能互动网站（www.95598.cn）是国家电网公司统一对外服务网站，提供电力信息浏览、网上业务受理、网上
							缴费、信息自助查询等网络服务功能，为电力客户提供信息咨询、沟通交流和互动服务平台，宣传电动汽车、绿色能源、智能用电等新型业务。95598智能互动
							网站服务范围已覆盖国家电网27个省（市）电力公司。</p>
						<p>2.唐山、张家口、廊坊、秦皇岛、承德五个城市用户请选择冀北注册。</p>
					</div>

				</div>

			</div>
		</div>
		<div class="text-center"></div>
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>