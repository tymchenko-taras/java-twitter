<#import "../controls/common.ftl" as common>
<@common.page>
    <form method="POST" action="/user/${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="text" name="username" value="${user.username}"/>
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="submit"/>
    </form>
</@common.page>