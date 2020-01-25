<#import "controls/common.ftl" as common>
<@common.page>
    <form action="signup" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="Sign Up"/></div>
    </form>
</@common.page>