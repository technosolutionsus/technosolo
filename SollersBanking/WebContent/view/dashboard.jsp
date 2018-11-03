<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>jQuery.mmenu demo</title>

		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/demo.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.mmenu.all.css" />

		<style>
			@media (min-width: 992px) {
				.header a {
					display: none;
				}
			}

			.mm-navbar_tabs span {
				display: inline-block;
				margin-left: 8px;
			}
			@media (max-width: 450px) {
				.mm-navbar_tabs span {
					display: none;
				}
			}
		</style>

		<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/jquery.mmenu.all.js"></script>
		<script>
			$(function() {
				$('nav#menu').mmenu({
					extensions	: [ 'theme-dark' ],
					setSelected	: true,
					counters	: true,
					iconbar		: {
						add 		: true,
						size		: 40,
						top 		: [ 
							'<a href="#/"><span class="fa fa-home"></span></a>'
						],
						bottom 		: [
							'<a href="#/"><span class="fa fa-gear"></span></a>'
						]
					},
					sidebar		: {
						collapsed		: {
							use 			: '(min-width: 450px)',
							size			: 40,
							hideNavbar		: false
						},
						expanded		: {
							use 			: '(min-width: 992px)',
							size			: 35
						}
					},
					navbars		: [
						{
							type		: 'tabs',
							content		: [ 
								'<a href="#panel-menu"><i class="fa fa-bars"></i> <span>Menu</span></a>'
							]
						}, {
							content		: [ 'prev', 'breadcrumbs', 'close' ]
						}, {
							position	: 'bottom',
							content		: [ '<a href="" target="_blank">Sign Out</a>' ]
						}
					]
				}, {
					navbars		: {
						breadcrumbs	: {
							removeFirst	: true
						}
					}
				});

				//$('a[href^="#/"]').click(function() {
					//alert( 'Thank you for clicking, but that\'s a demo link' );
					//return false;
				//})

				$('a.displaycontent').click(function() {
					alert( 'displaycontent Thank you for clicking, but that\'s a demo link' );
					 $('#panel-cart').load('index.jsp');
					return false;
				})
			});
		</script>
	</head>
	<body>
		<div id="page">
			<div class="header">
				User : ${loggedinuser}
			</div>
			<div class="header">
				<a href="#menu"><span></span></a>
				Demo
			</div>
			<div class="content">
				<p><strong>This is an advanced demo.</strong><br />
					Click the menu icon to open the menu.</p>
			</div>
			<nav id="menu">
				<div id="panel-menu">
					<ul>
						<li><a href="#/" class="displaycontent">Home</a></li>
						<li><a href="#/">Contact</a></li>
					</ul>
				</div>

				<div id="panel-cart">
					
				</div>
			</nav>
		</div>
	</body>
</html>
