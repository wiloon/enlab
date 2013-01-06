<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>jQuery UI Progressbar - Default functionality</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/themes/base/jquery.ui.all.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.6.2.js">
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ui/jquery.ui.widget.js"></script>
	<script src="../../ui/jquery.ui.progressbar.js"></script>
	<link rel="stylesheet" href="../demos.css">
	<script>
	$(function() {
		$( "#progressbar" ).progressbar({
			value: 37
		});
	});
	</script>
</head>
<body>

<div class="demo">

<div id="progressbar"></div>

</div><!-- End demo -->



<div class="demo-description">
<p>Default determinate progress bar.</p>
</div><!-- End demo-description -->

</body>
</html>
