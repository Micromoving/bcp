<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"
	description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true"
	description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true"
	description="输入框值"%>
<i style="float: left;"  id="${id}Icon" class="icon-${not empty value?value:' hide'}"></i>
&nbsp;&nbsp;&nbsp;&nbsp;<div style="float: left;" id="${id}IconLabel">${not empty value?value:'无'}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
<div style="float: left;">
	<a id="${id}Button" href="javascript:" class="btn pull-left">选择</a>&nbsp;&nbsp;
</div>
&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}" />
<script type="text/javascript">
	$("#${id}Button").click(
			function() {
				top.$.jBox.open("iframe:${ctx}/tag/iconselect?value="
						+ $("#${id}").val(), "选择图标", 800, 500, {
					buttons : {
						"确定" : "ok",
						"清除" : "clear",
						"关闭" : true
					},
					submit : function(v, h, f) {
						if (v == "ok") {
							var icon = h.find("iframe")[0].contentWindow.$(
									"#icon").val();
							$("#${id}Icon").attr("class", "icon-" + icon);
							$("#${id}IconLabel").text(icon);
							$("#${id}").val(icon);
						} else if (v == "clear") {
							$("#${id}Icon").attr("class", "icon- hide");
							$("#${id}IconLabel").text("无");
							$("#${id}").val("");
						}
					},
					loaded : function(h) {
						$(".jbox-content", top.document).css("overflow-y",
								"hidden");
					}
				});
			});
</script>