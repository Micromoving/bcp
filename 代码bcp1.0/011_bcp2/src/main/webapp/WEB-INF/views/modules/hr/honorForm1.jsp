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
					<b class="b-left-20"><a href="home.html">首页</a>&nbsp;>&nbsp;<a
						href="bizManagement.html">人力资源管理</a>&nbsp;>&nbsp;<a class="same">师资培养</a>&nbsp;>&nbsp;<a
						href="hrHonorList.html">师德信息（荣誉信息）</a>&nbsp;>&nbsp;<a
						href="hrHonorForm1.html">新增荣誉信息</a> </b>
				</div>
				<!-- 内容 -->
				<div class="formbar t-line">
					<div class="el-step is-horizontal b-left-20">
						<div class="el-step__head is-process is-text underway" id="d1">
							<div class="el-step__line is-horizontal">
								<i class="el-step__line-inner"></i>
							</div>
							<span class="el-step__icon"><div>1</div></span>
						</div>
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
							<span class="el-step__icon"><div>2</div></span>
						</div>
						<div class="el-step__main">
							<div class="el-step__title is-process">请填写其他有关信息</div>
							<div class="el-step__description is-process"></div>
						</div>
					</div>
					<div class="el-step is-horizontal">
						<div class="el-step__head is-process is-text" id="d3">
							<span class="el-step__icon"><div>3</div></span>
						</div>
						<div class="el-step__main">
							<div class="el-step__title is-process">确认填写信息是否无误</div>
							<div class="el-step__description is-process"></div>
						</div>
					</div>
					<div class="form">
						<!--个人与家庭注册start-->
						<form:form class="form-horizontal row-fluid" id="inputForm"
							modelAttribute="honor" action="${ctx}/hr/honor/form1"
							method="post">
							<sys:message content="${message}" />
							<div class="input_common">
								<div class="mb15">
									<span class="title g5"><em class="gred">*</em>工号</span>
									<form:input path="user.loginName" class="h30 w300 cg required"
										type="text"></form:input>
								</div>
							</div>
							<!-- 按钮 -->
							<div class="formbar t-line">
								<button class="btn-con btn-a mg-left-f1" type="submit">下一步</button>
								<a class="btn-con" href="${ctx}/hr/honor/list">返回</a>
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