<#import "/spring.ftl" as spring/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feed List</title>
</head>
<body>

<#list list as rssModel>
    <div>      
        <h3>${rssModel.title}</h3>
        <div><a target="_blank" href="${rssModel.url}">${rssModel.url}</a></div>
        <div style="color: gray;">Published on: ${rssModel.createdDate?date}</div>
        <div>${rssModel.summary}</div>
    </div>
    <hr>
<#else>
No feeds
</#list>

</body>
</html>