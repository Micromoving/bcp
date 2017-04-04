<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/employee/list");
		$("#searchForm").submit();
		return false;
	}
</script>

</head>
<body class="tag-index" data-mod="tag">

	<jsp:include page="../../include/top.jsp"></jsp:include>

	<div class="wrap">
		<div class="container">
			<form action="list.html">
				<div class="row">
					<!-- 路径 -->
					<div class="formbar">
						<b><a href="home.html">首页</a>&nbsp;>&nbsp;<a
							href="bizMenu.html">人力资源管理</a>&nbsp;>&nbsp;<a href="#">教职员工</a>&nbsp;>&nbsp;<a
							href="employeeList.html">基本信息</a>&nbsp;>&nbsp;<a
							href="userForm.html">增加</a> </b>
					</div>
					<!-- 内容 -->
					<div class="formbar t-line">

						<div class="alert alert-success">
							<strong>Well done!</strong> Now you are listening me :)
							<button type="button" class="close" data-dismiss="alert">×</button>
						</div>

						<table class="formtbl">
							<tbody>
								<tr>
									<td class="td-color">编号</td>
									<td><input type="text"> <br>即用户ID，供用户登录系统使用。建议教师采用工号，学生采用学号。
									</td>
								</tr>
								<tr>
									<td class="td-color">姓名</td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td class="td-color">邮箱</td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td class="td-color">密码</td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td class="td-color">确认密码</td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td class="td-color">归属部门</td>
									<td><input type="text"></td>
								</tr>
								<tr>
									<td class="td-color">是否允许登陆</td>
									<td><select><option>是</option>
											<option>否</option></select></td>
								</tr>
								<tr>
									<td class="td-color">用户类型</td>
									<td><select><option>教师</option>
											<option>学生</option></select></td>
								</tr>
								<tr>
									<td class="td-color">用户角色</td>
									<td><input type="checkbox">
									<lable>主管院领导 </lable> <input type="checkbox">
									<lable>人事处教育科 </lable> <input type="checkbox">
									<lable>人事处综合科 </lable> <input type="checkbox">
									<lable>人事处职员 </lable><br> <input type="checkbox">
									<lable>人事处薪酬 </lable> <input type="checkbox">
									<lable>副处长 </lable> <input type="checkbox">
									<lable>处长 </lable> <input type="checkbox">
									<lable>学工部领导 </lable> <input type="checkbox">
									<lable>形成工资表 </lable><br> <input type="checkbox">
									<lable>教务处领导 </lable> <input type="checkbox">
									<lable>教育科 </lable> <input type="checkbox">
									<lable>普通用户 </lable> <input type="checkbox">
									<lable>校级管理员 </lable> <input type="checkbox">
									<lable>校领导 </lable><br> <input type="checkbox">
									<lable>科长 </lable> <input type="checkbox">
									<lable>系统管理员 </lable> <input type="checkbox">
									<lable>院级、处室管理员 </lable> <input type="checkbox">
									<lable>院级、处室领导 </lable><br> <input type="checkbox">
									<lable>院长 </lable> <input type="checkbox">
									<lable>院领导（分管教学） </lable> <input type="checkbox">
									<lable>院领导（分管行政） </lable><br> <input type="checkbox">
									<lable>人事处招聘管理 </lable> <input type="checkbox">
									<lable>人事主管 </lable> <input type="checkbox">
									<lable>人事处备案 </lable></td>
								</tr>
								<tr>
									<td class="td-color">备注</td>
									<td><textarea style="max-width:400px;"></textarea></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 按钮 -->
					<div class="formbar t-line">
						<button class="btn-con btn-sub" type="submit">提交</button>
					</div>
				</div>
			</form>
			<div class="text-center"></div>
		</div>
	</div>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>