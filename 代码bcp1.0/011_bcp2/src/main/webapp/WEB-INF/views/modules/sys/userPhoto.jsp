<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="cn.micromoving.bcp.modules.sys.utils.UserUtils"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../include/head.jsp"></jsp:include>
<script src="${ctxStatic}/bootstrap/js/jquery.min.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/js/jquery.Jcrop.js"
	type="text/javascript"></script>
<script type="text/javascript">
	jQuery(function($) {

		// The variable jcrop_api will hold a reference to the
		// Jcrop API once Jcrop is instantiated.
		var jcrop_api, boundx, boundy,

		// Grab some information about the preview pane
		$preview = $('#preview-pane'), $pcnt = $('#preview-pane .preview-container'), $pimg = $('#preview-pane .preview-container img'),

		xsize = $pcnt.width(), ysize = $pcnt.height();

		$('#coords').on('change', 'input', function(e) {
			var x = $('#x').val(), y = $('#y').val();

		});
		// In this example, since Jcrop may be attached or detached
		// at the whim of the user, I've wrapped the call into a function
		initJcrop();

		// The function is pretty simple
		function initJcrop()//{{{
		{
			// Hide any interface elements that require Jcrop
			// (This is for the local user interface portion.)
			$('.requiresjcrop').hide();

			// Invoke Jcrop in typical fashion
			$('#target').Jcrop({

				onChange : updatePreview,
				onSelect : updatePreview,
				aspectRatio : xsize / ysize
			}, function() {

				jcrop_api = this;
				jcrop_api.animateTo([ 0, 0, 130, 190 ]);

				// Setup and dipslay the interface for "enabled"

				$('.requiresjcrop').show();
				var bounds = this.getBounds();
				boundx = bounds[0];
				boundy = bounds[1];
				// Store the API in the jcrop_api variable

				// Move the preview into the jcrop container for css positioning
				$preview.appendTo(jcrop_api.ui.holder);
				jcrop_api.setOptions({
					minSize : [ 60, 60 ],
					allowSelect : false
				});

			});

		}
		;
		//}}}
		function updatePreview(c) {

			$('#x').val(parseInt(c.x));
			$('#y').val(parseInt(c.y));

			$('#w').val(parseInt(c.w));
			$('#h').val(parseInt(c.h));
			if (parseInt(c.w) > 0) {
				var rx = xsize / c.w;
				var ry = ysize / c.h;

				$pimg.css({
					width : Math.round(rx * boundx) + 'px',
					height : Math.round(ry * boundy) + 'px',
					marginLeft : '-' + Math.round(rx * c.x) + 'px',
					marginTop : '-' + Math.round(ry * c.y) + 'px'
				});
			}
		}
		;

	});

	function changeImg() {
		document.mainForm.submit();
	}

	function cutImg() {
		document.cutForm.submit();
		//window.setTimeout(windowclose(), 5000)
		window.opener.location.reload();
	}
</script>
<link rel="stylesheet" href="${ctxStatic}/bootstrap/css/main.css"
	type="text/css" />
<link rel="stylesheet" href="${ctxStatic}/bootstrap/css/demos.css"
	type="text/css" />
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/css/jquery.Jcrop.css" type="text/css" />
<style type="text/css">

/* Apply these styles only when #preview-pane has
   been placed within the Jcrop widget */
.jcrop-holder #preview-pane {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 0px;
	right: -300px;
	padding: 6px;
	border: 1px rgba(0, 0, 0, .4) solid;
	background-color: white;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
}

/* The Javascript code will set the aspect ratio of the crop
   area based on the size of the thumbnail preview,
   specified here */
#preview-pane .preview-container {
	width: ${UserUtils.profile_photo_width}px;
	height: ${UserUtils.profile_photo_height}px;
	overflow:hidden;
}
.jcrop-tracker {
	height: 100%;
	width: 100%;
	/* "turn off" link highlight */
	-webkit-tap-highlight-color: transparent;
	/* disable callout, image save panel */
	-webkit-touch-callout: none;
	/* disable cut copy paste */
	-webkit-user-select: none;
	top: -6px ! important;
	left: -6px ! important;
	padding: 4px;
	border: 1px rgba(0, 0, 0, .4) solid ! important;
	border-radius: 6px ! important;
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2) ! important;
}
</style>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<div style="margin:0 auto;width:100%;">

					<div class="content">
						<div class="module">
							<div class="module-head">
								<h3>更改头像</h3>
							</div>
							<div class="module-body">
								<div class="stream-composer media">
									<div class="media-body" style="min-height: 500px;">
										<div style="float:left;margin: 8px;">

											<form:form action="${ctx}/sys/user/imgUpload" method="post"
												modelAttribute="user" name="mainForm"
												enctype="multipart/form-data">
<input type="hidden" name="id" value="${user.id }">
												<input class="btn" type="file" name="file" id="sf"
													onchange="changeImg();" />
												<input class="btn" type="button" value="保存" class="button"
													id="crop_submit" onclick="cutImg();" />
											</form:form>

											<form:form modelAttribute="user"
												action="${ctx}/sys/user/savePhoto" method="post"
												name="cutForm" enctype="multipart/form-data">

												<input type="hidden" name="id" value="${user.id }">
												<form:hidden path="photo" />
												<input type="hidden" id="x" name="x" />
												<input type="hidden" id="y" name="y" />
												<input type="hidden" id="w" name="w" />
												<input type="hidden" id="h" name="h" />
											</form:form>

											<c:if test="${not empty user.photo}">
												<img src="${ctxUploads}/user/${user.photo}" id="target" />
											</c:if>
											<c:if test="${empty user.photo}">
												<img src="${ctxUploads}/user/user.png" id="target" />
											</c:if>
										</div>
										<div id="preview-pane" style="float:right;">
											<div class="preview-container">
												<c:if test="${not empty user.photo}">
													<img src="${ctxUploads}/user/${user.photo}"
														class="jcrop-preview" />
												</c:if>
												<c:if test="${empty user.photo}">
													<img src="${ctxUploads}/user/user.png"
														class="jcrop-preview" />
												</c:if>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
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

