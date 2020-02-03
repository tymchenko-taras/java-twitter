<#import "controls/common.ftl" as common>
<@common.page>
    <div>
        <@common.logout/>
        <form method="POST" action="/main" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div>
                <input type="text" name="text" placeholder="Text..."/>
                <#if textError??>${textError}</#if>
            </div>
            <input type="text" name="tag" placeholder="Tag..."/>
            <input type="file" name="file">
            <input type="submit"/>
        </form>

        <form method="GET" action="/main">
            <input type="text" name="filter" value="${filter}" placeholder="Filter"/>
            <input type="submit"/>
        </form>
    </div>

    <table>
        <#list messages as message>
            <tr>
                <td><b>${message.id}</b></td>
                <td>${message.text}</td>
                <td>${message.tag}</td>
                <td>${message.authorName}</td>
                <td><#if message.filename??>
                        <img src="/img/${message.filename}" alt="">
                </#if></td>
            </tr>
        </#list>
    </table>
</@common.page>