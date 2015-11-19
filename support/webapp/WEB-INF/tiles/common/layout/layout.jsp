<%@ page import="am.iunetworks.ppcm.utils.LocalizedDictionary" %>
<%@ page isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="f"        uri="am.iunetworks.ppcm.taglib" %>
<%-- Page local variables --%>
<c:set var="DIR_STATIC" scope="request">${pageContext.request.contextPath}/static</c:set>
<c:set var="USER" scope="request" value="${pageContext.request.userPrincipal.principal}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Procurement Plan and Contracts management subsystem">
    <meta name="author" content="Ministry of Finance or RA">
    <link rel="icon" href="${DIR_STATIC}/images/favicon.ico">

    <title>PPCM - <tiles:getAsString name="title.page"/></title>

    <!-- Bootstrap core CSS -->
    <link href="${DIR_STATIC}/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${DIR_STATIC}/css/styles.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${DIR_STATIC}/js/common/components/date-range-picker/daterangepicker.css" />
    <link rel="stylesheet" type="text/css" href="${DIR_STATIC}/js/common/components/tree-grid/treeGrid.css" />
    <link rel="stylesheet" type="text/css" href="${DIR_STATIC}/js/common/components/xeditable.css" />
    <link rel="stylesheet" type="text/css" href="${DIR_STATIC}/js/angularjs/chart/angular-chart.css" />
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]-->
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <!--[endif]-->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="${DIR_STATIC}/js/bootstrap.min.js"></script>

    <%-- progress bar --%>
    <script src="${DIR_STATIC}/js/3pp/nprogress.js"></script>
    <link href="${DIR_STATIC}/js/3pp/nprogress.css" rel="stylesheet">

    <script src="${DIR_STATIC}/js/angularjs/angular.min.js"></script>
    <script src="${DIR_STATIC}/js/angularjs/angular-route.min.js"></script>
    <script src="${DIR_STATIC}/js/angularjs/ui-bootstrap-tpls-0.13.0.min.js"></script>
    <%--<script src="${DIR_STATIC}/js/angularjs/angular-ui-router.min.js"></script>--%>
    <script src="${DIR_STATIC}/js/angularjs/angular-cookies.min.js"></script>

    <script src="${DIR_STATIC}/js/common/components/tree-grid/tree-grid-directive.js" ></script>
    <script src="${DIR_STATIC}/js/common/components/date-range-picker/moment.js"></script>
    <script src="${DIR_STATIC}/js/common/components/date-range-picker/angular-daterangepicker.js"></script>
    <script src="${DIR_STATIC}/js/common/components/date-range-picker/daterangepicker.js"></script>
    <script type="application/javascript"
            src="${DIR_STATIC}/js/messages/error_messages.js"></script>


    <script src="${DIR_STATIC}/js/common/components/validator.min.js"></script>
    <script src="${DIR_STATIC}/js/common/components/xeditable.js"></script>
    <script src="${DIR_STATIC}/js/common/components/angular-uuid.js"></script>
    <script src="${DIR_STATIC}/js/angularjs/bootstrap-formhelpers-phone.js"></script>
    <script src="${DIR_STATIC}/js/angularjs/chart/Chart.min.js"></script>
    <script src="${DIR_STATIC}/js/angularjs/chart/angular-chart.min.js"></script>
    <script src="${DIR_STATIC}/js/common/app.js"></script>
    <script type="text/javascript">
        PPCM.value('pageConstants', {
            "currentLang" : '${pageContext.response.locale}',
            "contextPath" : '${pageContext.request.contextPath}',
            "username"    : '${USER.username}'
        })
        .value('localization', <%= LocalizedDictionary.jsonify(response.getLocale())%>)
        .value('PPCMPermissions',   ${f:getUserPermissions()} )


    </script>
    <script src="${DIR_STATIC}/js/common/interceptors.js"></script>
    <script src="${DIR_STATIC}/js/common/iu-directives.js"></script>
    <script src="${DIR_STATIC}/js/common/filters.js"></script>
    <script src="${DIR_STATIC}/js/common/page-controller.js"></script>
    <script src="${DIR_STATIC}/js/common/services/user-service.js"></script>




</head>

<body ng-app="ppcm" ng-controller="PageCtrl as page">

<div id="ngProgress-container" ></div>
<!-- Static navbar -->
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <img src="${DIR_STATIC}/images/logo.png" alt="" />
            </a>
            <h3 class="navbar-brand   ">PPCM
                <div class="small">${USER.authority.nameHy}</div>
            </h3>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/public/faq/show"><spring:message code="page.layout.faq" /></a> </li>
                <li class="dropdown ng-cloak language-switch">
                    <a href="#" onclick="return false;" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><b iu-lang="${pageContext.response.locale}"></b></a>
                    <ul class="dropdown-menu ">
                        <li><a href="?lang=hy" iu-switch-lang ng-hide="'${pageContext.response.locale}' == 'hy'"><b iu-lang="hy"></b></a></li>
                        <li><a href="?lang=ru" iu-switch-lang ng-hide="'${pageContext.response.locale}' == 'ru'"><b iu-lang="ru"></b></a> </li>
                        <li><a href="?lang=en" iu-switch-lang ng-hide="'${pageContext.response.locale}' == 'en'"><b iu-lang="en"></b></a> </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <tiles:insertAttribute name="auth.popup"/>
                </li>
                <li><a href="#" class="iu-toggle-width" onclick="return false"><i class="glyphicon glyphicon-fullscreen label label-default"> </i></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<section class="sec-search-row">
    <div class="container">
        <div class="sec-search-row-bg">
            <div class="row">
                <div class="col-sm-12">
                    <ol class="breadcrumb navigationBreadcrumb ng-cloak">
                        <li><a href="${pageContext.request.contextPath}/"><i class="glyphicon glyphicon-home"></i></a></li>
                        <li ng-repeat="p in navigationBreadcrumb " class="{{p.listClass}}"><a class="{{p.class}}" ng-href="{{p.href}}">{{p.name}}</a></li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="content">

<div id="body-container" class="container" >
    <div >
        <alert ng-repeat="alert in alerts" type="{{alert.type}}" close="closeAlert($index)">{{alert.msg}}</alert>
    </div>

    <tiles:insertAttribute name="header"/>
    <tiles:insertAttribute name="body"/>

</div> <!-- /container -->
</section>
<tiles:insertAttribute name="footer"/>

</body>
</html>
