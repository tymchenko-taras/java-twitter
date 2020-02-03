<#import "../controls/common.ftl" as common>
<@common.page>
    <h5>User profile</h5>
    <form method="POST" action="/user/profile">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="email" name="email" value="${user.email!''}"/>
        <input type="password" name="password"/>

        <input type="submit"/>
    </form>
</@common.page>