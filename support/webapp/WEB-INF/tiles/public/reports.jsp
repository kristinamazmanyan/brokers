<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

  <tiles:putAttribute name="title.page"><spring:message code="page.reports-page.title" /></tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="true">

      <%-- Table style for Block-view--%>
      <style type="text/css">
          .inLine {display:inline-block; width:200px; }
          .table {
              border: 1px solid #ebddd2;
          }
          table { table-layout: fixed; }
          table th, table td { overflow: hidden; width: 50%; text-align: center;}

          .table-bordered > thead > tr > th,
          .table-bordered > tbody > tr > th,
          .table-bordered > tfoot > tr > th,
          .table-bordered > thead > tr > td,
          .table-bordered > tbody > tr > td,
          .table-bordered > tfoot > tr > td {
              border: 2px solid #ebddd2;
              border-right-width:2px;
              border-left-width:2px;
          }
      </style>

    <div class="row">
      <ng-view/>
    </div>

    <script type="text/ng-template" id="template-graphical-reports.html">
    <div class="col-sm-12 ng-cloak">

      <div class=" row alert-success"  align="center" style="background-color: #ebddd2">
        <span><h4><spring:message code="page.reports.graphicalType" /> </h4></span>
      </div>

      <form id="reportForm" ng-init="list()">
          <div class="row "  id="mainDiv">


              <div class="col-md-6" ng-controller="Controller">
                      <canvas id="pie33" options="options" class="chart chart-pie chart-xs ng-isolate-scope"  data="pieDiskData.data" labels="pieDiskData.labels" colours="pieDiskData.colours" legend="true"></canvas>
              </div>

          </div>

      </form>
    </div>
    </script>



    <script src="${pageContext.request.contextPath}/static/js/generated/components/reports/report_service.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/public/reports-app.js"></script>


  </tiles:putAttribute>
</tiles:insertDefinition>


