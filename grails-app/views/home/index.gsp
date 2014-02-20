<%@page expressionCodec="raw"%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="layout" content="main" />
<r:require modules="uploadr" />
</head>
<body>
<body id="top" class="preview" data-spy="scroll" data-target=".subnav"
	data-offset="80">

	<!-- Navbar
    ================================================== -->
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="">Dogemyphoto</a>
				<div class="nav-collapse" id="main-menu">
					<ul class="nav" id="main-menu-left">
						<li style="padding-top: 6px">&nbsp; <a
							href="https://twitter.com/share" class="twitter-share-button"
							data-text="Geo, a new Bootstrap theme from Divshot"
							data-via="divshot" data-size="large">Tweet</a> <script>
								!function(d, s, id) {
									var js, fjs = d.getElementsByTagName(s)[0];
									if (!d.getElementById(id)) {
										js = d.createElement(s);
										js.id = id;
										js.src = "http://platform.twitter.com/widgets.js";
										fjs.parentNode.insertBefore(js, fjs);
									}
								}(document, "script", "twitter-wjs");
							</script>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container">


		<!-- Masthead
================================================== -->
		<header class="jumbotron subhead" id="overview">
			<div class="row">
				<div class="span6">
					<h1>
						<img src="img/test/7upspot.gif">&nbsp;<FONT COLOR="#FF0000">D</FONT><FONT
							COLOR="#FF5A00">o</FONT><FONT COLOR="#FFB400">g</FONT><FONT
							COLOR="#FFff00">e</FONT> <FONT COLOR="#A5ff00">M</FONT><FONT
							COLOR="#4Bff00">y</FONT>
						<blink>
							<FONT COLOR="#00ff00">P</FONT><FONT COLOR="#00ff5A">h</FONT><FONT
								COLOR="#00ffB4">o</FONT><FONT COLOR="#00ffff">t</FONT><FONT
								COLOR="#00B4ff">o</FONT>
						</blink>
					</h1>
					<p class="lead">Such website! Much laugh!</p>
					<table cellpadding="2" cellspacing="2">
						<tr>
							<td><img src="img/test/ie_logo.gif"></td>
							<td><img src="img/test/ns_logo.gif"></td>
							<td><img src="img/test/noframes.gif"></td>
							<td><img src="img/test/notepad.gif"></td>
						</tr>
					</table>
				</div>
				<div class="span6">
					<center>
						<img src="img/test/yahooweek.gif"> <img
							src="img/test/community.gif"> <img
							src="img/test/wabwalk.gif"> <img
							src="img/test/webtrips.gif">
					</center>
				</div>
			</div>
			<marquee>Much improve photo! Such doge meme!</marquee>
			<center>
				<img src="img/test/construction.gif">
			</center>
		</header>

		<div class="span12">
			<div id="lastPhoto">
				<g:if test="${dogeImagePath}">
					<ii:imageTag indirect-imagename="${dogeImagePath}"/>
				</g:if>
			</div>
			<g:uploadForm>
				<input type="file" name="dogePhoto"/>
				<g:actionSubmit value="Doge my photo" action="uploadPhoto"/>
			</g:uploadForm>
		</div>
		<br />
		<!-- Footer
      ================================================== -->
		<footer class="footer"> </footer>
		<center>
			<img src="img/test/counter2.gif">
		</center>
	</div>
	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="bootstrap/docs/assets/js/jquery.js"></script>
	<script src="bootstrap/docs/assets/js/google-code-prettify/prettify.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-transition.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-alert.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-modal.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-dropdown.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-scrollspy.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-tab.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-tooltip.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-popover.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-button.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-collapse.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-carousel.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-typeahead.js"></script>
	<script src="bootstrap/docs/assets/js/bootstrap-affix.js"></script>
</body>
</html>