<#import "../controls/common.ftl" as common>
<@common.page>
    <form method="POST" action="/message/${message.id}" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div>
            <input type="text" name="text" value="${message.text}" placeholder="Text..."/>
            <#if textError??>${textError}</#if>
        </div>
        <input type="text" name="tag" value="${message.tag}" placeholder="Tag..."/>
        <input type="file" name="file">
        <input type="submit"/>
    </form>
</@common.page>