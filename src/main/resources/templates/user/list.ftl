<#import "../controls/common.ftl" as common>
<@common.page>
    <table>
        <#list users as user>
            <tr>
                <td><b>${user.id}</b></td>
                <td>${user.username}</td>
                <td><#if user.active >Active</#if></td>
                <td><a href="/user/${user.id}">Edit user</a></td>
                <td><a href="/messages/${user.id}">Show messages</a></td>
            </tr>
        </#list>
    </table>
</@common.page>