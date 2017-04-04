<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
request.getSession().removeAttribute("menuIdFirst");
request.getSession().removeAttribute("menuIdSecond");
request.getSession().removeAttribute("menuIdThird");

%>

<div class="span3">
	<div class="sidebar">
		<c:set var="menuList" value="${fns:getMenuList()}" />

		<c:set var="currMenuId" value="${menuIds}" />


		<c:choose>

			<c:when test="${not empty  currMenuId }">
				<ul class="widget widget-menu unstyled">
					<c:forEach items="${menuList}" var="menu2">

						<c:if test="${menu2.parent.id eq menuIds&&menu2.isShow eq '1'}">

							<li><a
								href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}"><i
									class="menu-icon icon-${not empty menu2.icon ? menu2.icon : 'signout'}"></i>&nbsp;${menu2.name}</a>
							</li>

						</c:if>
					</c:forEach>
				</ul>

			</c:when>
			<c:otherwise>
				<c:forEach items="${menuList}" var="menu" varStatus="idxStatus">

					<c:if
						test="${menu.parent.id eq (param.parentId? param.parentId:1)&&menu.isShow eq '1'}">


						<ul class="widget widget-menu unstyled">
							<c:forEach items="${menuList}" var="menu2">

								<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">

									<li><a
										href="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}"><i
											class="menu-icon icon-${not empty menu2.icon ? menu2.icon : 'signout'}"></i>&nbsp;${menu2.name}</a>
									</li>

								</c:if>
							</c:forEach>
						</ul>
					</c:if>
				</c:forEach>
			</c:otherwise>
		</c:choose>




	</div>
	<!--/.sidebar-->
</div>
<!--/.span3-->


