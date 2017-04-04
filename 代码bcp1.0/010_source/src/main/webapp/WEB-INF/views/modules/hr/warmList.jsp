<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function submitBatch(state) {
		$("#postType").val(state);
		$("#batchForm").submit();
	}
	$(function() {
		$('td').css('text-align', 'center').css('padding', '2px');
		$('td input').css('padding', '2px');
	});
</script>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<jsp:include page="../../include/leftMenuSub.jsp"></jsp:include>
				<div class="span9">
						<div class="content">
							<div class="module">
								<div class="module-head">
									<h3>取暖标准</h3>
								</div>
								<div class="module-body">
									<form:form id="batchForm" action="${ctx}/hr/warm/batchSave"
										modelAttribute="warm" class="form-horizontal row-fluid"
										method="post">
										<form:hidden path="id"></form:hidden>
										<div class="stream-composer media">
											<div class="media-body">
												<!--/保存按钮-->
												<div class="control-group">
													<div class="controls" id="submit">
													<c:if test="${warm.salaryPlan.planStatus ne '1'}">
														<a href="#" onclick='$("#batchForm").submit();'
															class="btn btn-primary pull-right">保存</a>
													</c:if>
													</div>
												</div>
											</div>
										</div>
										<br>
										<table
											class="table table-striped table-bordered table-condensed">
											<tbody>
												<tr>
													<td colspan="11"><strong>教师系列</strong></td>
												</tr>
												<tr>
													<td colspan="3">正高级</td>
													<td colspan="2">副高级</td>
													<td colspan="2">中级</td>
													<td colspan="2">助理</td>
													<td colspan="2">见习</td>
												</tr>
												<tr>
													<td>一级</td>
													<td>二级</td>
													<td>三级</td>
													<td>四级</td>
													<td>五级</td>
													<td>六级</td>
													<td>七级</td>
													<td>八级</td>
													<td>九级</td>
													<td>见习期</td>
													<td class="p65">见习期满</td>
												</tr>
												<tr>
													<c:forEach items="${list1}" var="data" varStatus="status">
														<td><input type="text" class="theFormInput p45 "
															name="chargeStandardList1[${status.index}]"
															value="${data.chargeStandard}"></input> <input
															type="hidden" name="dataIdList1[${status.index }]"
															value="${data.id}"></td>
													</c:forEach>
												</tr>
												<tr>
													<td colspan="11">备注：担任主任助理及以上的教师发放标准为3200元/人。</td>
												</tr>
											</tbody>
										</table>
										<br>
										<br>
										<table
											class="table table-striped table-bordered table-condensed">
											<tbody>
												<tr>
													<td colspan="11"><strong>教辅系列</strong></td>
												</tr>
												<tr>
													<td colspan="3">正高级</td>
													<td colspan="2">副高级</td>
													<td colspan="2">中级</td>
													<td colspan="2">助理</td>
													<td colspan="2">见习</td>
												</tr>
												<tr>
													<td>一级</td>
													<td>二级</td>
													<td>三级</td>
													<td>四级</td>
													<td>五级</td>
													<td>六级</td>
													<td>七级</td>
													<td>八级</td>
													<td>九级</td>
													<td>见习期</td>
													<td>见习期满</td>
												</tr>
												<tr>
													<c:forEach items="${list3}" var="data" varStatus="status">
														<td><input type="text" class="theFormInput p45 "
															name="chargeStandardList3[${status.index}]"
															value="${data.chargeStandard}"></input> <input
															type="hidden" name="dataIdList3[${status.index }]"
															value="${data.id}"></td>
													</c:forEach>
												</tr>
												<tr>
													<td colspan="11">备注：担任主任助理及以上的教师发放标准为3200元/人。</td>
												</tr>
											</tbody>
										</table>
										<br>
										<br>
										<table
											class="table table-striped table-bordered table-condensed">
											<tbody>
												<tr>
													<td colspan="11"><strong>职员系列</strong></td>
												</tr>
												<tr>
													<td>一级</td>
													<td>二级</td>
													<td>三级</td>
													<td>四级</td>
													<td>五级</td>
													<td>六级</td>
													<td>七级</td>
													<td>八级</td>
													<td>九级</td>
													<td>见习期</td>
													<td>见习期满</td>
												</tr>
												<tr>
													<c:forEach items="${list2}" var="data" varStatus="status">
														<td><input type="text" class="theFormInput p45 "
															name="chargeStandardList2[${status.index}]"
															value="${data.chargeStandard}"></input> <input
															type="hidden" name="dataIdList2[${status.index }]"
															value="${data.id}"></td>
													</c:forEach>
												</tr>
												<tr>
													<td colspan="11">备注：有专业技术职务的职员，发放标准参考教师系列标准，采取就高原则。工勤岗位（一）参照职员系列发放；工勤岗位（二）发放标准为2400元/人。</td>
												</tr>
											</tbody>
										</table>
										<br>
										<br>
										<table
											class="table table-striped table-bordered table-condensed">
											<tbody>
												<tr>
													<td colspan="11"><strong>工勤系列</strong></td>
												</tr>
												<tr>
													<td>一级</td>
													<td>二级</td>
													<td>三级</td>
													<td>四级</td>
													<td>五级</td>
													<td>见习期</td>
													<td>见习期满</td>
												</tr>
												<tr>
													<c:forEach items="${list5}" var="data" varStatus="status">
														<td><input type="text" class="theFormInput p45 "
															name="chargeStandardList5[${status.index}]"
															value="${data.chargeStandard}"></input> <input
															type="hidden" name="dataIdList5[${status.index }]"
															value="${data.id}"></td>
													</c:forEach>
												</tr>
												<tr>
													<td colspan="11">备注：有专业技术职务的职员，发放标准参考教师系列标准，采取就高原则。工勤岗位（一）参照职员系列发放；工勤岗位（二）发放标准为2400元/人。</td>
												</tr>
											</tbody>
										</table>
										<br>
										<br>
										<table
											class="table table-striped table-bordered table-condensed">
											<tbody>
												<tr>
													<td colspan="7"><strong>专职辅导员系列</strong></td>
												</tr>
												<tr>
													<td>一级</td>
													<td>二级</td>
													<td>三级</td>
													<td>四级</td>
													<td>五级</td>
													<td>见习期</td>
													<td>见习期满</td>
												</tr>
												<tr>
													<c:forEach items="${list4}" var="data" varStatus="status">
														<td><input type="text" class="theFormInput p45 "
															name="chargeStandardList4[${status.index}]"
															value="${data.chargeStandard}"></input> <input
															type="hidden" name="dataIdList4[${status.index }]"
															value="${data.id}"></td>
													</c:forEach>
												</tr>
												<tr>
													<td colspan="7">备注：担任主任助理及以上的教师发放标准为3200元/人。</td>
												</tr>
											</tbody>
										</table>
										<br>
										<br>
										<table
											class="table table-striped table-bordered table-condensed">
											<tbody>
												<tr>
													<td colspan="9"><strong>离退休人员系列</strong></td>
												</tr>
												<tr>
													<td>处级</td>
													<td>科级</td>
													<td>科员</td>
													<td>正高</td>
													<td>副高</td>
													<td>中级</td>
													<td>助理</td>
													<td>技师</td>
													<td>高级技工及以下</td>
												</tr>
												<tr>
													<td><input class="p45" value="3200.0" type="text"></td>
													<td><input class="p45" value="2800.0" type="text"></td>
													<td><input class="p45" value="2400.0" type="text"></td>
													<td><input class="p45" value="4000.0" type="text"></td>
													<td><input class="p45" value="3200.0" type="text"></td>
													<td><input class="p45" value="2800.0" type="text"></td>
													<td><input class="p45" value="2400.0" type="text"></td>
													<td><input class="p45" value="2800.0" type="text"></td>
													<td><input class="p45" value="2400.0" type="text"></td>
												</tr>
											</tbody>
										</table>
										<br>
										<br>

									</form:form>
									<!--/.form-horizontal row-fluid-->
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