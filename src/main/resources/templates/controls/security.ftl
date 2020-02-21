<#assign known = Session.SPRING_SECURITY_CONTEXT??>

<#if known>
    <#assign
        currentUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        currentUsername = currentUser.getUsername()
        currentUserId = currentUser.getId()
    >
<#else>
    <#assign currentUsername = "">
    <#assign currentUserId = 0>
    <#assign isCurrentuserAdmin = false>
</#if>