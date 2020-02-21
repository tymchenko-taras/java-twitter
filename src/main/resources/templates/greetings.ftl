<#import "controls/common.ftl" as common>
<#import "controls/security.ftl" as security>
<@common.page>
greetings
    <a href="/main">Go to messages page</a>
    <a href="/users">Go to users page</a>
    <a href="/subscriptions">Subscriptions</a>
    <a href="/messages/${security.currentUserId}">User's Messages</a>
</@common.page>