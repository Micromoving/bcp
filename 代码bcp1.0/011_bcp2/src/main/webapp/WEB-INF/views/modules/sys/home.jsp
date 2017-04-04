<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
</head>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>

	<div class="wrap">
		<div class="container">
			<div class="row" style="padding-top: 15px;">
				<c:forEach items="${fns:getMenuList()}" var="menu"
					varStatus="idxStatus">
					<c:if test="${menu.parent.id eq 1&& menu.isShow eq 1}">
					<c:set var="currMenuHerf"
						value="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${not empty menu.href ? menu.href : '/404'}" />

					<div class="col-md-3" >
						<div class="media border">
							<a class="pull-left" href="${currMenuHerf }"><i
								class="iconfont icon-${not empty menu.icon ? menu.icon : 'signout'} icon-menu-bigger"></i></a>
							<div class="media-body">
								<h3 class="h5 media-heading">
									<a  title="${menu.remarks}" href="${currMenuHerf}">${menu.name}</a>
								</h3>
								<p class="text-muted mb0 pste01">${menu.remarks}</p>
							</div>
							<!-- 13 -->
						</div>
					</div>
					</c:if>
				</c:forEach>
			</div>
			<div class="text-center"></div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>