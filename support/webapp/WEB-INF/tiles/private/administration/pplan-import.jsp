<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<tiles:insertDefinition name="default" flush="true">
  <tiles:putAttribute name="title.page">ԳՄԱ բառարան</tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="false">

    <div class="row">
      <div class="col-sm-10 col-sm-offset-1" ng-view>
      </div>

      <script type="text/ng-template" id="pplanImportList.html">

        <table class="table  table-hover">
          <thead>
          <tr>
            <th> Տարի </th>
            <th> Ֆայլ </th>
            <th> Ֆայլի չափ </th>
            <th> Բեռնվել է </th>
            <th> Ում կողմից </th>
          </tr>
          </thead>
          <tr>
            <td> 2014 </td>
            <td> file1.xls </td>
            <td> 15kb </td>
            <td> 2011 </td>
            <td> Օպերատոր </td>
          </tr>
          <tr>
            <td> 2015 </td>
            <td> file2.pdf </td>
            <td> 19kb </td>
            <td> 2012 </td>
            <td> Ադմինիստրատոր </td>
          </tr>

        </table>

        <div class="row">
          <div class="col-md-8 col-md-offset-4">
            <ul class="pagination">
              <li class="disabled"><a href=""><span class="glyphicon glyphicon-backward"></span></a></li>
              <li class="active"><a href="">1</a></li>
              <li class="disabled"><a href="#">2</a></li>
              <li class="disabled"><a href="#">3</a></li>
              <li class="disabled"><a href="#">4</a></li>
              <li class="disabled"><a href="#">5</a></li>
              <li class="disabled"><a href=""><span class="glyphicon glyphicon-forward"></span></a></li>
            </ul>
          </div>
        </div> <br/><br/>

<form method="POST" action="pplan-import/upload-file" enctype="multipart/form-data">
        <div class="row">
          <label for="year" class="col-sm-2 control-label">Տարի:</label>
          <div class="col-sm-3">
            <select id="year" class="form-control " >
              <option value="">-- Ընտրել տարին --</option>
              <option value="2013">2013</option>
              <option value="2014">2014</option>
              <option value="2015">2015</option>
            </select>
          </div>
        </div><br/>

        <div  class="row">
          <label for="file" class="col-sm-2 control-label">Բեռնել ֆայլ:</label>
          <div class="col-sm-3">
            <input id="file" type="file" name="file" />
          </div>
        </div><br/>


        <div class="row">
          <div class="col-sm-4">
            <div class="pull-right">
              <button  class="btn pull-center " style="align-content: flex-end" ng-click="show=true"><a href="" >Բեռնել</a></button>
              <button  class="btn pull-center " style="align-content: flex-end" ><a href="" >Չեղարկել</a></button>
            </div>
          </div>
        </div><br/>
        <input type="submit" value="OK" />
</form>


        <div class="row" ng-show="show">
          <div class="col-md-8 col-md-offset-2">
            <div class="progress progress-striped active">
              <div class="progress-bar" aria-valuetransitiongoal="0"></div>
            </div>
          </div>
          <script>
            $(".progress-bar").animate({
              width: "99%"
            }, 2500 );
      </script>
    </div>

    </script>

    </div>

    <script src="${pageContext.request.contextPath}/static/js/private/administration/pplan-import-app.js"></script>
  </tiles:putAttribute>
</tiles:insertDefinition>