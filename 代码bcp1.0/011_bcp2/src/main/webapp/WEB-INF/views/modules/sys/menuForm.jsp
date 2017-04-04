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
			<div class="row" style="background:#f6f6f6;">
				<div class="formbar">
					<b><a href="${ ctx}/home">首页</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/bizMenu?id=400">系统管理</a>&nbsp;&nbsp;<a
						href="${ctx }/sys/menu/list">菜单管理</a>&nbsp;>&nbsp;<a
						href="${ctx }/sys/menu/form">增加</a>&nbsp;&nbsp; 
				</div>
				<!--/.media .stream-->

				<div class="form">
					<form:form class="form-horizontal row-fluid" name="menu"
						id="inputForm" modelAttribute="menu" action="${ctx}/sys/menu/save"
						method="post">
						<form:hidden path="id" />
						<form:hidden path="parentIds" />
						<sys:message content="${message}" />
						<!-- 内容 -->
						<div class="input_common">

							<!--/上级菜单-->
							<div class="mb15">
								<span class="title g5"><em class="gred">*</em> 上级菜单</span>
								<form:input path="parent.id" htmlEscape="false"
									class="h30 w300 required" />

							</div>
							<!--/名称-->
							<div class="mb15">
								<label for="name" class="title g5"><em class="gred">*</em>名称</label>
								<form:input path="name" htmlEscape="false"
									class="h30 w300 required" />
							</div>

							<!--/链接-->
							<div class="mb15">
								<label for="href" class="title g5"><em class="gred">*</em>链接</label>
								<form:input path="href" htmlEscape="false"
									class="h30 w300 required" />
								<span>点击菜单跳转的页面</span>
							</div>
							<!--/目标-->
							<div class="mb15">
								<label for="target" class="title g5"><em class="gred">*</em>目标</label>
								<form:input path="target" htmlEscape="false"
									class="h30 w300 required" />
								<span>链接地址打开的目标窗口，默认：mainFrame</span>
							</div>
							<!--图标-->
							<div class="mb15">
								<label class="title g5"><em class="gred">*</em>图标</label>
								<div class="title g5">
									<sys:iconselect id="icon" name="icon" value="${menu.icon}" />
								</div>
							</div>
							<!--/排序-->
							<div class="mb15">
								<label for="sort" class="title g5"><em class="gred">*</em>排序</label>
								<form:input path="sort" htmlEscape="false"></form:input>
								<span>排列顺序，升序</span>
							</div>
							<!--/可见-->
							<div class="mb15">
								<label  class="title g5"><em class="gred">*</em>可见</label>
								<form:select path="isShow">
									<form:options items="${fns:getDictList('show_hide')}"
										itemLabel="label" itemValue="value" htmlEscape="false"
										class="required" />
								</form:select>
								<span>该菜单或操作是否显示到系统菜单中</span>
							</div>

							<!--/权限标识-->
							<div class="mb15">
								<label for="permission" class="title g5"><em class="gred">*</em>权限标识</label>
								<form:input name="permission" class="h30 w300 required"
									type="text" htmlEscape="false" path="permission"></form:input>
								<span class="help-inline"></span>
								<br><label class="title g5"></label><span style="">控制器中定义的权限标识，如：@RequiresPermissions("权限标识")</span>
							</div>

							<!--备注-->
							<div class="mb15">
								<label for="remarks" class="title g5 veralg-top"><em class="gred">*</em>备注</label>
								<form:textarea name="remarks" class="h300 w300 required"
									rows="5" path="remarks"></form:textarea>
							</div>
						</div>
						<div class="formbar t-line form-bar-left">
							<button class="btn-con btn-sub btn-big-ft btn-left-70"
								type="submit">提&nbsp;&nbsp;交</button>
							<button class="btn-con btn-sub btn-big-ft btn-left-co"
								type="button">返&nbsp;&nbsp;回</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>