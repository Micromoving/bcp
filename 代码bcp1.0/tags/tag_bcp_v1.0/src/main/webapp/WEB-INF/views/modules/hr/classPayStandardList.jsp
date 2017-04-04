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
						<h3>课酬标准</h3>
							</div>
							<form:form  id="batchForm"  class="form-horizontal row-fluid"
									modelAttribute="classPayStandard"
									action="${ctx}/hr/classPayStandard/batchSave" method="post" >
									<form:hidden path="id"></form:hidden>
							
							<div class="module-body">
							
									<!--/保存按钮-->
												<div class="control-group">
													<div class="controls" id="submit">
														<a href="#" onclick='$("#batchForm").submit();'
															class="btn btn-primary pull-right">保存</a>
													</div>
												</div>
											<br> <br>
											<table
												class="table table-striped table-bordered table-condensed">
												<tbody>
													<c:forEach items="${list}" var="data"  varStatus="status">
														<tr>
															<td style="text-align:center;">${fns:getDictLabel(data.engageMode,'engage_mode','') }教师</td>
															<td style="text-align:center"> ${fns:getDictLabel(data.techPosition,'tech_position_level','')}
															</td>
															<td style="text-align:center"><input type="text"
																class="theFormInput p65"
																name="classPayList[${status.index }]"
																value=${data.classPay } style="width:35px;height:15px">
																元/课时 <input
															type="hidden" name="dataIdList[${status.index }]"
															value="${data.id}">
																</td>
														</tr>
													</c:forEach>
    
												</tbody>
											</table>
									</div>
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