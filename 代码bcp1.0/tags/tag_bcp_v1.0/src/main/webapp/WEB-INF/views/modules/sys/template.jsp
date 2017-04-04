<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="../../include/head.jsp"></jsp:include>
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
								<h3>教师基本资料</h3>
					</div><!--/.module-head-->
                    <div class="module-body">
                        <form class="form-horizontal row-fluid">
                            <!--/姓名-->
                            <div class="control-group">
                                <label class="control-label" for="name">姓名：</label>
                                <div class="controls">
                                    <input name="name" class="span8" type="text" >
                                        
                                </div>
                            </div>
                            <!--/工号-->
                             <div class="control-group">
								<label class="control-label">工号：</label>
									<div class="controls">
                                    <input name="num" class="span8" type="text" >
                                        
                                </div>
							</div> 
                            <!--/密码--> 
                            <div class="control-group">
								<label class="control-label" for="password">密码：</label>
									<div class="controls">
                                    <input name="password" class="span8" type="text" >
                                        
                                </div>
							</div> 
                            <!--/确认密码-->
                            <div class="control-group">
								<label class="control-label">确认密码：</label>
									<div class="controls">
                                    <input name="password" class="span8" type="text" >
                                        
                                </div>
							</div> 
                            <!--/部门-->
                            <div class="control-group">
                                <label class="control-label" for="companyDepartment">部门：</label>
                                <div class="controls">
                                    <input name="companyDepartment" class="span8" type="text" >
                                        
                                </div>
                            </div>
                            
                            <!--/提交按钮--> 
                           <div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">提交</button>
											</div>
										</div>
           
                        </form><!--/.form-horizontal row-fluid-->
                    </div><!--/.module-body-->
                    </div><!--/.module-->
				</div><!--/.content-->
				</div><!--/.span9-->
			</div>
		</div><!--/.container-->
	</div><!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>