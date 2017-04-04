<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
    String menuIds = request.getParameter("menuIds");
    if (menuIds != null) {
        request.getSession().removeAttribute("menuIdFirst");
        request.getSession().removeAttribute("menuIdSecond");
        request.getSession().removeAttribute("menuIdThird");

        String[] menuId = menuIds.split(",");
        request.getSession().setAttribute("menuIdFirst", menuId[0]);
        if (menuId.length >= 2) {
            request.getSession().setAttribute("menuIdSecond", menuId[1]);
        }
        if (menuId.length >= 3) {
            request.getSession().setAttribute("menuIdThird", menuId[2]);
        }
    }
%>
<div class="span3">
	<div class="sidebar">
		<c:set var="menuList" value="${fns:getMenuList()}" />

		<c:set var="firstMenuId" value="${menuIdFirst}" />
		<c:set var="secondMenuId" value="${menuIdSecond}" />
		<c:set var="thirdMenuId" value="${menuIdThird}" />


		<ul class="widget widget-menu unstyled">
			<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">

				<c:if test="${menu.parent.id eq firstMenuId&&menu.isShow eq '1'}">


					<li><c:set var="subMenuListSize"
							value="${fns:getMenuListByParentId(menu.id).size()}" /> <c:if
							test="${subMenuListSize gt 0 }">
							<a class="collapsed" href="#togglePages${menu.id }"
								data-toggle="collapse"> <i
								class="menu-icon icon-${not empty menu.icon ? menu.icon : 'signout'}"></i>
								<i class="icon-chevron-down pull-right"></i> <i
								class="icon-chevron-up pull-right"></i>${menu.name}
							</a>
							<ul id="togglePages${menu.id }"
								class='${ (empty secondMenuId || menu.id ne secondMenuId)?"collapse":"" } unstyled'>

								<c:set var="subMenuList"
									value="${fns:getMenuListByParentId(menu.id)}" />
								<c:forEach items="${subMenuList }" var="subMenu">
									<li><a
										${not empty thirdMenuId && subMenu.id eq thirdMenuId?"class=\"selected\"":"" }
										href="${fn:indexOf(subMenu.href, '://') eq -1 ? ctx : ''}${not empty subMenu.href ? subMenu.href : '/404'}"><i
											class=" icon-${not empty subMenu.icon ? subMenu.icon : 'signout'}"></i>&nbsp;${subMenu.name}</a>
									</li>
								</c:forEach>


							</ul>

						</c:if> <c:if test="${subMenuListSize eq 0 }">




							<a
								href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${not empty menu.href ? menu.href : '/404'}"><i
								class="menu-icon icon-${not empty menu.icon ? menu.icon : 'signout'}"></i>&nbsp;${menu.name}</a>
						</c:if></li>

				</c:if>
			</c:forEach>
		</ul>



	</div>
	<!--/.sidebar-->
</div>
<!--/.span3-->


