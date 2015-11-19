<%@ include  file="/WEB-INF/tiles/common/layout/definitions.jsp"%>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page"><spring:message code="page.private.dashboard.title"/></tiles:putAttribute>

    <tiles:putAttribute name="body" cascade="false">
        <div class="row">

            <t:insertTemplate template="/WEB-INF/tiles/common/toolbox.jsp">
                <t:putAttribute name="title">
                    Կառավարում
                </t:putAttribute>
                <t:putAttribute name="links">
                        <a href="/private/administration/pplan-import">Գնումների պլանի մուտքագրում</a>
                        <a href="/private/administration/dictionaries/cpv">ԳՄԱ բառարան</a>
                        <a href="/private/administration/dictionaries/programs">Ծրագրերի բառարան</a>
                        <a href="/private/administration/dictionaries/articles">ՏԴՀ բառարան</a>
                        <a href="/private/administration/dictionaries/roles">Դերերի լիազորությունների կառավարում</a>
                        <a href="/private/administration/authorities">ՊՊ կառավարում</a>
                        <a href="/private/administration/users">Համակարգի օգտվողներ</a>

                        <a href="/private/users">ՊՊ Գործածողներ</a>
                        <a href="/private/authorities/department">Բաժիններ</a>

                </t:putAttribute>
            </t:insertTemplate>

            <t:insertTemplate template="/WEB-INF/tiles/common/toolbox.jsp">
                <t:putAttribute name="title">
                    Աշխատանքային տարածք
                </t:putAttribute>
                <t:putAttribute name="links">
                        <a href="/private/authorities/tasks">Առաջադրանքներ / Օրացույց</a>

                        <a href="/private/authorities/pplan/index">Գնումների ընթացիկ պլան</a>
                        <a href="/private/authorities/pplan/drafts">Սևագրեր</a>
                        <a href="/private/authorities/pplan/tenders/tenders">Մրցույթներ</a>

                        <a href="/private/authorities/contracts/index">Պայմանագրեր</a>
                        <a href="/private/authorities/contracts/pending">Առկախված Պայմանագրեր</a>
                </t:putAttribute>
            </t:insertTemplate>

            <t:insertTemplate template="/WEB-INF/tiles/common/toolbox.jsp">
                <t:putAttribute name="title">
                    Հաշվետվություններ
                </t:putAttribute>
                <t:putAttribute name="links">
                        <a href="/private/authority/report/pplan-status">Գնումների պլան</a>
                        <a href="/private/authority/report/contracts">Պայմանագրեր</a>
                        <a href="/private/administration/report/pplan-status">Գնումների պլան ըստ մարմինների</a>
                        <a href="/private/administration/report/contracts">Պայմանագրեր ըստ մարմինների</a>
                </t:putAttribute>
            </t:insertTemplate>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>