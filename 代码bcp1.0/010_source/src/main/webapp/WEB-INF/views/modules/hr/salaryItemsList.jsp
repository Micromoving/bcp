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
              <h3>工资项</h3>
            </div>
            <div class="module-body"> 
              <!--/增加按钮-->
              <div class="stream-composer media">
                <div class="media-body">
                  <div class="clearfix"> <a href="${ctx}/hr/salaryItems/form" class="btn btn-primary pull-right"> 增加 </a></div>
                </div>
              </div>
              <!--/搜索栏-->
              <div class="stream-list">
                <div class="media stream">
              
                    
               
                <!--/.media .stream--> 
                <!--/表格栏-->
               
                  <table class="table table-striped table-bordered table-condensed">
                    <thead>
                      <tr>
                        <th class="p27">序号</th>
                        <th class="p55">工资标识</th>
                        <th class="p55">工资项名称</th>
						<th class="p45">类型</th>
						<th class="p75">是否系统数据</th>
						<th class="p65">创建时间</th>
                        <th class="p75">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="salaryItemsData" items="${list}" varStatus="status">
                      <tr>
                        <td>${status.count }</td>
                        <td>${salaryItemsData.salaryMark }</td>
                        <td>${salaryItemsData.salaryItemsName }</td>
                        <td>${fns:getDictLabel(salaryItemsData.salaryType,'salary_type','')}</td>
                        <td>${fns:getDictLabel(salaryItemsData.isSys,'yes_no','')}</td>
                        <td>${fns:formatDate("yyyy-MM",salaryItemsData.createDate)}</td>
                        <td>
                        <a href="${ctx}/hr/salaryItems/form?id=${salaryItemsData.id}">修改</a>
                        <a href="${ctx}/hr/salaryItems/delete?id=${salaryItemsData.id}" onclick="return confirmx('确认要删除该险种吗？', this.href)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除</a></td>
                      </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                  
                </div>
                <!--/.media .stream--> 
                
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
      
			</div>
			<!--/.container-->
		</div>
		<!--/.wrapper-->
		<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>
