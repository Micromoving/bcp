<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

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
			<div>
				<div class="row" style="background:#f6f6f6;">
					<!-- 路径 -->
					<div class="formbar">
						<b class="b-left-20"><a href="${ctx}/home">首页</a>&nbsp;>&nbsp;<a
							href="${ctx}/sys/menu/bizMenu?id=400">系统管理</a>&nbsp;>&nbsp;<a href="${ctx}/sys/dict">数据字典</a> </b>
					</div>
					<!-- 内容 -->
					<div class="formbar t-line">

						<div class="form">
							
							<form:form class="form-horizontal row-fluid" id="inputForm"
								modelAttribute="dict" action="${ctx}/sys/dict/save"
								method="post">
								<form:hidden path="id" />

								<div class="input_common">
								<sys:message content="${message}" />
									<!--/键值-->
									<div class="mb15">
										<span class="title g5"><em class="gred">*</em> 键值</span>

										<form:input class="h30 w300 cg" htmlEscape="false"
											path="value" type="text" />
									</div>
									<div class="mb15">

										<!--/类型-->

										<span class="title g5"><em class="gred">*</em> 类型</span>
										<form:input class="h30 w300 cg" htmlEscape="false" path="type"
											type="text" />

									</div>
									<div class="mb15">
										<!--/描述-->

										<span class="title g5"><em class="gred">*</em> 描述</span>
										<form:input class="required h30 w300 cg" htmlEscape="false"
											path="description" type="text" />

									</div>
									<div class="mb15">
										<!--/排序-->

										<span class="title g5"><em class="gred">*</em> 排序</span>
										<form:input class=" h30 w300 cg" htmlEscape="false" path="sort"
											type="text" />
									</div>
									<div class="mb15">
										<!--/备注-->

										<span class="title g5"> 备注</span>
										<form:textarea path="remarks" htmlEscape="false" rows="6"
											cols="57" />

									</div>
									

									<div class="formbar t-line form-bar-left">
										<button class="btn-con btn-sub btn-big-ft btn-left-70"
											type="submit">提&nbsp;&nbsp;交</button>
										<button class="btn-con btn-sub btn-big-ft btn-left-co"
											type="button">返&nbsp;&nbsp;回</button>
									</div>
								</div>
							</form:form>
						</div>
						<div class="wxts">
						
							
						</div>

					</div>

				</div>
			</div>
			<div class="text-center"></div>
		</div>
	</div>

	<jsp:include page="../../include/footer.jsp"></jsp:include>

</body>
</html>