<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="../../include/top.jsp"></jsp:include>

	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
				<div class="span9">
					<div class="content">
						<div class="btn-box-row row-fluid">
							<a href="#" class="btn-box span3"> <i class="icon-bell"></i>
								<b>我的通知</b>
							</a> <a href="#" class="btn-box span3"> <i class="icon-book"></i>
								<b>图书馆</b>
							</a> <a href="#" class="btn-box span3"> <i class="icon-building"></i>
								<b>综合教务</b>
							</a> <a href="#" class="btn-box span3"> <i class="icon-file-alt"></i>
								<b>校内简讯</b>
							</a>
						</div>

						<div class="btn-box-row row-fluid">
							<a href="#" class="btn-box big span4"> <i class="icon-group"></i>
								<b>学生工作</b>
							</a> <a href="#" class="btn-box big span4"> <i
								class="icon-folder-close"></i> <b>教学资源</b>
							</a> <a href="#" class="btn-box big span4"> <i
								class="icon-beaker"></i> <b>科研管理</b>
							</a>
						</div>

						<div class="btn-box-row row-fluid">
							<c:set var="menuList" value="${fns:getMenuList()}" />
							<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">

								<c:if
									test="${menu.parent.id eq (param.parentId? param.parentId:1)&&menu.isShow eq '2'}">



									<c:forEach items="${menuList}" var="menu2">

										<c:if
											test="${menu2.parent.id eq menu.id&&menu2.isShow eq '2'}">

											<a class="btn-box small span2"
												href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}"><i
												class="${not empty menu2.icon ? menu2.icon : 'icon-signout'}"></i><b>${menu2.name}</b></a>


										</c:if>
									</c:forEach>

								</c:if>
							</c:forEach>

							<a href="#" class="btn-box small span2"> <i
								class="icon-envelope"></i> <b>进入邮箱</b>
							</a>


						</div>
						<!--/.btn-controls-->

					</div>
					<!--/.content-->
				</div>
				<!--/.span9-->
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->

	<jsp:include page="../../include/footer.jsp"></jsp:include>

</body>