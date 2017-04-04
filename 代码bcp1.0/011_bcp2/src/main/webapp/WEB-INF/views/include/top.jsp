<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div class="global-nav sf-header">
	<nav class="container nav">
		<div class="row hidden-xs">
			<div class="col-sm-10 col-md-8 col-lg-7">
				<div class="sf-header__logo">
					<a href="${ctx}/home"><img src="${ctxStatic}/images/sylogo.gif"
						width="45%" height="30%"></a>
				</div>

			</div>
			<div class="col-sm-2 col-md-4 col-lg-5 text-right">
				<div class="row">

					<div class="col-sm-8 col-md-8 col-lg-8">
						<form action="#" class="header-search  hidden-sm hidden-xs mr10">
							<input id="searchBox" name="q" placeholder="输入关键字搜索"
								class="form-control" type="text">
							<button class="btn btn-search">搜索</button>
						</form>
					</div>
					<!--user info-->
					<div class="user_info">
						<div class="user_id">${fns:getUser().loginName}
							<div class="sjx"></div>
						</div>
						<div class="user_more">
							<a href="${ctx}/sys/user/info">个人资料</a> <a
								href="${ctx}/sys/user/modifyPwd">修改密码</a> <a
								href="${ctx}/logout">注销用户</a>
						</div>
					</div>

				</div>


			</div>
		</div>
	</nav>
</div>

<div class="global-navTags" style="margin-top: 1em" id="sco">
	<div class="container">
		<nav class="nav">
			<c:set var="menuList" value="${fns:getMenuList()}" />
			<ul class="nav__list">
				<li class="nav__item"><a href="${ctx}/home"><i
						class="iconfont icon-icon icon-bigger"></i>首页</a></li>
				<c:forEach items="${menuList}" var="menu2">
					<%-- menu2.isShow==2表示此菜单在水平导航中可见。 --%>
					<c:if test="${menu2.isShow eq '2'}">
						<li class="nav__item"><a
							href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}"><i
								class="#"></i>&nbsp;${menu2.name}</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</nav>
	</div>
</div>
