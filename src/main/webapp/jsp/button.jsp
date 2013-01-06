<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>jQuery UI Button - Default demo</title>

<link type="text/css"
	href="${pageContext.request.contextPath}/css/themes/base/jquery.ui.all.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.4.2.js">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui/jquery.ui.core.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/ui/jquery.ui.button.js"></script>

<link type="text/css"
	href="${pageContext.request.contextPath}/css/demos.css"
	rel="stylesheet" />
<script type="text/javascript">
	$(function() {
		$("button, input:submit, a", ".demo").button();

		$("a", ".demo").click(function() {
			return false;
		});
	});
</script>
<style>
</style>
</head>
<body>

	<div class="demo">

		<button>A button element</button>

		<input type="submit" value="A submit button" /> <a href="#">An
			anchor</a>

	</div>
	<!-- End demo -->



	<div class="demo-description">

		<p>Examples of the markup that can be used for buttons: A button
			element, an input of type submit and an anchor.</p>

	</div>
	<!-- End demo-description -->



</body>
</html>
