<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<tilesx:useAttribute id="boxLinks" name="links" />
<div class="col-sm-4">
    <div class="panel panel-info">
        <div class="panel-heading"><tiles:getAsString name="title"/></div>
        <div class="panel-body">
            <ul class="list-unstyled">
                <x:parse xml="<i>${boxLinks}</i>" var="output"/>
                <x:forEach select="$output//a" var="a">
                    <li><a class="secured" href="${pageContext.request.contextPath}<x:out select="$a/@href" />"><x:out select="$a"/></a> </li>
                </x:forEach>
            </ul>
        </div>
    </div>
</div>
