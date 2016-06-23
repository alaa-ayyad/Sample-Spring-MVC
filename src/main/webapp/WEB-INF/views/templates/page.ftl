<#import "/spring.ftl" as spring/>

<#macro page title="">
<!DOCTYPE HTML>
<html>

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Test Spring MVC App <#if title=""> <#else>| ${title}</#if></title>
		<#include "imports.ftl" parse=true>
	
	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
		  <div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href='<@spring.url "/" />'>Spring 3 MVC Test App</a>
			</div>
		  </div>
		</nav>
	
		<div id="container" class="container">
			<#nested>
		</div>
		<#include "footer.ftl" parse=true>
	</body>

</html>
</#macro>