<#import "/spring.ftl" as spring/>


<@customize.page title="RSS Feed List">

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

</@customize.page>
