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
			<div class="row tag-list mt20">
				<!-- 路径 -->
				<div class="formbar b-line">
					<b><a href="home.html">首页</a>&nbsp;>&nbsp;<a
						href="teachersManage.html">师资管理</a></b>
				</div>
				<c:forEach items="${menus1}" var="menu1">

					<section class="tag-list__item col-md-3">
						<div class="widget-tag">
							<h2 class="h4 h-color">${menu1.name}</h2>
							<section class="tag-list__item col-md-3" style="width: 264px;">

									<div class="tag-list__itemWraper t-line">
										<ul class="tag-list__itembody multi">
										<c:forEach items="${menus2 }" var="menu2">
											<c:if test="${menu2.parent.id eq menu1.id}">
												<li class="tagPopup"><a href="${ctx}${menu2.href }">${menu2.name }</a></li>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</section>
						</div>
					</section>
				</c:forEach>
			</div>
		</div>
	</div>

	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>