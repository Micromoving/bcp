<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctxStatic}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#inputForm").validate({
			errorPlacement : function(error, element) {
				error.appendTo(element.next());
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenu.jsp"></jsp:include>
				<div class="span9">
					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>个人培训</h3>
							</div>
							<div class="module-body">
								<!--/增加按钮-->
								<div class="stream-composer media">
									<div class="media-body">
										<div class="clearfix">
											<a href="${ctx}/hr/trainExperience/selfForm"
												class="btn btn-primary pull-right"> 增加 </a>
										</div>
									</div>
								</div>
								<!--基本信息-->
								<div class="stream-list">
									<div class="media stream">
										<form class="form-horizontal breadcrumb" method="post"
											style="margin-bottom:0px;">
											<div class="control-group" style="height:20px;">
												${user.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${employee.gender}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;讲师
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信息学院 <br> <br>
											</div>
										</form>
									</div>
								</div>

								<!--/列表-->
								<div class="stream-list">
									<div class="media stream">
										<div class="media-body">
											<div class="stream-headline">
												<table>
													<tbody>
														<tr>
															<td class="p120">[年月－年月]${fns:formatDate("yyyy-MM-dd",list.startDate)}-${fns:formatDate("yyyy-MM-dd",list.endDate)}</td>
															<td class="p120">[培训类型]${fns:getDictLabel(list.trainingType,'training_type','')}</td>
															<td class="p180">[培训周期]${fns:getDictLabel(list.trainingCycle,'training_cycle','')}</td>
															<td>[培训地点]${list.trainingPlace}</td>
														</tr>
														<tr>
															<td></td>
															<td>[培训费用]${list.trainingFunds}</td>
															<td>[经费来源]${fns:getDictLabel(list.fundsOrigin,'funds_origin','')}</td>
														</tr>

													</tbody>
												</table>
											</div>
											<!--/.stream-headline-->

											<div class="stream-options">
												<a href="${ctx}/hr/trainExperience/detail" class="btn btn-small">
													详情 </a>
											</div>
										</div>
									</div>

								</div>
								<!--/.stream-list-->
							</div>
							<!--/.module-body-->
						</div>
						<!--/.module-->

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
</html>