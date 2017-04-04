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
			
		});
		$("#officeName").css('width','306px');
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
                            <h3>添加部门</h3>
                        </div><!--/.module-head-->
                        <div class="module-body">
                           <form:form class="form-horizontal row-fluid" id="inputForm"
								modelAttribute="office"
								action="${ctx}/sys/office/save" method="post">
	<form:hidden path="id" />
								<div class="control-group">
												<label class="control-label">上级机构</label>
												<div class="controls">
												<sys:treeselect id="office" name="parent.id"
												value="${office.parent.id}" labelName="parent.name"
												labelValue="${office.parent.name}" title="部门"
												url="/sys/office/treeData?type=2" extId="${office.id}" cssClass="input-small"
												allowClear="${office.currentUser.admin}" />
												</div>
								</div>
											
                                <!--/-->
                                <div class="control-group">
                                    <label class="control-label">名称</label>
                                    <div class="controls">
                                        <label>
                                            <form:input path="name" class="span8" type="text"/>
                                        </label>

                                    </div>
                                </div>

                                <!---->
                                <div class="control-group">
                                    <label class="control-label">排序</label>
                                    <div class="controls">
                                        <form:input path="sort" class="span8" type="text"/>

                                    </div>
                                </div>
                                <!--/-->
                                <div class="control-group">
                                    <label class="control-label">层次级别</label>
                                    <div class="controls">
											<form:select path="levels" class="input-medium span8">
												<form:options items="${fns:getDictList('sys_office_grade')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
                                    </div>
                                </div>
                                <!--/-->
                                <div class="control-group">
                                    <label class="control-label">成立文号</label>
                                    <div class="controls">
                                        <form:input path="foundingDocNumber" class="span8" type="text"/>

                                    </div>
                                </div>
                                <!--机构类型-->
                                <div class="control-group">
                                    <label class="control-label">机构类型</label>
                                    <div class="controls">
                                      <form:select path="type" class="span8">
												<form:options items="${fns:getDictList('sys_office_type')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
                                    </div>
                                </div>
                                <!--机构级别-->
                                <div class="control-group">
                                    <label class="control-label">机构等级</label>
                                    <div class="controls">
                                       <form:select path="grade" class="span8">
												<form:options items="${fns:getDictList('sys_office_grade')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
                                    </div>
                                </div>
                                <!--/联系地址-->
                                <div class="control-group">
                                    <label class="control-label">联系地址</label>
                                    <div class="controls">
                                        <form:input path="address" class="span8" type="text"/>

                                    </div>
                                </div>
                                <!--/邮政编码-->
                                <div class="control-group">
                                    <label class="control-label">邮政编码</label>
                                    <div class="controls">
                                        <form:input path="zipCode" class="span8" type="text"/>

                                    </div>
                                </div>
                                <!--/主管院领导分管-->
                                <div class="control-group">
                                    <label class="control-label">主管院领导分管</label>
                                    <div class="controls">
                                        <form:input path="master" class="span8" type="text"/>

                                    </div>
                                </div>
                                <!--/电话-->
                                <div class="control-group">
                                    <label class="control-label">电话</label>
                                    <div class="controls">
                                        <form:input path="phone" class="span8" type="text"/>

                                    </div>
                                </div>
                                <!--/传真-->
                                <div class="control-group">
                                    <label class="control-label">传真</label>
                                    <div class="controls">
                                        <form:input path="fax" class="span8" type="text"/>
                                    </div>
                                </div>
                                <!--/邮箱-->
                                <div class="control-group">
                                    <label class="control-label">邮箱</label>
                                    <div class="controls">
                                        <form:input path="email" class="span8" type="text"/>
                                    </div>
                                </div>
                                <!--是否可用-->
                                <div class="control-group">
                                    <label class="control-label">是否可用</label>
										<div class="controls">
											<form:select path="useable" class="span8">
												<form:options items="${fns:getDictList('yes_no')}"
													itemLabel="label" itemValue="value" htmlEscape="false" />
											</form:select>
										</div>
                                </div>

                                <!--/主副负责人-->
                                <div class="control-group">
                                    <label class="control-label">主负责人</label>
                                    <div class="controls">
                                        <form:input path="primaryPerson" class="span8" type="text"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">副负责人</label>
                                    <div class="controls">
                                        <form:input path="deputyPerson" class="span8" type="text"/>
                                    </div>
                                </div>
                                <!--/成立时间-->
                                 <div class="control-group">
                                    <label class="control-label">成立时间</label>
                                    <div class="controls">
                                        <form:input path="foundingDateString" class="span8" 
                                        onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
                                    </div>
                                    </div>
                                    <!--/聘任文号-->
                                 <div class="control-group">
                                    <label class="control-label">聘任文号</label>
                                    <div class="controls">
                                        <form:input path="engageDocNumber" class="span8" ></form:input>
                                    </div>
                                    </div>
                                    <!--/聘任时间-->
                                 <div class="control-group">
                                    <label class="control-label">聘任时间</label>
                                    <div class="controls">
                                        <form:input path="engageDateString" class="span8" 
                                        onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
                                    </div>
                                    </div>
                                    <!--/部门印章名称-->
                                 <div class="control-group">
                                    <label class="control-label">部门印章名称</label>
                                    <div class="controls">
                                        <form:input path="sealName" class="span8" ></form:input>
                                    </div>
                                    </div>
                                    <!--/印章启用时间-->
                                 <div class="control-group">
                                    <label class="control-label">印章启用时间</label>
                                    <div class="controls">
                                        <form:input path="sealDateString" class="span8" 
                                        onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月'})"></form:input>
                                    </div>
                                <!--/备注-->
                                <div class="control-group">
                                    <label class="control-label">备注信息</label>
                                    <div class="controls">
                                        <form:textarea path="remarks" class="span8" rows="5"></form:textarea>
                                    </div>
                                </div>
                               
                                </div>
                                <!--/提交按钮-->
                                <div class="control-group">
                                    <div class="controls">
                                        <button type="submit" class="btn">提交</button>
                                    </div>
                                </div>

                            </form:form>
                        </div>
                    </div><!--/.module-->
                </div><!--/.content-->
            </div><!--9_over-->
			</div>
		</div>
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>