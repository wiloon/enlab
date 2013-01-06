<!doctype html>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("a").click(function(event) {
			   event.preventDefault();
			   $(this).hide("slow");
		});

		  $("a").addClass("test");
	});
</script>
<style type="text/css">
a.test{font-weight:bold;}
</style>
</head>
<body>
<a href="#">jQuery</a>
</body>
</html>