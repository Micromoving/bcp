<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!--基本信息-->
<div class="stream-list">
	<div class="media stream">
		<form class="form-horizontal breadcrumb" method="post"
			style="margin-bottom:0px;">
			<div class="control-group" style="height:20px;">
				${user.name }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工号：${user.loginName }
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${fns:getOfficeRootName(employee.office.id)}<br>
				<br>
			</div>
		</form>
	</div>
</div>