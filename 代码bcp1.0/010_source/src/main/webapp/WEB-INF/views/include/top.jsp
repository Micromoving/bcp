<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!--顶部菜单-->
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<!--logo-->
			<a class="brand" href="${ctx}/home"> <img 
				src="${ctxStatic}/images/sylogo.jpg">
			</a>

			<div class="nav-collapse collapse navbar-inverse-collapse">

				<c:if test="${ not empty fns:getUser().id}">
					<c:if test="${'2' ne fns:getUser().userType }">
						<!--返回菜单按钮-->
						<ul class="nav nav-icons">
							<li class="active"><a href="${ctx}/home"> <img
									src="${ctxStatic}/images/zhuye.png" class="home-logo">
							</a></li>

						</ul>
						<!--搜索图标-->
						<form class="navbar-search pull-left input-append" action="#">
							<input type="text" class="span3">
							<button class="btn" type="button">
								<i class="icon-search"></i>
							</button>
						</form>
					</c:if>
					<ul class="nav pull-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">我的任务 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx}/sys/task/todolist">待办任务</a></li>
								<li><a href="${ctx}/sys/task/hislist">已办任务</a></li>
							</ul></li>
						<li class="nav-user dropdown"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <c:if
									test="${not empty fns:getUser().photo }">
									<img src="${ctxUploads}/user/profile/${fns:getUser().photo}"
										class="nav-avatar" />
								</c:if> <c:if test="${ empty fns:getUser().photo }">
									<img src="${ctxUploads}/user/profile/user.png"
										class="nav-avatar" />
								</c:if> <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<c:if test="${'1'eq fns:getUser().userType }">
									<li><a href="${ctx}/sys/user/info?menuIds=400">个人资料</a></li>
								</c:if>

								<c:if test="${'2'eq fns:getUser().userType }">
									<li><a href="${ctxFront}/hr/employ/form">个人简历</a></li>
								</c:if>
								<li><a href="${ctx}/sys/user/modifyPwd">更改密码</a></li>
								<li class="divider"></li>
								<li><a href="${ctx}/logout">用户注销</a></li>
							</ul></li>
					</ul>
				</c:if>
			</div>
			<!-- /.nav-collapse -->
		</div>
	</div>
	<!-- /navbar-inner -->
</div>
<!-- /navbar -->

