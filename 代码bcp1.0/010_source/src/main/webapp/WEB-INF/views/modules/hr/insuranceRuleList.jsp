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
              <h3>险种规则</h3>
            </div>
            <div class="module-body"> 
             <!--/增加按钮-->
              <div class="stream-composer media">
                <div class="media-body">   
                  <div class="clearfix"> <a href="${ctx}/hr/insuranceRule/form" class="btn btn-primary pull-right"> 增加 </a>  </div>
                </div>
              </div>
              <!--/搜索栏-->
              <div class="stream-list">

                <!--/.media .stream--> 
              <div class="media stream"> 
               
                <!--/.media .stream--> 
                <!--/表格栏-->
                <div class="media stream">
                  <table class="table table-striped table-bordered table-condensed">
                    <thead>
                      <tr>
                        <th class="p27">序号</th>
                        <th>险种</th>
                        <th>单位比例</th>
                        <th>个人比例</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="insuranceRuleData" items="${list}" varStatus="status">
                    <tr>
						<td style="text-align: center;">${status.count }</td>
						<td>${insuranceRuleData.insuranceType}</td>      
						<td>${insuranceRuleData.unitProportion}%</td>
						<td>${insuranceRuleData.peopleProportion}%</td>
						<td>
						<a href="${ctx}/hr/insuranceRule/form?id=${insuranceRuleData.id}">修改</a>
						<a href="${ctx}/hr/insuranceRule/delete?id=${insuranceRuleData.id}"
								onclick="return confirmx('确认要删除该险种吗？', this.href)">删除</a></td>
					</tr>
					</c:forEach>
					</tbody>
                  </table>
                </div>
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
		<!--/.container-->
	</div>
	<!--/.wrapper-->
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>