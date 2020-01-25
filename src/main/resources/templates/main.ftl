<#import "controls/common.ftl" as common>
<@common.page>
    <div>
        <@common.logout/>
        <form method="POST">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="text" name="text" placeholder="Text..."/>
            <input type="text" name="tag" placeholder="Tag..."/>
            <input type="submit"/>
        </form>

        <form method="POST" action="filter">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="text" name="filter" placeholder="Filter"/>
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
            </tr>
        </#list>
    </table>
</@common.page>