<#import "controls/common.ftl" as common>
<@common.page>
    <form action="signup" method="post">
        <#if message??><b>${message}</b></#if>
        <div class="g-recaptcha" data-sitekey="${sitekey}"></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Email : <input type="email" name="email"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><label> Confirm Password: <input type="password" name="password2"/> </label></div>
        <div><input type="submit" value="Sign Up"/></div>
    </form>
</@common.page>