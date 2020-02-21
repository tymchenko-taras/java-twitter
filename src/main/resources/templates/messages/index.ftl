<#import "../controls/common.ftl" as common>
<@common.page>
    <h2>Messages of ${username}</h2>
    <table>
        <#list messages as message>
            <tr>
                <td><b>${message.id}</b></td>
                <td>${message.text}</td>
                <td>${message.tag}</td>
                <td>${message.authorName}</td>
                <td><a href="/message/${message.id}">Edit Message</a></td>
                <td><#if message.filename??>
                        <img src="/img/${message.filename}" alt="">
                </#if></td>

            </tr>
        </#list>
    </table>
</@common.page>