<#import "../controls/common.ftl" as common>
<@common.page>
    <h3>Subscriptions of ${username}</h3>
    <#list subscriptions as user>
        <div>
            <b>${user.id}</b> ${user.username}
        </div>
    </#list>
</@common.page>